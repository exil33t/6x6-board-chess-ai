package game.chess.board;

import game.chess.pawns.Pawn;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by exil33t on 5/16/2017.
 */
public class XYPoint  implements Serializable {

    int x;
    int y;

    public XYPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public XYPoint() {

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public XYPoint getLeftRearBackPointOfWhitePawn() {
        return new XYPoint(x - 1, y + 1);
    }

    public XYPoint getRightRearBackPointOfWhitePawn() {
        return new XYPoint(x - 1, y - 1);
    }

    public XYPoint getLeftRearFrontPointOfWhitePawn() {
        return new XYPoint(x + 1, y - 1);
    }

    public XYPoint getRightRearFrontPointOfWhitePawn() {
        return new XYPoint(x + 1, y + 1);
    }

    public XYPoint getLeftRearFrontPointOfBlackPawn() {
        return new XYPoint(x - 1, y + 1);
    }

    public XYPoint getRightRearFrontPointOfBlackPawn() {
        return new XYPoint(x - 1, y - 1);
    }

    public XYPoint getLeftRearBackPointOfBlackPawn() {
        return new XYPoint(x + 1, y + 1);
    }

    public XYPoint getRightRearBackPointOfBlackPawn() {
        return new XYPoint(x + 1, y - 1);
    }

    public XYPoint getFrontBlockOfWhitePawn() {
        return new XYPoint(x + 1, y);
    }

    public XYPoint getFrontBlockOfBlackPawn() {
        return new XYPoint(x - 1, y);
    }

    public XYPoint getLeftBlockOfBlackPawn() {
        return new XYPoint(x, y - 1);
    }

    public XYPoint getRightBlockOfBlackPawn() {
        return new XYPoint(x, y + 1);
    }

    public boolean pointMatchesWith(XYPoint xyPoint) {
        return xyPoint.getX() == x && xyPoint.getY() == y ? true : false;
    }

    public String getPointAsString() {
        return "Point x: " + x + " - y: " + y;
    }

    public XYPoint getBackBlockOfBlackPawn() {
        return new XYPoint(x + 1, y);
    }

    public ArrayList<XYPoint> getAllSurroundingOfBlack(GameBoard gameBoard) {
        ArrayList<XYPoint> points = new ArrayList<>();
        points.add(getFrontBlockOfWhitePawn());
        points.add(getBackBlockOfWhitePawn());
        points.add(getLeftRearFrontPointOfWhitePawn());
        points.add(getRightRearFrontPointOfWhitePawn());
        points.add(getLeftRearBackPointOfWhitePawn());
        points.add(getRightRearBackPointOfWhitePawn());
        points.add(getLeftBlockOfWhitePawn());
        points.add(getRightBlockOfWhitePawn());
        return trimKingMoves(gameBoard, points);
    }

    private ArrayList<XYPoint> trimKingMoves(GameBoard gameBoard, ArrayList<XYPoint> points) {
        ArrayList<XYPoint> points1 = new ArrayList<>();

        for(XYPoint p : points){
            if(!(p.getX() <0 || p.getX() > 5 || p.getY() < 0 || p.getX() > 5 || gameBoard.getPawnAtXYPoint(this).sameColorAs(gameBoard.getPawnAtXYPoint(p)))){
                points1.add(p);
            }
        }
        return points1;
    }

    public ArrayList<XYPoint> getAllSurroundingOfWhite(GameBoard gameBoard) {
        ArrayList<XYPoint> points = new ArrayList<>();
        points.add(getFrontBlockOfBlackPawn());
        points.add(getBackBlockOfBlackPawn());
        points.add(getLeftRearFrontPointOfBlackPawn());
        points.add(getRightRearFrontPointOfBlackPawn());
        points.add(getLeftRearBackPointOfBlackPawn());
        points.add(getRightRearBackPointOfBlackPawn());
        points.add(getLeftBlockOfBlackPawn());
        points.add(getRightBlockOfBlackPawn());
        return trimKingMoves(gameBoard, points);
    }

    public XYPoint getBackBlockOfWhitePawn() {
        return new XYPoint(x - 1, y);
    }

    public XYPoint getLeftBlockOfWhitePawn() {
        return new XYPoint(x, y - 1);
    }

    public XYPoint getRightBlockOfWhitePawn() {
        return new XYPoint(x, y + 1);
    }

    public ArrayList<XYPoint> getVerticalLane(Pawn p, GameBoard gameBoard){
        ArrayList<XYPoint> moves = new ArrayList<>();
            /* from point backwards until pawn is found, breaks on another pawn found in
            row, last move added if pawn colors do not match hence pawn consumes one another
             */
        for (int i = p.getX()-1; i >= 0; i--) {
            if(gameBoard.blockEmpty(new XYPoint(i, y))){
                moves.add(new XYPoint(i, y));
            }else{
                if(gameBoard.getPawnAtXYPoint(new XYPoint(i, y)).sameColorAs(p)){
                    break;
                }else{
                    moves.add(new XYPoint(i, y));
                    break;
                }
            }
        }

            /* from point onwards until pawn is found, breaks on another pawn found in
            row, last move added if pawn colors do not match hence pawn consumes one another
             */
        for (int i = p.getX()+1; i < 6; i++) {
            if(gameBoard.blockEmpty(new XYPoint(i, y))){
                moves.add(new XYPoint(i, y));
            }else{
                if(gameBoard.getPawnAtXYPoint(new XYPoint(i, y)).sameColorAs(p)){
                    break;
                }else{
                    moves.add(new XYPoint(i, y));
                    break;
                }
            }
        }

        return moves;
    }

    public ArrayList<XYPoint> getHorizontalLane(Pawn p, GameBoard gameBoard) {
        ArrayList<XYPoint> moves = new ArrayList<>();
            /* from point backwards until pawn is found, breaks on another pawn found in
            row, last move added if pawn colors do not match hence pawn consumes one another
             */
            for (int i = p.getY()-1; i >= 0; i--) {
                if(gameBoard.blockEmpty(new XYPoint(x, i))){
                    moves.add(new XYPoint(x, i));
                }else{
                    if(gameBoard.getPawnAtXYPoint(new XYPoint(x, i)).sameColorAs(p)){
                        break;
                    }else{
                        moves.add(new XYPoint(x, i));
                        break;
                    }
                }
            }

            /* from point onwards until pawn is found, breaks on another pawn found in
            row, last move added if pawn colors do not match hence pawn consumes one another
             */
            for (int i = p.getY()+1; i < 6; i++) {

                if(gameBoard.blockEmpty(new XYPoint(x, i))){
                    moves.add(new XYPoint(x, i));
                }else{
                    if(gameBoard.getPawnAtXYPoint(new XYPoint(x, i)).sameColorAs(p)){
                        break;
                    }else{
                        moves.add(new XYPoint(x, i));
                        break;
                    }
                }
            }

        return moves;
    }

    public ArrayList<XYPoint> getLeftDiagonalLane(Pawn p, GameBoard gameBoard){
        ArrayList<XYPoint> moves = new ArrayList<>();

        int j = y-1;
        for (int i = p.getX()+1; i < 6; i++) {
            if (i > 5 || i < 0 || j > 5 || j < 0)
                break;
            if(gameBoard.blockEmpty(new XYPoint(i, j))){
                moves.add(new XYPoint(i, j));
            }else{
                if(gameBoard.getPawnAtXYPoint(new XYPoint(i, j)).sameColorAs(p)){
                    break;
                }else{
                    moves.add(new XYPoint(i, j));
                    break;
                }
            }
            moves.add(new XYPoint(i, j));
            j--;
        }

        j = y+1;
        for (int i = p.getX()-1; i >= 0; i--) {
            if (i > 5 || i < 0 || j > 5 || j < 0)
                break;
            if(gameBoard.blockEmpty(new XYPoint(i, j))){
                moves.add(new XYPoint(i, j));
            }else{
                if(gameBoard.getPawnAtXYPoint(new XYPoint(i, j)).sameColorAs(p)){
                    break;
                }else{
                    moves.add(new XYPoint(i, j));
                    break;
                }
            }
            moves.add(new XYPoint(i, j));
            j++;
        }
        return trim(moves);
    }

    public ArrayList<XYPoint> getRightDiagonalLane(Pawn p, GameBoard gameBoard){
        ArrayList<XYPoint> moves = new ArrayList<>();

        int j = y+1;
        for (int i = p.getX()+1; i < 6; i++) {
            if (i > 5 || i < 0 || j > 5 || j < 0)
                break;
            if(gameBoard.blockEmpty(new XYPoint(i, j))){
                moves.add(new XYPoint(i, j));
            }else{
                if(gameBoard.getPawnAtXYPoint(new XYPoint(i, j)).sameColorAs(p)){
                    break;
                }else{
                    moves.add(new XYPoint(i, j));
                    break;
                }
            }
            j++;
        }

        j = y-1;
        for (int i = p.getX()-1; i >= 0; i--) {
            if (i > 5 || i < 0 || j > 5 || j < 0)
                break;
            if(gameBoard.blockEmpty(new XYPoint(i, j))){
                moves.add(new XYPoint(i, j));
            }else{
                if(gameBoard.getPawnAtXYPoint(new XYPoint(i, j)).sameColorAs(p)){
                    break;
                }else{
                    moves.add(new XYPoint(i, j));
                    break;
                }
            }
            j--;
        }


        return trim(moves);
    }


    private ArrayList<XYPoint> trim(ArrayList<XYPoint> moves) {
        ArrayList<XYPoint> m = new ArrayList<>();
        for(XYPoint move: moves){
            if(move.getX() < 6 && move.getY() > -1 && move.getX() > -1 && move.getY() < 6){
                m.add(move);
            }
        }

        return m;
    }
}
