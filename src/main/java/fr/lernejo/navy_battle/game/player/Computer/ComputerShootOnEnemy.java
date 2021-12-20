package fr.lernejo.navy_battle.game.player.Computer;

import fr.lernejo.navy_battle.Constant;
import fr.lernejo.navy_battle.game.point.IPoint;
import fr.lernejo.navy_battle.game.point.Point;

import java.util.HashMap;
import java.util.Map;

public class ComputerShootOnEnemy implements IComputerShootOnEnemy {
    final private Map<Character,Integer> lastShoot;
    public ComputerShootOnEnemy(){
        this.lastShoot = new HashMap<>();
        lastShoot.put('x', -1);
        lastShoot.put('y',0);
    }
    @Override
    public IPoint shoot() {
        return nexPoint();
    }
    private IPoint nexPoint(){
        final Constant constant = new Constant();
        constant.GetSizeOfBoard();
        if(lastShoot.get('x')+1 >= constant.GetSizeOfBoard()) {
            this.lastShoot.put('x',0);
            if(this.lastShoot.get('y')+1 >= constant.GetSizeOfBoard()) {
                this.lastShoot.put('y', 0);
            } else{
                this.lastShoot.put('y', this.lastShoot.get('y')+1);
            }
        } else{
            this.lastShoot.put('x', this.lastShoot.get('x')+1);
        }
        return new Point(this.lastShoot.get('x'), this.lastShoot.get('y'));
    }
}
