package project.lab6.element;

import java.awt.Color;

public abstract class AbstractElement implements Element {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    private final Color color;

    public AbstractElement(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.width = SIZE;
        this.height = SIZE;
        this.color = color;
    }
    
    public AbstractElement(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
