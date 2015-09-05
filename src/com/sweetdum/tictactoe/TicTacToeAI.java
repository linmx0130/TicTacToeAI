package com.sweetdum.tictactoe;

/**
 * Created by sweetdum on 2015/9/4.
 */
public class TicTacToeAI {
    private ChessBoard chessBoard;
    private int choiceX,choiceY;
    private int searchCount=0;
    private int endBlockCount = 0;
    public TicTacToeAI(ChessBoard chessBoard){
        this.chessBoard = chessBoard;
    }
    /*
    A simple Minimax search algorithm with alpha-beta pruning
     */
    private int search(ChessBoard cb, int alpha,int beta, int searchLimit, boolean isMax,int result){
        if (searchLimit == 0 ){
            return result;
        }
        if (cb.getScore(2) == ChessBoard.LOST_SCORE || cb.getScore(1)== ChessBoard.LOST_SCORE)
            return cb.getScore(2)-cb.getScore(1);
        searchCount ++ ;
        int s=cb.getBoardSize();
        if (isMax) {
            if (cb.getLeftBlock() == endBlockCount) return cb.getScore(2)-cb.getScore(1);
            int myChoiceX = -1, myChoiceY = -1, myChoiceRet = cb.LOST_SCORE - 1;
            for (int i = 0; i < s; ++i) {
                for (int j = 0; j < s; ++j) {
                    if (cb.getBoard(i, j) == 0) {
                        cb.setBoard(i,j,2);
                        int subret = search(cb, alpha, beta, searchLimit - 1, !isMax, cb.getScore(2) - cb.getScore(1));
                        cb.setBoard(i,j,0);
                        if (subret > beta) return subret;
                        if (subret > myChoiceRet) {
                            myChoiceRet = subret;
                            myChoiceX = i;
                            myChoiceY = j;
                            alpha = subret;
                        }
                    }
                }
            }
            choiceX = myChoiceX;
            choiceY = myChoiceY;
            return myChoiceRet;
        }else {
            if (cb.getLeftBlock() == endBlockCount) return cb.getScore(2)-cb.getScore(1);
            int myChoiceX=-1, myChoiceY=-1, myChoiceRet= 9999;
            for (int i=0;i<s;++i) {
                for (int j = 0; j < s; ++j) {
                    if (cb.getBoard(i, j) == 0) {
                        cb.setBoard(i,j,1);
                        int subret = search(cb, alpha, beta, searchLimit - 1, !isMax, cb.getScore(2) - cb.getScore(1));
                        cb.setBoard(i,j,0);
                        if (subret < alpha) return subret;  // alpha pruning
                        if (subret < myChoiceRet) {
                            myChoiceRet = subret;
                            beta =subret;
                            myChoiceX = i;
                            myChoiceY = j;
                        }
                    }
                }
            }
            return myChoiceRet;
        }
    }
    public void buildChoice() {
        int s = chessBoard.getBoardSize();
        int searchLimit = 8;
        searchCount = 0;
        search(chessBoard, chessBoard.getScore(2), 99999, searchLimit, true, chessBoard.getScore(2) - chessBoard.getScore(1));
        System.out.println(">>> AI: search count = "+searchCount);
    }
    public int getChoiceX(){
        return choiceX;
    }
    public int getChoiceY(){
        return choiceY;
    }
    public void setEndBlockCount(int endBlockCount){
        this.endBlockCount=endBlockCount;
    }
}
