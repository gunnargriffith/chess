package chess;


public class Knight extends ChessPiece{
  private ChessGame.TeamColor pieceColor;
  private PieceType type;

  public Knight(ChessGame.TeamColor pieceColor, PieceType type) {
    this.pieceColor=pieceColor;
    this.type=type;
  }
}
