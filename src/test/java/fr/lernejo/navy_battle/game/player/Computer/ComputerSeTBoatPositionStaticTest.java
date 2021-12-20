package fr.lernejo.navy_battle.game.player.Computer;

import fr.lernejo.navy_battle.game.point.IPoint;
import fr.lernejo.navy_battle.game.point.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ComputerSetBoatPositionStaticTest {

    private ComputerSetBoatPositionStatic computerSetBoatPositionStatic;
    @BeforeEach
    public void Initialize(){
        computerSetBoatPositionStatic = new ComputerSetBoatPositionStatic();
    }
    @Test
    void positionABoat() {
        int size1 = 2;
        int size2 = 3;
        List <IPoint> expected1 = new ArrayList<>();
        expected1.add(new Point(0,0));
        expected1.add(new Point(0,1));
        List <IPoint> expected2 = new ArrayList<>();
        expected2.add(new Point(1,0));
        expected2.add(new Point(1,1));
        List<IPoint> shiPosition1 = computerSetBoatPositionStatic.PositionABoat(size1);
        List<IPoint> shiPosition2 = computerSetBoatPositionStatic.PositionABoat(size2);
        Assertions.assertEquals(size1, shiPosition1.size());
        Assertions.assertEquals(size2, shiPosition2.size());
        Assertions.assertTrue(shiPosition1.containsAll(expected1));
        Assertions.assertTrue(shiPosition2.containsAll(expected2));
    }
}
