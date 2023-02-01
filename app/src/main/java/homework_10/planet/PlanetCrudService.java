package homework_10.planet;

import homework_10.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class PlanetCrudService implements IPlanetService{
    @Override
    public void create(Planet planet) throws SQLException {
        if(isEntityExist(planet.getId())){
            System.out.println("ID = "+planet.getId()+" is already present in the database");
        } else {

            try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {

                Transaction transaction = session.beginTransaction();
                session.persist(planet);
                transaction.commit();

                System.out.println("Planet "+planet.getName()+" is created");
            }
        }
    }

    @Override
    public Planet read(String id) throws SQLException {

        Planet planet;
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            planet = session.get(Planet.class, id);
        }
        if (planet == null){
            throw new SQLException("No found planet for this id = " + id);
        }
        return planet;
    }

    @Override
    public void update(Planet planet) throws SQLException {
        if(isEntityExist(planet.getId())){

            try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {

                Transaction transaction = session.beginTransaction();
                session.update(planet);
                transaction.commit();
                System.out.println("Planet for this id = " + planet.getId()+" is update!");
            }
        } else {
            System.out.println("No found planet for this id = " + planet.getId());
        }
    }

    @Override
    public void delete(String id) throws SQLException {
        if(isEntityExist(id)){

            try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {

                Transaction transaction = session.beginTransaction();
                session.delete(session.get(Planet.class, id));
                transaction.commit();
                System.out.println("Planet for this id = " + id+" is delete!");
            }
        } else {
            System.out.println("No found planet for this id = " + id);
        }
    }
    public List<Planet> readAll(){
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        List<Planet> planets = session.createQuery("from Planet", Planet.class).list();
        session.close();
        return planets;
    }
    private static boolean isEntityExist(String id){
        Planet planet;
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {

            planet = session.get(Planet.class, id);
        }
        if (planet == null){
            return false;
        } else {
            return true;
        }

    }
}
