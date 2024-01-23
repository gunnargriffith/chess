package chess;

public class Rook extends ChessPiece{
  private ChessGame.TeamColor pieceColor;
  private PieceType type;

  public Rook(ChessGame.TeamColor pieceColor, PieceType type) {
    this.pieceColor=pieceColor;
    this.type=type;
  }
}
