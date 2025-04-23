package project.lab6.crossroad;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import project.lab6.AbstractBasePanel;
import static project.lab6.crossroad.Direction.DOWN;
import static project.lab6.crossroad.Direction.LEFT;
import static project.lab6.crossroad.Direction.RIGHT;
import static project.lab6.crossroad.Direction.UP;
import static project.lab6.element.Element.SIZE;
import project.lab6.element.ElementShift;

public class CrossroadPanel extends AbstractBasePanel {

    private final int MIDDLE_CAR_VERICAL;
    private final int MIDDLE_CAR_HORIZONTAL;
    private final int MIDDLE_ROAD_VERICAL;
    private final int MIDDLE_ROAD_HORIZONTAL;

    private Car car;
    boolean sameDirection = true;

    private Direction nextDirection;
    private Direction currentDirection;

    private final List<BrickPair> verticalBricks = new ArrayList<>();
    private final List<BrickPair> horizontalBricks = new ArrayList<>();

    public CrossroadPanel() {
        initComponents();

        nextDirection = Direction.UP;
        currentDirection = Direction.UP;

        MIDDLE_CAR_VERICAL = getHeight() / 2 - SIZE / 2;
        MIDDLE_CAR_HORIZONTAL = getWidth() / 2 - SIZE / 2;
        MIDDLE_ROAD_VERICAL = getHeight() / 2 - SIZE;
        MIDDLE_ROAD_HORIZONTAL = getWidth() / 2 - SIZE;

        new Timer(5, (evt) -> repaint()).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (draw) {
            Direction previousDirection = currentDirection;

            if (play && sameDirection) {
                restructureElements();
                sameDirection = previousDirection.equals(currentDirection);
            } else {
                sameDirection = true;
            }

            ElementShift elementShift = currentDirection.getPointShift();

            for (BrickPair brickPair : verticalBricks) {
                if (play && sameDirection) {
                    brickPair.addX(elementShift.getX() / SIZE);
                    brickPair.addY(elementShift.getY() / SIZE);
                }
                fillRectElement(g, brickPair.getFirst());
                fillRectElement(g, brickPair.getSecond());
            }

            for (BrickPair brickPair : horizontalBricks) {
                if (play && sameDirection) {
                    brickPair.addX(elementShift.getX() / SIZE);
                    brickPair.addY(elementShift.getY() / SIZE);
                }
                fillRectElement(g, brickPair.getFirst());
                fillRectElement(g, brickPair.getSecond());
            }

            fillRectElement(g, car);

            logText("Next direction: " + nextDirection.getStringDirection());
        }
    }

