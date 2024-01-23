package chess;

public class Pawn extends ChessPiece{
  private ChessGame.TeamColor pieceColor;
  private PieceType type;

  public Pawn(ChessGame.TeamColor pieceColor, PieceType type) {
    this.pieceColor=pieceColor;
    this.type=type;
  }

  @Override
  public ChessGame.TeamColor getPieceColor() {
    return pieceColor;
  }
}
