package fr.lernejo.navy_battle.game.player.Computer;

import fr.lernejo.navy_battle.Constant;
import fr.lernejo.navy_battle.game.point.IPoint;
import fr.lernejo.navy_battle.game.point.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerSetBoatPosition implements IComputerSetBoatPosition {
    private final int sizeOfBoard;
    ComputerSetBoatPosition(){
        sizeOfBoard = new Constant().GetSizeOfBoard();
    }
    @Override
    public List<IPoint> PositionABoat(final int size) {
        final int maxPosition = sizeOfBoard - size;
        Random rand = new Random();
        final boolean rowOrientation = rand.nextBoolean();
        final int Dim1 = rand.nextInt(maxPosition);
        final int Dim2 = rand.nextInt(sizeOfBoard);
        final List<IPoint> listPoint;
        if(rowOrientation) {
            listPoint = GenerateARow(Dim1,Dim2, size);
        }else{
            listPoint = GenerateAColumn(Dim2,Dim1, size);
        }
        return listPoint;
    }
    public List<IPoint> GenerateARow(final int initialX, final int initialY, final int size){
        List<IPoint> listPoint = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            listPoint.add(new Point(initialX + i, initialY));
        }
        return listPoint;
    }
    public List<IPoint> GenerateAColumn(final int initialX, final int initialY, final int size){
        List<IPoint> listPoint = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            listPoint.add(new Point(initialX, initialY+i));
        }
        return listPoint;
    }
}
