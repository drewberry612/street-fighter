package utilities;

public final class Vector {
    public double x, y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object o) {
        if (o instanceof Vector v) {
            return x == v.x && y == v.y;
        }
        else {
            return false;
        }
    }

    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void addScaled(Vector v, double fac) {
        this.x += fac * v.x;
        this.y += fac * v.y;
    }

    public void subtract(double x, double y) {
        this.x -= x;
        this.y -= y;
    }

    public double dist(Vector v) {
        return Math.hypot(this.x - v.x, this.y - v.y);
    }
}