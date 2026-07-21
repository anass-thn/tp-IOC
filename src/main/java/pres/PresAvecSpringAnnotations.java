package pres;

import metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PresAvecSpringAnnotations {
    public static void main(String[] args) {
        // 1. Démarrer le conteneur Spring en scannant les packages dao et metier
        ApplicationContext context = new AnnotationConfigApplicationContext("dao", "metier");

        // 2. Récupérer le bean 'metier' instancié automatiquement par Spring
        IMetier metier = (IMetier) context.getBean("metier");

        // 3. Appeler la méthode de calcul et afficher le résultat
        System.out.println("Résultat de calcul = " + metier.calcul());
    }
}
