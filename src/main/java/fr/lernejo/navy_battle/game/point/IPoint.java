package fr.lernejo.navy_battle.game.point;

/**
 * Point correspondant à une case sur le plateau
 */
public interface IPoint {
    /**
     * Numéro de la colonne
     * @return
     */
    public int getX();

    /**
     * Numéro de la ligne
     * @return
     */
    public int getY();
}
