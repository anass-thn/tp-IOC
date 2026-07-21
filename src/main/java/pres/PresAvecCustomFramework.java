package pres;

import framework.CustomApplicationContext;
import metier.IMetier;

public class PresAvecCustomFramework {
    public static void main(String[] args) {
        // 1. Instanciation de notre propre conteneur IoC avec les packages à scanner
        CustomApplicationContext context = new CustomApplicationContext("dao", "metier");

        // 2. Récupération du bean métier géré par notre framework
        IMetier metier = context.getBean(IMetier.class);

        // 3. Exécution du traitement
        System.out.println("Résultat du calcul avec notre Mini-Framework = " + metier.calcul());
    }
}
