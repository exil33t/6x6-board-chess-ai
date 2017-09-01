package game.chess.userInterface;

import game.chess.board.ChessGame;
import game.chess.board.GameBoard;
import game.chess.board.XYPoint;
import game.chess.pawns.PawnColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by exil33t on 5/14/2017.
 */
public class GameUI extends JFrame {
    private JFrame mainFrame;
    private JPanel mainPanel;
    private JMenuBar topMenu;
    private JPanel gameBoard;
    private ChessGame game;
    private BlockClickListener clickListen;
    private ArrayList<JButton> currButtons;
    private JMenuItem calculationDepth;
    private JCheckBoxMenuItem aiVSai;
    private JDialog optionPopup;
    private JTextField depthField;


    public GameUI() {
        clickListen = new BlockClickListener();
        clickListen.setGameUI(this);

        makeFrame();
        makePanel();
        game = new ChessGame(this);

        makeGameBoard();
        mainFrame.pack();
        mainFrame.setVisible(true);
        try {
            game.initGame();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void makeGameBoard() {
        gameBoard = new JPanel(new GridLayout(6, 6));
        mainPanel.add(gameBoard, BorderLayout.CENTER);
    }

    private void makePanel() {
        mainPanel = new JPanel(new BorderLayout());
        mainFrame.add(mainPanel);
    }

    private void makeFrame() {
        mainFrame = new JFrame("Chess");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(800, 800));
    }



    public void rendBoard(GameBoard gameBoard) {
        this.gameBoard.removeAll();

        currButtons = gameBoard.getBlocksAsJButtons();
        for (JButton bt : currButtons) {
            bt.addMouseListener(clickListen);
            this.gameBoard.add(bt);
        }

        this.mainPanel.validate();
    }

    public GameBoard getGameBoard() {
        return this.game.getGameBoard();
    }


    public ChessGame getGame() {
        return game;
    }

    public PawnColor getRound() {
        return game.getRound();
    }

    public void reRendBoard() {
        rendBoard(this.game.getGameBoard());
    }

    public void setBlockGray(XYPoint blockGray) {
        currButtons.get(blockGray.getX() * 6 + blockGray.getY()).setBackground(Color.LIGHT_GRAY);
    }

    public void gameFinished() throws InterruptedException {
        Thread.sleep(1000000000);
    }
}
