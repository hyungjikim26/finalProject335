/**
 * File: Leaderboard.java
 * Authors: Claire O'Brien (obrien9), Hyungji Kim (hyungjikim),
 *          Juwon Lee (juwonlee), Tatiana Rastoskueva (trastoskueva)
 * Purpose:
 * 
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Leaderboard {
    // external txt file to store the scores
    private static final String FILENAME = "leaderboard.txt";
    // return only the top 10 scores
    private static final int MAX_ENTRIES = 10;
    private final ArrayList<Entry> entries;

    public Leaderboard() {
        entries = new ArrayList<>();
        load();
    }

    public void addScore(String name, int score) {
        entries.add(new Entry(name, score));
        Collections.sort(entries);
        // save the updated leaderboard
        save();
    }

    /**
     * 
     * @pre leaderboard.txt either doesn't exist or, it exists, is readable, and
     * has the correct format
     * @post entries is populated with the scores from the file
     */
    private void load() {
        // read from the external file leaderboard.txt, populate the entry
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] input = line.split(";");
                entries.add(new Entry(input[0], Integer.parseInt(input[1])));
            }
        } catch (IOException e) {
            // if file doesn't exist already, create new empty entry file
            save();
        }
    }

    private void save() {
        // write to the external file leaderboard.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
            for (Entry entry : entries) {
                writer.write(entry.getName() + ";" + entry.getScore() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving leaderboard: " + e.getMessage());
        }
    }


    public ArrayList<Entry> getTopScore() {
        // return only the top 10 scores
        // handle case where there are less than 10 scores
        if (entries.size() < MAX_ENTRIES) {
            return new ArrayList<>(entries);
        }
        return new ArrayList<>(entries.subList(0, MAX_ENTRIES));
    }

    public ArrayList<Entry> getAllScores() {
        // return unmodifiable list
        return new ArrayList<>(entries);
    }

    public void clear() {
        entries.clear();
        save();
    }
}