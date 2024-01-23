package chess;

public class King extends ChessPiece{
  private ChessGame.TeamColor pieceColor;
  private PieceType type;
  public King(ChessGame.TeamColor pieceColor, PieceType type) {
    this.pieceColor = pieceColor;
    this.type = type;
  }

  @Override
  public ChessGame.TeamColor getPieceColor() {
    return pieceColor;
  }
}
