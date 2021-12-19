package fr.lernejo.navy_battle.game.board;

import fr.lernejo.navy_battle.IController;
import fr.lernejo.navy_battle.game.boat.BoatBuilder;
import fr.lernejo.navy_battle.game.boat.IBoat;
import fr.lernejo.navy_battle.game.boat.IBoatBuilder;
import fr.lernejo.navy_battle.game.player.IPlayer;
import fr.lernejo.navy_battle.game.selectPosition.ConfigurePosition;
import fr.lernejo.navy_battle.game.selectPosition.IConfigurePosition;

import java.util.ArrayList;
import java.util.List;

public class Board implements  IBoard {
    private final List<IBoat> listBoat;
    private final IController controller;
    private final IPlayer player;
    public Board(IController controller, IPlayer player){
        this.controller = controller;
        IBoatBuilder boatBuilder = new BoatBuilder();
        this.listBoat = boatBuilder.GetBoat();
        this.player = player;
    }

    public void InitializeBoats(){
        final IConfigurePosition configurePosition = new ConfigurePosition(player);
        configurePosition.PositionAllBoat(this.listBoat);
    }

}
