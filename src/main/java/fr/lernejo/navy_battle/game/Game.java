package fr.lernejo.navy_battle.game;

import fr.lernejo.navy_battle.IController;
import fr.lernejo.navy_battle.enumeration.Consequence;
import fr.lernejo.navy_battle.enumeration.Victory;
import fr.lernejo.navy_battle.game.board.Board;
import fr.lernejo.navy_battle.game.board.IBoard;
import fr.lernejo.navy_battle.game.player.Computer.ComputerPlayer;
import fr.lernejo.navy_battle.game.player.IPlayer;
import fr.lernejo.navy_battle.game.point.IPoint;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Game implements  IGame{
    private final IPlayer player;
    private final List<Boolean> readyToShoot;
    private final IController controller;
    private final IBoard board;
    private final List<Boolean> victory;
    public Game(IController controller, boolean readyToShoot, IPlayer player){
        this.player = player;
        this.controller = controller;
        board = new Board(controller, player);
        this.readyToShoot = new ArrayList<>();
        this.readyToShoot.add(readyToShoot);
        this.victory = new ArrayList<>();
        this.victory.add(false);
    }

    @Override
    public void Initialize() {
        board.InitializeShip();
    }

    @Override
    public boolean GetReadyTOShoot() {
        return this.readyToShoot.get(0);
    }

    @Override
    public Consequence receiveShoot(IPoint point) {
        return this.board.EnemyShoot(point);
    }

    @Override
    public boolean GetIfLost() {
        return this.board.GetIfLost();
    }

    @Override
    public void Start() throws UnknownHostException, InterruptedException {
        while (this.GetIfLost() == false && victory.get(0) == false) {
            while (readyToShoot.get(0) == false) {
                TimeUnit.MICROSECONDS.sleep(2);
            }
            if(this.GetIfLost() == false && victory.get(0) == false) {
                this.readyToShoot.set(0, false);
                final IPoint point = player.Shoot();
                final Consequence consequence = this.controller.getIClientManager().Fire(point);
                player.InformConsequenceOfShoot(point, consequence);
            }
        }
    }

    @Override
    public void SetTrueReadyTOShoot() {
        this.readyToShoot.set(0,true);
    }

    @Override
    public void SetVictory() {
        this.victory.set(0, true);
        player.InformVictory();
    }
    @Override
    public void SetDefeat(){
        player.InformDefeat();
    }
    @Override
    public Victory GetVictory(){
        if(this.GetIfLost())
            return Victory.defeat;
        if(this.victory.get(0))
            return  Victory.victory;
        return Victory.undetermined;
    }
}
