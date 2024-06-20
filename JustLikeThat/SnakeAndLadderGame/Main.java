package JustLikeThat.SnakeAndLadderGame;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Lets Play Snake and Ladder!");

        Scanner s = new Scanner(System.in);
        System.out.println("Enter the Game size");
        int sz = s.nextInt();

        System.out.println("Enter the number of players");
        int noOfPlayers = s.nextInt();

        System.out.println("Enter the names of the players");
        Players[] players = new Players[noOfPlayers];
        String name;
        for(int i = 0; i < noOfPlayers; i++)
        {
            name = s.next();
            players[i] = new Players(name);
        }

        Game snakeLadderGame = new Game(sz, players);
        boolean status;
        int i = 0;

        do {
            status = snakeLadderGame.rollDice(snakeLadderGame.players[i]);
            i = (i + 1) % (noOfPlayers-1);
        } while(!status);

       /* - 2 players
        - list 100
        - ladder starts->up
        - snake starts->down
        - dice - 1-6

        class player
        idx
        name

        class game
        list no
        Players n
        Map<start, end> snakeLadder
        randomfun()  6
        checkStatus()    */

    }
    }

