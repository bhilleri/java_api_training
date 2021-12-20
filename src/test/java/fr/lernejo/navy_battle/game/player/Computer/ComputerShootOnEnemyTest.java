package fr.lernejo.navy_battle.game.player.Computer;

import fr.lernejo.navy_battle.Constant;
import fr.lernejo.navy_battle.game.point.IPoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComputerShootOnEnemyTest {
    private static ComputerShootOnEnemy computerShootOnEnemy = new ComputerShootOnEnemy();
    private static int count =0;
    @RepeatedTest(101)
    void shoot() {
        IPoint point = computerShootOnEnemy.shoot();
        Assertions.assertEquals((count%100)%10, point.getX());
        Assertions.assertEquals((int)((count%100)/10), point.getY());
        count++;
    }
}
