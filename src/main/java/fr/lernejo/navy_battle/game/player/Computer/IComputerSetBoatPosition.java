package fr.lernejo.navy_battle.game.player.Computer;

import fr.lernejo.navy_battle.game.point.IPoint;

import java.util.List;

public interface IComputerSetBoatPosition {
    public List<IPoint> PositionABoat(int size);
}
