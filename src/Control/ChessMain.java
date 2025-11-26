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
        Piece piece = board.getPiece(x, y);
        if (selectedX == -1) {
            if (board.getPiece(x, y) != null) {
                selectedX = x;
                selectedY = y;
                view.setSelectedSquare(selectedX, selectedY);
                view.updateView(board);
            } else if (piece != null) {
                view.showMessage("It is " + currentTurn + "'s turn. Select your piece.", "Invalid Selection", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            if(x == selectedX && y == selectedY) {
                //click the same square to deselect
                selectedX = -1;
                selectedY = -1;
            } else {
                //attempt to move
                processMove(selectedX, selectedY, x, y);
            }

            //clears selection
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
        Piece pieceToMove = board.getPiece(startX, startY);

        //safety check
        if(pieceToMove == null || pieceToMove.getColor() != currentTurn) {
            return;
        }

        //Checks if the piece in a legal way
        if(!pieceToMove.validMove(startX, startY, endX, endY, board)) {
            view.showMessage("Illegal move for a " + pieceToMove.getType() + ".", "Invalid Move", JOptionPane.WARNING_MESSAGE);
            return;
        }
        //Shows a warning if piece is trying to move while their king is checked
        if(moveCheck(startX, startY, endX, endY)) {
            view.showMessage("King is checked!", "Illegal Move", JOptionPane.WARNING_MESSAGE);
            return;
        }

        //execute move
        board.movePiece(startX, startY, endX, endY);

        //swaps turn
        currentTurn = (currentTurn == Piece.Color.WHITE) ? Piece.Color.BLACK : Piece.Color.WHITE;
    }

    private boolean moveCheck(int startX, int startY, int endX, int endY) { return false;}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChessMain().start());
    }


}
