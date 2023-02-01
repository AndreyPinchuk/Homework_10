package homework_10.hibernate;

import homework_10.DatabaseInitService;
import homework_10.client.Client;
import homework_10.client.ClientCrudService;
import homework_10.planet.Planet;
import homework_10.planet.PlanetCrudService;
import homework_10.ticket.Ticket;
import homework_10.ticket.TicketCrudService;
import lombok.Getter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class HibernateUtil {
    private static final HibernateUtil INSTANCE;

    @Getter
    private SessionFactory sessionFactory;

    static {
        INSTANCE = new HibernateUtil();
    }

    private HibernateUtil(){
        sessionFactory = new Configuration()
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Planet.class)
                .addAnnotatedClass(Ticket.class)
                .buildSessionFactory();
    }

    public static HibernateUtil getInstance(){
        return INSTANCE;
    }
    public void close(){

        sessionFactory.close();
    }


}
