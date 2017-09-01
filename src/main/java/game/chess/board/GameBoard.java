package game.chess.board;

import game.chess.pawns.Pawn;
import game.chess.pawns.PawnColor;
import game.chess.pawns.PawnSet;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by exil33t on 5/14/2017.
 */
public class GameBoard implements Serializable{
    private PawnSet black;
    private PawnSet white;
    private String imageTypeSuffix = ".png";

    public GameBoard() {
        black = new PawnSet();
        black.initNewPawnSet(false);
        white = new PawnSet();
        white.initNewPawnSet(true);
    }

    public PawnSet getBlack() {
        return black;
    }

    public PawnSet getWhite() {
        return white;
    }

    public ArrayList<JButton> getBlocksAsJButtons() {
        ArrayList<JButton> blocks = new ArrayList<>();
        JButton b;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                b = new JButton();
                b.setBackground(((j % 2 == (i % 2 == 0 ? 0 : 1) ? Color.GRAY : Color.WHITE)));
                if (pawnExistsAtIndex(i, j)) {
                    Pawn p = getPawnAtIndex(new XYPoint(i, j));
                    b.setIcon(new ImageIcon("icons/" +
                            ((p.getPawnColor() == PawnColor.BLACK) ? "Black_" : "White_")
                            + p.getClass().toString().split("\\.")[3]
                            + imageTypeSuffix));
                    blocks.add(b);
                } else {
                    blocks.add(b);
                }
            }
        }
        return blocks;
    }

    private Pawn getPawnAtIndex(XYPoint xyPoint) {
        ArrayList<Pawn> tmp = getAllPawns();

        for (Pawn p : tmp) {
            if (p.isAtIndex(xyPoint)) {
                return p;
            }
        }
        return null;
    }

    public boolean pawnExistsAtIndex(int i, int j) {
        ArrayList<Pawn> tmp = getAllPawns();
        for (Pawn p : tmp) {
            if (p.getX() == i && p.getY() == j) {
                return true;
            }
        }
        return false;
    }


    public ArrayList<Pawn> getAllPawns() {
        ArrayList<Pawn> tmp = new ArrayList<>(black.getPawns());
        tmp.addAll(white.getPawns());
        return tmp;
    }

    public boolean blockEmpty(XYPoint xyPoint) {
        if (xyPoint.getY() <= 5 && xyPoint.getX() <= 5 && !pawnExistsAtIndex(xyPoint.getX(), xyPoint.getY())) {
            return true;
        }
        return false;
    }


    public Pawn getPawnAtXYPoint(XYPoint xyPoint) {
        return getPawnAtIndex(xyPoint);
    }

    public boolean thereIsOpponentPawnInXYPoint(Pawn pawn, XYPoint xyPoint) {
        return (pawnExistsAtIndex(xyPoint.getX(), xyPoint.getY()) && !getPawnAtXYPoint(xyPoint).sameColorAs(pawn) ? true : false);
    }

    public void removePawnAtXYPoint(XYPoint xyPoint) {
        black.removePawnAtXYPoint(xyPoint);
        white.removePawnAtXYPoint(xyPoint);
    }



    public void executeMove(Move m) {
        Pawn p = getPawnAtXYPoint(m.getFrom());
        p.moveTo(this, m.getTo());
    }

    public boolean kingMissing() {
        int i = 0;
        for(Pawn pawn: getAllPawns()){
            if(pawn.getPawnValue() == 100){
                i++;
            }
        }
        if(i < 2){
            return true;
        }

        return false;

    }
}
