package dao;

import framework.annotations.MyComponent;

@MyComponent
public class DaoImpl2 implements IDao {
    @Override
    public double getValue() {
        System.out.println("[DAO V2 (Custom Framework)] Récupération des données depuis les capteurs web service...");
        return 55.5;
    }
}
