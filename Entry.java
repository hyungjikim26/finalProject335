/**
 * File: Entry.java
 * Authors: Claire O'Brien (obrien9), Hyungji Kim (hyungjikim),
 *          Juwon Lee (juwonlee), Tatiana Rastoskueva (trastoskueva)
 * Purpose: Defines the Entry class that represents a player's score and name
 *          in the leaderboard.
 * 
 */

public class Entry implements Comparable<Entry> {
    private final String name;
    private final int score;


    /**
     * Constructor for the Entry class.
     * 
     * @param name  the name of the player
     * @param score the score of the player
     * @pre name != null && score >= 0
     */
    public Entry(String name, int score) {
        this.name = name;
        this.score = score;
    }


    /**
     * @return the name of the player
     */
    public String getName() {
        return name;
    }


    /**
     * @return the score of the player
     */
    public int getScore() {
        return score;
    }


    /**
     * Compares two entries based on their scores.
     * @return a negative integer, zero, or a positive integer if this Entry is
     *        less than, equal to, or greater than the specified "other" Entry.
     * @pre other != null
     * @post the entries are compared based on their scores
     */
    @Override
    public int compareTo(Entry other) {
        return Integer.compare(other.score, this.score);
    }


    /**
     * @return a string representation of the Entry object
     * @pre name != null && score >= 0
     */
    @Override
    public String toString() {
        return name + ": " + score;
    }
}

