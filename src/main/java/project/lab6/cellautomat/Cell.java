package project.lab6.cellautomat;

import java.awt.Color;
import java.util.Objects;
import project.lab6.element.AbstractElement;

public class Cell extends AbstractElement {

    private int neighborCount = 0;

    public Cell(int x, int y, Color color) {
        super(x, y, color);
    }

    public void resetNeighborCount() {
        neighborCount = 0;
    }

    public void incrementNeighborCount() {
        neighborCount++;
    }

    public int getNeighborCount() {
        return neighborCount;
    }

     @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return x == cell.x && y == cell.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
