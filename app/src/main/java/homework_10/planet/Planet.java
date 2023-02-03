package homework_10.planet;

import homework_10.ticket.Ticket;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Table(name = "planet")
@Entity
@Data
public class Planet {
    @Column(length = 10)
    @Id
    private String id;
    @Column(length = 500)
    private String name;

    @OneToMany(mappedBy = "from_planet")
    private List<Ticket> tickets_from_planet;

    @OneToMany(mappedBy = "to_planet")
    private List<Ticket> tickets_to_planet;

    public Planet() {
    }

    public Planet(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString(){
        return "ID = "+id+", "+name;
    }
}
