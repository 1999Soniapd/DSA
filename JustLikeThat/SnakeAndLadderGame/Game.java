package JustLikeThat.SnakeAndLadderGame;

import java.util.HashMap;

public class Game {

    int[] board;
    Players[] players ;
    HashMap<Integer, Integer> snakeAndLadder;

    public Game(int size, Players[] listOfPlayers)
    {
        board = new int[size];
        players = listOfPlayers;
//        players = new Players[2];
//        players[0] = new Players("Player1");
//        players[1] = new Players("Player2");
        //players = new Players[noOfPlayers];
        initializeSnakesAndLadders();
    }

    public void initializeSnakesAndLadders()
    {
        snakeAndLadder = new HashMap<>();
        snakeAndLadder.put(5,9);
        snakeAndLadder.put(10, 20);
        snakeAndLadder.put(80, 95);
        snakeAndLadder.put(50, 30);
        snakeAndLadder.put(99, 78);
    }

    public boolean rollDice(Players player)
    {
        int dice = (int)(Math.random() * 6) + 1;
        return updatePlayerPosition(player, dice);
    }

    public boolean updatePlayerPosition(Players player, int dice)
    {
        if(player.position + dice > board.length)
        {
            System.out.println(player.playerName + " Dice roll : " + dice + " Goes out of the board");
        }
        else {
            player.position = player.position + dice;
            // check if there is a snake or ladder at the new position

            if(snakeAndLadder.containsKey(player.position))
            {
                if(snakeAndLadder.get(player.position) > player.position)
                {
                    // ladder
                    System.out.println(player.playerName + " Dice roll : " + dice + " New Position : " + player.position + " You got a ladder !!");
                }
                else
                {
                    System.out.println(player.playerName + " Dice roll : " + dice + " New Position : " + player.position + " Oops you were eaten by snake");
                }
                player.position = snakeAndLadder.get(player.position);

            }
            else
            {
                System.out.println(player.playerName + " Dice roll : " + dice + " New Position : " + player.position);
            }

        }
        return  checkWinner(player);
    }

    public boolean checkWinner(Players player)
    {
        if(player.position == board.length)
        {
            System.out.println(player.playerName + " Won");
            return true;
        }


        return false;
    }
}

