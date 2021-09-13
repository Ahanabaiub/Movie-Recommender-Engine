
import java.util.Arrays;
import java.util.List;

public class DirectorsFilter implements Filter {

    private final String directors;

    public DirectorsFilter(String directors) {
        this.directors = directors;
    }

    @Override
    public boolean satisfies(String id) {

        String[] directors = this.directors.split(",");

        String movieDirectors = MovieDatabase.getDirector(id);

        for(String s : directors)
        {
            if(movieDirectors.contains(s))
            {
                return true;
            }
        }

        return false;
    }
}
