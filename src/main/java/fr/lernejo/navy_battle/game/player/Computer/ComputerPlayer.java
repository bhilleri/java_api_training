package fr.lernejo.navy_battle.game.player.Computer;

import fr.lernejo.navy_battle.ColorConsole;
import fr.lernejo.navy_battle.Constant;
import fr.lernejo.navy_battle.enumeration.Consequence;
import fr.lernejo.navy_battle.game.player.IPlayer;
import fr.lernejo.navy_battle.game.point.IPoint;

import java.util.ArrayList;
import java.util.List;

public class ComputerPlayer implements IPlayer {
    private final int sizeOfBoard;
    private final IComputerShootOnEnemy computerShootOnEnemy;
    private final IComputerSetBoatPosition computerSetBoatPosition;
    private final ColorConsole color = new ColorConsole();
    public ComputerPlayer(){
        sizeOfBoard = new Constant().GetSizeOfBoard();
        computerSetBoatPosition = new ComputerSetBoatPosition();
        this.computerShootOnEnemy = new ComputerShootOnEnemy();
    }
    @Override
    public List<IPoint> PositionABoat(final int size) {
        return computerSetBoatPosition.PositionABoat(size);
    }

    @Override
    public IPoint Shoot() {
        final IPoint point = this.computerShootOnEnemy.shoot();
        return point;
    }
    @Override
    public String InformEnemySHoot(IPoint point, Consequence consequence)
    {
        final String information = color.Red() + "L'adversaire a tiré en : " + point.toString() + " conséquance :" + consequence.toString() + color.Reset();
        System.out.println(information);
        return information;
    }

    @Override
    public List<String> InformShipLost(List<String> shipNameList) {
        final List<String> information= new ArrayList<>();
        for (String shipName : shipNameList) {
            information.add(color.Purple() + "L'adversaire à coulé : " + shipName.toString() + color.Reset());
            System.out.println(information.get(information.size()-1));
        }
        return information;
    }

    @Override
    public String InformConsequenceOfShoot(IPoint point, Consequence consequence) {
        final String information = color.Green() + "Tir sur : " + point.toString() + " : " + consequence.toString() + color.Reset();
        System.out.println(information);
        return information;
    }

    @Override
    public String InformVictory() {
        final String information = "\n" + color.Green() + "Victoire" + color.Reset();
        System.out.println(information);
        return  information;
    }

    @Override
    public String InformDefeat() {
        final String information ="\n"+color.Red() + "Défaite" + color.Reset();
        System.out.println(information);
        return information;
    }

    @Override
    public String InformPlacement(String name, List<IPoint> pointList) {
        String information = "Position du " + name + " : " + pointList.toString();
        System.out.println(information);
        return information;
    }

}
