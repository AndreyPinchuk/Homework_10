package homework_10.planet;

import java.sql.SQLException;

public interface IPlanetService {
    void create(Planet planet) throws SQLException;
    Planet read(String id) throws SQLException;
    void update(Planet planet) throws SQLException;
    void delete(String id) throws SQLException;
}
