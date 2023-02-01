package homework_10.client;

import homework_10.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

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
        Client client;
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            client = session.get(Client.class, id);
        }
        if (client == null){
            throw new SQLException("No found client for this id = " + id);
        }
        return client;

    }
    public Client readForTicket(long id) {
        Client client;
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            client = session.get(Client.class, id);
        }
        if (client == null){
            System.out.println("No found client for this id = "+id);
            System.out.println("Please create new client");
            Scanner sc = new Scanner(System.in);
            String name = sc.nextLine();
            client = new Client(name);
            sc.close();
            try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.persist(client);
                transaction.commit();
                return client;
            }
        }
        return client;
    }

    @Override
    public void update(Client client) throws SQLException {
        if(isEntityExist(client.getId())){

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
        if(isEntityExist(id)){

            try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {

                Transaction transaction = session.beginTransaction();
                session.delete(session.get(Client.class,id));
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
    private static boolean isEntityExist(long id){
        Client client;
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            client = session.get(Client.class, id);
        }
        if (client == null){
            return false;
        } else {
            return true;
        }

    }
}
