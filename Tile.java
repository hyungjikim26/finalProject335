
public class Tile {
    private int value ;

    public Tile(int value) {
        this.value = value;
    }

    public Tile() {
        this(0);
    }

    // if other has the same value, update this.value
    public void merge(Tile other){
        if (other.getValue() == value){
            value *= 2;
        }
    }

    public int getValue(){
        return value;
    }
}