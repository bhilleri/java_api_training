package fr.lernejo.navy_battle.game;

import java.util.concurrent.TimeUnit;

public class RunnerSetEnd implements Runnable{
    private IGame game;
    public RunnerSetEnd(IGame game){
        this.game = game;
    }
    @Override
    public void run() {
        try {

            TimeUnit.MILLISECONDS.sleep(500);
            game.SetVictory();
            TimeUnit.MILLISECONDS.sleep(500);
            game.SetTrueReadyTOShoot();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
