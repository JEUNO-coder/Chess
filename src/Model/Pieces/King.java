package Model.Pieces;
import Model.Board;
import Model.Piece;


public class King extends Piece {
    public King(Color color) {
        super(color, Type.KING);
    }


    @Override
    public boolean validMove(int startX, int startY, int endX, int endY, Board board) {
        int deltaX = Math.abs(endX - startX);
        int deltaY = Math.abs(endY - startY);

        if (deltaX > 1 || deltaY > 1) {
            return false;
        }

        if (deltaX == 0 && deltaY == 0){
            return false;
        }

        //Checks destination
        Piece destinationPiece = board.getPiece(endX, endY);
        return destinationPiece == null || destinationPiece.getColor() != this.color;
    }
} 