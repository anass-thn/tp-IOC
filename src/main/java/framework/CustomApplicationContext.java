package framework;

import framework.annotations.MyAutowired;
import framework.annotations.MyComponent;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

/**
 * Notre propre conteneur IoC (Mini-Framework).
 * Il scanne les packages, instancie les classes annotées par @MyComponent,
 * et injecte les dépendances marquées par @MyAutowired.
 */
public class CustomApplicationContext {
    // Map stockant les beans instanciés (Clé: Type/Interface/Classe, Valeur: Instance)
    private final Map<Class<?>, Object> context = new HashMap<>();

    public CustomApplicationContext(String... packagesToScan) {
        try {
            // 1. Scanner les packages et instancier les composants @MyComponent
            for (String pkg : packagesToScan) {
                scanAndInstantiate(pkg);
            }
            // 2. Réaliser l'injection de dépendances via Réflexion
            doDependencyInjection();
        } catch (Exception e) {
            System.err.println("Erreur lors de l'initialisation du conteneur IoC : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void scanAndInstantiate(String packageName) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        URL resource = classLoader.getResource(path);

        if (resource == null) return;

        String filePath = URLDecoder.decode(resource.getFile(), "UTF-8");
        File folder = new File(filePath);

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.getName().endsWith(".class")) {
                        String className = packageName + "." + file.getName().substring(0, file.getName().length() - 6);
                        Class<?> clazz = Class.forName(className);

                        // Si la classe est annotée avec @MyComponent
                        if (clazz.isAnnotationPresent(MyComponent.class)) {
                            Object instance = clazz.getDeclaredConstructor().newInstance();
                            context.put(clazz, instance);
                            // Enregistrer aussi sous les interfaces implémentées
                            for (Class<?> inter : clazz.getInterfaces()) {
                                context.put(inter, instance);
                            }
                        }
                    }
                }
            }
        }
    }

    private void doDependencyInjection() throws Exception {
        for (Object bean : new HashSet<>(context.values())) {
            Class<?> clazz = bean.getClass();

            // 1. Injection par Attribut (Field Injection)
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(MyAutowired.class)) {
                    Object dependency = context.get(field.getType());
                    if (dependency != null) {
                        field.setAccessible(true); // Permet d'accéder aux attributs privés
                        field.set(bean, dependency);
                    }
                }
            }

            // 2. Injection par Setter (Setter Injection)
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(MyAutowired.class) && method.getParameterCount() == 1) {
                    Class<?> paramType = method.getParameterTypes()[0];
                    Object dependency = context.get(paramType);
                    if (dependency != null) {
                        method.invoke(bean, dependency);
                    }
                }
            }
        }
    }

    /**
     * Récupère un bean du conteneur IoC par son type/interface.
     */
    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> clazz) {
        return (T) context.get(clazz);
    }
}
