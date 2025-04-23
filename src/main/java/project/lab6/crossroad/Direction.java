package project.lab6.crossroad;

import static project.lab6.element.Element.SIZE;
import project.lab6.element.ElementShift;

public enum Direction {
    UP {
        @Override
        Direction getOposit() {
            return DOWN;
        }

        @Override
        ElementShift getPointShift() {
            return new ElementShift(0, SIZE);
        }

        @Override
        String getStringDirection() {
            return "↑";
        }
    }, DOWN {
        @Override
        Direction getOposit() {
            return UP;
        }

        @Override
        ElementShift getPointShift() {
            return new ElementShift(0, -SIZE);
        }

        @Override
        String getStringDirection() {
            return "↓";
        }
    }, LEFT {
        @Override
        Direction getOposit() {
            return RIGHT;
        }

        @Override
        ElementShift getPointShift() {
            return new ElementShift(SIZE, 0);
        }

        @Override
        String getStringDirection() {
            return "←";
        }
    }, RIGHT {
        @Override
        Direction getOposit() {
            return LEFT;
        }

        @Override
        ElementShift getPointShift() {
            return new ElementShift(-SIZE, 0);
        }

        @Override
        String getStringDirection() {
            return "→";
        }
    };

    abstract Direction getOposit();

    abstract String getStringDirection();

    abstract ElementShift getPointShift();
}
