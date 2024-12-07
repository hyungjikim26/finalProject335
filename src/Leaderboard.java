/**
 * File: Leaderboard.java
 * Authors: Claire O'Brien (obrien9), Hyungji Kim (hyungjikim),
 *          Juwon Lee (juwonlee), Tatiana Rastoskueva (trastoskueva)
 * Purpose: Defines the Leaderboard class, which stores final scores of
 * players and saves them to an external file.
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

    /**
     * Constructor for Leaderboard. Loads the entries list from the file.
     */
    public Leaderboard() {
        entries = new ArrayList<>();
        // load existing leaderboard
        load();
    }

    
    /**
     * 
     * Adds a new score to the leaderboard and saves the updated leaderboard
     * to the text file.
     * 
     * @param name the name of the player
     * @param score the score of the player
     * @pre name is not null, score >= 0
     * @post entries is updated with the new score and saved to the file
     */
    public void addScore(String name, int score) {
        entries.add(new Entry(name, score));
        // will sort the entries in descending order
        Collections.sort(entries);
        // save the updated leaderboard
        save();
    }


    /**
     * Load the entries saved in the external file into the entries list.
     * Creates a new file if it doesn't exist.
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


    /**
     * Save the entries list to the external file leaderboard.txt.
     * 
     * @pre entries is not null
     * @post leaderboard.txt is updated with the entries
     */
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

    
    /**
     * Gets the top 10 scores from the entries list.
     * 
     * @return the top 10 scores from the entries list
     * @pre entries is not null
     * @post the top 10 scores are returned
     */
    public ArrayList<Entry> getTopScore() {
        // return only the top 10 scores
        // handle case where there are less than 10 scores
        if (entries.size() < MAX_ENTRIES) {
            return new ArrayList<>(entries);
        }
        return new ArrayList<>(entries.subList(0, MAX_ENTRIES));
    }


    /**
     * Gets all the scores from the entries list.
     * 
     * @return all the scores from the entries list
     * @pre entries is not null
     * @post all the scores are returned
     */
    public ArrayList<Entry> getAllScores() {
        // return unmodifiable list
        return new ArrayList<>(entries);
    }


    /**
     * Clears the entries list and saves the updated leaderboard to the file.
     * Used for testing purposes.
     * 
     * @pre entries is not null
     * @post entries is cleared and saved to the file
     */
    public void clear() {
        entries.clear();
        save();
    }
}

