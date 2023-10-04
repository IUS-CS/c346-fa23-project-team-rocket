package team.rocket;

import team.rocket.Enums.Direction;

public class Grass extends AbstractOrganism {
    public Grass() {

    }

    public void die(AbstractOrganism[][] grid, int y, int x) {

    }

    public static char toIcon() {
        return ' ';
    }

    public static int getCount() {
        return 0;
    }

    @Override
    public void setCount(int i) {

    }

    @Override
    public void reduceCount() {

    }

    @Override
    public AbstractOrganism getNewObjectFromExistingObject() {
        return null;
    }

    public void resetGrown() {

    }

    public Direction availableSpace(AbstractOrganism[] array) {
        return Direction.DOWN;
    }

    public void grow(AbstractOrganism[][] grid, AbstractOrganism[] neighbors, int y, int x) {

    }

    @Override
    public void reproduce() {

    }
}
