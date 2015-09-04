package com.sweetdum.tictactoe;

import java.util.Random;

/**
 * Created by sweetdum on 2015/9/4.
 */
public class ChessBoard implements Cloneable{
    private int boardSize;
    private int board[][];
    private int score[][];
    public final static int LOST_SCORE= -999;
    public ChessBoard(int boardSize){
        this.boardSize = boardSize;
        board=new int[boardSize][boardSize];
        score=new int[boardSize][boardSize];
        Random random=new Random();
        for (int i=0;i<boardSize;++i) {
            for (int j = 0; j < boardSize; ++j) {
                score[i][j] = random.nextInt() % 10;
                score[i][j] += 10;
                score[i][j] %= 10;
            }
        }
    }

    public void setBoard(int i,int j,int mark){
        board[i][j]=mark;
    }

    public int getBoard(int i,int j) {
        return board[i][j];
    }

    public int getBoardSize(){
        return boardSize;
    }

    public int getScore(int i, int j){
        return score[i][j];
    }

    public int getLeftBlock() {
        int ret = 0;
        for (int i = 0; i < boardSize; ++i) {
            for (int j = 0; j < boardSize; ++j) {
                if (board[i][j] == 0) ret++;
            }
        }
        return ret;
    }
    @Override
    public ChessBoard clone() {
        ChessBoard ret = new ChessBoard(boardSize);
        for (int i = 0; i < boardSize; ++i) {
            for (int j = 0; j < boardSize; ++j) {
                ret.board[i][j] = this.board[i][j];
            }
        }
        return ret;
    }

    public int getScore(int player){
        int sum = 0 ;
        int anotherPlayer= player==1?2:1;
        //check line
        for (int i=0;i<boardSize;++i){
            boolean t=true;
            for (int j=0;j<boardSize;++j){
                if (board[i][j]!=anotherPlayer) t=false;
            }
            if (t) return LOST_SCORE;
        }
        for (int i=0;i<boardSize;++i){
            boolean t=true;
            for (int j=0;j<boardSize;++j){
                if (board[j][i]!=anotherPlayer) t=false;
            }
            if (t) return LOST_SCORE;
        }
        {
            boolean t=true;
            for (int i=0;i<boardSize;++i){
                if (board[i][i]!=anotherPlayer) t=false;
            }
            if (t) return LOST_SCORE;
        }
        {
            boolean t=true;
            for (int i=0;i<boardSize;++i){
                if (board[i][boardSize-i-1]!=anotherPlayer) t=false;
            }
            if (t) return LOST_SCORE;
        }

        for (int i=0;i<boardSize;++i){
            for (int j=0;j<boardSize;++j){
                if (board[i][j] == player) sum += score[i][j];
            }
        }
        return sum;
    }
}
