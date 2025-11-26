package Model.Pieces;

import Model.Board;
import Model.Piece;

public class Knight extends Piece {

    public Knight (Color color) {
        super(color, Type.KNIGHT);
    }

    @Override
    public boolean validMove(int startX, int startY, int endX, int endY, Board board) {

        int deltaX = Math.abs(endX - startX);
        int deltaY = Math.abs(endY - startY);

        boolean isLMove = (deltaX == 1 && deltaY == 2) || (deltaX == 2 && deltaY == 1);

        if(!isLMove) {
            return false;
        }

        Piece destinationPiece = board.getPiece(endX, endY);

        return destinationPiece == null || destinationPiece.getColor() != this.color;
    }
}
