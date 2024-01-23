package chess;

public class Queen extends ChessPiece{
  private ChessGame.TeamColor pieceColor;
  private PieceType type;

  public Queen(ChessGame.TeamColor pieceColor, PieceType type) {
    this.pieceColor=pieceColor;
    this.type=type;
  }

  @Override
  public ChessGame.TeamColor getPieceColor() {
    return pieceColor;
  }
}
