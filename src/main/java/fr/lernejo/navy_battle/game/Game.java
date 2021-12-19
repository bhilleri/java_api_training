package fr.lernejo.navy_battle.game;

import fr.lernejo.navy_battle.Controller;
import fr.lernejo.navy_battle.IController;
import fr.lernejo.navy_battle.game.board.Board;
import fr.lernejo.navy_battle.game.board.IBoard;
import fr.lernejo.navy_battle.game.player.Computer.ComputerPlayer;
import fr.lernejo.navy_battle.game.player.IPlayer;
import fr.lernejo.navy_battle.game.point.Point;

import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class Game implements  IGame{
    private final IPlayer player;
    private final IController controller;
    private final IBoard board;
    public Game(IController controller){
        this.player = new ComputerPlayer();
        this.controller = controller;
        board = new Board(controller, player);
    }

    @Override
    public void Initialize() {
        board.InitializeBoats();

    }
    @Override
    public void Fire()
    {
        try {
            while(controller.getIClientManager().getAddress().length() == 0){
                TimeUnit.MICROSECONDS.sleep(50);
            }
            controller.getIClientManager().Fire(new Point(3,3));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
