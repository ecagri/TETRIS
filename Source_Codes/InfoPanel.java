package Source_Codes;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InfoPanel extends JPanel {

    TetrisPanel tetrisPanel;
    private JLabel score;
    private JLabel level;
    JLabel failed;
    private Tetromino tetromino;

    public InfoPanel(TetrisPanel tetrisPanel){
        this.tetrisPanel = tetrisPanel;
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(Color.white);
        this.setLayout(null);

        JLabel scoreText = new JLabel("YOUR SCORE");
        scoreText.setFont(new Font("Calibri", Font.BOLD, 24));
        scoreText.setBounds(0, 0, 150, 30);
        scoreText.setHorizontalAlignment(JLabel.CENTER);

        score = new JLabel("" + tetrisPanel.getScore());
        score.setFont(new Font("Calibri", Font.BOLD, 24));
        score.setBounds(0, 30, 150, 30);
        score.setHorizontalAlignment(JLabel.CENTER);

        JLabel levelText = new JLabel("LEVEL");
        levelText.setFont(new Font("Calibri", Font.BOLD, 24));
        levelText.setBounds(0, 60, 150, 30);
        levelText.setHorizontalAlignment(JLabel.CENTER);

        level = new JLabel("" + tetrisPanel.getLevel());
        level.setFont(new Font("Calibri", Font.BOLD, 24));
        level.setBounds(0, 90, 150, 30);
        level.setHorizontalAlignment(JLabel.CENTER);

        JLabel nextTetrominoText = new JLabel("NEXT");
        nextTetrominoText.setFont(new Font("Calibri", Font.BOLD, 24));
        nextTetrominoText.setBounds(0, 120, 150, 30);
        nextTetrominoText.setHorizontalAlignment(JLabel.CENTER);

        failed = new JLabel("YOU FAILED!");
        failed.setFont(new Font("Calibri", Font.BOLD, 28));
        failed.setBounds(0, 300, 150, 30);
        failed.setForeground(Color.red);
        failed.setHorizontalAlignment(JLabel.CENTER);
        failed.setVisible(false);

        JButton tryAgain = new JButton("TRY AGAIN");
        tryAgain.setFont(new Font("Calibri", Font.BOLD, 24));
        tryAgain.setBounds(0, 380, 150, 30);
        tryAgain.setForeground(new Color(78, 164,  44));
        tryAgain.setBackground(Color.lightGray);
        tryAgain.setHorizontalAlignment(JLabel.CENTER);
        tryAgain.setVerticalAlignment(JLabel.TOP);
        tryAgain.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tetrisPanel.reset();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


        JButton stopButton = new JButton("STOP");
        stopButton.setFont(new Font("Calibri", Font.BOLD, 24));
        stopButton.setBounds(0, 420, 150, 30);
        stopButton.setForeground(Color.red);
        stopButton.setBackground(Color.lightGray);
        stopButton.setHorizontalAlignment(JLabel.CENTER);
        stopButton.setVerticalAlignment(JLabel.TOP);
        stopButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(stopButton.getText().compareTo("STOP") == 0){
                    tetrisPanel.setStop(true);
                    stopButton.setText("CONTINUE");
                }else{
                    tetrisPanel.setStop(false);
                    stopButton.setText("STOP");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.add(scoreText);
        this.add(score);
        this.add(levelText);
        this.add(level);
        this.add(nextTetrominoText);
        this.add(failed);
        this.add(tryAgain);
        this.add(stopButton);
        tetromino = tetrisPanel.getNextTetromino();
    }

    public void updateScore(){
        tetromino = tetrisPanel.getNextTetromino();
        score.setText("" + tetrisPanel.getScore());
        level.setText("" + tetrisPanel.getLevel());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawLine(0, 55, this.getWidth(), 55);
        g.drawLine(0, 115, this.getWidth(), 115);

        for(int i = 0; i < tetromino.getHeight(); i++){
            for(int j = 0; j < tetromino.getWidth(); j++){
                if(tetromino.getShape()[i][j] == 1){
                    g.setColor(tetromino.getColor());
                    g.fillRect((j * 30) + ((this.getWidth() - (tetromino.getWidth() * 30)) / 2), (i * 30) + 150 , 30, 30);
                    g.setColor(Color.black);
                    g.drawRect((j * 30) + ((this.getWidth() - (tetromino.getWidth() * 30)) / 2), (i * 30) + 150, 30, 30);
                }
            }
        }
    }

    public void setFailed(boolean fail){
        failed.setVisible(fail);
    }
}
