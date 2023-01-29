package homework_10.ticket;

import java.sql.SQLException;

public interface ITicketService {
    void create(Ticket ticket) throws SQLException;
    Ticket read(long id) throws SQLException;
    void update(Ticket ticket) throws SQLException;
    void delete(long id) throws SQLException;
}
