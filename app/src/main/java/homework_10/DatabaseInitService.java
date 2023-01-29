package homework_10;

import homework_10.hibernate.HibernateUtil;
import org.flywaydb.core.Flyway;


public class DatabaseInitService {
    public void initDb(HibernateUtil util){
        Flyway flyway = Flyway
                .configure()
                .dataSource(util.getSessionFactory().getProperties().get("hibernate.connection.url").toString()
                        ,null
                        ,null)
                .load();
        flyway.migrate();
    }
}
