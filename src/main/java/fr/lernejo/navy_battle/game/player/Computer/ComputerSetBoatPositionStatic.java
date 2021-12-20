package fr.lernejo.navy_battle.game.player.Computer;

import fr.lernejo.navy_battle.game.point.IPoint;
import fr.lernejo.navy_battle.game.point.Point;

import java.util.ArrayList;
import java.util.List;

public class ComputerSetBoatPositionStatic implements IComputerSetBoatPosition{
    final List<Integer> numbers = new ArrayList<>();
    @Override
    public List<IPoint> PositionABoat(int size) {
        List <IPoint> pointList = new ArrayList<>();
        for (int i = 0; i <size; i++)
        {
            pointList.add(new Point(numbers.size(),i));
        }
        numbers.add(1);
        return pointList;
    }
}
