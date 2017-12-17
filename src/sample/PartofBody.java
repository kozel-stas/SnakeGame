package sample;

public class PartofBody {
    private int x,y;
    private PartofBody Top,Bottom;

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public PartofBody getBottom() {
        return Bottom;
    }

    public PartofBody getTop() {
        return Top;
    }

    public void setBottom(PartofBody bottom) {
        Bottom = bottom;
    }

    public void setTop(PartofBody top) {
        Top = top;
    }
}
