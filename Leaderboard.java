import java.util.ArrayList;
import java.util.Collections;

public class Leaderboard {
    // external txt file to store the scores
    private static final String FILENAME = "leaderboard.txt";
    // return only the top 10 scores
    private static final int MAX_ENTRIES = 10;
    private ArrayList<Entry> entries;

    public Leaderboard() {
        entries = new ArrayList<>();
        load();
    }

    public void addScore(String name, int score) {
        entries.add(new Entry(name, score));
        // sort in descending order
        Collections.sort(entries, Collections.reverseOrder());
        // save the updated leaderboard
        save();
    }

    private void load() {

    }

    private void save() {

    }

    public ArrayList<Entry> getTopScore() {
        // return only the top 10 scores
        return new ArrayList<>(entries.subList(0, MAX_ENTRIES));
    }

    public ArrayList<Entry> getAllScores() {
        // return unmodifiable list
        return new ArrayList<>(entries);
    }


}