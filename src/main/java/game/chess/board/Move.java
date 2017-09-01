package game.chess.board;

/**
 * Created by exil33t on 5/27/2017.
 */
public class Move {
    private XYPoint from;
    private XYPoint to;
    int value;
    int valueForOpponent;

    public Move(XYPoint from, XYPoint to, int value) {
        this.from = from;
        this.to = to;
        this.value = value;
    }

    public void setValueForOpponent(int valueForOpponent) {
        this.valueForOpponent = valueForOpponent;
        this.value -= valueForOpponent;
    }

    public XYPoint getFrom() {
        return from;
    }

    public void setFrom(XYPoint from) {
        this.from = from;
    }

    public XYPoint getTo() {
        return to;
    }

    public void setTo(XYPoint to) {
        this.to = to;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValueForOpponent() {
        return valueForOpponent;
    }
}
