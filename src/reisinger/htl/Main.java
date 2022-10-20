package reisinger.htl;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        Scanner scan = new Scanner(System.in);
        char[][] board = new char[3][3];

        for (int q = 0; q < board.length; q++) {
            for (int i = 0; i < board.length; i++) {
                board[q][i] = ' ';
            }
        }

        /* board[1][1] = 'o';
        board[2][0] = 'x';
        board[0][2] = 'o';
        board[1][0] = 'o';
        board[1][2] = 'x';
        board[0][1] = 'x';
        board[2][1] = 'x';

        printBoard(board);
        Move m = main.findBestMove(board);
        board[m.getY()][m.getX()] = 'o';
        printBoard(board); */


        while (true) {
            System.out.println("Geben sie x ein");
            int x = scan.nextInt();
            System.out.println("Geben sie y ein");
            int y = scan.nextInt();
            board[y][x] = 'x';
            printBoard(board);
            Move m = main.findBestMove(board);
            board[m.getY()][m.getX()] = 'o';
            printBoard(board);
        }
    }

    static void printBoard(char[][] board) {
        for (char[] c:
             board) {
            for (char c2 :
                    c) {
                System.out.print(c2 + " | ");
            }
            System.out.println();
        }
    }

    Move findBestMove(char[][] board) {
        Move bestMove = new Move(-1, -1);
        int bestVal= -2;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ') {
                    System.out.println("getting movwval");
                    board[i][j] = 'o';
                    int moveVal = minimax(board, 0, false);
                    System.out.println(moveVal);
                    board[i][j] = ' ';
                    if (moveVal > bestVal)
                    {

                        bestMove.setY(i);
                        bestMove.setX(j);
                        System.out.println("found bether move: " + bestMove.getX() + "/" + bestMove.getY());
                        bestVal = moveVal;
                        }
                }
        }

        return bestMove;
    }

    int minimax(char[][] board, int depth, Boolean isMax) {
        int score = evaluate(board);
        if ( score == 1) return score;
        if ( score == -1) return score;
        if (!isMovesLeft(board)) return 0;

        if (isMax) {
            int best = Integer.MIN_VALUE;
            for ( int i = 0; i < 3; i++) {
                for ( int j = 0; j < 3; j++) {
                    if (board[ i ][ j ]==' ') {
                        board[ i ][ j ] = 'o';
                        best = Math.max(best, minimax(board, depth + 1, false));
                        board[ i ][ j ] = ' ' ;
                    }
                }
            }
            return best;
        } else {
            int worst = Integer.MAX_VALUE;
            for ( int i = 0; i < 3; i++) {
                for ( int j = 0; j < 3; j++) {
                    if (board[ i ][ j ]==' ') {
                        board[ i ][ j ] = 'x';
                        worst = Math.min(worst, minimax(board, depth + 1, true));
                        board[ i ][ j ] = ' ';
                    }
                }
            }
            return worst;
        }

    }

    int evaluate(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            if ((board[i][0] == 'x' && board[i][1] == 'x' &&board[i][2] == 'x')) {
                return -1;
            } else if ((board[i][0] == 'o' && board[i][1] == 'o' &&board[i][2] == 'o')) {
                return 1;
            }
        }

        for (int i = 0; i < board.length; i++) {
            if ((board[0][i] == 'x' && board[1][i] == 'x' &&board[2][i] == 'x')) {
                return -1;
            } else if ((board[0][i] == 'o' && board[1][i] == 'o' &&board[2][i] == 'o')) {
                return 1;
            }
        }

        if ((board[0][0] == 'x' && board[1][1] == 'x' && board[2][2] == 'x')) {
            return -1;
        } else if ((board[0][0] == 'o' && board[1][1] == 'o' && board[2][2] == 'o')) {
            return 1;
        }

        if ((board[2][0] == 'x' && board[1][1] == 'x' && board[0][2] == 'x')) {
            return -1;
        } else if ((board[2][0] == 'o' && board[1][1] == 'o' &&board[0][2] == 'o')) {
            return 1;
        }

        return -2;
    }

    boolean isMovesLeft(char[][] board) {
        for (char[] c :
                board) {
            for (char c2 :
            c) {
                if (c2 == ' ') {
                    return true;
                }
            }
        }

        return false;
    }

}
