package game.chess.pawns;

import game.chess.board.XYPoint;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by exil33t on 5/14/2017.
 */
public class PawnSet  implements Serializable {

    ArrayList<Pawn> pawns;

    public PawnSet() {
        pawns = new ArrayList<>();
    }

    public void initNewPawnSet(boolean white) {
        if (white) {
            pawns.add(new Soldier(new XYPoint(1, 0), PawnColor.WHITE));
            pawns.add(new Soldier(new XYPoint(1, 1), PawnColor.WHITE));
            pawns.add(new Soldier(new XYPoint(1, 2), PawnColor.WHITE));
            pawns.add(new Soldier(new XYPoint(1, 3), PawnColor.WHITE));
            pawns.add(new Soldier(new XYPoint(1, 4), PawnColor.WHITE));
            pawns.add(new Soldier(new XYPoint(1, 5), PawnColor.WHITE));
            pawns.add(new Rook(new XYPoint(0, 0), PawnColor.WHITE));
            pawns.add(new Bishop(new XYPoint(0, 1), PawnColor.WHITE));
            pawns.add(new King(new XYPoint(0, 2), PawnColor.WHITE));
            pawns.add(new Queen(new XYPoint(0, 3), PawnColor.WHITE));
            pawns.add(new Bishop(new XYPoint(0, 4), PawnColor.WHITE));
            pawns.add(new Rook(new XYPoint(0, 5), PawnColor.WHITE));
        } else {
            pawns.add(new Rook(new XYPoint(5, 0), PawnColor.BLACK));
            pawns.add(new Bishop(new XYPoint(5, 1), PawnColor.BLACK));
            pawns.add(new King(new XYPoint(5, 2), PawnColor.BLACK));
            pawns.add(new Queen(new XYPoint(5, 3), PawnColor.BLACK));
            pawns.add(new Bishop(new XYPoint(5, 4), PawnColor.BLACK));
            pawns.add(new Rook(new XYPoint(5, 5), PawnColor.BLACK));
            pawns.add(new Soldier(new XYPoint(4, 0), PawnColor.BLACK));
            pawns.add(new Soldier(new XYPoint(4, 1), PawnColor.BLACK));
            pawns.add(new Soldier(new XYPoint(4, 2), PawnColor.BLACK));
            pawns.add(new Soldier(new XYPoint(4, 3), PawnColor.BLACK));
            pawns.add(new Soldier(new XYPoint(4, 4), PawnColor.BLACK));
            pawns.add(new Soldier(new XYPoint(4, 5), PawnColor.BLACK));
        }
    }

    public ArrayList<Pawn> getPawns() {
        return pawns;
    }

    public void removePawnAtXYPoint(XYPoint xyPoint) {
        Pawn pawn = null;
        for (Pawn p : pawns) {
            if (p.isAtIndex(xyPoint)) {
                pawn = p;
            }
        }
        pawns.remove(pawn);
    }
}
