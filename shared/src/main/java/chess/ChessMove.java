package chess;

/**
 * Represents moving a chess piece on a chessboard
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessMove {
    private ChessPosition startPosition;
    private ChessPosition endPosition;
    private ChessPiece.PieceType promotionPiece = null;

    public ChessMove(ChessPosition startPosition, ChessPosition endPosition,
                     ChessPiece.PieceType promotionPiece) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        //For now I'm assuming I always pass null for non-pawn pieces
        this.promotionPiece = promotionPiece;
    }

    /**
     * @return ChessPosition of starting location
     */
    public ChessPosition getStartPosition() {
        return startPosition;
    }

    /**
     * @return ChessPosition of ending location
     */
    public ChessPosition getEndPosition() {
        return endPosition;
    }

    /**
     * Gets the type of piece to promote a pawn to if pawn promotion is part of this
     * chess move
     *
     * @return Type of piece to promote a pawn to, or null if no promotion
     */
    public ChessPiece.PieceType getPromotionPiece() {
        return promotionPiece;
    }

    @Override
    public int hashCode(){
        int hash = 0;
        hash = (startPosition.getRow()*31 + startPosition.getColumn()*37) + (endPosition.getRow()*41 + startPosition.getColumn()*47);
        return hash;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof ChessMove that)) return false;
        return startPosition.equals(that.startPosition) && endPosition.equals(that.endPosition) && promotionPiece == that.promotionPiece;
    }
}
