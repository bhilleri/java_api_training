package fr.lernejo.navy_battle.game.selectPosition;

import fr.lernejo.navy_battle.game.boat.IBoat;
import fr.lernejo.navy_battle.game.player.IPlayer;
import fr.lernejo.navy_battle.game.point.IPoint;

import java.util.List;

public class ConfigurePosition implements IConfigurePosition {
    private IPlayer player;
    public ConfigurePosition(IPlayer player)
    {
        this.player = player;
    }
    @Override
    public void PositionAllBoat(final List<IBoat> boatList) {
        for (IBoat boat : boatList) {
            while(true)
            {
                final List<IPoint> listPoint = player.PositionABoat(boat.GetSize());
                if(ValidPosition(boatList,listPoint))
                {
                    boat.SetPosition(listPoint);
                    System.out.println("position du " + boat.getName() + " : " + boat.GetPosition().toString());
                    break;
                }
            }
        }
    }
    public boolean ValidPosition(final List<IBoat> boatList, final List<IPoint> pointList)
    {
        for (IBoat boat : boatList) {
            for (IPoint point : pointList) {
                if(boat.GetPosition().contains(point))
                {
                    return false;
                }
            }
        }
        return true;
    }
}
