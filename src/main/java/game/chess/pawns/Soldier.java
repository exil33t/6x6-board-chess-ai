package game.chess.pawns;

import game.chess.board.GameBoard;
import game.chess.board.XYPoint;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by exil33t on 5/14/2017.
 */
public class Soldier extends Pawn  implements Serializable {
    public Soldier(XYPoint xyPoint, PawnColor pawnCol) {
        super(xyPoint, pawnCol);
        pawnValue = 1;
    }

    public ArrayList<XYPoint> calcPossibleMoves(GameBoard gameBoard) {
        ArrayList<XYPoint> moves = new ArrayList<>();

        /* checking if next block is available for moving */
        //TODO: check if next block is final and enable queen if needed
        moves.add(getFrontBlock(gameBoard, getXyPoint()));
        moves.addAll(getRearBlocks(gameBoard, getXyPoint()));
        return moves;
    }

    private ArrayList<XYPoint> getRearBlocks(GameBoard gameBoard, XYPoint xyPoint) {
        ArrayList<XYPoint> moves = new ArrayList<>();
        /* Case of black pawns */
        if (getPawnColor() == PawnColor.BLACK) {
            Pawn leftRearPawn = gameBoard.getPawnAtXYPoint(xyPoint.getLeftRearFrontPointOfBlackPawn());
            Pawn rightRearPawn = gameBoard.getPawnAtXYPoint(xyPoint.getRightRearFrontPointOfBlackPawn());
            if (leftRearPawn != null) {
                if (!leftRearPawn.sameColorAs(gameBoard.getPawnAtXYPoint(xyPoint))) {
                    moves.add(leftRearPawn.getXyPoint());
                }
            }
            if (rightRearPawn != null) {
                if (!rightRearPawn.sameColorAs(gameBoard.getPawnAtXYPoint(xyPoint))) {
                    moves.add(rightRearPawn.getXyPoint());
                }
            }
        }
        /* Case of white pawns */
        if (getPawnColor() == PawnColor.WHITE) {
            Pawn leftRearPawn = gameBoard.getPawnAtXYPoint(xyPoint.getLeftRearFrontPointOfWhitePawn());
            Pawn rightRearPawn = gameBoard.getPawnAtXYPoint(xyPoint.getRightRearFrontPointOfWhitePawn());
            if (leftRearPawn != null) {
                if (!leftRearPawn.sameColorAs(gameBoard.getPawnAtXYPoint(xyPoint))) {
                    moves.add(leftRearPawn.getXyPoint());
                }
            }
            if (rightRearPawn != null) {
                if (!rightRearPawn.sameColorAs(gameBoard.getPawnAtXYPoint(xyPoint))) {
                    moves.add(rightRearPawn.getXyPoint());
                }
            }
        }
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

    private XYPoint getFrontBlock(GameBoard gameBoard, XYPoint xyPoint) {

        if (getPawnColor() == PawnColor.BLACK) {
            if (gameBoard.blockEmpty(xyPoint.getFrontBlockOfBlackPawn())) {
                return xyPoint.getFrontBlockOfBlackPawn();
            } else {
                return null;
            }
        }else{
            if (gameBoard.blockEmpty(xyPoint.getFrontBlockOfWhitePawn())) {
                return xyPoint.getFrontBlockOfWhitePawn();
            } else {
                return null;
            }
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
