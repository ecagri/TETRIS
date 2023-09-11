package Source_Codes;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class TetrisPanel extends JPanel {

    /**
     * Number of rows in the tetris game.
     */
    private final int number_of_rows = 15;
    /**
     * Number of columns in the tetris game.
     */
    private final int number_of_cols = 10;
    /**
     * Keeps the number of Tetromino.
     */
    private int number_of_tetromino = 0;
    /**
     * Keeps the current score of the player.
     */
    private int score = 0;
    /**
     * Keeps the player failed or not.
     */
    private boolean fail = false;
    /** Check whether Tetromino reached the bottom. */
    private boolean goBottom = false;
    /** Check whether stop button is pressed. */
    private boolean stop = false;
    /**
     * Keeps the tetris board.
     */
    private Color[][] board;
    /**
     * Keeps the current Tetromino.
     */
    Tetromino tetromino;
    /**
     * Keeps the next Tetromino.
     */
    Tetromino nextTetromino;

    /**
     * Constructor for tetris panel.
     */
    public TetrisPanel(){
        this.setBorder(BorderFactory.createLineBorder(Color.black));

        this.addKeyListener(new KeyListener() { /* Adds key listener to move the Tetromino. */
            @Override
            public void keyTyped(KeyEvent e) {

            }
            @Override
            public void keyPressed(KeyEvent e) {
                if(fail) return;
                if(e.getKeyCode() == 39){ /* If right arrow is pressed. */
                    blockMoveRight();
                }else if(e.getKeyCode() == 37){ /* If left arrow is pressed. */
                    blockMoveLeft();
                }else if(e.getKeyCode() == 32){ /* If space is pressed. */
                    blockRotate();
                }else if(e.getKeyCode() == 40){ /* If down arrow is pressed. */
                    blockGoBottom();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        reset();
    }

    /**
     * Resets the tetris panel.
     */
    public void reset(){
        tetromino = null;
        fail = false;
        goBottom = false;
        score = 0;
        number_of_tetromino = 0;
        board = new Color[number_of_rows][number_of_cols];
        for(int i = 0; i < number_of_rows; i++){
            for(int j = 0; j < number_of_cols; j++){
                board[i][j] = Color.white;
            }
        }
        spawn();
    }

    /**
     * Spawns the next Tetromino.
     */
    public void spawn(){
        number_of_tetromino++;
        if(tetromino == null)
            tetromino = new Tetromino(new int[][]{{1, 0}, {1, 0}, {1, 1}}, Color.red);
        else
            tetromino = nextTetromino;


        Random random = new Random();
        int next = random.nextInt(7);
        switch (next) {
            case 0 -> nextTetromino = new Tetromino(new int[][]{{1, 0}, {1, 0}, {1, 1}}, Color.red);
            case 1 -> nextTetromino = new Tetromino(new int[][]{{0, 1}, {0, 1}, {1, 1}}, Color.green);
            case 2 -> nextTetromino = new Tetromino(new int[][]{{0, 1, 1}, {1, 1, 0}}, Color.blue);
            case 3 -> nextTetromino = new Tetromino(new int[][]{{1, 1, 0}, {0, 1, 1}}, Color.orange);
            case 4 -> nextTetromino = new Tetromino(new int[][]{{1}, {1}, {1}, {1}}, Color.pink);
            case 5 -> nextTetromino = new Tetromino(new int[][]{{0, 1, 0}, {1, 1, 1}}, Color.gray);
            case 6 -> nextTetromino = new Tetromino(new int[][]{{1, 1}, {1, 1}}, Color.cyan);
        }

    }

    /**
     * Rotates the Tetromino.
     */
    public void blockRotate(){
        tetromino.rotate();

        boolean contravention = (tetromino.getPositionX() + tetromino.getWidth() > number_of_cols || tetromino.getPositionX() < 0 || tetromino.getPositionY() + tetromino.getHeight() >= number_of_rows || tetromino.getPositionY() < 0);

        for(int i = 0; i < tetromino.getHeight() && !contravention; i++){
            for(int j = 0; j < tetromino.getWidth(); j++){
                if(tetromino.getShape()[i][j] == 1 && tetromino.getPositionY() + i >= 0 && board[tetromino.getPositionY() + i][tetromino.getPositionX() + j] != Color.white){
                    contravention = true;
                }
            }
        }

        if(contravention){
            tetromino.rotateBackward();
        }
        repaint();
    }

    /**
     * Moves the Tetromino right.
     */
    public void blockMoveRight(){
        boolean contravention = (tetromino.getPositionX() + tetromino.getWidth() >= number_of_cols);

        for(int i = 0; i < tetromino.getHeight() && !contravention; i++){
            for(int j = 0; j < tetromino.getWidth(); j++){
                if(tetromino.getShape()[i][j] == 1 && tetromino.getPositionY() + i >= 0 && board[tetromino.getPositionY() + i][tetromino.getPositionX() + j + 1] != Color.white){
                    contravention = true;
                }
            }
        }
        if (!contravention) {
            tetromino.moveRight();
            repaint();
        }
    }
    /**
     * Moves the Tetromino left.
     */
    public void blockMoveLeft(){
        boolean contravention = (tetromino.getPositionX() <= 0);

        for(int i = 0; i < tetromino.getHeight() && !contravention; i++){
            for(int j = 0; j < tetromino.getWidth(); j++){
                if(tetromino.getShape()[i][j] == 1 && tetromino.getPositionY() + i >= 0 && board[tetromino.getPositionY() + i][tetromino.getPositionX() + j - 1] != Color.white){
                    contravention = true;
                }
            }
        }
        if (!contravention) {
            tetromino.moveLeft();
            repaint();
        }
    }
    /**
     * Moves the Tetromino down.
     */
    public void blockMoveDown(){
        if(stop == true) return;
        this.requestFocus();

        boolean contravention = (tetromino.getPositionY() + tetromino.getHeight() == number_of_rows);

        for(int i = 0; i < tetromino.getHeight() && !contravention; i++){
            for(int j = 0; j < tetromino.getWidth(); j++){
                if(tetromino.getShape()[i][j] == 1 && tetromino.getPositionY() + i + 1 >= 0 && board[tetromino.getPositionY() + i + 1][tetromino.getPositionX() + j] != Color.white){
                    contravention = true;
                }
            }
        }
        if (!contravention) {
            tetromino.moveDown();
            repaint();
        }else {
            goBottom = false;
            if(tetromino.getPositionY() < 0){
                fail = true;
                return;
            }
            for(int i = 0; i < tetromino.getHeight(); i++){
                for(int j = 0; j < tetromino.getWidth(); j++){
                    if(tetromino.getShape()[i][j] == 1){
                        board[i + tetromino.getPositionY()][j + tetromino.getPositionX()] = tetromino.getColor();
                    }
                }
            }
            spawn();
        }
        checkLayer();
    }

    /**
     * Moves the Tetromino down until it reaches the bottom.
     */
    public void blockGoBottom(){
        goBottom = true;
        while(goBottom) {
            blockMoveDown();
        }
        blockMoveDown();
    }

    /**
     * Check layers is filled or not. If the layer is already filled, removes the layer.
     */
    private void checkLayer(){
        Color[][] newBoard = new Color[number_of_rows][number_of_cols];
        for(int i = board.length - 1; i > -1; i--){
            boolean layerfull = true;
            for(int j = 0; j < board[0].length && layerfull; j++){
                if(board[i][j] == Color.white){
                    layerfull = false;
                }
            }
            if(layerfull){
                score += 100;
                int counter = 0;
                for (int y = board.length - 1; y > 0; y--) {
                    if (y == i) counter++;
                    System.arraycopy(board[y - counter], 0, newBoard[y], 0, board[0].length);
                }
                for (int x = 0; x < board[0].length; x++) {
                    newBoard[0][x] = Color.white;
                }
                board = newBoard;
            }
        }
    }
    /**
     * Paints the tetris board.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = (this.getWidth()) / number_of_cols;
        int height = (this.getHeight()) / number_of_rows;

        for(int i = 0; i < number_of_rows; i++){
            for(int j = 0; j < number_of_cols; j++){
                if(board[i][j] != Color.white){
                    g.setColor(board[i][j]);
                    g.fillRect((j) * width , (i) * height, width, height);
                    g.setColor(Color.black);
                    g.drawRect(j * width, i * height, width, height);
                }else{
                    //g.setColor(new Color(235, 235, 235));
                    //g.drawRect(j * width, i * height, width, height);
                }
            }
        }

        drawBlock(g, tetromino);

    }

    /**
     * Draw each tetris block.
     * @param g the <code>Graphics</code> object to protect
     * @param tetromino Gets current tetromino to paint.
     */
    private void drawBlock(Graphics g, Tetromino tetromino){
        int width = (this.getWidth()) / number_of_cols;
        int height = (this.getHeight()) / number_of_rows;
        for(int i = 0; i < tetromino.getHeight(); i++){
            for(int j = 0; j < tetromino.getWidth(); j++){
                if(tetromino.getShape()[i][j] == 1){
                    g.setColor(tetromino.getColor());
                    g.fillRect((tetromino.getPositionX() + j) * width , (tetromino.getPositionY() + i) * height, width, height);
                    g.setColor(Color.black);
                    g.drawRect((tetromino.getPositionX() + j) * width, (tetromino.getPositionY() + i) * height, width, height);
                }
            }
        }
    }

    /**
     * Gets the player failed or not.
     * @return Returns a boolean to check player failed or not.
     */
    public boolean isFail() {
        return fail;
    }

    /**
     * Gets the player score.
     * @return Returns an integer as player score.
     */
    public int getScore() {
        return score;
    }
    /**
     * Gets the current level by dividing number of tetrominos by 15.
     * @return Returns the current level as integer.
     */
    public int getLevel(){
        return (number_of_tetromino / 15) + 1;
    }

    /**
     * Gets the next Tetromino to show it Info panel.
     * @return Returns a Tetromino to show.
     */
    public Tetromino getNextTetromino() {
        return nextTetromino;
    }

    /**
     * Stops or continues the Tetromino.
     * @param stop Gets a boolean to stop or continue to move the Tetromino.
     */
    public void setStop(boolean stop){
        this.stop = stop;
    }
}
