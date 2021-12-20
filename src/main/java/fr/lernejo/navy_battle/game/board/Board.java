package fr.lernejo.navy_battle.game.board;

import fr.lernejo.navy_battle.IController;
import fr.lernejo.navy_battle.enumeration.Consequence;
import fr.lernejo.navy_battle.game.board.shoot.ShootFromEnemy;
import fr.lernejo.navy_battle.game.ship.ShipBuilder;
import fr.lernejo.navy_battle.game.ship.IShip;
import fr.lernejo.navy_battle.game.ship.IShipBuilder;
import fr.lernejo.navy_battle.game.player.IPlayer;
import fr.lernejo.navy_battle.game.point.IPoint;
import fr.lernejo.navy_battle.game.selectPosition.ConfigurePosition;
import fr.lernejo.navy_battle.game.selectPosition.IConfigurePosition;

import java.util.List;

public class Board implements  IBoard {
    private final List<IShip> shipList;
    private final IController controller;
    private final IPlayer player;
    private final ShootFromEnemy shootFromEnemy;
    public Board(IController controller, IPlayer player){
        this.controller = controller;
        IShipBuilder boatBuilder = new ShipBuilder();
        this.shipList = boatBuilder.GetBoat();
        this.player = player;
        this.shootFromEnemy = new ShootFromEnemy(this.shipList);
    }

    @Override
    public void InitializeShip(){
        final IConfigurePosition configurePosition = new ConfigurePosition(player);
        configurePosition.PositionAllBoat(this.shipList);
    }

    @Override
    public Consequence EnemyShoot(IPoint targetedPoint) {
        final Consequence consequence = this.shootFromEnemy.shoot(targetedPoint);
        player.InformEnemySHoot(targetedPoint, consequence);
        final List<String> destroyedShip = this.shootFromEnemy.UpdateDestroyedShip();
        player.InformShipLost(destroyedShip);
        return  consequence;
    }

    @Override
    public boolean GetIfLost() {
        if(this.shipList.size()<=0)
            return true;
        return false;
    }
}
