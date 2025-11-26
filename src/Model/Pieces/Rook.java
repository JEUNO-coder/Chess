package Model.Pieces;

import Model.Board;
import Model.Piece;

public class Rook extends Piece {

        public Rook(Color color){
            super(color, Type.ROOK);
        }

        @Override
        public boolean validMove(int startX, int startY, int endX, int endY, Board board) {

            int deltaX =  Math.abs(endX - startX);
            int deltaY = Math.abs(endY - startY);

            //Checks if it moves like a rook
            boolean isStraight = (startX == endX && startY != endY) || (startY == endY && startX != endY);

            if(!isStraight) {
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
            return true;
        }
}
