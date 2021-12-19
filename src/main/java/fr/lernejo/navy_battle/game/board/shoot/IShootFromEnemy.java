package fr.lernejo.navy_battle.game.board.shoot;

import fr.lernejo.navy_battle.enumeration.Consequence;
import fr.lernejo.navy_battle.game.point.IPoint;

import java.util.List;

public interface IShootFromEnemy {
    public Consequence shoot(IPoint targetedPoint);
    public List<String> UpdateDestroyedShip();
}
