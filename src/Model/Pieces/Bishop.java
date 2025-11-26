package Model.Pieces;
import Model.Board;
import Model.Piece;

public class Bishop extends Piece {

    public Bishop(Color color){
        super(color, Type.BISHOP);
    }
    @Override
    public boolean validMove(int startX, int startY, int endX, int endY, Board board) {

        int deltaX =  Math.abs(endX - startX);
        int deltaY = Math.abs(endY - startY);

        //Checks if it moves like a bishop
        boolean isDiagonal = (deltaX == deltaY) && (deltaX !=0);

        if(!isDiagonal) {
            return false;
        }


        Piece destinationPiece = board.getPiece(endX, endY);
        if(destinationPiece != null && destinationPiece.getColor() == this.color) {
            return false;
        }

      if (isDiagonal) {
            int xStep = (endX > startX) ? 1 : -1;
            int yStep = (endY > startY) ? 1 : -1;

            int currentX = startX + xStep;
            int currentY = startY + yStep;

            while (currentX != endX) {
                if(board.getPiece(currentX, currentY) != null) {
                    return false;
                }
                currentX += xStep;
                currentY += yStep;
            }
        }
        return true;
    }
}
