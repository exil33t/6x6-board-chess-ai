package game.chess.pawns;

import game.chess.board.GameBoard;
import game.chess.board.XYPoint;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by exil33t on 5/14/2017.
 */
public class Queen extends Pawn  implements Serializable {
    public Queen(XYPoint xyPoint, PawnColor pawnCol) {
        super(xyPoint, pawnCol);
        pawnValue = 9;
    }

    public ArrayList<XYPoint> calcPossibleMoves(GameBoard gameBoard){
        ArrayList<XYPoint> moves = new ArrayList<>();
        moves.addAll(getDiagonalLanes(gameBoard, getXyPoint()));
        moves.addAll(getHorizontalLane(gameBoard, getXyPoint()));
        return moves;
    }

    private ArrayList<XYPoint> getDiagonalLanes(GameBoard gameBoard, XYPoint xyPoint){
        ArrayList<XYPoint> moves = new ArrayList<>();
        /* Case of black pawns */
            moves.addAll(xyPoint.getLeftDiagonalLane(this, gameBoard));
            moves.addAll(xyPoint.getRightDiagonalLane(this, gameBoard));

        return moves;
    }

    private ArrayList<XYPoint> getHorizontalLane(GameBoard gameBoard, XYPoint xyPoint) {
        ArrayList<XYPoint> moves = new ArrayList<>();
        moves.addAll(xyPoint.getHorizontalLane(this, gameBoard));
        moves.addAll(xyPoint.getVerticalLane(this, gameBoard));
        return moves;
    }


    public boolean canMoveTo(GameBoard gameBoard, XYPoint xyPoint) {
        ArrayList<XYPoint> moves = calcPossibleMoves(gameBoard);

        for (XYPoint move : moves) {
            if (move != null) {
                if (move.pointMatchesWith(xyPoint)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* moves this pawn to certain point, if opponent is detected in point is eliminated. */
    public boolean moveTo(GameBoard gameBoard, XYPoint xyPoint) {
        if (this.canMoveTo(gameBoard, xyPoint)) {
            if (gameBoard.thereIsOpponentPawnInXYPoint(this, xyPoint)) {  // in case there is an opponent
                this.eliminatePawn(gameBoard, xyPoint);
                this.moveToPoint(xyPoint);
                return true;
            } else {
                this.moveToPoint(xyPoint);
                return true;
            }
        } else {
            return false;
        }
    }


    public void eliminatePawn(GameBoard gameBoard, XYPoint xyPoint) {
        gameBoard.removePawnAtXYPoint(xyPoint);
    }

    public void moveToPoint(XYPoint to) {
        this.getXyPoint().setX(to.getX());
        this.getXyPoint().setY(to.getY());
    }

    public int getX() {
        return this.getXyPoint().getX();
    }

    public int getY() {
        return this.getXyPoint().getY();
    }


}
