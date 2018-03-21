import java.io.*;

/**
 *
 * @author James Spargo
 * This class controls the game play for the Max Connect-Four game.
 * To compile the program, use the following command from the maxConnectFour directory:
 * javac *.java
 *
 * the usage to run the program is as follows:
 * ( again, from the maxConnectFour directory )
 *
 *  -- for interactive mode:
 * java MaxConnectFour interactive [ input_file ] [ computer-next / human-next ] [ search depth]
 *
 * -- for one move mode
 * java maxConnectFour.MaxConnectFour one-move [ input_file ] [ output_file ] [ search depth]
 *
 * description of arguments:
 *  [ input_file ]
 *  -- the path and filename of the input file for the game
 *
 *  [ computer-next / human-next ]
 *  -- the entity to make the next move. either computer or human. can be abbreviated to either C or H. This is only used in interactive mode
 *
 *  [ output_file ]
 *  -- the path and filename of the output file for the game.  this is only used in one-move mode
 *
 *  [ search depth ]
 *  -- the depth of the minimax search algorithm
 *
 *
 */

public class maxconnect4 {

  public static void main(String[] args) {
    // check for the correct number of arguments
    if ( args.length != 4 ) {
      System.out.println("Four command-line arguments are needed:\n"
                         + "Usage: java [program name] interactive [input_file] [computer-next / human-next] [depth]\n"
                         + " or:  java [program name] one-move [input_file] [output_file] [depth]\n");

      exit_function( 0 );
    }

    // parse the input arguments
    String game_mode = args[0].toString();        // the game mode
    String input = args[1].toString();          // the input game file
    int depthLevel = Integer.parseInt( args[3] );     // the depth level of the ai search

    // create and initialize the game board
    GameBoard currentGame = new GameBoard( input );

    // create the Ai Player
    AiPlayer calculon = new AiPlayer();

    //  variables to keep up with the game
    int playColumn = 99;        //  the players choice of column to play
    boolean playMade = false;     //  set to true once a play has been made

    if ( game_mode.equalsIgnoreCase( "interactive" ) ) {
      //System.out.println("interactive mode is currently not implemented\n");
      playInteractiveGame(currentGame, depthLevel, game_mode);

      return;
    } else if ( !game_mode.equalsIgnoreCase( "one-move" ) ) {
      System.out.println( "\n" + game_mode + " is an unrecognized game mode \n try again. \n" );
      return;
    }

    /////////////   one-move mode ///////////
    // get the output file name
    String output = args[2].toString();       // the output game file

    System.out.print("\nMaxConnect-4 game\n");
    System.out.print("game state before move:\n");

    //print the current game board
    currentGame.printGameBoard();
    // print the current scores
    System.out.println( "Score: Player 1 = " + currentGame.getScore( 1 ) +
                        ", Player2 = " + currentGame.getScore( 2 ) + "\n " );

    // ****************** this chunk of code makes the computer play
    if ( currentGame.getPieceCount() < 42 ) {
      int current_player = currentGame.getCurrentTurn();
      // AI play - random play
      playColumn = calculon.findBestPlay( currentGame );

      // play the piece
      currentGame.playPiece( playColumn );

      // display the current game board
      System.out.println("move " + currentGame.getPieceCount()
                         + ": Player " + current_player
                         + ", column " + playColumn);
      System.out.print("game state after move:\n");
      currentGame.printGameBoard();

      // print the current scores
      System.out.println( "Score: Player 1 = " + currentGame.getScore( 1 ) +
                          ", Player2 = " + currentGame.getScore( 2 ) + "\n " );

      currentGame.printGameBoardToFile( output );
    } else {
      System.out.println("\nI can't play.\nThe Board is Full\n\nGame Over");
    }

    //************************** end computer play


    return;

  } // end of main()

