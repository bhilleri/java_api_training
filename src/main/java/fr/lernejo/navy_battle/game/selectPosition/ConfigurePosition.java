package fr.lernejo.navy_battle.game.selectPosition;

import fr.lernejo.navy_battle.game.ship.IShip;
import fr.lernejo.navy_battle.game.player.IPlayer;
import fr.lernejo.navy_battle.game.point.IPoint;

import java.util.List;

public class ConfigurePosition implements IConfigurePosition {
    final private IPlayer player;
    public ConfigurePosition(IPlayer player)
    {
        this.player = player;
    }
    @Override
    public void PositionAllBoat(final List<IShip> boatList) {
        for (IShip boat : boatList) {
            while(true)
            {
                final List<IPoint> listPoint = player.PositionABoat(boat.GetSize());
                if(ValidPosition(boatList,listPoint))
                {
                    if(boat.SetPosition(listPoint))
                    {
                        player.InformPlacement(boat.getName(), boat.GetPosition());
                        break;
                    }
                }
            }
        }
    }
    private boolean ValidPosition(final List<IShip> boatList, final List<IPoint> pointList)
    {
        for (IShip boat : boatList) {
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
