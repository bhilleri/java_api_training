package fr.lernejo.navy_battle.game.board.shoot;

import fr.lernejo.navy_battle.IController;
import fr.lernejo.navy_battle.enumeration.Consequence;
import fr.lernejo.navy_battle.game.board.IBoard;
import fr.lernejo.navy_battle.game.ship.IShip;
import fr.lernejo.navy_battle.game.player.IPlayer;
import fr.lernejo.navy_battle.game.point.IPoint;

import java.util.ArrayList;
import java.util.List;

public class ShootFromEnemy implements IShootFromEnemy {
    private final List<IShip> shipList;
    public ShootFromEnemy(List<IShip> shipList){
        this.shipList = shipList;
    }
    @Override
    public Consequence shoot(IPoint targetedPoint) {
        for (IShip ship : shipList) {
            final Consequence consequence = ship.hit(targetedPoint);
            if (consequence != Consequence.miss)
                return consequence;
        }
        return Consequence.miss;
    }

    @Override
    public List<String> UpdateDestroyedShip() {
        final List<String> stringDestroyedShip = new ArrayList<>();
        final List<IShip> shipDestroyed = new ArrayList<>();
        for (IShip ship : shipList) {
            if(ship.IsDestroy())
            {
                stringDestroyedShip.add(ship.getName());
                shipDestroyed.add(ship);
            }
        }
        this.shipList.removeAll(shipDestroyed);
        return stringDestroyedShip;
    }

}
