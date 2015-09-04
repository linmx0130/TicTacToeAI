package com.sweetdum.tictactoe;

/**
 * Created by sweetdum on 2015/9/4.
 */
public class TicTacToeAI {
    ChessBoard chessBoard;
    int choiceX,choiceY;
    int searchCount=0;
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
            if (cb.getLeftBlock() == 1) return cb.getScore(2)-cb.getScore(1);
            int myChoiceX = -1, myChoiceY = -1, myChoiceRet = cb.LOST_SCORE - 1;
            for (int i = 0; i < s; ++i) {
                for (int j = 0; j < s; ++j) {
                    if (cb.getBoard(i, j) == 0) {
                        ChessBoard cb2 = cb.clone();
                        cb2.setBoard(i, j, 2);
                        int subret = search(cb2, alpha, beta, searchLimit - 1, !isMax, cb2.getScore(2) - cb2.getScore(1));
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
            if (cb.getLeftBlock() == 0) return cb.getScore(2)-cb.getScore(1);
            int myChoiceX=-1, myChoiceY=-1, myChoiceRet= 9999;
            for (int i=0;i<s;++i) {
                for (int j = 0; j < s; ++j) {
                    if (cb.getBoard(i, j) == 0) {
                        ChessBoard cb2 = cb.clone();
                        cb2.setBoard(i, j, 1);
                        int subret = search(cb2, alpha, beta, searchLimit - 1, !isMax, cb2.getScore(2) - cb2.getScore(1));
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
        int searchLimit = 7;
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
}
