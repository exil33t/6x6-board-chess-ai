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
        makeMenu();
//        rendBoard(this.game.getGameBoard());
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

    private void makeMenu() {
        topMenu = new JMenuBar();
        JMenu game = new JMenu("Game");
        JMenuItem newGame = new JMenuItem("New Game");
        game.add(newGame);
        JMenu options = new JMenu("Options");
        aiVSai = new JCheckBoxMenuItem("Ai vs Ai");
        calculationDepth = new JMenuItem("Calculation depth");
        calculationDepth.addActionListener((e)-> {
                optionPopup.setVisible(true);
        });


        aiVSai.addActionListener((ActionEvent e) ->{

            try {
                this.getGame().swapGameMode();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

        });

//        options.add(aiVSai);
        options.add(calculationDepth);


        topMenu.add(game);
        topMenu.add(options);
        optionPopup = new JDialog();
        optionPopup.setTitle("Depth");
        optionPopup.setPreferredSize(new Dimension(200,70));
        optionPopup.pack();
        depthField = new JTextField(10);
        optionPopup.add(depthField);


        mainPanel.add(topMenu, BorderLayout.NORTH);
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
}
