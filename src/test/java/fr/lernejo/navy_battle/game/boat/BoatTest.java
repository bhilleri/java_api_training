package fr.lernejo.navy_battle.game.boat;

import fr.lernejo.navy_battle.Controller;
import fr.lernejo.navy_battle.enumeration.Consequence;
import fr.lernejo.navy_battle.game.point.IPoint;
import fr.lernejo.navy_battle.game.point.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.mock;


class BoatTest {

    private final String name = "Aircraft carrier";
    private final int size = 5;
    private Boat boat;
    private List<IPoint> pointList;
    private final IPoint point1 = new Point(4,6);
    private final IPoint point2 = new Point(5,6);
    private final IPoint point3 =new Point(6,6);
    private final IPoint point4 = new Point(7,6);
    private final IPoint point5 = new Point(8,6);
    @BeforeEach
    public void initialize()
    {
        boat = new Boat(name, size);
        this.pointList = new ArrayList<>();
        this.pointList.add(point1);
        this.pointList.add(point2);
        this.pointList.add(point3);
        this.pointList.add(point4);
        this.pointList.add(point5);
    }

    @Test
    void setPositionCorrect() {
        boolean booleanResult = boat.SetPosition(this.pointList);
        Point pointTest1 = new Point(4,6);
        Point pointTest2 = new Point(5,6);
        Point pointTest3 =new Point(6,6);
        Point pointTest4 = new Point(7,6);
        Point pointTest5 = new Point(8,6);
        List <IPoint> listResult = this.boat.GetPosition();
        Assertions.assertTrue(booleanResult);
        Assertions.assertEquals(5, listResult.size());
        Assertions.assertEquals(pointTest1, listResult.get(0));
        Assertions.assertEquals(pointTest2, listResult.get(1));
        Assertions.assertEquals(pointTest3, listResult.get(2));
        Assertions.assertEquals(pointTest4, listResult.get(3));
        Assertions.assertEquals(pointTest5, listResult.get(4));
    }
    @Test
    void setPositionNotCorrect(){
        this.pointList.remove(point5);
        boolean booleanResult = boat.SetPosition(this.pointList);
        List <IPoint> listResult = this.boat.GetPosition();
        Assertions.assertFalse(booleanResult);
        Assertions.assertEquals(0, listResult.size());
    }

    @Test
    void DestroyHit() {
        boolean booleanResult = boat.SetPosition(this.pointList);
        boat.hit(new Point(4,6));
        boat.hit(new Point(5,6));
        boat.hit(new Point(6,6));
        boat.hit(new Point(7,6));
        Consequence consequence = boat.hit(new Point(8,6));
        List <IPoint> listResult = this.boat.GetPosition();
        Assertions.assertTrue(booleanResult);
        Assertions.assertEquals(Consequence.sunk,consequence);
        Assertions.assertEquals(0, listResult.size());
    }

    @Test
    void OneHit() {
        boolean booleanResult = boat.SetPosition(this.pointList);
        Point pointTest1 = new Point(4,6);
        Consequence consequence = boat.hit(pointTest1);
        List <IPoint> listResult = this.boat.GetPosition();
        boolean ifNotPresent = !(listResult.contains(pointTest1));
        Assertions.assertTrue(booleanResult);
        Assertions.assertEquals(Consequence.hint,consequence);
        Assertions.assertTrue(ifNotPresent);
        Assertions.assertEquals(4, listResult.size());
    }

    @Test
    void MissedHit() {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(4,6));
        pointList.add(new Point(5,6));
        pointList.add(new Point(6,6));
        pointList.add(new Point(7,6));
        pointList.add(new Point(8,6));
        boolean booleanResult = boat.SetPosition(this.pointList);
        Point pointTest1 = new Point(4,3);
        Consequence consequence = boat.hit(pointTest1);
        List <IPoint> listResult = this.boat.GetPosition();

        Assertions.assertTrue(booleanResult);
        Assertions.assertEquals(Consequence.miss,consequence);
        for(int i =0; i < 5; i++){
            boolean assertion = listResult.get(i).equals(pointList.get(i));
            Assertions.assertTrue(assertion);
        }
        Assertions.assertEquals(5, listResult.size());
    }

    @Test
    void isPresentAtAPoint() {
        Point pointTest1 = new Point(4,6);
        Point pointTest2 = new Point(5,6);
        Point pointTest3 =new Point(6,6);
        Point pointTest4 = new Point(7,6);
        Point pointTest5 = new Point(8,6);
        Point pointAbsent6 = new Point(1,7);
        boolean booleanResult = boat.SetPosition(this.pointList);
        Assertions.assertTrue(boat.IsPresentAtAPoint(pointTest1));
        Assertions.assertTrue(boat.IsPresentAtAPoint(pointTest2));
        Assertions.assertTrue(boat.IsPresentAtAPoint(pointTest3));
        Assertions.assertTrue(boat.IsPresentAtAPoint(pointTest4));
        Assertions.assertTrue(boat.IsPresentAtAPoint(pointTest5));
        Assertions.assertFalse(boat.IsPresentAtAPoint(pointAbsent6));
    }

    @Test
    void getName() {
        String nameOfBoat= boat.getName();
        Assertions.assertEquals(name, nameOfBoat);
    }
    @Test
    void getSize(){
        int sizeOfBoat= boat.GetSize();
        Assertions.assertEquals(size, sizeOfBoat);
    }
}
