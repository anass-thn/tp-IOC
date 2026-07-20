package pres;

import dao.IDao;
import metier.IMetier;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Pres2 {
    public static void main(String[] args) {
        try {
            // 1. Lire le fichier de configuration config.txt
            Scanner scanner = new Scanner(new File("config.txt"));

            // Lire la première ligne : nom de la classe DAO
            String daoClassName = scanner.nextLine().trim();
            // Charger la classe DAO dynamiquement
            Class<?> cDao = Class.forName(daoClassName);
            // Instancier la classe DAO (équivalent de new DaoImpl())
            IDao dao = (IDao) cDao.getDeclaredConstructor().newInstance();

            // Lire la deuxième ligne : nom de la classe Métier
            String metierClassName = scanner.nextLine().trim();
            // Charger la classe Métier dynamiquement
            Class<?> cMetier = Class.forName(metierClassName);
            // Instancier la classe Métier (équivalent de new MetierImpl())
            IMetier metier = (IMetier) cMetier.getDeclaredConstructor().newInstance();

            // 2. Injection de dépendance dynamique par Réflexion
            // Recherche de la méthode setDao(IDao dao) dans la classe Métier chargée
            Method method = cMetier.getMethod("setDao", IDao.class);
            // Invocation de la méthode setDao sur l'objet 'metier' avec le paramètre 'dao'
            method.invoke(metier, dao);

            // 3. Appel de la méthode calcul
            System.out.println("Résultat de calcul = " + metier.calcul());

            scanner.close();
        } catch (Exception e) {
            System.err.println("Erreur lors de l'injection dynamique : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
