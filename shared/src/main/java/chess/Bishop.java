package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Bishop extends ChessPiece{
  private ChessGame.TeamColor pieceColor;
  private PieceType type;


  public Bishop(ChessGame.TeamColor pieceColor, PieceType type) {
    this.pieceColor = pieceColor;
    this.type = type;
  }


  private boolean inBounds(ChessPosition pos){
    if (pos.getRow() > 8 || pos.getRow() < 0){
      return false;
    }
    if (pos.getColumn() > 8 || pos.getColumn() < 0){
      return false;
    }
    return true;
  }

  @Override
  public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
    Set<ChessMove> moveList = new HashSet<>();

    boolean collision = false;
    boolean boundBool = true;
    int RowInt = 0;
    int ColInt = 0;

    for(int i = 0; i < 5; i++){
      ChessPosition currentPostion = myPosition;
      if (i == 0){
        //up-left
        RowInt = 1;
        ColInt = -1;
      } else if (i == 2) {
        //up-right
        RowInt = 1;
        ColInt = 1;
      } else if (i == 3) {
        //down-left
        RowInt = -1;
        ColInt = -1;
      }else{
        //down-right
        RowInt = -1;
        ColInt = 1;
      }

      while(boundBool && collision){
        currentPostion = new ChessPosition(currentPostion.getRow() + RowInt, currentPostion.getColumn() + ColInt);
        boundBool = inBounds(currentPostion);
        if(!boundBool){
          //don't add move
        } else if (board.getPiece(currentPostion) != null) {
          collision = true;
          ChessPiece hitPiece = board.getPiece(currentPostion);
          if(hitPiece.getTeamColor() != pieceColor){
            ChessMove move = new ChessMove(myPosition, currentPostion, null);
            moveList.add(move);
          }
        } else{
          ChessMove move = new ChessMove(myPosition, currentPostion, null);
          moveList.add(move);
        }
      }
    }
    return moveList;
  }



}
