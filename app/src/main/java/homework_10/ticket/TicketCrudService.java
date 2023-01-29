package homework_10.ticket;

import homework_10.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
        if (findId(id)){

            try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
                return session.get(Ticket.class, id);
            }
        } else {
            throw new SQLException("No found ticket for this id = "+id);
        }
    }

    @Override
    public void update(Ticket ticket) throws SQLException {
        if(findId(ticket.getId())){
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
        if(findId(id)){

            Ticket ticket = new Ticket();
            ticket.setId(id);
            try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {

                Transaction transaction = session.beginTransaction();
                session.delete(ticket);
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
    private static boolean findId(long id){
        Ticket ticket;
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {

            Query<Ticket> query = session.createQuery(
                    "from Ticket WHERE id = :id",
                    Ticket.class
            );
            query.setParameter("id", id);

            ticket = query.stream().findFirst().orElse(new Ticket());
            if (ticket.getId() == 0) {
                throw new SQLException("No found ticket for this id = " + id);
            }
            return true;
        } catch (SQLException e){
            return false;
        }
    }
}
