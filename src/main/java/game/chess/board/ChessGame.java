package game.chess.board;

import game.chess.logic.AIPlayer;
import game.chess.pawns.PawnColor;
import game.chess.userInterface.GameUI;

/**
 * Created by exil33t on 5/14/2017.
 */
public class ChessGame {

    private boolean aiVsAi = false;
    private boolean firstRound = true;
    private GameBoard gameBoard;
    private PawnColor round = PawnColor.BLACK;
    private AIPlayer aiPlayer;
    private AIPlayer aiPlayer2;
    GameUI ui;


    public ChessGame(GameUI ui) {
        this.ui = ui;
        gameBoard = new GameBoard();
        aiPlayer = new AIPlayer(gameBoard, PawnColor.WHITE);
        aiPlayer2 = new AIPlayer(gameBoard, PawnColor.BLACK);
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public PawnColor getRound() {
        return round;
    }

    public void initGame() throws InterruptedException {
        nextRound(); /* round shall be initialized in the opposite color*/
    }


    public void nextRound() throws InterruptedException {
        if(gameBoard.kingMissing()){
            ui.gameFinished();
        }

        round = round == PawnColor.BLACK ? PawnColor.WHITE : PawnColor.BLACK;
        if(aiPlayer.getPlayerColor() == round){ /* Rend board insta without delay */
            if(aiVsAi && !firstRound){
                firstRound = false;
                Thread.sleep(2000);
            }
            Move nextMove = aiPlayer.calculateNextMove(gameBoard);
            gameBoard.getPawnAtXYPoint(nextMove.getFrom()).moveTo(gameBoard, nextMove.getTo());
            ui.reRendBoard();
            nextRound();
        }
        if(aiPlayer2.getPlayerColor() == round && aiVsAi){
            System.out.println(aiVsAi);
            Thread.sleep(2000);
            Move nextMove = aiPlayer2.calculateNextMove(gameBoard);
            gameBoard.getPawnAtXYPoint(nextMove.getFrom()).moveTo(gameBoard, nextMove.getTo());
            ui.reRendBoard();
            nextRound();
        }

    }


    public void swapGameMode() throws InterruptedException {
        gameBoard = new GameBoard();
        aiPlayer = new AIPlayer(gameBoard, PawnColor.WHITE);
        aiPlayer2 = new AIPlayer(gameBoard, PawnColor.BLACK);
        aiVsAi = !aiVsAi;
        nextRound();
    }

    public void restart() throws InterruptedException {
        firstRound = true;
        gameBoard = new GameBoard();
        aiPlayer = new AIPlayer(gameBoard, PawnColor.WHITE);
        aiPlayer2 = new AIPlayer(gameBoard, PawnColor.BLACK);
        round = PawnColor.BLACK;
        ui.reRendBoard();
        nextRound();
    }
}