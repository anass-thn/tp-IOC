package framework.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation permettant de demander l'injection automatique d'une dépendance par notre Mini-Framework IoC.
 */
@Retention(RetentionPolicy.RUNTIME) // Disponible à l'exécution pour la Réflexion
@Target({ElementType.FIELD, ElementType.METHOD}) // S'applique sur un attribut ou un setter
public @interface MyAutowired {
}
