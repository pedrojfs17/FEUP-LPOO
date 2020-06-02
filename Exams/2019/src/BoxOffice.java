import java.util.ArrayList;
import java.util.List;

public class BoxOffice {
    public static List<Ticket> buy(Concert concert, int numTickets) throws InvalidTicket {
        List<Ticket> tickets = new ArrayList<>();

        for (int i = concert.getSoldTickets() + 1; i <= concert.getSoldTickets() + numTickets; i++)
            tickets.add(new Ticket(i, concert));

        concert.setSoldTickets(concert.getSoldTickets() + numTickets);
        return tickets;
    }
}
