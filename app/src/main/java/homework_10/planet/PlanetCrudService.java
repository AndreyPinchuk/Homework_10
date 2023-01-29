package homework_10.planet;

import homework_10.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class PlanetCrudService implements IPlanetService{
    @Override
    public void create(Planet planet) throws SQLException {
        if(findId(planet.getId())){
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
        if(findId(id)){
            try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
                return session.get(Planet.class, id);
            }
        } else {
            throw new SQLException("No found planet for this id = "+id);
        }
    }

    @Override
    public void update(Planet planet) throws SQLException {
        if(findId(planet.getId())){

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
        if(findId(id)){

            Planet planet = new Planet();
            planet.setId(id);
            try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {

                Transaction transaction = session.beginTransaction();
                session.delete(planet);
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
    private static boolean findId(String id){
        Planet planet;
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {

            Query<Planet> query = session.createQuery(
                    "from Planet WHERE id = :id",
                    Planet.class
            );
            query.setParameter("id", id);

            planet = query.stream().findFirst().orElse(new Planet());
            if (planet.getName() == null) {
                throw new SQLException("No found planet for this id = " + id);
            }
            return true;
        } catch (SQLException e){
            return false;
        }
    }
}
