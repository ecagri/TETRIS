package Source_Codes;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {

    public static void main(String[] args) {
        var gui = new GUI();
    }
    public GUI(){
        TetrisPanel tetrisPanel = new TetrisPanel();
        InfoPanel infoPanel = new InfoPanel(tetrisPanel);
        infoPanel.setBounds(400, 50, 150, 450);
        tetrisPanel.setBounds(50, 50, 300, 450);
        ImageIcon imageIcon = new ImageIcon("icon.png");
        this.setTitle("TETRIS");
        this.setIconImage(imageIcon.getImage());
        this.setLayout(null);
        this.setResizable(false);
        this.add(tetrisPanel);
        this.add(infoPanel);
        this.setBackground(Color.white);
        this.setVisible(true);
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        new TetrisThread(tetrisPanel, infoPanel).start();
    }
}
