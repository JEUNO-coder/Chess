package Model.Pieces;

import Model.Board;
import Model.Piece;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color, Type.PAWN);
    }

    @Override
    public boolean validMove(int startX, int startY, int endX, int endY, Board board) {
        // White pieces move from lower X rows (0) to higher X rows (7). Black pieces move the opposite.
        int moveForward = (this.color == Color.WHITE) ? 1 : -1;

        int deltaX = endX - startX;
        int deltaY = Math.abs(endY - startY);

        if (deltaX * moveForward <= 0) {
            return false;
        }

        if (deltaY == 0) {
            //Checks if the block in front is empty
            if (board.getPiece(endX, endY) != null) {
                return false;
            }

            //Move 1 step forward
            if (deltaX * moveForward == 1) {
                return true;
            }
            // moves 2 steps forward if coming from starting point
            if (deltaX * moveForward == 2) {
                //checks if pieces are in starting rank
                boolean startingRank = (this.color == Color.WHITE && startX == 1) ||
                        (this.color == Color.BLACK && startX == 6);

                if (!startingRank) {
                    return false;
                }

                int intermediateX = startX + moveForward;
                if (board.getPiece(intermediateX, startY) != null) {
                    return false;
                }
                // If it was a straight move, but not 1 or 2 steps, it's not valid.
                return true;
            }

            return false;
        }
            if (deltaY == 1 && deltaX * moveForward == 1) {

                Piece destinationPiece = board.getPiece(endX, endY);
                if (destinationPiece == null) {
                    return false;
                }

                if (destinationPiece.getColor() == this.color) {
                    return true;
                }
            }
            return false;
    }
}

