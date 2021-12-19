package fr.lernejo.navy_battle.game.ship;

import fr.lernejo.navy_battle.enumeration.Consequence;
import fr.lernejo.navy_battle.game.point.IPoint;

import java.util.ArrayList;
import java.util.List;

public class Ship implements IShip {
    private final int size;
    private final String name;
    private final List<IPoint> pointList;
    public Ship(String name, int size){
        this.name = name;
        this.size = size;
        pointList = new ArrayList<>();
    }

    @Override
    public boolean SetPosition(List<IPoint> pointList) {
        this.pointList.clear();
        if(pointList.size() != this.size)
            return false;
        for (IPoint point : pointList) {
            this.pointList.add(point);
        }
        return true;
    }

    @Override
    public Consequence hit(IPoint targetedPoint) {
        if(this.pointList.remove(targetedPoint))
        {
            if(this.pointList.size() <= 0)
                return Consequence.sunk;
            return Consequence.hint;
        }
        return Consequence.miss;
    }

    @Override
    public boolean IsPresentAtAPoint(IPoint testedPoint) {
        return (this.pointList.contains(testedPoint));
    }

    @Override
    public String getName() {return this.name;}

    @Override
    public List<IPoint> GetPosition() {return this.pointList;}

    @Override
    public int GetSize() {return this.size;}

    @Override
    public boolean IsDestroy() {
        if(this.pointList.size() <=0)
            return true;
        return false;
    }
}
