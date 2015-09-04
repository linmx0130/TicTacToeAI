package com.sweetdum.tictactoe;
import java.util.Scanner;
/**
 * Created by sweetdum on 2015/9/4.
 */
public class ConsoleMain {
    private static int chessBoardSize=4;
    public static void showBoard(ChessBoard cb){
        System.out.print("|");
        for (int i=0;i<cb.getBoardSize();++i){
            System.out.print("---|");
        }
        System.out.println();

        for (int i=0;i<cb.getBoardSize();++i){
            System.out.print("|");
            for (int j=0;j<cb.getBoardSize();++j){
                String s=null;
                switch (cb.getBoard(i,j)) {
                    case 0:
                        s = " " + cb.getScore(i, j) + " |";
                        break;
                    case 1:
                        s = " x |";
                        break;
                    case 2:
                        s = " o |";
                        break;
                }
                System.out.print(s);
            }
            System.out.println();

            System.out.print("|");
            for (int j=0;j<cb.getBoardSize();++j){
                System.out.print("---|");
            }
            System.out.println();
        }
    }
    public static void main(String args[]){
        ChessBoard chessBoard= new ChessBoard(chessBoardSize);
        int step =chessBoardSize*chessBoardSize;
        if ((step & 1) ==1 ) step--;
        Scanner cin = new Scanner(System.in);
        TicTacToeAI ai=new TicTacToeAI(chessBoard);
        for (int i=0;i<step;++i){
            if ( (i & 1) ==0 ){
                //player's step
                showBoard(chessBoard);
                System.out.println("Score : player = " + chessBoard.getScore(1) + " ai = " + chessBoard.getScore(2));
                if (chessBoard.getScore(1)==chessBoard.LOST_SCORE) break;
                System.out.println("Input Choice, please! (x,y)>");
                int x=cin.nextInt();
                int y=cin.nextInt();
                chessBoard.setBoard(x,y,1);
            }else {
                //ai's step
                ai.buildChoice();
                if (chessBoard.getScore(2)==chessBoard.LOST_SCORE) break;
                chessBoard.setBoard(ai.getChoiceX(),ai.getChoiceY(),2);
            }
        }
        System.out.println("***");
        showBoard(chessBoard);
        System.out.println("***");
        System.out.println("Score : player1 = "+ chessBoard.getScore(1)+" player2 = "+chessBoard.getScore(2));
    }
}
