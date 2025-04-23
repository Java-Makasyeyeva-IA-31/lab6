package project.lab6.crossroad;

import java.awt.Color;
import project.lab6.element.AbstractElement;

public class Car extends AbstractElement {

    public Car(Color color) {
        super(0, 0, 0, 0, color);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
