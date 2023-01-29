package homework_10.planet;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "planet")
@Entity
@Data
public class Planet {
    @Column(length = 10)
    @Id
    private String id;
    @Column(length = 500)
    private String name;

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
