package fr.lernejo.navy_battle.game;

import java.util.concurrent.TimeUnit;

public class RunnerSetReadyToPlay implements Runnable {
    private IGame game;
    public RunnerSetReadyToPlay(IGame game)
    {
        this.game = game;
    }
    @Override
    public void run() {
        try {
            TimeUnit.MICROSECONDS.sleep(200);
            game.SetTrueReadyTOShoot();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
