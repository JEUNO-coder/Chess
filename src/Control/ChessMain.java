package Control;

import Model.Board;
import Model.Piece;
import View.ChessGui;

import javax.swing.*;

public class ChessMain {
    private ChessGui view;
    private Board board;
    private Piece.Color currentTurn = Piece.Color.WHITE;

    private int selectedX = -1;
    private int selectedY = -1;

    public ChessMain () {
        this.board = new Board();
        board.initBoard();
    }


    public void handleSquareClick(int x, int y) {
        if (selectedX == -1) {
            if (board.getPiece(x, y) != null) {
                selectedX = x;
                selectedY = y;
                view.setSelectedSquare(selectedX, selectedY);
                view.updateView(board);
            }
        } else {
            selectedX = -1;
            selectedY = -1;
            view.setSelectedSquare(selectedX, selectedY);
            view.updateView(board);
        }
    }

    public Piece.Color getCurrentTurn() {
        return currentTurn;
    }

    private void start() {
        this.view = new ChessGui(this);
        view.updateView(board);
    }

    private void processMove(int startX, int startY, int endX, int endY) {

    }

    private boolean moveCheck(int startX, int startY, int endX, int endY) { return false;}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChessMain().start());
    }


}
