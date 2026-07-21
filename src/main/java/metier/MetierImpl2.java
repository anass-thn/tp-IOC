package metier;

import dao.IDao;
import framework.annotations.MyAutowired;
import framework.annotations.MyComponent;

@MyComponent
public class MetierImpl2 implements IMetier {
    @MyAutowired
    private IDao dao;

    @Override
    public double calcul() {
        if (dao == null) {
            throw new IllegalStateException("L'objet DAO n'a pas été injecté !");
        }
        double data = dao.getValue();
        return data * 100;
    }

    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
