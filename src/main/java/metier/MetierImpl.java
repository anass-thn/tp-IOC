package metier;

import dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("metier")
public class MetierImpl implements IMetier {
    // Couplage faible
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

    // Injection automatique via le setter
    @Autowired
    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