  private static void playInteractiveGame(GameBoard currentGame, int depthLevel, String game_mode) {
    //currentGame = minimax(currentGame, currentGame.getCurrentTurn(), Integer.MIN_VALUE, Integer.MAX_VALUE, depthLevel);
    //currentGame.printGameBoard();
    // int current_player = currentGame.getCurrentTurn();
    // for (currentGame.getPieceCount() < 42) {
    //   // display the current game board
    //   System.out.println("move " + currentGame.getPieceCount()
    //                      + ": Player " + current_player);
    //   //+ ", column " + playColumn);
    //   System.out.print("game state after move:\n");
    //   currentGame.printGameBoard();

    //   // print the current scores
    //   System.out.println( "Score: Player 1 = " + currentGame.getScore( 1 ) +
    //                       ", Player2 = " + currentGame.getScore( 2 ) + "\n " );
    //   if (game_mode.equalsIgnoreCase("computer-next")) {
    //     currentGame = minimax(currentGame, current_player, Integer.MIN_VALUE, Integer.MAX_VALUE, depthLevel);
    //     currentGame.printGameBoardToFile( "computer.txt" );
    //     game_mode = "human-next";
    //   } else {
        //human shit
        //currentGame = minimax(currentGame, current_player, Integer.MIN_VALUE, Integer.MAX_VALUE, depthLevel);
        // System.out.println("Your move (1-7): ");
        // int move = scan.nextInt();
        // while (move < 1 || move > 7 || !currentGame.isValidPlay(move - 1)) {
        //   System.out.println("Invalid move.\n\nYour move (1-7): ");
        //   move = scan.nextInt();
        // }

        // //Assume 2 is the opponent
        // currentGame.playPiece(move - 1, (byte)2);
        // currentGame.printGameBoardToFile( "human.txt" );
        // game_mode = "computer-next";
      }
    }
    /*
    function alphabeta(node, depth, α, β, maximizingPlayer)
    02      if depth = 0 or node is a terminal node
    03          return the heuristic value of node
    04      if maximizingPlayer
    05          v := -∞
    06          for each child of node
    07              v := max(v, alphabeta(child, depth – 1, α, β, FALSE))
    08              α := max(α, v)
    09              if β ≤ α
    10                  break (* β cut-off *)
    11          return v
    12      else
    13          v := +∞
    14          for each child of node
    15              v := min(v, alphabeta(child, depth – 1, α, β, TRUE))
    16              β := min(β, v)
    17              if β ≤ α
    18                  break (* α cut-off *)
    19          return v
    */


  }

  public static GameBoard minimax(GameBoard gb, int turn, int alpha, int beta, int depth) {
    GameBoard finalGameBoard = new GameBoard(gb.getGameBoard());
    if (depth == 0) {
      return finalGameBoard;
    }
    for (int i = 0; i < 7; i++) {
      if (!gb.isValidPlay(i)) continue;
      GameBoard currentGameBoard = new GameBoard(gb.getGameBoard());
      gb.playPiece(i);
      if (turn == 1) {
        //comp
        currentGameBoard = minimax(new GameBoard(gb.getGameBoard()), 2, alpha, beta, depth - 1);
        //currentGameBoard.printGameBoard();
        if (currentGameBoard.getScore(1) > alpha) {
          alpha = currentGameBoard.getScore(1);
          finalGameBoard = new GameBoard(gb.getGameBoard()); // be careful
          //currentGameBoard.printGameBoard();
          //finalGameBoard.printGameBoard();
          //System.out.println("hi");
        }
        if (alpha >= beta) break;
      } else {
        //human
        currentGameBoard = minimax(new GameBoard(gb.getGameBoard()), 1, alpha, beta, depth - 1);
        //currentGameBoard.printGameBoard();
        if (currentGameBoard.getScore(2) < beta) {
          beta = currentGameBoard.getScore(2);
          finalGameBoard = new GameBoard(gb.getGameBoard());
          //finalGameBoard.printGameBoard();
        }
        if (alpha >= beta) break;
      }
      gb.removePiece(i);
    }
    return finalGameBoard;
  }

  // public GameBoard minimax(int depth, int turn, int alpha, int beta, int maxDepth) {
  //   if (depth == maxDepth) {
  //     return currScore;
  //   }
  //   int maxScore = Integer.MIN_VALUE, minScore = Integer.MAX_VALUE;
  //   for (int j = 0; j <= 6; ++j) {

  //     int currentScore = 0;

  //     if (!b.isLegalMove(j)) continue;

  //     if (turn == 1) {
  //       b.placeMove(j, 1);
  //       currentScore = minimax(depth + 1, 2, alpha, beta);

  //       if (depth == 0) {
  //         System.out.println("Score for location " + j + " = " + currentScore);
  //         if (currentScore > maxScore)nextMoveLocation = j;
  //         if (currentScore == Integer.MAX_VALUE / 2) {b.undoMove(j); break;}
  //       }

  //       maxScore = Math.max(currentScore, maxScore);

  //       alpha = Math.max(currentScore, alpha);
  //     } else if (turn == 2) {
  //       b.placeMove(j, 2);
  //       currentScore = minimax(depth + 1, 1, alpha, beta);
  //       minScore = Math.min(currentScore, minScore);

  //       beta = Math.min(currentScore, beta);
  //     }
  //     b.undoMove(j);
  //     if (currentScore == Integer.MAX_VALUE || currentScore == Integer.MIN_VALUE) break;
  //   }
  // }

  // public int minimax(int depth, int turn, int alpha, int beta) {

  //   if (beta <= alpha) {if (turn == 1) return Integer.MAX_VALUE; else return Integer.MIN_VALUE; }
  //   int gameResult = gameResult(b);

  //   if (gameResult == 1)return Integer.MAX_VALUE / 2;
  //   else if (gameResult == 2)return Integer.MIN_VALUE / 2;
  //   else if (gameResult == 0)return 0;

  //   if (depth == maxDepth)return evaluateBoard(b);

  //   int maxScore = Integer.MIN_VALUE, minScore = Integer.MAX_VALUE;

  //   for (int j = 0; j <= 6; ++j) {

  //     int currentScore = 0;

  //     if (!b.isLegalMove(j)) continue;

  //     if (turn == 1) {
  //       b.placeMove(j, 1);
  //       currentScore = minimax(depth + 1, 2, alpha, beta);

  //       if (depth == 0) {
  //         System.out.println("Score for location " + j + " = " + currentScore);
  //         if (currentScore > maxScore)nextMoveLocation = j;
  //         if (currentScore == Integer.MAX_VALUE / 2) {b.undoMove(j); break;}
  //       }

  //       maxScore = Math.max(currentScore, maxScore);

  //       alpha = Math.max(currentScore, alpha);
  //     } else if (turn == 2) {
  //       b.placeMove(j, 2);
  //       currentScore = minimax(depth + 1, 1, alpha, beta);
  //       minScore = Math.min(currentScore, minScore);

  //       beta = Math.min(currentScore, beta);
  //     }
  //     b.undoMove(j);
  //     if (currentScore == Integer.MAX_VALUE || currentScore == Integer.MIN_VALUE) break;
  //   }
  //   return turn == 1 ? maxScore : minScore;
  // }



  /**
   * This method is used when to exit the program prematurly.
   * @param value an integer that is returned to the system when the program exits.
   */
  private static void exit_function( int value ) {
    System.out.println("exiting from MaxConnectFour.java!\n\n");
    System.exit( value );
  }
} // end of class connectFour
