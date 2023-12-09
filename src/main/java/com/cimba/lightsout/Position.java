package com.cimba.lightsout;

public record Position(int x, int y) {


    @Override
    public boolean equals(Object other) {
        if (other.getClass() == this.getClass()) {
            Position that = (Position) other;
            return this.x == that.x && this.y == that.y;
        }
        return false;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

}
