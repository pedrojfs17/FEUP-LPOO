import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Concert {
    private String city;
    private String country;
    private String date;

    private int soldTickets;

    private List<Act> acts;

    public Concert(String city, String country, String date) {
        this.city = city;
        this.country = country;
        this.date = date;
        this.soldTickets = 0;
        this.acts = new ArrayList<>();
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Act> getActs() {
        return acts;
    }

    public void setActs(List<Act> acts) {
        this.acts = acts;
    }

    public void addAct(Act act) {
        this.acts.add(act);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Concert concert = (Concert) o;
        return Objects.equals(city, concert.city) &&
                Objects.equals(country, concert.country) &&
                Objects.equals(date, concert.date) &&
                Objects.equals(acts, concert.acts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, country, date, acts);
    }

    public boolean isValid(Ticket ticket) {
        return ticket.getConcert() == this;
    }

    public boolean participates(Artist artist) {
        for (Act a : acts) {
            if ((a instanceof Band && ((Band) a).containsArtist(artist)) || (a.equals(artist))) return true;
        }
        return false;
    }

    public int getSoldTickets() {
        return soldTickets;
    }

    public void setSoldTickets(int soldTickets) {
        this.soldTickets = soldTickets;
    }
}
