import java.util.ArrayList;
import java.util.Random;

public class Board {
    private final Random rand = new Random();
    // Board will have a 2-D array of Tiles, 4x4 by default
    private final int size;
    private final Tile[][] grid;
    private final double NEW_TILE_PROB = 0.9;
    // CONSIDER: add a counter to keep track of no. of empty/valid tiles


    /**
     * Constructor for the Board class.
     * 
     * @pre numTiles >= 0 && numTiles <= size*size && size > 0
     * @post a new Board object is created
     * @param size the size of the board
     * @param numTiles the number of tiles to place on the board
     * @return void
     */
    public Board(int size, int numTiles) {
        // create board with the given size
        this.size = size;
        grid = new Tile[size][size];
        // initialize the board with two tiles
        initializeBoard(numTiles);
    }


    /**
     * Default constructor for the Board class.
     * 
     * @pre none
     * @post a new Board object is created with default size 4x4, and two tiles
     * @param none
     * @return void
     */
    public Board() {
        this(4, 2);
    }
    
    /**
     * @return size of the board
     */
    public int getSize() {
    	return size;
    }
    
    /**
     * @param row		- row index of the tile
     * @param column	- column index of the tile
     * @return			- value of a tile at an index
     */
    public int getValue(int row, int column) {
    	return grid[row][column].getValue();
    }
    
    /**
     * Returns a copy of the grid instance
     * 
     * @pre all cells in grid are not null
     * @post return deep copy of the instance variable grid
     * @return copy of the grid instance
     */
    public Tile[][] getGrid() {
    	Tile[][] gridCopy = new Tile[size][size];
    	for (int row = 0; row < size; row++) {
    		for (int col = 0; col < size; col++) {
    			gridCopy[row][col] = new Tile(grid[row][col].getValue());
    		}
    	}
    	return gridCopy;
    }
    

    /**
     * Initialize the board by placing n empty tiles in random locations.
     * 
     * @pre the grid has null values in each cell
     * @post the board has numTiles tiles placed in random locations
     * @param numTiles the number of tiles to place on the board
     * @return void
     */
    private void initializeBoard(int numTiles) {
    	for (int row = 0; row < size; row++) {
    		for (int column = 0; column < size; column++) {
    			grid[row][column] = new Tile();
    		}
    	}
        for (int i = 0; i < numTiles; i++) {
            addRandomTile();
        }
    }


    // CONSIDER: could utilize counter to check if the board is full
    // that way, we don't have to iterate through the entire board
    // which could be expensive for larger boards
    // For 4x4 board, it should be fine
    /**
     * Checks if the board is full.
     * @pre grid doesn't have null cells
     * @post returns whether grid is full or not
     * @return true if the board is full, false otherwise
     */
    private boolean isFull() {
        // iterate through the board and check if any tile is empty
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (grid[row][col].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Add a random tile to the board.
     * If board is full, tile is not added 
     * 
     * @pre grid doesn't have empty cells
     * @post the board has one more tile if it is not full
     * @return void
     */
    private void addRandomTile() {
        int location;
        ArrayList<Integer> emptyTiles = new ArrayList<Integer>();
        // find a random empty spot
        for (int i = 0; i < size; i++) {
        	for (int j = 0; j < size; j++) {
        		if (grid[i][j].isEmpty()) {
        			location = i * size + j;
        			emptyTiles.add(location);
        		}
        	}
        }
        if (emptyTiles.isEmpty())
        	return;
        int randIndex = rand.nextInt(emptyTiles.size());

        int row = emptyTiles.get(randIndex) / size;
        int col = emptyTiles.get(randIndex) % size;
        // place new tile in the empty spot with a NEW_TILE_PROBability of
        // tile with value 2, and 1-NEW_TILE_PROB of tile with value 4
        int val = rand.nextDouble() < NEW_TILE_PROB ? 2 : 4;
        grid[row][col] = new Tile(val);
    }
    
    /**
     * All nonempty tiles are moved up and merged if two 
     * identical tiles are vertically adjacent or have 
     * only empty tiles between them.
     * 
     */
    public void moveUp() {
    	int tempRow;
    	for (int row = 1; row < size; row++) {
    		for (int column = 0; column < size; column++) {
    			if (grid[row][column].isEmpty())
    				continue;
    			tempRow = row;
    			while (tempRow > 0) {
    				if (grid[tempRow-1][column].isEmpty()) {
    					exchangeTiles(tempRow-1, column, tempRow, column);
    				}
    				else if (grid[tempRow-1][column].equals(grid[tempRow][column])) {
    					grid[tempRow-1][column].merge(grid[tempRow][column]);
    					break;
    				}
    				tempRow--;
    			}
    		}
    	}
    }
    
    /**
     * Exchanges tiles at two different locations
     * 
     * @param rowFirst 		- row index of the first tile
     * @param columnFirst	- column ndex of the first tile
     * @param rowSecond		- row index of the second tile
     * @param columnSecond	- column index of the second tile
     */
    private void exchangeTiles(int rowFirst, int columnFirst, int rowSecond, int columnSecond) {
    	Tile tile = grid[rowFirst][columnFirst];
    	grid[rowFirst][columnFirst] =  grid[rowSecond][columnSecond];
    	grid[rowSecond][columnSecond] = tile;
    }
    
    // temporary method for tests only
    public void addTile(int row, int column, int value) {
		grid[row][column] = new Tile(value);
    }
    // for tests
    /**
     * @return text version of the Board object
     */
    public String toString() {
    	String result = "";
    	String tile;
    	for (int i = 0; i < size; i++) {
    		for (int j = 0; j < size; j++) {
    			if (grid[i][j].isEmpty())
    				tile = "*";
    			else
    				tile = Integer.toString(grid[i][j].getValue());
    			result += String.format("%-8s", tile);
    		}
    		result += "\n";
    	}
    	return result;
    }
}