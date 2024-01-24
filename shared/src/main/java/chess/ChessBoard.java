package chess;

import java.util.Arrays;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {
    private ChessPiece[][] boardArray;

    public ChessBoard() {
        boardArray = new ChessPiece[8][8];
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        boardArray[position.getRow() - 1][position.getColumn() -1] = piece;
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        return boardArray[position.getRow()-1][position.getColumn()-1];
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        boardArray = new ChessPiece[8][8];
        //Kings
        ChessPiece wKing = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING);
        ChessPosition wkPos = new ChessPosition(1, 5);
        addPiece(wkPos, wKing);
        ChessPiece bKing = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING);
        ChessPosition bkPos = new ChessPosition(8, 5);
        addPiece(bkPos, bKing);
        //Queens
        ChessPiece wQueen = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.QUEEN);
        ChessPosition wqPos = new ChessPosition(1, 4);
        addPiece(wqPos, wQueen);
        ChessPiece bQueen = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN);
        ChessPosition bqPos = new ChessPosition(8, 4);
        addPiece(bqPos, bQueen);
        //Bishops
        for (int i = 0; i < 2; i++){
            int col = -1;
            if(i==0){
                col = 3;
            }else{
                col = 6;
            }
            ChessPiece wBishop = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP);
            ChessPosition bishopPos = new ChessPosition(1, col);
            addPiece(bishopPos, wBishop);
        }
        for (int i = 0; i < 2; i++){
            int col = -1;
            if(i==0){
                col = 3;
            }else{
                col = 6;
            }
            ChessPiece bBishop = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);
            ChessPosition bishopPos = new ChessPosition(8, col);
            addPiece(bishopPos, bBishop);
        }
        //Knight
        for (int i = 0; i < 2; i++){
            int col = -1;
            if(i==0){
                col = 2;
            }else{
                col = 7;
            }
            ChessPiece knight = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT);
            ChessPosition knightPos = new ChessPosition(1, col);
            addPiece(knightPos, knight);
        }
        for (int i = 0; i < 2; i++){
            int col = -1;
            if(i==0){
                col = 2;
            }else{
                col = 7;
            }
            ChessPiece knight = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT);
            ChessPosition knightPos = new ChessPosition(8, col);
            addPiece(knightPos, knight);
        }
        //Rook
        for (int i = 0; i < 2; i++){
            int col = -1;
            if(i==0){
                col = 1;
            }else{
                col = 8;
            }
            ChessPiece rook = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK);
            ChessPosition rookPos = new ChessPosition(1, col);
            addPiece(rookPos, rook);
        }
        for (int i = 0; i < 2; i++){
            int col = -1;
            if(i==0){
                col = 1;
            }else{
                col = 8;
            }
            ChessPiece rook = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK);
            ChessPosition rookPos = new ChessPosition(8, col);
            addPiece(rookPos, rook);
        }
        //Pawns
        ChessPiece pawn = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        for (int i = 0; i < 8; i++){
            ChessPosition pawnPos = new ChessPosition(2, i + 1);
            addPiece(pawnPos, pawn);
        }
        ChessPiece bpawn = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        for (int i = 0; i < 8; i++){
            ChessPosition pawnPos = new ChessPosition(7, i + 1);
            addPiece(pawnPos, bpawn);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChessBoard that)) return false;
        return Arrays.equals(boardArray, that.boardArray);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(boardArray);
    }
}
