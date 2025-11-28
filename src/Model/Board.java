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

    public void movePiece(int startX, int startY, int endX, int endY ) {
        Piece piece = getPiece(startX, startY);
        if (piece != null) {
            board[endX][endY] = piece; //place piece in a new location
            board[startX][startY] = null; //remove piece from old location
        }
    }
    //sets a piece ona specific square
    public void setPiece(int x, int y, Piece piece) {
        if ( x>=0 && x < 8 && y >= 0 && y < 8) {
            this.board[x][y] = piece;
        }
    }

    //King safety helpers
    public int[] findKing(Piece.Color color) {
        for (int x = 0; x < 8; x++ ) {
            for (int y = 0; y < 8; y++) {
                Piece piece = getPiece(x, y);
                    if (piece != null && piece.getType() == Piece.Type.KING && piece.getColor() == color) {
                        System.out.println("where are u king");
                        return new int[]{x, y};
                    }
                }
        }
        return new int[]{-1, -1};
    }

    //check if square is being attacked
    //core logic for checking
    //target x for row being checked
    //target y for column being checked
    //byColor the color of the pieces that is attacking
    //return true if square is being attack
    public boolean isSquareAttacked(int targetX, int targetY, Piece.Color byColor) {
        //goes through entire board
        for (int startX = 0; startX < 8; startX++) {
            for(int startY =0; startY <8; startY++) {
                Piece attacker = getPiece(startX, startY);

                    // is the piece on attacker
                    if (attacker != null && attacker.getColor() == byColor) {
                        //can piece move?
                        //valid move method to check geometry and path
                        if (attacker.validMove(startX, startY, targetX, targetY, this)) {
                            System.out.println("bitch your king is being attacked don't move me???");
                            return true;
                        }
                    }
                }

            }
        System.out.println("king is not being attack phew");
        return false;
    }
}
