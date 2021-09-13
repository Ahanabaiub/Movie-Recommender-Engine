import java.io.File;

public class Main {
    public static void main(String[] args) {
//      MovieRunnerAverage movieRunnerAverage =new MovieRunnerAverage();
//      movieRunnerAverage.printAverageRatings();
//      movieRunnerAverage.getAverageRatingOneMovie();

       // MovieRunnerSimilarRatings movieRunnerSimilarRatings = new MovieRunnerSimilarRatings();
        //movieRunnerSimilarRatings.printSimilarRatingsByYearAfterAndMinutes();
        RecommendationRunner runner = new RecommendationRunner();
        runner.getItemsToRate();
    }
}
