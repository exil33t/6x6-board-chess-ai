package game.chess.pawns;

import game.chess.board.XYPoint;

/**
 * Created by exil33t on 5/16/2017.
 */
public class PawnFactory {
    public static Pawn getPawn(String st, XYPoint xyPoint) {
        String color = st.split("_")[0];
        String pawnType = st.split("_")[1];
        if (pawnType.equals("King")) {
            return new King(xyPoint, (color.equals("Black") ? PawnColor.BLACK : PawnColor.WHITE));
        } else if (pawnType.equals("Queen")) {
            return new Queen(xyPoint, (color.equals("Black") ? PawnColor.BLACK : PawnColor.WHITE));
        } else if (pawnType.equals("Rook")) {
            return new Rook(xyPoint, (color.equals("Black") ? PawnColor.BLACK : PawnColor.WHITE));
        } else if (pawnType.equals("Bishop")) {
            return new Bishop(xyPoint, (color.equals("Black") ? PawnColor.BLACK : PawnColor.WHITE));
        } else if (pawnType.equals("Soldier")) {
            return new Soldier(xyPoint, (color.equals("Black") ? PawnColor.BLACK : PawnColor.WHITE));
        }
        return null;
    }
}
