package homework_10;

import homework_10.client.Client;
import homework_10.client.ClientCrudService;
import homework_10.planet.Planet;
import homework_10.planet.PlanetCrudService;
import homework_10.ticket.Ticket;
import homework_10.ticket.TicketCrudService;

import java.sql.SQLException;
import java.util.List;

public class App {
    public static void main(String[] args) {
        new DatabaseInitService().initDb();

        ClientCrudService clientCrudService = new ClientCrudService();
        PlanetCrudService planetCrudService = new PlanetCrudService();
        TicketCrudService ticketCrudService = new TicketCrudService();
        try {
            //Client
            clientCrudService.create(new Client("Jonny"));
            System.out.println(clientCrudService.read(8));
            Client client = new Client("John Wick");
            client.setId(7);
            clientCrudService.update(client);
            clientCrudService.delete(11);

            System.out.println();

            //Planet
            planetCrudService.create(new Planet("PLU","Plutone"));
            System.out.println(planetCrudService.read("SAT").toString());
            Planet planet = new Planet("PLU","Pluto");
            planetCrudService.update(planet);
            planetCrudService.delete("PLU");

            System.out.println();

            //Ticket
            ticketCrudService.create(new Ticket(5,"VEN","EAR"));
            System.out.println(ticketCrudService.read(8).toString());
            Ticket ticket = new Ticket(5,"VEN","SAT");
            ticket.setId(5);
            ticketCrudService.update(ticket);
            ticketCrudService.delete(12);

            System.out.println();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Client> clients = clientCrudService.readAll();
        for (Client client1 : clients) {
            System.out.println(client1.toString());
        }
        System.out.println();

        List<Planet> planets = planetCrudService.readAll();
        for (Planet planet : planets) {
            System.out.println(planet);
        }
        System.out.println();

        List<Ticket> tickets = ticketCrudService.readAll();
        for (Ticket ticket : tickets) {
            System.out.println(ticket);
        }
    }
}
