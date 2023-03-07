package ratings;
import java.util.LinkedList;
import ratings.datastructures.LinkedListNode;

public class Song {
    private String title;
    private String artist;
    private String songID;
    private LinkedListNode<Rating> ratings;

    public Song(String title, String artist, String songID){
        this.title = title;
        this.artist = artist;
        this.songID = songID;


    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getArtist(){
        return artist;
    }
    public void setArtist(String artist){
        this.artist = artist;
    }
    public String getSongID(){
        return songID;
    }
    public void setSongID(String songID){
        this.songID=songID;
    }

    public LinkedListNode<Rating> getRatings(){
        return ratings;
    }


    public void addRating(Rating rating){
        if (!didReviewerRateSong(rating.getReviewerID())){
            LinkedListNode<Rating> newRating = new LinkedListNode<Rating>(rating);
            if (ratings == null){
                ratings = newRating;

            }else{
                ratings.append(newRating.getValue());
            }
        }
    }
    public double averageRating() {
        if (ratings == null) {
            return 0.0;
        }
        double total = 0.0;
        int count = 0;

        LinkedListNode<Rating> current = ratings;
        while (current != null) {
            int ratingValue = current.getValue().getRating();
            if (ratingValue != -1) {
                total += ratingValue;
                count++;
            }
            current = current.getNext();
        }
        if (count == 0) {
            return 0.0;
        } else {
            return total / count;
        }
    }
    public boolean didReviewerRateSong(String reviewerID){

        LinkedListNode<Rating> current = ratings;
        LinkedListNode<Rating> previous = null;
        while(current != null){
            if (current.getValue().getReviewerID().equals(reviewerID)){
                return true;

            }
            if (current.getValue().getReviewerID().equals("")){
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
    public void removeRatingByReviewer(Reviewer reviewer){
        LinkedListNode<Rating> current = ratings;
        LinkedListNode<Rating> previous = null;
        while (current != null){
            if (current.getValue().getReviewerID().equals(reviewer.getReviewerID())){
                if (previous == null){
                    ratings = current.getNext();
                }else{
                    previous.setNext(current.getNext());
                }
                break;
            }
            previous = current;
            current = current.getNext();
        }
    }




}
