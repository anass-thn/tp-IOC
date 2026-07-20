package metier;

import dao.IDao;

public class MetierImpl implements IMetier {
    // Couplage faible : on dépend de l'interface IDao et non de la classe concrète DaoImpl
    private IDao dao;

    @Override
    public double calcul() {
        if (dao == null) {
            throw new IllegalStateException("L'objet DAO n'a pas été injecté !");
        }
        double data = dao.getValue();
        // Simulation d'un calcul métier
        return data * 11.5;
    }

    // Setter pour injecter la dépendance
    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
