package fr.lernejo.navy_battle.game;

import fr.lernejo.navy_battle.enumeration.Consequence;
import fr.lernejo.navy_battle.enumeration.Victory;
import fr.lernejo.navy_battle.game.point.IPoint;

import java.net.UnknownHostException;

/**
 * Gère le déroulement de la partie
 */
public interface IGame {
    /**
     * Demande à la classe Board d'initialiser la position des navires
     */
    public void Initialize();

    /**
     * Chaque joueur doit attendre son tour avant de tirer.
     * @return Si l'utilisateur peut tirer
     */
    public boolean GetReadyTOShoot();

    /**
     * Reçoit un tir adverse et transmet l'information
     * @param point Case ciblée par l'adverse
     * @return Les consequences du tir
     */
    public Consequence receiveShoot(IPoint point);

    /**
     *
     * @return informe si l'utilisateur à perdu tout ces navires
     */
    public boolean GetIfLost();

    /**
     * Lance la boucle permettant de tirer jusqu'à la victoire ou la défaite
     * @throws UnknownHostException
     * @throws InterruptedException
     */
    public void Start() throws UnknownHostException, InterruptedException;

    /**
     * Informe à la classe Game que elle peut tirer
     */
    public void SetTrueReadyTOShoot();

    /**
     * Informe la classe Game d'une victoire -> arrêt du jeu
     */
    public void SetVictory();

    /**
     * Informe la classe Game d'une défaite -> arrêt du jeu
     */
    public void SetDefeat();

    /**
     *
     * @return permet de savoir si la partie est gagné, perdu ou en cours
     */
    public Victory GetVictory();
}
