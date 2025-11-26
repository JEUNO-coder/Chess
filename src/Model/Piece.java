package Model;

public abstract class Piece {
    public enum Color { WHITE, BLACK }
    public enum Type { KING, QUEEN, BISHOP, KNIGHT, ROOK, PAWN }

    protected final Color color;
    protected final Type type;

    public Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public abstract boolean validMove(int startX, int startY, int endX, int endY, Board board);
}
