import java.util.ArrayList;

public class RecommendationRunner implements Recommender{
    @Override
    public ArrayList<String> getItemsToRate() {

        ArrayList<String> items = new ArrayList<>();

        ArrayList<String> temp;

        AllFilters filters = new AllFilters();
        filters.addFilter(new YearAfterFilter(2005));
        filters.addFilter(new GenreFilter("Action"));

        temp = MovieDatabase.filterBy(filters);

        int val = 0;

        for(String id : temp)
        {
            if(val>=5)
                break;

            items.add(id);
            val++;

        }

        val=0;

        AllFilters f1 = new AllFilters();
        f1.addFilter(new YearAfterFilter(2005));
        f1.addFilter(new GenreFilter("Romance"));

        temp = MovieDatabase.filterBy(f1);

        for(String id : temp)
        {
            if(val>=5)
                break;

            items.add(id);
            val++;

        }


        val=0;

        AllFilters f2 = new AllFilters();
        f2.addFilter(new YearAfterFilter(2005));
        f2.addFilter(new GenreFilter("Comedy"));

        temp = MovieDatabase.filterBy(f2);

        for(String id : temp)
        {
            if(val>=5)
                break;

            items.add(id);
            val++;

        }

        val=0;

        AllFilters f3 = new AllFilters();
        f3.addFilter(new YearAfterFilter(2005));
        f3.addFilter(new GenreFilter("Sci-Fi"));

        temp = MovieDatabase.filterBy(f3);


        for(String id : temp)
        {
            if(val>=5)
                break;

            items.add(id);
            val++;

        }


        return items;
    }

    @Override
    public void printRecommendationsFor(String webRaterID) {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        FourthRatings fourthRatings = new FourthRatings();
        ArrayList<Rating> ratings = new ArrayList<>();

            ratings = fourthRatings.getSimilarRatings(webRaterID,10, 2);



        if(ratings.size()==0)
        {
            System.out.println("<h1>Could Not Found Any Recommendation !</h1>");
        }
        else
        {
            int item = 1;
            System.out.println("<style>" +
                    "table, td, th {\n" +
                    "  border: 1px solid black;\n" +
                    "}\n"+
                    "table {\n" +
                    "  border-collapse: collapse;\n" +
                    "  text-align: center;\n" +
                    "}"+
                    "</style>");
            System.out.println("<h1>Recommended Movies</h1>");
            System.out.println("<table>" +
                    "<tr>" +
                    "<th style=\"width:180px\">Rank</th>" +
                    "<th>Movie</th>" +
                    "</tr>");

            for(Rating rating : ratings){

                if(item>20)
                    break;

                System.out.println("<tr>" +
                        "<td>"+item++ +"</td>" +
                        "<td>"+"<img  width=\"70\" height=\"100\" src='"+MovieDatabase.getPoster(rating.getItem())+"'>" +
                        "<br>" +
                        "<h3>" +
                        MovieDatabase.getTitle(rating.getItem())+"</td>" +
                        "</h3>"+
                        "</tr>");

            }

            System.out.println("</table>");

        }


    }
}
