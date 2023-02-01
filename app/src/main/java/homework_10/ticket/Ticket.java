package homework_10.ticket;

import homework_10.client.Client;
import homework_10.client.ClientCrudService;
import homework_10.planet.Planet;
import homework_10.planet.PlanetCrudService;
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
    private Client client_id;
    @ManyToOne
    @JoinColumn(name = "from_planet_id", referencedColumnName = "id",nullable = false)
    private Planet from_planet_id;
    @ManyToOne
    @JoinColumn(name = "to_planet_id", referencedColumnName = "id",nullable = false)
    private Planet to_planet_id;

    public Ticket() {
    }

    public Ticket( long client_id, String from_planet_id, String to_planet_id) throws SQLException {
        this.created_at = ZonedDateTime.now(ZoneId.of("UTC"))
                .format(DateTimeFormatter
                        .ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.client_id = new ClientCrudService().readForTicket(client_id);
        this.from_planet_id = new PlanetCrudService().read(from_planet_id);
        this.to_planet_id = new PlanetCrudService().read(to_planet_id);
    }

    public String toString(){
        return "ID = "+id+"; "
                +created_at+"; "
                +client_id.toString()+"; "
                +from_planet_id.getId()+"; "
                +to_planet_id.getId();
    }
}
