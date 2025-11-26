package Model.Pieces;
import Model.Board;
import Model.Piece;

public class Queen extends Piece {

    public Queen(Color color){
        super(color, Type.QUEEN);
    }
    @Override
    public boolean validMove(int startX, int startY, int endX, int endY, Board board) {

        int deltaX =  Math.abs(endX - startX);
        int deltaY = Math.abs(endY - startY);

        //Checks if it moves like a rook
        boolean isStraight = (startX == endX && startY != endY) || (startY == endY && startX != endY);

        //Checks if it moves like a bishop
        boolean isDiagonal = (deltaX == deltaY) && (deltaX !=0);

        if(!isStraight && !isDiagonal) {
            return false;
        }


        Piece destinationPiece = board.getPiece(endX, endY);
        if(destinationPiece != null && destinationPiece.getColor() == this.color) {
            return false;
        }

        //Rook movements
        if(isStraight) {
            //Horizontal Movement
            if(startX == endX) {
                int step = (endY > startY) ? 1 : -1;
                for (int y = startY + step; y != endY; y+= step) {
                    if (board.getPiece(startX, y) != null ) {
                        return false;
                    }
                }
            } else {
                // Vertical Movement
                int step = (endX > startX) ? 1 : -1;
                for (int x = startX + step; x != endX; x += step) {
                    if (board.getPiece(x, startY) != null) {
                        return false;
                    }
                }
            }
        }

        else if (isDiagonal) {
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
