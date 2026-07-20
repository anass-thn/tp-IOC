package pres;

import dao.DaoImpl;
import metier.MetierImpl;

public class Pres1 {
    public static void main(String[] args) {
        // 1. Instanciation des objets par couplage fort (statique)
        DaoImpl dao = new DaoImpl();
        MetierImpl metier = new MetierImpl();

        // 2. Injection de la dépendance (dao dans metier) via le setter
        metier.setDao(dao);

        // 3. Appel de la méthode calcul de la couche métier et affichage du résultat
        double resultat = metier.calcul();
        System.out.println("Résultat de calcul = " + resultat);
    }
}
