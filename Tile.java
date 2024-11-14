import java.util.Random;

public class Tile {

    private int value;

    // value is assigned 2 with a 90% probability 
    // and 4 with a 10% probability
    public Tile(){
        Random random = new Random();

        double probability = random.nextDouble(); 

        if (probability < 0.1){
            value = 4;
        }
        else{
            value = 2;
        }
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