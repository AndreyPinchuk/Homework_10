package homework_10.ticket;

import homework_10.client.Client;
import homework_10.planet.Planet;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Table(name = "ticket")
@Entity
@Data
public class Ticket {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    @Column
    private String created_at;
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id",nullable = false)
    private Client client;
    @ManyToOne
    @JoinColumn(name = "from_planet_id", referencedColumnName = "id",nullable = false)
    private Planet from_planet;
    @ManyToOne
    @JoinColumn(name = "to_planet_id", referencedColumnName = "id",nullable = false)
    private Planet to_planet;

    public Ticket() {
    }

    public Ticket(Client client, Planet from_planet, Planet to_planet) throws SQLException {
        this.created_at = ZonedDateTime.now(ZoneId.of("UTC"))
                .format(DateTimeFormatter
                        .ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.client = client;
        this.from_planet = from_planet;
        this.to_planet = to_planet;
    }

    public String toString(){
        return "ID = "+id+"; "
                +created_at+"; "
                +client.toString()+"; "
                +from_planet.getId()+"; "
                +to_planet.getId();
    }
}
