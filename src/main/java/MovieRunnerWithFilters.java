import java.util.ArrayList;
import java.util.Comparator;

public class MovieRunnerWithFilters {

    private final ThirdRatings thirdRatings = new ThirdRatings(System.getProperty("user.dir")+"/src/main/resources/data/ratings_short.csv");



    public void printAverageRatings()
    {
        System.out.println("Read data for "+thirdRatings.getRaterSize()+" raters");

        MovieDatabase.initialize("ratedmovies_short.csv");

        System.out.println("Read data for "+MovieDatabase.size()+" movies");

        ArrayList<Rating> ratings = thirdRatings.getAverageRatings(1);
        System.out.println("Found "+ratings.size()+" movies");

        ratings.sort(Comparator.comparingDouble(Rating::getValue));

        for(Rating rating : ratings)
        {
            System.out.println(rating.getValue()+" "+MovieDatabase.getTitle(rating.getItem()));
        }

    }

    public void printAverageRatingsByYear(){

        System.out.println("Read data for "+thirdRatings.getRaterSize()+" raters");

        MovieDatabase.initialize("ratedmovies_short.csv");

        System.out.println("Read data for "+MovieDatabase.size()+" movies");

        ArrayList<Rating> ratings = thirdRatings.getAverageRatingsByFilter(1,new YearAfterFilter(2000));
        System.out.println("Found "+ratings.size()+" movies");

        ratings.sort(Comparator.comparingDouble(Rating::getValue));

        for(Rating rating : ratings)
        {
            System.out.println(rating.getValue()+" "+MovieDatabase.getYear(rating.getItem())+" "+MovieDatabase.getTitle(rating.getItem()));
        }


    }

    public void printAverageRatingsByGenre()
    {


        System.out.println("Read data for "+thirdRatings.getRaterSize()+" raters");

        MovieDatabase.initialize("ratedmovies_short.csv");

        System.out.println("Read data for "+MovieDatabase.size()+" movies");

        ArrayList<Rating> ratings = thirdRatings.getAverageRatingsByFilter(1,new GenreFilter("Crime"));
        System.out.println("Found "+ratings.size()+" movies");

        ratings.sort(Comparator.comparingDouble(Rating::getValue));

        for(Rating rating : ratings)
        {
            System.out.println(rating.getValue()+" "+MovieDatabase.getTitle(rating.getItem()));
            System.out.println("    "+MovieDatabase.getGenres(rating.getItem()));
        }
    }

    public void printAverageRatingsByMinutes()
    {
        System.out.println("Read data for "+thirdRatings.getRaterSize()+" raters");

        MovieDatabase.initialize("ratedmovies_short.csv");

        System.out.println("Read data for "+MovieDatabase.size()+" movies");

        ArrayList<Rating> ratings = thirdRatings.getAverageRatingsByFilter(1,new MinutesFilter(110,170));
        System.out.println("Found "+ratings.size()+" movies");

        ratings.sort(Comparator.comparingDouble(Rating::getValue));

        for(Rating rating : ratings)
        {
            System.out.println(rating.getValue()+" Time: "+MovieDatabase.getMinutes(rating.getItem())+" "+MovieDatabase.getTitle(rating.getItem()));

        }
    }

    public void printAverageRatingsByDirectors()
    {
        System.out.println("Read data for "+thirdRatings.getRaterSize()+" raters");

        MovieDatabase.initialize("ratedmovies_short.csv");

        System.out.println("Read data for "+MovieDatabase.size()+" movies");

        ArrayList<Rating> ratings = thirdRatings.getAverageRatingsByFilter(1,new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze"));
        System.out.println("Found "+ratings.size()+" movies");

        ratings.sort(Comparator.comparingDouble(Rating::getValue));

        for(Rating rating : ratings)
        {
            System.out.println(rating.getValue()+" "+MovieDatabase.getTitle(rating.getItem()));
            System.out.println("    "+MovieDatabase.getDirector(rating.getItem()));
        }
    }

    public void printAverageRatingsByYearAfterAndGenre()
    {
        System.out.println("Read data for "+thirdRatings.getRaterSize()+" raters");

        MovieDatabase.initialize("ratedmovies_short.csv");

        System.out.println("Read data for "+MovieDatabase.size()+" movies");

        AllFilters allFilters = new AllFilters();

        allFilters.addFilter(new YearAfterFilter(1980));
        allFilters.addFilter(new GenreFilter("Romance"));

        ArrayList<Rating> ratings = thirdRatings.getAverageRatingsByFilter(1,allFilters);
        System.out.println(ratings.size()+" movie matched");

        ratings.sort(Comparator.comparingDouble(Rating::getValue));

        for(Rating rating : ratings)
        {
            System.out.println(rating.getValue()+" "+MovieDatabase.getYear(rating.getItem())+" "+MovieDatabase.getTitle(rating.getItem()));
            System.out.println("    "+MovieDatabase.getGenres(rating.getItem()));
        }
    }

    public void printAverageRatingsByDirectorsAndMinutes()
    {
        System.out.println("Read data for "+thirdRatings.getRaterSize()+" raters");

        MovieDatabase.initialize("ratedmovies_short.csv");

        System.out.println("Read data for "+MovieDatabase.size()+" movies");

        AllFilters allFilters = new AllFilters();

        allFilters.addFilter(new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola"));
        allFilters.addFilter(new MinutesFilter(30,170));

        ArrayList<Rating> ratings = thirdRatings.getAverageRatingsByFilter(1,allFilters);
        System.out.println(ratings.size()+" movie matched");

        ratings.sort(Comparator.comparingDouble(Rating::getValue));

        for(Rating rating : ratings)
        {
            System.out.println(rating.getValue()+" Time: "+MovieDatabase.getMinutes(rating.getItem())+" "+MovieDatabase.getTitle(rating.getItem()));
            System.out.println("    "+MovieDatabase.getDirector(rating.getItem()));
        }
    }


}
