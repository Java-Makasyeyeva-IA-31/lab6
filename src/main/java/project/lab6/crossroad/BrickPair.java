package project.lab6.crossroad;

import static java.awt.Color.BLACK;
import static java.awt.Color.GRAY;
import static project.lab6.element.Element.SIZE;

public class BrickPair {

    private final Brick first;
    private final Brick second;
    private final boolean even;

    public BrickPair(int x, int y, boolean vertical, boolean even) {
        this.first = new Brick(x, y, even ? BLACK : GRAY);
        this.second = new Brick(
                vertical ? x + SIZE : x,
                vertical ? y : y + SIZE, 
                even ? GRAY : BLACK);
        this.even = even;
    }

    public Brick getFirst() {
        return first;
    }

    public Brick getSecond() {
        return second;
    }

    public boolean isEven() {
        return even;
    }

    public void addX(int x) {
        first.setX(first.getX() + x);
        second.setX(second.getX() + x);
    }

    public void addY(int y) {
        first.setY(first.getY() + y);
        second.setY(second.getY() + y);
    }
}
