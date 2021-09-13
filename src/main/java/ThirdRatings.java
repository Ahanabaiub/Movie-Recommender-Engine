
/**
 * Write a description of SecondRatings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class ThirdRatings {

    private ArrayList<Rater> myRaters;

    public ThirdRatings() {
        // default constructor
        this(System.getProperty("user.dir")+"/src/main/resources/data/ratings.csv");
    }

    public ThirdRatings(String ratingsfile)
    {
        FirstRatings firstRatings = new FirstRatings();

        myRaters = firstRatings.loadRaters(ratingsfile);
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

        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());

        for (String movieId : movies)
        {
            averageRating=this.getAverageByID(movieId,minimalRaters);

            if(averageRating>0.0)
            {
                allAvgRatings.add(new Rating(movieId,averageRating));
            }
        }


        return allAvgRatings;

    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria)
    {
        ArrayList<Rating> ratings = new ArrayList<>();
        ArrayList<String> filteredMovies = MovieDatabase.filterBy(filterCriteria);

        for (String movieId : filteredMovies)
        {
            double rating = this.getAverageByID(movieId,minimalRaters);
            if(rating>0.0)
                ratings.add(new Rating(movieId,rating));
        }

        return ratings;
    }





}