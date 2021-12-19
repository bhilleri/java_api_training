package fr.lernejo.navy_battle.game;

import fr.lernejo.navy_battle.Controller;
import fr.lernejo.navy_battle.IController;
import fr.lernejo.navy_battle.enumeration.Consequence;
import fr.lernejo.navy_battle.game.board.Board;
import fr.lernejo.navy_battle.game.board.IBoard;
import fr.lernejo.navy_battle.game.player.Computer.ComputerPlayer;
import fr.lernejo.navy_battle.game.player.IPlayer;
import fr.lernejo.navy_battle.game.point.IPoint;
import fr.lernejo.navy_battle.game.point.Point;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Game implements  IGame{
    private final IPlayer player;
    private final List<Boolean> readyToShoot;
    private final IController controller;
    private final IBoard board;
    private final List<Boolean>victoire;
    public Game(IController controller, boolean readyToShoot){
        this.player = new ComputerPlayer();
        this.controller = controller;
        board = new Board(controller, player);
        this.readyToShoot = new ArrayList<>();
        this.readyToShoot.add(readyToShoot);
        this.victoire = new ArrayList<>();
        this.victoire.add(false);
    }

    @Override
    public void Initialize() {
        board.InitializeBoats();

    }
    @Override
    public void Shoot()
    {
        try {
            while(controller.getIClientManager().getAddress().length() == 0){
                TimeUnit.MICROSECONDS.sleep(2);
            }
            controller.getIClientManager().Fire(new Point(3,3));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
    public void Start() throws UnknownHostException {
        while (this.GetIfLost() == false && victoire.get(0) == false) {
            try {
                while (readyToShoot.get(0) == false) {
                    TimeUnit.MICROSECONDS.sleep(2);
                }
                this.readyToShoot.set(0, false);
                final IPoint point = player.Shoot();
                final Consequence consequence= this.controller.getIClientManager().Fire(point);
                player.InformConsequenceOfShoot(point, consequence);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void SetTrueReadyTOShoot() {
        this.readyToShoot.set(0,true);
    }

    @Override
    public void SetVictory() {
        this.victoire.set(0, true);
        player.InformVictory();
    }
}