    private void restructureElements() {
        BrickPair borderBrickPair;

        switch (currentDirection) {
            case UP -> {
                borderBrickPair = verticalBricks.get(0);
                if (borderBrickPair.getFirst().getY() == 0) {
                    verticalBricks.remove(verticalBricks.size() - 1);
                    verticalBricks.add(0, new BrickPair(MIDDLE_ROAD_HORIZONTAL, -SIZE, true, !borderBrickPair.isEven()));
                }
                if (horizontalBricks.get(0).getFirst().getY() == getHeight()) {
                    initHorizontalElements(-SIZE * 2);
                }
                if (horizontalBricks.get(0).getFirst().getY() == MIDDLE_ROAD_VERICAL) {
                    changeDirrection();
                }
            }
            case DOWN -> {
                borderBrickPair = verticalBricks.get(verticalBricks.size() - 1);
                if (borderBrickPair.getFirst().getY() == getHeight()) {
                    verticalBricks.remove(0);
                    verticalBricks.add(new BrickPair(MIDDLE_ROAD_HORIZONTAL, borderBrickPair.getFirst().getY() + SIZE, true, !borderBrickPair.isEven()));

                    if (horizontalBricks.get(0).getFirst().getY() == -SIZE * 2) {
                        initHorizontalElements(getHeight());
                    }

                    if (horizontalBricks.get(0).getFirst().getY() == MIDDLE_ROAD_VERICAL) {
                        changeDirrection();
                    }
                }
            }
            case LEFT -> {
                borderBrickPair = horizontalBricks.get(0);
                if (borderBrickPair.getFirst().getX() == 0) {
                    horizontalBricks.remove(horizontalBricks.size() - 1);
                    horizontalBricks.add(0, new BrickPair(-SIZE, MIDDLE_ROAD_VERICAL, false, !borderBrickPair.isEven()));
                }
                if (verticalBricks.get(0).getFirst().getX() == getWidth()) {
                    initVerticalElements(-SIZE * 2);
                }
                if (verticalBricks.get(0).getFirst().getX() == MIDDLE_ROAD_HORIZONTAL) {
                    changeDirrection();
                }
            }
            default -> { //RIGHT
                borderBrickPair = horizontalBricks.get(horizontalBricks.size() - 1);
                if (borderBrickPair.getFirst().getX() == getWidth()) {
                    horizontalBricks.remove(0);
                    horizontalBricks.add(new BrickPair(borderBrickPair.getFirst().getX() + SIZE, MIDDLE_ROAD_VERICAL, false, !borderBrickPair.isEven()));

                    if (verticalBricks.get(0).getFirst().getX() == -SIZE * 2) {
                        initVerticalElements(getWidth());
                    }

                    if (verticalBricks.get(0).getFirst().getX() == MIDDLE_ROAD_HORIZONTAL) {
                        changeDirrection();
                    }
                }
            }
        }
    }

    private void changeDirrection() {
        currentDirection = nextDirection;

        shiftCarByDirection();
    }

    private void initVerticalElements(int x) {
        boolean even = true;
        verticalBricks.clear();

        for (int y = -SIZE * 2; y <= getHeight() + SIZE; y += SIZE) {
            even = !even;
            verticalBricks.add(new BrickPair(x, y, true, even));
        }
    }

    private void initHorizontalElements(int y) {
        boolean even = true;
        horizontalBricks.clear();

        for (int x = -SIZE * 2; x <= getWidth() + SIZE; x += SIZE) {
            even = !even;
            horizontalBricks.add(new BrickPair(x, y, false, even));
        }
    }

    private void initCar() {
        car = new Car(Color.RED);
        shiftCarByDirection();
    }

    private void shiftCarByDirection() {
        ElementShift pointShift = currentDirection.getPointShift();
        int horizontalShiftMulty = LEFT.equals(currentDirection) || RIGHT.equals(currentDirection) ? -1 : 1;

        car.setX(MIDDLE_CAR_HORIZONTAL + getCarPositionShift(pointShift.getY()));
        car.setY(MIDDLE_CAR_VERICAL + getCarPositionShift(pointShift.getX()) * horizontalShiftMulty);
        car.setWidth(SIZE + Math.abs(pointShift.getX()));
        car.setHeight(SIZE + Math.abs(pointShift.getY()));
    }

    private int getCarPositionShift(int shift) {
        return shift == 0 ? -SIZE / 2 : shift / 2;
    }

    @Override
    public void setDraw(boolean draw) {
        super.setDraw(draw);

        if (draw) {
            currentDirection = UP;
            initCar();
            initVerticalElements(MIDDLE_ROAD_HORIZONTAL);
            initHorizontalElements(-SIZE * 2);
        }
    }

    @Override
    @SuppressWarnings("empty-statement")
    public void handleKeyEvent(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'w' ->
                nextDirection = UP;
            case 's' ->
                nextDirection = DOWN;
            case 'a' ->
                nextDirection = LEFT;
            case 'd' ->
                nextDirection = RIGHT;
        };

        if (currentDirection.getOposit().equals(nextDirection)) {
            changeDirrection();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setMaximumSize(new java.awt.Dimension(400, 400));
        setMinimumSize(new java.awt.Dimension(400, 400));
        setPreferredSize(new java.awt.Dimension(400, 400));
        setSize(new java.awt.Dimension(400, 400));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
