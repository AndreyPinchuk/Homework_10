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

        //Client
        try {
            //Client create
            clientCrudService.create(new Client("Jonny"));
            //Client read
            System.out.println(clientCrudService.read(8));
            //Client update
            Client client = new Client("John Wick");
            client.setId(7);
            clientCrudService.update(client);
            //Client delete
            clientCrudService.delete(10);

        } catch (SQLException e) {
            e.printStackTrace();
        }
            System.out.println();
        //Client readAll
        List<Client> clients = clientCrudService.readAll();
        for (Client client1 : clients) {
            System.out.println(client1.toString());
        }
        System.out.println();

        //Planet
        try {
            //Planet create
            planetCrudService.create(new Planet("PLU","Plutone"));
            //Planet read
            System.out.println(planetCrudService.read("SAT").toString());
            //Planet update
            Planet planet = new Planet("PLU","Pluto");
            planetCrudService.update(planet);
            //Planet delete
            planetCrudService.delete("PLU");

        } catch (SQLException e) {
            e.printStackTrace();
        }
            System.out.println();
        //Planet readAll
        List<Planet> planets = planetCrudService.readAll();
        for (Planet planet : planets) {
            System.out.println(planet);
        }
        System.out.println();

        //Ticket
        try {
            //Ticket create
            ticketCrudService.create(new Ticket(
                    clientCrudService.read(8),
                    planetCrudService.read("EAR"),
                    planetCrudService.read("VEN")
            ));
            //Ticket read
            System.out.println(ticketCrudService.read(8).toString());
            //Ticket update
            Ticket ticket = new Ticket(
                    clientCrudService.read(5),
                    planetCrudService.read("VEN"),
                    planetCrudService.read("SAT")
            );
            ticket.setId(5);
            ticketCrudService.update(ticket);
            //Ticket delete
            ticketCrudService.delete(9);

            System.out.println();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Ticket readAll
        List<Ticket> tickets = ticketCrudService.readAll();
        for (Ticket ticket : tickets) {
            System.out.println(ticket);
        }
    }
}
