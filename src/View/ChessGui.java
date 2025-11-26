package View;

import Control.ChessMain;
import Model.Board;
import Model.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessGui extends JFrame implements ActionListener {

    private final ChessMain controller;

    private int selectedX = -1;
    private int selectedY = -1;

    private JPanel boardPanel;
    private JButton[][] boardSquares = new JButton[8][8];
    private JLabel statusLabel;

    public ChessGui(ChessMain controller) {
        this.controller = controller;

        setTitle("Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initGUI();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initGUI() {
        statusLabel = new JLabel();

        statusLabel.setText(controller.getCurrentTurn().toString() + "'s Turn");

        statusLabel.setFont(new Font("Arial Narrow", Font.BOLD, 16));
        add(statusLabel, BorderLayout.NORTH);

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(8, 8));
        boardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        initBoardSquares();
        add(boardPanel, BorderLayout.CENTER);
    }

    private void initBoardSquares() {
        for (int x = 7; x >= 0; x--) {
            for (int y = 0; y < 8; y++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(64, 64));
                button.setOpaque(true);
                button.setBorderPainted(false);

                Color lightSquare =  Color.decode("#FFF1CC");
                Color darkSquare = Color.decode("#8B8056");

                if ((x + y) % 2 == 0) {
                    button.setBackground(darkSquare);
                } else {
                    button.setBackground(lightSquare);
                }

                button.putClientProperty("gridX", x);
                button.putClientProperty("gridY", y);
                button.addActionListener(this);

                boardSquares[x][y] = button;
                boardPanel.add(button);
            }
        }
    }

    public void updateView(Board board) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece piece = board.getPiece(x, y);

                if(piece != null) {
                    String symbol = getPieceUnicode(piece);
                    boardSquares[x][y].setText(symbol);
                    boardSquares[x][y].setFont(new Font("Arial Unicode MS", Font.BOLD, 30));
                } else {
                    boardSquares[x][y].setText("");
                }

                boardSquares[x][y].setBorder(null);
            }

            if (selectedX != -1) {
                boardSquares[selectedX][selectedY].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            }
        }

        statusLabel.setText(controller.getCurrentTurn().toString() + "'s Turn");
    }

    private String getPieceUnicode(Piece piece) {
        String baseSymbol = "";

        switch (piece.getType()) {
            case KING: baseSymbol = "K";
                break;
            case QUEEN: baseSymbol = "Q";
                break;
            case BISHOP: baseSymbol = "B";
                break;
            case KNIGHT: baseSymbol = "N";
                break;
            case ROOK: baseSymbol = "R";
                break;
            case PAWN: baseSymbol = "P";
                break;
            default: return " ";
        }

        if(piece.getColor() == Piece.Color.WHITE) {
            return switch (baseSymbol) {
                case "K" -> "♔";
                case "Q" -> "♕";
                case "R" -> "♖";
                case "B" -> "♗";
                case "N" -> "♘";
                case "P" -> "♙";
                default -> "  ";
            };
        } else {
            switch (baseSymbol) {
                case "K": return "♚";
                case "Q": return "♛";
                case "R": return "♜";
                case "B": return "♝";
                case "N": return "♞";
                case "P": return "♟";
                default: return "  ";
            }
        }
    }

    public void setSelectedSquare(int x, int y){
        this.selectedX = x;
        this.selectedY = y;
    }

    public void showMessage(String message, String title, int messageType){
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedSquare = (JButton) e.getSource();
        int x = (int) clickedSquare.getClientProperty("gridX");
        int y = (int) clickedSquare.getClientProperty("gridY");
        controller.handleSquareClick(x, y);
    }
}
