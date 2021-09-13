
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
    }

    public SecondRatings(String moviefile, String ratingsfile)
    {
        FirstRatings firstRatings = new FirstRatings();

        myMovies = firstRatings.loadMovies(moviefile);
        myRaters = firstRatings.loadRaters(ratingsfile);
    }

    public int getMovieSize()
    {
        return myMovies.size();
    }

    public int getRaterSize()
    {
        return myRaters.size();
    }

    private double getAverageByID(String id, int minimalRaters)
    {
        double numRater = 0;
        double sumRatings = 0;

        for (Rater rater: myRaters)
        {
            if(rater.hasRating(id))
            {
                numRater++;
                sumRatings+=rater.getRating(id);
            }
        }

        if(numRater<minimalRaters)
            return 0.0;
        else
        {
            return sumRatings/numRater;
        }

    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters)
    {
        ArrayList<Rating> allAvgRatings = new ArrayList<>();
        double averageRating;

        for (Movie movie : myMovies)
        {
            averageRating=this.getAverageByID(movie.getID(),minimalRaters);

            if(averageRating>0.0)
            {
                allAvgRatings.add(new Rating(movie.getID(),averageRating));
            }
        }
        
        
        return allAvgRatings;

    }

    public String getTitle(String id)
    {
        String title="Movie ID was not found.";

        for (Movie movie: myMovies)
        {
            if(id.equals(movie.getID()))
            {
                title = movie.getTitle();
                return title;
            }

        }

        return title;
    }

    public String getId(String title)
    {

        for(Movie movie : myMovies)
        {
            if(movie.getTitle().equals(title))
            {
                return movie.getID();
            }
        }

        return "NO SUCH TITLE.";

    }

    public String getAverageRatingByTitle(String title)
    {

        if(!this.getId(title).equals("NO SUCH TITLE."))
            return String.valueOf(this.getAverageByID(this.getId(title),0));
        else
            return "NO SUCH TITLE.";
    }

    
}