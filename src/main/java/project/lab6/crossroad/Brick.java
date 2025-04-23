package project.lab6.crossroad;

import java.awt.Color;
import project.lab6.element.AbstractElement;

public class Brick extends AbstractElement {

    public Brick(int x, int y, Color color) {
        super(x, y, color);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
