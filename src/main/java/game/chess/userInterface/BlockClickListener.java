package game.chess.userInterface;

import game.chess.board.XYPoint;
import game.chess.pawns.Pawn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by exil33t on 5/16/2017.
 */
public class BlockClickListener implements MouseListener {
    private static GameUI gameUI;
    private Pawn selectedPawn;

    public BlockClickListener() {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton buttonPressed = (JButton) e.getSource();
        XYPoint newPointSelected = calcIndexOfBlock(buttonPressed);
        if (!checkIfCanProceed(buttonPressed)) {
            return;
        }

        /* In case there is no pawn selected */
        if (selectedPawn == null) {
            setSelectedPawn(buttonPressed);
        }
        /* If selected block is available for moving */
        else if (selectedPawn.canMoveTo(gameUI.getGameBoard(), newPointSelected)) {
            selectedPawn.moveTo(gameUI.getGameBoard(), newPointSelected);
            gameUI.reRendBoard();
            nextRound();
            return;
        }
        /* In case that there is a pawn already selected and player chooses another one */
        else if (selectedPawn.sameColorAs(gameUI.getGameBoard().getPawnAtXYPoint(newPointSelected))) {
            setSelectedPawn(buttonPressed);
        }
    }


    private void nextRound() {
        try {
            gameUI.getGame().nextRound();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectedPawn = null;
    }

    private boolean checkIfCanProceed(JButton b) {
        XYPoint newPointSelected = calcIndexOfBlock(b);
        if (selectedPawn == null && !gameUI.getGame().getGameBoard().pawnExistsAtIndex(newPointSelected.getX(), newPointSelected.getY())) {
            return false;
        }

        Pawn p = getSelectedPawn(b);

        if (p != null) {
            if (p.getPawnColor() != gameUI.getRound()) {  /* In case that the opposing player tries to pick a pawn not his */
                if (selectedPawn != null) {
                    if (selectedPawn.canMoveTo(gameUI.getGameBoard(), newPointSelected)) {  /* In case the opponent is clicking in a pawn that it's about to be consumed */
                        return true;
                    }
                }
                return false;
            }
        }
        return true;
    }

    private void highlightSelectedBlock(JButton source) {
        gameUI.reRendBoard();
        gameUI.setBlockGray(calcIndexOfBlock(source));
    }

    private void setSelectedPawn(JButton e) {
        this.selectedPawn = getSelectedPawn(e);
        highlightSelectedBlock(e);
    }

    private Pawn getSelectedPawn(JButton e) {
        return gameUI.getGameBoard().getPawnAtXYPoint(calcIndexOfBlock(e));
    }

    private XYPoint calcIndexOfBlock(JButton b) {
        Rectangle r = b.getBounds();
        Point p = b.getLocation();
        return new XYPoint((p.y / r.height), (p.x / r.width));
    }

    public void setGameUI(GameUI gameUI) {
        this.gameUI = gameUI;
    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

