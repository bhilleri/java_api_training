package fr.lernejo.navy_battle.game.board;

import fr.lernejo.navy_battle.enumeration.Consequence;
import fr.lernejo.navy_battle.game.point.IPoint;

public interface IBoard {
    public void InitializeShip();
    public Consequence EnemyShoot(IPoint point);
    public boolean GetIfLost();
}
