package homework_10.client;

import homework_10.ticket.Ticket;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Table(name = "client")
@Entity
@Data
public class Client {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    @Column(length = 200)
    private String name;


    @OneToMany(mappedBy = "client")
    private List<Ticket> tickets;

    public Client() {
    }

    public Client(String name) {
        this.name = name;
    }

    public String toString(){
        return "ID = "+id+", "+name;
    }
}
