package chess;

import java.util.Arrays;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {

    private ChessPiece[][] boardArray = new ChessPiece[8][8];

    public ChessBoard() {
        //resetBoard();
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        if(position.inBonds()) {
            int row=position.getRow() - 1;
            int col=position.getColumn() - 1;
            boardArray[row][col]=piece;
            piece.setPiecePosition(position);
        }
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        int row=position.getRow() - 1;
        int col=position.getColumn() - 1;
        return boardArray[row][col];
    }


    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        //Kings
        ChessPiece wKing = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING);
        ChessPosition wkPos = new ChessPosition(1,5);
        addPiece(wkPos, wKing);
        ChessPiece bKing = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING);
        ChessPosition bkPos = new ChessPosition(8,5);
        addPiece(bkPos, bKing);

        //Queens
        ChessPiece wQueen = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.QUEEN);
        ChessPosition wqPos = new ChessPosition(1,4);
        addPiece(wqPos, wQueen);
        ChessPiece bQueen = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN);
        ChessPosition bqPos = new ChessPosition(8,4);
        addPiece(bqPos, bQueen);

        //Bishops
        ChessPiece w1Bishop = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP);
        ChessPosition w1bPos = new ChessPosition(1, 3);
        addPiece(w1bPos, w1Bishop);
        ChessPiece w2Bishop = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP);
        ChessPosition w2bPos = new ChessPosition(1, 6);
        addPiece(w2bPos, w2Bishop);
        ChessPiece b1Bishop = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);
        ChessPosition b1bPos = new ChessPosition(8, 3);
        addPiece(b1bPos, b1Bishop);
        ChessPiece b2Bishop = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);
        ChessPosition b2bPos = new ChessPosition(8, 6);
        addPiece(b2bPos, b2Bishop);

        //Knights
        ChessPiece w1Knight = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT);
        ChessPosition w1nPos = new ChessPosition(1, 2);
        addPiece(w1nPos, w1Knight);
        ChessPiece w2Knight = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT);
        ChessPosition w2nPos = new ChessPosition(1, 7);
        addPiece(w2nPos, w2Knight);
        ChessPiece b1Knight = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT);
        ChessPosition b1nPos = new ChessPosition(8, 2);
        addPiece(b1nPos, b1Knight);
        ChessPiece b2Knight = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT);
        ChessPosition b2nPos = new ChessPosition(8, 7);
        addPiece(b2nPos, b2Knight);

        //Rooks
        ChessPiece w1Rook = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK);
        ChessPosition w1rPos = new ChessPosition(1, 1);
        addPiece(w1rPos, w1Rook);
        ChessPiece w2Rook = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK);
        ChessPosition w2rPos = new ChessPosition(1, 8);
        addPiece(w2rPos, w2Rook);
        ChessPiece b1Rook = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK);
        ChessPosition b1rPos = new ChessPosition(8, 1);
        addPiece(b1rPos, b1Rook);
        ChessPiece b2Rook = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK);
        ChessPosition b2rPos = new ChessPosition(8, 8);
        addPiece(b2rPos, b2Rook);

        //Pawns
        for(int i = 1; i < 9; i++){
            ChessPiece wPawn = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
            ChessPosition wPos = new ChessPosition(2, i);
            addPiece(wPos, wPawn);
        }
        for(int i = 1; i < 9; i++){
            ChessPiece bPawn = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
            ChessPosition bPos = new ChessPosition(7, i);
            addPiece(bPos, bPawn);
        }
        //System.out.println("HIT");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChessBoard that)) return false;
        return Arrays.deepEquals(boardArray, that.boardArray);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(boardArray);
    }

    @Override
    public String toString() {
        return "ChessBoard{" +
                "boardArray=" + Arrays.toString(boardArray) +
                '}';
    }
}
