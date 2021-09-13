import java.util.ArrayList;
import java.util.Comparator;

public class MovieRunnerSimilarRatings {

    private final FourthRatings fourthRatings = new FourthRatings();


    public void printAverageRatings() {

        RaterDatabase.addRatings("ratings_short.csv");
        System.out.println("Read data for "+RaterDatabase.size()+" raters");

        MovieDatabase.initialize("ratedmovies_short.csv");

        System.out.println("Read data for "+MovieDatabase.size()+" movies");

        ArrayList<Rating> ratings = fourthRatings.getAverageRatings(1);
        System.out.println("Found "+ratings.size()+" movies");

        ratings.sort(Comparator.comparingDouble(Rating::getValue));

        for(Rating rating : ratings)
        {
            System.out.println(rating.getValue()+" "+MovieDatabase.getTitle(rating.getItem()));
        }

    }


    public void printAverageRatingsByYearAfterAndGenre()
    {
        RaterDatabase.addRatings("ratings_short.csv");
        System.out.println("Read data for "+RaterDatabase.size()+" raters");

        MovieDatabase.initialize("ratedmovies_short.csv");

        System.out.println("Read data for "+MovieDatabase.size()+" movies");

        AllFilters allFilters = new AllFilters();

        allFilters.addFilter(new YearAfterFilter(1980));
        allFilters.addFilter(new GenreFilter("Romance"));

        ArrayList<Rating> ratings = fourthRatings.getAverageRatingsByFilter(1,allFilters);
        System.out.println(ratings.size()+" movie matched");

        ratings.sort(Comparator.comparingDouble(Rating::getValue));

        for(Rating rating : ratings)
        {
            System.out.println(rating.getValue()+" "+MovieDatabase.getYear(rating.getItem())+" "+MovieDatabase.getTitle(rating.getItem()));
            System.out.println("    "+MovieDatabase.getGenres(rating.getItem()));
        }
    }

    public void printSimilarRatings()
    {
        FourthRatings fourthRatings = new FourthRatings();
        RaterDatabase.addRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");

        ArrayList<Rating> ratings = fourthRatings.getSimilarRatings("71",20,5);

        for(Rating rating : ratings)
        {
            System.out.println(rating.getItem()+" "+rating.getValue()+" "+MovieDatabase.getTitle(rating.getItem()));
        }

    }

    public void  printSimilarRatingsByGenre()
    {
        FourthRatings fourthRatings = new FourthRatings();
        RaterDatabase.addRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");

        ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter("964",20,5,new GenreFilter("Mystery"));

        for(Rating rating : ratings)
        {
            System.out.println(rating.getValue()+" "+MovieDatabase.getTitle(rating.getItem()));
            System.out.println("   "+MovieDatabase.getGenres(rating.getItem()));
        }

    }

    public void printSimilarRatingsByDirector()
    {
        FourthRatings fourthRatings = new FourthRatings();
        RaterDatabase.addRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");

        ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter("120",10,2,new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));

        for(Rating rating : ratings)
        {
            System.out.println(rating.getValue()+" "+MovieDatabase.getTitle(rating.getItem()));
            System.out.println("   "+MovieDatabase.getDirector(rating.getItem()));
        }
    }

    public void printSimilarRatingsByGenreAndMinutes()
    {
        FourthRatings fourthRatings = new FourthRatings();
        RaterDatabase.addRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");

        AllFilters allFilters = new AllFilters();

        allFilters.addFilter(new GenreFilter("Drama"));
        allFilters.addFilter(new MinutesFilter(80,160));

        ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter("168",10,3,allFilters);

        for(Rating rating : ratings)
        {
            System.out.println(MovieDatabase.getTitle(rating.getItem())+" "+MovieDatabase.getMinutes(rating.getItem())+" "+rating.getValue());
            System.out.println("   "+MovieDatabase.getGenres(rating.getItem()));
        }
    }

    public void printSimilarRatingsByYearAfterAndMinutes()
    {
        FourthRatings fourthRatings = new FourthRatings();
        RaterDatabase.addRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");

        AllFilters allFilters = new AllFilters();

        allFilters.addFilter(new YearAfterFilter(1975));
        allFilters.addFilter(new MinutesFilter(70,200));

        ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter("314",10,5,allFilters);

        for(Rating rating : ratings)
        {
            System.out.println(MovieDatabase.getTitle(rating.getItem())+" "+MovieDatabase.getYear(rating.getItem())+" "+rating.getValue());
        }
    }

}
