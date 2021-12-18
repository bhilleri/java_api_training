package fr.lernejo.navy_battle;

import fr.lernejo.navy_battle.Init.IInitConnectPlayer;
import fr.lernejo.navy_battle.Init.IInitWaitingPlayer;
import fr.lernejo.navy_battle.Init.InitConnectPlayer;
import fr.lernejo.navy_battle.Init.InitWaitingPlayer;

/**
 * Classe contenant le Main
 */
public class Launcher {
    /**
     * Point d'entrée dans le programme
     */
    public static void main(String [] args)
    {
        final int port = Integer.parseInt(args[0]);
        if(args.length == 1)
        {
            final IInitWaitingPlayer initWaitingPlayer = new InitWaitingPlayer();
            initWaitingPlayer.launch(port);
        }
        else{
            final String uri = args[1];
            final IInitConnectPlayer initWaitingPlayer = new InitConnectPlayer();
            initWaitingPlayer.launch(port, uri);
        }
    }
}
