package Source_Codes;

public class TetrisThread extends Thread{
    TetrisPanel tetrisPanel;
    InfoPanel infoPanel;
    public TetrisThread(TetrisPanel tetrisPanel, InfoPanel infoPanel){
        this.tetrisPanel = tetrisPanel;
        this.infoPanel = infoPanel;
    }
    @Override
    public void run() {
        try {
            if(!tetrisPanel.isFail()){
                tetrisPanel.blockMoveDown();
                infoPanel.updateScore();
                infoPanel.setFailed(tetrisPanel.isFail());
            }
            Thread.sleep(400 - (5 * tetrisPanel.getLevel()));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        run();
    }
}
