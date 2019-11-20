package Modele;

/**
 * classe static direction
 * Created by Fabien on 27/04/2017
 * @author Fabien
 */

public class Direction {
    public static Direction UP = new Direction();
    public static Direction DOWN = new Direction();
    public static Direction RIGHT = new Direction();
    public static Direction LEFT = new Direction();
    public static Direction NONE = new Direction();

    public short getDirectionValue() {
        if (this.equals(UP)) return 2;
        if (this.equals(DOWN)) return 3;
        if (this.equals(RIGHT)) return (short) (1);
        if (this.equals(LEFT)) return (short) (-1);
        return (short) (0);
    }

    public short getDirectionValueOneD(short colNumber) {
        if (this.equals(UP)) return (short) (-1 * colNumber);
        if (this.equals(DOWN)) return colNumber;
        if (this.equals(RIGHT)) return (short) (1);
        if (this.equals(LEFT)) return (short) (-1);
        return (short) (0);
    }

    @Override
    public String toString() {
        if (this.equals(UP)) return "UP";
        if (this.equals(DOWN)) return "DOWN";
        if (this.equals(RIGHT)) return "RIGHT";
        if (this.equals(LEFT)) return "LEFT";
        return "NONE";
    }

    public short toNumber(){
        if (this.equals(UP)) return 0;
        if (this.equals(DOWN)) return 1;
        if (this.equals(RIGHT)) return 3;
        if (this.equals(LEFT)) return 2;
        return -1;
    }

    //TODO : TO_ASK si c'est vraiment necessaire en statique
    public static Direction toDir(short numberToDir){
        if (numberToDir == 0) return Direction.UP;
        if (numberToDir == 1) return Direction.DOWN;
        if (numberToDir == 2) return Direction.LEFT;
        if (numberToDir == 3) return Direction.RIGHT;
        return Direction.NONE;
    }
}
