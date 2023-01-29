package homework_10.ticket;

import jakarta.persistence.*;
import lombok.Data;

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
    @Column
    private long client_id;
    @Column
    private String from_planet_id;
    @Column
    private String to_planet_id;

    public Ticket() {
    }

    public Ticket( long client_id, String from_planet_id, String to_planet_id) {
        this.created_at = ZonedDateTime.now(ZoneId.of("UTC"))
                .format(DateTimeFormatter
                        .ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.client_id = client_id;
        this.from_planet_id = from_planet_id;
        this.to_planet_id = to_planet_id;
    }

    public String toString(){
        return "ID = "+id+"; "
                +created_at+"; "
                +client_id+"; "
                +from_planet_id+"; "
                +to_planet_id;
    }
}
