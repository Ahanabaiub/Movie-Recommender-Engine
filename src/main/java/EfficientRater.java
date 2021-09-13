
/**
 * Write a description of class Rater here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class EfficientRater implements Rater {
    private final String myID;
    private final HashMap<String, Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item,new Rating(item,rating));
    }

    public boolean hasRating(String item) {

        return myRatings.containsKey(item);
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {

        return this.hasRating(item)?myRatings.get(item).getValue():-1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<>();

        Iterator hmIterator = myRatings.entrySet().iterator();

        while(hmIterator.hasNext())
        {
            Map.Entry element =(Map.Entry) hmIterator.next();

            list.add((String) element.getKey());
        }




        return list;
    }



}
