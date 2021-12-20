package fr.lernejo.navy_battle.game.player.Computer;

import fr.lernejo.navy_battle.ColorConsole;
import fr.lernejo.navy_battle.Constant;
import fr.lernejo.navy_battle.enumeration.Consequence;
import fr.lernejo.navy_battle.game.point.IPoint;
import fr.lernejo.navy_battle.game.point.Point;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ComputerPlayerTest {
    private ComputerPlayer player;

    @BeforeEach
    public void initialisation(){
        player = new ComputerPlayer();
    }

    @Test
    void positionABoat() {
        int size = 3;
        List<IPoint> pointList = player.PositionABoat(size);
        Assertions.assertEquals(size, pointList.size());
    }

    @Test
    void shoot() {
        IPoint point = player.Shoot();
        Assertions.assertNotNull(point);
        Assertions.assertTrue(point.getX() >=0);
        Assertions.assertTrue(point.getY()>=0);
        Assertions.assertTrue(point.getX()< new Constant().GetSizeOfBoard());
        Assertions.assertTrue(point.getY()< new Constant().GetSizeOfBoard());
    }

    @Test
    void informEnemySHoot() {
        ColorConsole color = new ColorConsole();
        int xPoint= 5;
        int yPoint = 4;
        IPoint point = new Point(xPoint,yPoint);
        Consequence consequence = Consequence.miss;
        String expectedResult = color.Red() + "L'adversaire a tiré en : " + "F5" + " conséquance :" + consequence.toString() + color.Reset();
        String information = player.InformEnemySHoot(point, consequence);
        Assertions.assertEquals(expectedResult, information);
    }

    @Test
    void informShipLost() {
        ColorConsole color = new ColorConsole();
        List<String> shipName = new ArrayList<>();
        List<String> information;
        List<String> expectedResult = new ArrayList<>();
        String name1 = "destroyer";
        String name2 = "Titanic";
        expectedResult.add(color.Purple() + "L'adversaire à coulé : " + name1.toString() + color.Reset());
        expectedResult.add(color.Purple() + "L'adversaire à coulé : " + name2.toString() + color.Reset());
        shipName.add(name1);
        shipName.add(name2);
        information = player.InformShipLost(shipName);
        assertEquals(2, information.size());
        assertTrue(information.containsAll(expectedResult));
    }

    @Test
    void informConsequenceOfShoot() {
        ColorConsole color = new ColorConsole();
        IPoint point = new Point(5,5);
        Consequence consequence = Consequence.sunk;
        String expectedResult = color.Green() + "Tir sur : " + "F6" + " : " + consequence.toString() + color.Reset();
        String information = player.InformConsequenceOfShoot(point, consequence);
        Assertions.assertEquals(information, expectedResult);
    }

    @Test
    void informVictory() {
        ColorConsole color = new ColorConsole();
        String expectedResult = "\n" + color.Green() + "Victoire" + color.Reset();
        String information = player.InformVictory();
        Assertions.assertEquals(expectedResult, information);
    }

    @Test
    void informDefeat() {
        ColorConsole color = new ColorConsole();
        String expectedResult = "\n"+color.Red() + "Défaite" + color.Reset();
        String information = player.InformDefeat();
        Assertions.assertEquals(expectedResult, information);
    }

    @Test
    void informPlacement() {
        String name = "";
        List <IPoint> pointList = new ArrayList<>();
        pointList.add(new Point(1,2));
        pointList.add(new Point(1,3));
        String expectedResult =  "Position du " + name +" : [B3, B4]";
        String information = player.InformPlacement(name, pointList);
        Assertions.assertEquals(expectedResult, information);

    }
}
