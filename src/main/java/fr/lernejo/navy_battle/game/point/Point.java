package fr.lernejo.navy_battle.game.point;

public class Point implements IPoint {
    private final int X;
    private final int Y;
    public Point(int x, int y){
        this.X = x;
        this.Y = y;
    }
    @Override
    public int getX(){return this.X;}
    @Override
    public int getY(){return this.Y;}
    @Override
    public boolean equals(Object object){
        final Point point = (Point) object;
        if(this.X == point.getX())
            if(this.Y == point.getY())
                return true;
        return false;
    }
    @Override
    public String toString(){
        return (char) (this.X +65) + "" + (this.Y +1);
    }
}
