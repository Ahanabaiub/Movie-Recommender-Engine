import java.util.ArrayList;
import java.util.Comparator;

public class MovieRunnerAverage
{
    SecondRatings secondRatings = new SecondRatings(System.getProperty("user.dir")+"/src/main/resources/data/ratedmovies_short.csv",
            System.getProperty("user.dir")+"/src/main/resources/data/ratings_short.csv");

    public void printAverageRatings()
    {
        System.out.println(secondRatings.getMovieSize());
        System.out.println(secondRatings.getRaterSize());

        ArrayList<Rating> ratings = secondRatings.getAverageRatings(3);

        ratings.sort(Comparator.comparingDouble(Rating::getValue));

        for(Rating rating : ratings)
        {
            System.out.println(rating.getValue()+" "+secondRatings.getTitle(rating.getItem()));
        }

    }

    public void getAverageRatingOneMovie()
    {
        System.out.println(secondRatings.getAverageRatingByTitle("The Godfather"));
    }
}