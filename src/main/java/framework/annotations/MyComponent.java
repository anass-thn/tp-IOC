package framework.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation permettant de marquer une classe pour qu'elle soit gérée par notre Mini-Framework IoC.
 */
@Retention(RetentionPolicy.RUNTIME) // L'annotation reste disponible lors de l'exécution (Réflexion)
@Target(ElementType.TYPE)           // S'applique sur les classes ou interfaces
public @interface MyComponent {
    String value() default ""; // Nom optionnel du composant
}
