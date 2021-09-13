
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FourthRatings {


    private double dotProduct(Rater me, Rater r)
    {
         ArrayList<String> myRatings = me.getItemsRated();
         ArrayList<String> rRatings = r.getItemsRated();

         double dotProd = 0.0;

         for (String item : myRatings)
         {
             if(rRatings.contains(item))
             {
                 dotProd += (me.getRating(item)-5)*(r.getRating(item)-5);
             }
         }

         return dotProd;

    }

    private double formatDouble(double value)
    {
        DecimalFormat df = new DecimalFormat("#0.00");
        return Double.parseDouble(df.format(value));
    }

    private ArrayList<Rating> getSimilarities(String id)
    {
        ArrayList<Rating> similarRatings = new ArrayList<>();
        ArrayList<Rater> raters = RaterDatabase.getRaters();

        for (Rater rater : raters)
        {
            if(!rater.getID().equals(id))
            {
                double similarRating = this.dotProduct(RaterDatabase.getRater(id),RaterDatabase.getRater(rater.getID()));

                if(similarRating>0)
                {
                    similarRatings.add(new Rating(rater.getID(),similarRating));
                }

            }
        }

        //Sort Ratings in Reverse orders
        similarRatings.sort(Comparator.comparingDouble(Rating::getValue).reversed());

        return similarRatings;
    }

    private double getAverageByID(String id, int minimalRaters)
    {
        double numRater = 0;
        double sumRatings = 0;

        for (Rater rater: RaterDatabase.getRaters())
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


    public ArrayList<Rating> getSimilarRatings( String id, int numSimilarRaters,int minimalRaters)
    {
        ArrayList<Rating> similarity = this.getSimilarities(id);
        ArrayList<Rating> movieRating = new ArrayList<>();

       for (String movieId : MovieDatabase.filterBy(new TrueFilter()))
       {
           int count = 0;
           int sum = 0;

           if(similarity.size()<numSimilarRaters)
               numSimilarRaters=similarity.size();

           for(int i=0; i<numSimilarRaters; i++)
           {
               for(Rater rater : RaterDatabase.getRaters())
               {
                   if(rater.getID().equals(similarity.get(i).getItem()) && rater.hasRating(movieId))
                   {
                        sum+=similarity.get(i).getValue()*rater.getRating(movieId);
                        count++;
                        break;
                   }
               }
           }

           if(count>=minimalRaters)
           {
              try{
                  double weightedAvg = this.formatDouble((double)sum/count);
                  movieRating.add(new Rating(movieId,weightedAvg));
              }catch (Exception e)
              {
                  System.out.println(e.getStackTrace());
              }
           }

       }

       movieRating.sort(Comparator.comparingDouble(Rating::getValue).reversed());

       return movieRating;
    }


    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters,Filter filterCriteria)
    {
        ArrayList<Rating> similarity = this.getSimilarities(id);
        ArrayList<Rating> movieRating = new ArrayList<>();

        for (String movieId : MovieDatabase.filterBy(filterCriteria))
        {
            int count = 0;
            int sum = 0;
            for(int i=0; i<numSimilarRaters; i++)
            {
                for(Rater rater : RaterDatabase.getRaters())
                {
                    if(rater.getID().equals(similarity.get(i).getItem()) && rater.hasRating(movieId))
                    {
                        sum+=similarity.get(i).getValue()*rater.getRating(movieId);
                        count++;
                        break;
                    }
                }
            }

            if(count>=minimalRaters)
            {
                try{
                    double weightedAvg = this.formatDouble((double)sum/count);
                    movieRating.add(new Rating(movieId,weightedAvg));
                }catch (Exception e)
                {
                    System.out.println(e.getStackTrace());
                }
            }

        }

        movieRating.sort(Comparator.comparingDouble(Rating::getValue).reversed());

        return movieRating;
    }




}
