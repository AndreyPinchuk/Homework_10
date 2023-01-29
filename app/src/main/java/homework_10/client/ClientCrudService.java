package homework_10.client;

import homework_10.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class ClientCrudService implements IClientService{
    @Override
    public void create(Client client) throws SQLException {
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            session.persist(client);
            transaction.commit();
            System.out.println("Client "+client.getName()+" is created");
        }
    }

    @Override
    public Client read(long id) throws SQLException {
        if(findId(id)){
            try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
                return session.get(Client.class, id);
            }
        } else {
            throw new SQLException("No found client for this id = "+id);
        }
    }

    @Override
    public void update(Client client) throws SQLException {
        if(findId(client.getId())){

            try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {

                Transaction transaction = session.beginTransaction();
                session.update(client);
                transaction.commit();
                System.out.println("Client for this id = " + client.getId()+" is update!");
            }
        } else {
            System.out.println("No found client for this id = " + client.getId());
        }
    }

    @Override
    public void delete(long id) throws SQLException {
        if(findId(id)){

            Client client = new Client();
            client.setId(id);
            try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {

                Transaction transaction = session.beginTransaction();
                session.delete(client);
                transaction.commit();
                System.out.println("Client for this id = " + id+" is delete!");
            }
        } else {
            System.out.println("No found client for this id = " + id);
        }
    }
    public List<Client> readAll(){
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        List<Client> clients = session.createQuery("from Client", Client.class).list();
        session.close();
        return clients;
    }
    private static boolean findId(long id){
        Client client;
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {

            Query<Client> query = session.createQuery(
                    "from Client WHERE id = :id",
                    Client.class
            );
            query.setParameter("id", id);

            client = query.stream().findFirst().orElse(new Client());
            if (client.getName() == null) {
                throw new SQLException("No found client for this id = " + id);
            }
            return true;
        } catch (SQLException e){
            return false;
        }
    }
}
