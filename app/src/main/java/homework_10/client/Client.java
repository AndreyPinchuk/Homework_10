package homework_10.client;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "client")
@Entity
@Data
public class Client {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    @Column(length = 200)
    private String name;

    public Client() {
    }

    public Client(String name) {
        this.name = name;
    }

    public String toString(){
        return "ID = "+id+", "+name;
    }
}
