package game.chess.logic;

import com.rits.cloning.Cloner;
import game.chess.board.GameBoard;
import game.chess.board.Move;
import game.chess.board.XYPoint;
import game.chess.pawns.Pawn;
import game.chess.pawns.PawnColor;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by exil33t on 5/26/2017.
 */
public class AIPlayer {
    private PawnColor playerColor;
    private GameBoard gameBoard;
    private BoardEvaluator boardEvaluator;
    private int aiDepthCounter = 0;

    public AIPlayer(GameBoard gameBoard, PawnColor p) {
        playerColor = p;
        this.gameBoard = gameBoard;
        this.boardEvaluator = new BoardEvaluator();
    }

    public int getAiDepth() {
        return aiDepth;
    }

    int aiDepth = 6;

    public AIPlayer(PawnColor AIColor) {
        playerColor = AIColor;
    }



    public Move calculateNextMove(GameBoard gameBoard) {
        Cloner c = new Cloner();
        GameBoard hypotheticalGameboard = c.deepClone(gameBoard);
        aiDepthCounter = 0;
        Move ownBestMove = getOwnBestMove(hypotheticalGameboard);
        return ownBestMove;
    }


    public PawnColor getPlayerColor() {
        return playerColor;
    }


    public Move getOpponentBestMove(GameBoard gameBoard){

        Cloner c = new Cloner();
        GameBoard gameBoard1 =  c.deepClone(gameBoard);
        ArrayList<Pawn> pawns = (getOppositeColor() == PawnColor.BLACK ? gameBoard1.getBlack().getPawns() : gameBoard1.getWhite().getPawns());
        ArrayList<Move> moves = new ArrayList<>();


        for(Pawn p: pawns){
            for(XYPoint point: p.calcPossibleMoves(gameBoard1)){
                if(point != null){
                    XYPoint from = new XYPoint(p.getXyPoint().getX(), p.getXyPoint().getY());
                    p.moveTo(gameBoard1, point);
                    moves.add(new Move(from,
                            new XYPoint(p.getXyPoint().getX(),
                            p.getY()),
                            boardEvaluator.evaluateGameBoardValue(gameBoard1, getOppositeColor())));
                    p.moveToPoint(from);
                    gameBoard1 = c.deepClone(gameBoard);
                }
            }
        }



        return pickRandomMove(trimMoves(moves));
    }

    private ArrayList<Move> calculateWorthOfMovesForOpponent(ArrayList<Move> moves, GameBoard gameBoard) {
        Cloner c = new Cloner();
        GameBoard gameBoard1 = c.deepClone(gameBoard);


        for(Move m : moves){
            gameBoard1.executeMove(m);
            Move oppBestMove = getOpponentBestMove(gameBoard1);

            m.setValueForOpponent(oppBestMove.getValue());
            gameBoard1 = c.deepClone(gameBoard);

        }
        return moves;
    }


    public  Move getOwnBestMove(GameBoard gameBoard) {
        Cloner c = new Cloner();
        GameBoard gameBoard1 = c.deepClone(gameBoard);
        ArrayList<Pawn> pawns = (playerColor == PawnColor.BLACK ? gameBoard1.getBlack().getPawns() : gameBoard1.getWhite().getPawns());
        ArrayList<Move> moves = new ArrayList<>();


        for(Pawn p: pawns){
            for(XYPoint point: p.calcPossibleMoves(gameBoard1)){
                if(point != null){
                    XYPoint from = new XYPoint(p.getXyPoint().getX(), p.getXyPoint().getY());
                    p.moveTo(gameBoard1, point);
                    moves.add(new Move(from,
                            new XYPoint(p.getXyPoint().getX(),
                                    p.getY()),
                            boardEvaluator.evaluateGameBoardValue(gameBoard1, playerColor)));
                    p.moveToPoint(from);
                    gameBoard1 = c.deepClone(gameBoard);
                    }
                }
            }




        moves = calculateWorthOfMovesForOpponent(moves, gameBoard1);

        for(Move m : moves){
            System.out.println("Move " + " value: " + m.getValue() + " value for opp: " + m.getValueForOpponent() + " final: " + m.getValue());
        }


        return pickRandomMove(trimMoves(moves));

    }



    private Move pickRandomMove(ArrayList<Move> moves) {
        int randomNum = ThreadLocalRandom.current().nextInt(0, moves.size() + 0);
        return moves.get(randomNum);
    }

    private ArrayList<Move> trimMoves(ArrayList<Move> moves) {
        if(!moves.isEmpty()){
            int top = moves.get(0).getValue();

            for(Move m: moves){
                if(m.getValue() > top){
                    top = m.getValue();
                }
            }

            ArrayList<Move> newMoves = new ArrayList<>();

            for(Move m: moves){
                if(m.getValue()  == top){
                    newMoves.add(m);
                }
            }
            return newMoves;

        }
        return null;
    }

    public PawnColor getOppositeColor() {
        return playerColor == PawnColor.BLACK ? PawnColor.WHITE : PawnColor.BLACK;
    }
}
