

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings{

    ArrayList<Movie> movieList;
    Movie movie;

    Reader reader = null;
    CSVParser csvParser;

    public ArrayList<Movie> loadMovies(String fileName)
    {
            movieList=new ArrayList<>();



        try {
            reader = Files.newBufferedReader(Paths.get(fileName));
            csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
            );


            for(CSVRecord record : csvParser)
            {
                movie = new Movie(record.get("id"),  record.get("title"),record.get("year"),record.get("genre"),record.get("director"), record.get("country"),
                        record.get("poster"),record.get("minutes"));

                movieList.add(movie);


            }
        } catch (IOException e) {
            e.printStackTrace();
        }




            return movieList;

    }

    public void testLoadMovie()
    {
        movieList = loadMovies("E:\\projects\\Movie-Recomender-Engine project\\Step 3\\Movie Recomender Engine\\src\\main\\resources\\data\\ratedmoviesfull.csv");

        System.out.println("Number of Movies : "+movieList.size());
        int comedyMovies=0;

        for(Movie m : movieList)
        {
            //System.out.println(m.getID()+","+m.getTitle()+","+m.getYear()+","+m.getCountry()+","+m.getGenres()+","+m.getDirector()+","+m.getMinutes()+","+m.getPoster());

            String  gnrs[] = m.getGenres().split(", ");


            for(String s : gnrs)
            {
                if(s.equals("Comedy"))
                {
                    comedyMovies++;
                }
            }

        }

        System.out.println("Number of Comedy movies: "+comedyMovies);


        moviesGreaterThan150m(movieList);




    }

    public static void moviesGreaterThan150m(List<Movie> list)
    {
        int num=0;

        for(Movie m : list)
        {
            if(m.getMinutes()>150)
            {
                num++;
            }
        }
        System.out.println("Movies Graeter than 150m : "+num);
    }

    public ArrayList<Rater> loadRaters(String fileName)
    {
        ArrayList<Rater> raterList = new ArrayList<>();

        Rater rater ;
        try {
            reader = Files.newBufferedReader(Paths.get(fileName));
            csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
            );

        } catch (IOException e) {
            e.printStackTrace();
        }


        for(CSVRecord record : csvParser)
        {
            boolean isInTheRaterList= false;


            for(Rater rtr : raterList)
            {
                //System.out.println(rtr.getID());
                //System.out.println(record.get("movie_id"));


                if(rtr.getID().equals(record.get("rater_id")))
                {
                    isInTheRaterList=true;
                    if(!(rtr.hasRating(record.get("movie_id"))))
                    {
                        rtr.addRating(record.get("movie_id"),Double.parseDouble(record.get("rating")));
                    }
                    break;
                }
            }

            if(!isInTheRaterList)
            {
                rater = new EfficientRater(record.get("rater_id"));
                raterList.add(rater);
                rater.addRating(record.get("movie_id"),Double.parseDouble(record.get("rating")));

            }



        }


        return raterList;


    }


    public void testLoadRaters()
    {
        ArrayList<Rater> listOfRaters = loadRaters("E:\\projects\\Movie-Recomender-Engine project\\Step 3\\Movie Recomender Engine\\src\\main\\resources\\data\\ratings.csv");

        System.out.println("Total number of Rater : "+listOfRaters.size());

        /*for(Rater rater : listOfRaters)
        {
            System.out.println("Rater ID : "+rater.getID() +"    Numbers of Rating : "+rater.numRatings());
            ArrayList<String> items = rater.getItemsRated();
            System.out.println("List of ratings : ");
            for(String item : items)
            {
                System.out.println("Movie Id : "+item+"   Rating : "+rater.getRating(item));
            }

        }*/

        int nOfRating = numberOfRating(listOfRaters,"193");
        System.out.println("Number of rated movies : "+nOfRating);

        maxRatingQuery(listOfRaters);
        numberOfRatings(listOfRaters,"1798709");
        numberOfMoviesRated(listOfRaters);

    }

    public int numberOfRating(ArrayList<Rater> raters, String raterId)
    {


        for(Rater rtr : raters)
        {
            if(rtr.getID().equals(raterId))
            {
                return rtr.numRatings();
            }
        }
        return 0;
    }

    public void maxRatingQuery(ArrayList<Rater> raters)
    {
        int maxNumRating = -1;
        int numOfMaxRaters = 0;
        ArrayList<String> maxRaterIds = new ArrayList<>();

        for(Rater rtr : raters)
        {
            if(rtr.numRatings()>maxNumRating)
            {
                maxNumRating = rtr.numRatings();
            }
        }

        for(Rater rtr : raters)
        {
            if(rtr.numRatings()==maxNumRating)
            {
                numOfMaxRaters++;
                maxRaterIds.add(rtr.getID());
            }
        }

        System.out.println("Max Number of Rating : "+maxNumRating);
        System.out.println("Number of maximum raters : "+numOfMaxRaters);
        for(String s : maxRaterIds)
        {
            System.out.println("Max rater Ids : "+s);
        }

    }

    public void numberOfRatings(ArrayList<Rater> listOfRater,String movieId)
    {
        int num=0;
        ArrayList<String> items;

        for(Rater rtr : listOfRater)
        {
             items = rtr.getItemsRated();

             for(String s : items)
             {
                 if(s.equals(movieId))
                 {
                     num++;
                     break;
                 }
             }
        }

        System.out.println(movieId+" movie item rated = "+num);

    }

    public void numberOfMoviesRated(ArrayList<Rater> listOfRatres)
    {
        LinkedHashSet<String> movies = new LinkedHashSet<>();

        for(Rater rtr : listOfRatres)
        {
            ArrayList<String> ratedMovieList = rtr.getItemsRated();

            for(String s : ratedMovieList)
            {
                movies.add(s);
            }
        }

        System.out.println("Number of Rated movies : "+movies.size());

    }






}
