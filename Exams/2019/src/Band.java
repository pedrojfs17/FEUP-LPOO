import java.util.ArrayList;
import java.util.List;

public class Band extends Act {
    private List<Artist> artists;

    public Band(String name, String country) {
        super(name, country);
        this.artists = new ArrayList<>();
    }

    public void addArtist(Artist artist) {
        this.artists.add(artist);
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public boolean containsArtist(Artist artist) {
        for (Artist a : artists) {
            if (a.equals(artist)) return true;
        }
        return false;
    }
}
