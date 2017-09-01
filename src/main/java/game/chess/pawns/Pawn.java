package game.chess.pawns;

import game.chess.board.GameBoard;
import game.chess.board.XYPoint;

import java.util.ArrayList;

/**
 * Created by exil33t on 5/14/2017.
 */
public class Pawn {
    private XYPoint xyPoint;
    private PawnColor pawnColor;
    protected Integer pawnValue;

    public Pawn(XYPoint xyPoint, PawnColor pawnCol) {
        this.xyPoint = xyPoint;
        pawnColor = pawnCol;
    }

    public Pawn() {
    }

    public ArrayList<XYPoint> calcPossibleMoves(GameBoard gameBoard){
        return null;
    }

    public int getX() {
        return this.getXyPoint().getX();
    }

    public int getY() {
        return this.getXyPoint().getY();
    }

    public boolean moveTo(GameBoard gameBoard, XYPoint xyPoint) {
        return false;
    }

    public boolean canMoveTo(GameBoard gameBoard, XYPoint xyPoint) {
        return false;
    }


    public PawnColor getPawnColor() {
        return pawnColor;
    }


    public XYPoint getXyPoint() {
        return xyPoint;
    }

    public void setXyPoint(XYPoint xyPoint) {
        this.xyPoint = xyPoint;
    }

    public boolean sameColorAs(Pawn pawnAtXYPoint) {
        if (pawnAtXYPoint != null) {
            if (getPawnColor() == pawnAtXYPoint.getPawnColor()) {
                return true;
            }
        } else {
            return false;
        }

        return false;
    }

    public boolean isAtIndex(XYPoint xyPoint) {
        return xyPoint.getX() == getX() && xyPoint.getY() == getY() ? true : false;
    }

    public void moveToPoint(XYPoint to){

    }

    public Integer getPawnValue() {
        return pawnValue;
    }

    public boolean capturesPointAt(GameBoard gameBoard1, XYPoint point) {
        if(!gameBoard1.blockEmpty(point)){
            if(canMoveTo(gameBoard1, point) && !gameBoard1.getPawnAtXYPoint(point).sameColorAs(this)){
                return true;
            }
        }
        return false;
    }
}
