/**
 * File: Entry.java
 * Authors: Claire O'Brien (obrien9), Hyungji Kim (hyungjikim),
 *          Juwon Lee (juwonlee), Tatiana Rastoskueva (trastoskueva)
 * Purpose:
 * 
 */

public class Entry implements Comparable<Entry> {
    private final String name;
    private final int score;

    public Entry(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(Entry other) {
        return Integer.compare(other.score, this.score);
    }

    @Override
    public String toString() {
        return name + ": " + score;
    }
}