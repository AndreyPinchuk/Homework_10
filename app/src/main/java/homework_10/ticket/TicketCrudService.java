package homework_10.ticket;

import homework_10.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class TicketCrudService implements ITicketService{
    @Override
    public void create(Ticket ticket) throws SQLException {
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            session.persist(ticket);
            transaction.commit();
            System.out.println("Ticket is created");
        }
    }

    @Override
    public Ticket read(long id) throws SQLException {

        Ticket ticket;
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            ticket = session.get(Ticket.class, id);
        }
        if (ticket == null){
            throw new SQLException("No found ticket for this id = " + id);
        }
        return ticket;
    }

    @Override
    public void update(Ticket ticket) throws SQLException {
        if(isEntityExist(ticket.getId())){
            try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {

                Transaction transaction = session.beginTransaction();
                session.update(ticket);
                transaction.commit();
                System.out.println("Ticket for this id = " + ticket.getId()+" is update!");
            }
        } else {
            System.out.println("No found ticket for this id = " + ticket.getId());
        }
    }

    @Override
    public void delete(long id) throws SQLException {
        if(isEntityExist(id)){

            try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {

                Transaction transaction = session.beginTransaction();
                session.detach(session.get(Ticket.class,id));
                transaction.commit();
                System.out.println("Ticket for this id = " + id+" is delete!");
            }
        } else {
            System.out.println("No found ticket for this id = " + id);
        }
    }
    public List<Ticket> readAll(){
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        List<Ticket> tickets = session.createQuery("from Ticket", Ticket.class).list();
        session.close();
        return tickets;
    }
    private static boolean isEntityExist(long id){
        Ticket ticket;
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {

            ticket = session.get(Ticket.class,id);
        }
        if (ticket == null){
            return false;
        } else {
            return true;
        }

    }
}
