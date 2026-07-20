package pres;

import metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PresAvecSpringXml {
    public static void main(String[] args) {
        // 1. Démarrer le conteneur Spring en chargeant le fichier applicationContext.xml
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 2. Récupérer le bean 'metier' géré par le conteneur Spring
        IMetier metier = (IMetier) context.getBean("metier");

        // 3. Appeler la logique métier
        System.out.println("Résultat de calcul = " + metier.calcul());
    }
}
