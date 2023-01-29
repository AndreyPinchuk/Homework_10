package homework_10.client;

import java.sql.SQLException;

public interface IClientService {
    void create(Client client) throws SQLException;
    Client read(long id) throws SQLException;
    void update(Client client) throws SQLException;
    void delete(long id) throws SQLException;
}
