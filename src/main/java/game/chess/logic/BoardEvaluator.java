package game.chess.logic;
import game.chess.board.GameBoard;
import game.chess.pawns.Pawn;
import game.chess.pawns.PawnColor;

import java.util.ArrayList;

/**
 * Created by exil33t on 5/26/2017.
 */
public class  BoardEvaluator {
    public Integer evaluateGameBoardValue(GameBoard gameBoard, PawnColor pawnColor){
        int whitePawnValue = 0;
        int blackPawnValue = 0;

        ArrayList<Pawn> blackPawns = gameBoard.getBlack().getPawns();
        ArrayList<Pawn> whitePawns = gameBoard.getWhite().getPawns();


        for(Pawn p: blackPawns){
            blackPawnValue += p.getPawnValue();
        }
        for(Pawn p: whitePawns){
            whitePawnValue += p.getPawnValue();
        }

//        System.out.println("Gameboard black pawns : " + blackPawnValue + " - white pawns: " + whitePawnValue);
        return pawnColor == PawnColor.BLACK ? blackPawnValue - whitePawnValue : whitePawnValue - blackPawnValue;
    }
}
