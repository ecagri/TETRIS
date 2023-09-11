package Source_Codes;
import java.awt.*;

public class Tetromino {
    /**
     * Keeps the current shape of the Tetromino.
     */
    private int[][] shape;

    /**
     * Keeps the all the versions of the Tetromino (rotated 90 degree, rotated 180 degree, rotated 270 degree).
     */
    private int[][][] shapes;

    /**
     * Keeps the x position of the Tetromino.
     */
    private int positionX;
    /**
     * Keeps the y position of the Tetromino.
     */
    private int positionY;
    /**
     * Keeps the index of the current shape.
     */
    private int current_shape = 3;

    /**
     * Keeps the color of the Tetromino.
     */
    private final Color color;

    /**
     * Constructor for Tetromino.
     * @param shape Gets a 2D integer array as shape of the Tetromino.
     * @param color Gets a color as color of the Tetromino.
     */
    public Tetromino(int[][] shape, Color color){
        this.shape = shape;
        this.color = color;
        this.positionX = (10 - getWidth()) / 2; /* Setting start position x. */
        this.positionY = -getHeight(); /* Setting start position y. */
        initialize(); /* Initialize all the versions of the Tetromino. */
    }

    /**
     * Initialize all the versions of the Tetromino.
     */
    private void initialize(){
        shapes = new int[4][][]; /* Creates a 3D array. */

        for(int i = 0; i < 4; i++){ /* Fill the array with 90 degree rotated version of previous shape. */
            shapes[i] = new int[shape[0].length][shape.length];
            for(int y = 0; y < shape[0].length; y++){
                for(int x = 0; x < shape.length; x++){
                    shapes[i][y][x] = shape[shape.length - x - 1][y];
                }
            }
            shape = shapes[i];
        }
    }

    /**
     * Gets the color of the Tetromino.
     * @return Returns the Tetromino color as Color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Gets the shape of the Tetromino.
     * @return Returns a 2D array as shape of the Tetromino.
     */
    public int[][] getShape() {
        return shape;
    }

    /**
     * Gets the width of the shape.
     * @return Returns an integer as width of the shape.
     */
    public int getWidth() {
        return shape[0].length;
    }
    /**
     * Gets the height of the shape.
     * @return Returns an integer as height of the shape.
     */
    public int getHeight() {
        return shape.length;
    }

    /**
     * Gets the x position of the Tetromino.
     * @return Returns an integer as x position of the shape.
     */
    public int getPositionX() {
        return positionX;
    }
    /**
     * Gets the y position of the Tetromino.
     * @return Returns an integer as y position of the shape.
     */
    public int getPositionY() {
        return positionY;
    }

    /**
     * Moves the Tetromino 1 block down.
     */
    public void moveDown(){
        positionY++;
    }
    /**
     * Moves the Tetromino 1 block right.
     */
    public void moveRight(){
        positionX++;
    }
    /**
     * Moves the Tetromino 1 block left.
     */
    public void moveLeft(){
        positionX--;
    }
    /**
     * Rotates the Tetromino 90 degree right.
     */
    public void rotate() {
        current_shape++;
        if (current_shape == 4) current_shape = 0;
        shape = shapes[current_shape];
    }

    /**
     * Rotates the Tetromino 90 degree left.
     */
    public void rotateBackward(){
        current_shape--;
        if(current_shape == -1) current_shape = 3;
        shape = shapes[current_shape];
    }
}
