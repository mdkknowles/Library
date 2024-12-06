import java.util.Random;

public class Wordsearch {
    private static final int GRID_SIZE = 16;
    // Direction multiplier array starting with right and going counter-clockwise.
    private static final int[] DIRECTION_MODIFIER = { 1,0, 1,1, 0,1, -1,1, -1,0, -1,-1, 0,-1, 1,-1 };
    // This random number generated object is declared once and shared by all methods of the object.
    private static final Random rand = new Random();
    // The wordsearch grid.
    private final char[][] grid;

    public Wordsearch(String[] words) {
        // Create grid array of chars with dimensions GRID_SIZE.
        grid = new char[GRID_SIZE][GRID_SIZE];

        // Fill this array with '.' as placeholders.
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = '.';
            }
        }

        // For each word passed as an argument, place into the grid array.
        for (String word : words) {
            boolean placed = false;
            while (!placed) {
                // Generate a random x,y coordinate and cardinal direction.
                int row = rand.nextInt(GRID_SIZE);
                int col = rand.nextInt(GRID_SIZE);
                int direction = rand.nextInt(8);
                // The word is only placed if the canPlaceWord() method returns true.
                // New coordinates and directions are generated until the word is able to be placed.
                if (canPlaceWord(grid, word, row, col, direction)) {
                    placeWord(grid, word, row, col, direction);
                    placed = true;
                }
            }
        }
    }
    
    // This method takes the grid, word, row, column, and direction and uses a for loop to place the chars.
    private void placeWord(char[][] grid, String word, int row, int col, int direction) {
        for (int i = 0; i < word.length(); i++) {
            // The direction modifier is used to calculate the offset to place the char.
            int r = row + i * DIRECTION_MODIFIER[2 * direction];
            int c = col + i * DIRECTION_MODIFIER[2 * direction + 1];
            grid[r][c] = word.charAt(i);
        }
    }
    // Takes the inputs of placeWord and returns a boolean indicated whether the word can be placed in that position.
    private boolean canPlaceWord(char[][] grid, String word, int row, int col, int direction) {
        // This for loop iterates over every char of the String word. It calculates the position of said char.
        for (int i = 0; i < word.length(); i++) {
            int r = row + i * DIRECTION_MODIFIER[2 * direction];
            int c = col + i * DIRECTION_MODIFIER[2 * direction + 1];
            // This if statement checks whether the char's coordinates are out of bounds and whether it matches
            // the non placeholder value, if any.
            if ( r < 0 || c < 0 || r >= GRID_SIZE || c >= GRID_SIZE || (grid[r][c] != '.' && grid[r][c] != word.charAt(i)) ) {
                return false;
            }
        }
        return true;
    }
    // Takes a boolean indicated whether the solutions should be shown.
    // If true, it prints the grid as is.
    // If false, it prints the grid but replaces the '.' with a random capital english letter.
    public void print(boolean show) {
        for (char[] row : this.grid) {
            for (char col : row) {
                if ( show == false && col == '.' ) {
                    System.out.print((char)('A' + rand.nextInt(26))+" ");
                } else {
                    System.out.print(col+" ");
                }
            }
            System.out.print("\n");
        }
    }
}