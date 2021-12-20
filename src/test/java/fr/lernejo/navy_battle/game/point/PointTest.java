package fr.lernejo.navy_battle.game.point;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    private int XPoint1 = 5;
    private int YPoint1 = 2;
    private int XPoint2 = 4;
    private int YPoint2 = 6;
    private Point point1;
    private Point point2;

    @BeforeEach
    public void Initialize(){
        point1 = new Point(XPoint1, YPoint1);
        point2 = new Point(XPoint2, YPoint2);
    }
    @Test
    void getX() {
        Assertions.assertEquals(XPoint1, point1.getX());
        Assertions.assertEquals(XPoint2, point2.getX());
    }

    @Test
    void getY() {
        Assertions.assertEquals(YPoint1, point1.getY());
        Assertions.assertEquals(YPoint2, point2.getY());
    }

    @Test
    void testEqualsTrue() {
        Point point3 = new Point(XPoint1, YPoint1);
        Assertions.assertTrue(point3.equals(point1));
        Assertions.assertTrue(point1.equals(point3));
        Assertions.assertFalse(point1 == point3);
    }

    @Test
    void testEqualsFalse() {
        Assertions.assertFalse(point2.equals(point1));
        Assertions.assertFalse(point1.equals(point2));
        Assertions.assertFalse(point1 == point2);
    }

    @Test
    void testToString() {
        String string1Excepted = "F3";
        String string2Excepted = "E7";
        Assertions.assertEquals(string1Excepted, point1.toString());
        Assertions.assertEquals(string2Excepted, point2.toString());
    }
}
