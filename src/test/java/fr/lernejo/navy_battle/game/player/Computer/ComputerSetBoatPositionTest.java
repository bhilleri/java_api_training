package fr.lernejo.navy_battle.game.player.Computer;

import fr.lernejo.navy_battle.Constant;
import fr.lernejo.navy_battle.game.point.IPoint;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Random;

public class ComputerSetBoatPositionTest {
    ComputerSetBoatPosition computerSetBoatPosition;
    @BeforeEach
    public void initialisation(){
        computerSetBoatPosition = new ComputerSetBoatPosition();
    }

    @DisplayName("Test set position of boat with random")
    @RepeatedTest(15)// To explore all path
    public void PositionABoatTest(){
        Random random = new Random();
        int size = random.nextInt(2,5);
        List<IPoint> pointList = computerSetBoatPosition.PositionABoat(size);
        for (IPoint point : pointList) {
            Assertions.assertNotNull(point);
            Assertions.assertTrue(point.getY()>=0);
            Assertions.assertTrue(point.getX()>=0);
            Assertions.assertTrue(point.getX()< new Constant().GetSizeOfBoard());
            Assertions.assertTrue(point.getY()< new Constant().GetSizeOfBoard());
        }
        Assertions.assertEquals(size, pointList.size());
    }

    @RepeatedTest(15)
    public void GenerateARow(){
        Random random = new Random();
        int initialX = random.nextInt(0,10);
        int initialY = random.nextInt(0,10);
        int size = random.nextInt(2,5);
        List <IPoint> pointList = computerSetBoatPosition.GenerateARow(initialX, initialY, size);
        Assertions.assertEquals(size, pointList.size());
        for (int i = 0; i < pointList.size(); i++) {
            Assertions.assertEquals(initialX +i, pointList.get(i).getX());
            Assertions.assertEquals(initialY, pointList.get(i).getY());
        }
    }

    @RepeatedTest(15)
    public void GenerateAColumn(){
        Random random = new Random();
        int initialX = random.nextInt(0,10);
        int initialY = random.nextInt(0,10);
        int size = random.nextInt(2,5);
        List <IPoint> pointList = computerSetBoatPosition.GenerateAColumn(initialX, initialY, size);
        Assertions.assertEquals(size, pointList.size());
        for (int i = 0; i < pointList.size(); i++) {
            Assertions.assertEquals(initialX, pointList.get(i).getX());
            Assertions.assertEquals(initialY + i, pointList.get(i).getY());
        }
    }
}
