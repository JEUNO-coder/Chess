package Model;
import Model.Pieces.*;

public class Board {
    private Piece[][] board = new Piece[8][8];

    public void initBoard(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = null;
            }
        }

        //places white pawn on the second row
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(Piece.Color.WHITE);
        }

        //places black pawn on row
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(Piece.Color.BLACK);
        }

        placeBackRow(Piece.Color.WHITE, 0);
        placeBackRow(Piece.Color.BLACK, 7);

    }

    private void placeBackRow(Piece.Color color, int row) {

        //rooks
        board[row][0] = new Rook(color);
        board[row][7] = new Rook(color);

        //Knights
        board[row][1] = new Knight(color);
        board[row][6] = new Knight(color);

        //bishop
        board[row][2] = new Bishop(color);
        board[row][5] = new Bishop(color);

        //king and queen
        board[row][4] = new King(color);
        board[row][3] = new Queen(color);
    }

    public Piece getPiece(int x, int y){
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return null;
        }
        return board[x][y];
    }
}
