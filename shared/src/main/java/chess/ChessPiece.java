package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {
    private ChessGame.TeamColor pieceColor;
    private ChessPiece.PieceType type;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */


    public ChessGame.TeamColor getTeamColor() {
        return null;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
    }


    public  ChessGame.TeamColor getPieceColor(){
        return pieceColor;
    };

    private boolean inBounds(ChessPosition pos){
        if (pos.getRow() > 8 || pos.getRow() < 1){
            return false;
        }
        if (pos.getColumn() > 8 || pos.getColumn() < 1){
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {

       var moveList = new HashSet<ChessMove>();

        if(type == PieceType.KING){
            boolean boundBool=true;
            int row =myPosition.getRow();
            int column =myPosition.getColumn();
            for (int i = 0; i < 8; i++){
                ChessPosition newPosition;
                if(i ==0){
                    newPosition = new ChessPosition(row+1,column-1);
                } else if (i==1) {
                    newPosition = new ChessPosition(row+1,column);
                }else if (i==2) {
                    newPosition = new ChessPosition(row+1,column+1);
                }else if (i==3) {
                    newPosition = new ChessPosition(row,column+1);
                }else if (i==4) {
                    newPosition = new ChessPosition(row-1,column+1);
                }else if (i==5) {
                    newPosition = new ChessPosition(row-1,column);
                }else if (i==6) {
                    newPosition = new ChessPosition(row-1,column-1);
                }else{
                    newPosition = new ChessPosition(row,column-1);
                }

                if (inBounds(newPosition)){
                    ChessPiece boardPiece =board.getPiece(newPosition);
                    if(boardPiece == null){
                        ChessMove move = new ChessMove(myPosition, newPosition, null);
                        moveList.add(move);
                    } else if (boardPiece.getPieceColor() != pieceColor) {
                        ChessMove move = new ChessMove(myPosition, newPosition, null);
                        moveList.add(move);
                    }
                }
            }

        } else if (type == PieceType.KNIGHT) {
            boolean boundBool=true;
            int row = myPosition.getRow();
            int column = myPosition.getColumn();
            for (int i = 0; i < 8; i++){
                ChessPosition newPosition;
                if(i ==0){
                    newPosition = new ChessPosition(row+2,column-1);
                } else if (i==1) {
                    newPosition = new ChessPosition(row+2,column+1);
                }else if (i==2) {
                    newPosition = new ChessPosition(row+1,column+2);
                }else if (i==3) {
                    newPosition = new ChessPosition(row-1,column+2);
                }else if (i==4) {
                    newPosition = new ChessPosition(row-2,column+1);
                }else if (i==5) {
                    newPosition = new ChessPosition(row-2,column-1);
                }else if (i==6) {
                    newPosition = new ChessPosition(row-1,column-2);
                }else{
                    newPosition = new ChessPosition(row+1,column-2);
                }

                if (inBounds(newPosition)){
                    ChessPiece boardPiece =board.getPiece(newPosition);
                    if(boardPiece == null){
                        ChessMove move = new ChessMove(myPosition, newPosition, null);
                        moveList.add(move);
                    } else if (boardPiece.getPieceColor() != pieceColor) {
                        ChessMove move = new ChessMove(myPosition, newPosition, null);
                        moveList.add(move);
                    }
                }
            }

        } else if (type == PieceType.PAWN) {
            if (pieceColor == ChessGame.TeamColor.WHITE){
                if(myPosition.getRow() == 2){
                    //First move
                    ChessPosition oneMoveUp = new ChessPosition(3, myPosition.getColumn());
                    ChessPosition twoMovesUp = new ChessPosition(4, myPosition.getColumn());
                    if(board.getPiece(oneMoveUp) == null){
                        ChessMove newMove = new ChessMove(myPosition, oneMoveUp, null);
                        moveList.add(newMove);
                        if(board.getPiece(twoMovesUp) == null){
                            ChessMove newMove2 = new ChessMove(myPosition, twoMovesUp, null);
                            moveList.add(newMove2);
                        }
                    }
                    //Capture White 1
                    ChessPosition capPosLeft = new ChessPosition(myPosition.getRow()+1, myPosition.getColumn() -1);
                    ChessPosition capPosRight = new ChessPosition(myPosition.getRow()+1, myPosition.getColumn()+1 );
                    if(board.getPiece(capPosLeft) != null) {
                        if (board.getPiece(capPosLeft).getPieceColor() != pieceColor) {
                            ChessMove newMove=new ChessMove(myPosition, capPosLeft, null);
                            moveList.add(newMove);
                        }
                    }
                    if(board.getPiece(capPosRight) != null) {
                        if (board.getPiece(capPosRight).getPieceColor() != pieceColor) {
                            ChessMove newMove=new ChessMove(myPosition, capPosRight, null);
                            moveList.add(newMove);
                        }
                    }
                } else if (myPosition.getRow() == 7) {
                    ChessPosition oneMoveUp = new ChessPosition(myPosition.getRow()+1, myPosition.getColumn());
                    if(board.getPiece(oneMoveUp) == null){
                        PieceType promoPiece;
                        for(int i = 0; i < 4; i++){
                            if (i==0){
                                promoPiece = PieceType.QUEEN;
                            } else if (i == 1) {
                                promoPiece = PieceType.KNIGHT;
                            } else if (i==2) {
                                promoPiece = PieceType.ROOK;
                            }else{
                                promoPiece = PieceType.BISHOP;
                            }
                            ChessMove newMove = new ChessMove(myPosition, oneMoveUp, promoPiece);
                            moveList.add(newMove);
                        }
                    }
                    //Promotion Capture
                    ChessPosition capPosLeft = new ChessPosition(myPosition.getRow()+1, myPosition.getColumn() -1);
                    ChessPosition capPosRight = new ChessPosition(myPosition.getRow()+1, myPosition.getColumn()+1 );
                    if(board.getPiece(capPosLeft) != null) {
                        if (board.getPiece(capPosLeft).getPieceColor() != pieceColor) {
                            PieceType promoPiece;
                            for (int i=0; i < 4; i++) {
                                if (i == 0) {
                                    promoPiece=PieceType.QUEEN;
                                } else if (i == 1) {
                                    promoPiece=PieceType.KNIGHT;
                                } else if (i == 2) {
                                    promoPiece=PieceType.ROOK;
                                } else {
                                    promoPiece=PieceType.BISHOP;
                                }
                                ChessMove newMove=new ChessMove(myPosition, capPosLeft, promoPiece);
                                moveList.add(newMove);
                            }
                        }
                    }
                    if(board.getPiece(capPosRight) != null) {
                        if (board.getPiece(capPosRight).getPieceColor() != pieceColor) {
                            PieceType promoPiece;
                            for (int i=0; i < 4; i++) {
                                if (i == 0) {
                                    promoPiece=PieceType.QUEEN;
                                } else if (i == 1) {
                                    promoPiece=PieceType.KNIGHT;
                                } else if (i == 2) {
                                    promoPiece=PieceType.ROOK;
                                } else {
                                    promoPiece=PieceType.BISHOP;
                                }
                                ChessMove newMove=new ChessMove(myPosition, capPosRight, promoPiece);
                                moveList.add(newMove);
                            }
                        }
                    }
                }else{
                    ChessPosition oneMoveUp = new ChessPosition(myPosition.getRow()+1, myPosition.getColumn());
                    if(board.getPiece(oneMoveUp) == null){
                        ChessMove newMove = new ChessMove(myPosition, oneMoveUp, null);
                        moveList.add(newMove);
                    }
                    //Capture White 2
                    ChessPosition capPosLeft = new ChessPosition(myPosition.getRow()+1, myPosition.getColumn() -1);
                    ChessPosition capPosRight = new ChessPosition(myPosition.getRow()+1, myPosition.getColumn()+1 );
                    if(board.getPiece(capPosLeft) != null) {
                        if (board.getPiece(capPosLeft).getPieceColor() != pieceColor) {
                            ChessMove newMove=new ChessMove(myPosition, capPosLeft, null);
                            moveList.add(newMove);
                        }
                    }
                    if(board.getPiece(capPosRight) != null) {
                        if (board.getPiece(capPosRight).getPieceColor() != pieceColor) {
                            ChessMove newMove=new ChessMove(myPosition, capPosRight, null);
                            moveList.add(newMove);
                        }
                    }
                }


            }else{
                //Black Pawn
                if(myPosition.getRow() == 7){
                    //First move
                    ChessPosition oneMoveDown = new ChessPosition(6, myPosition.getColumn());
                    ChessPosition twoMovesDown = new ChessPosition(5, myPosition.getColumn());
                    if(board.getPiece(oneMoveDown) == null){
                        ChessMove newMove = new ChessMove(myPosition, oneMoveDown, null);
                        moveList.add(newMove);
                        if(board.getPiece(twoMovesDown) == null){
                            ChessMove newMove2 = new ChessMove(myPosition, twoMovesDown, null);
                            moveList.add(newMove2);
                        }
                    }
                    //Capture Black 1
                    ChessPosition capPosLeft = new ChessPosition(myPosition.getRow()-1, myPosition.getColumn() -1);
                    ChessPosition capPosRight = new ChessPosition(myPosition.getRow()-1, myPosition.getColumn()+1 );
                    if(board.getPiece(capPosLeft) != null) {
                        if (board.getPiece(capPosLeft).getPieceColor() != pieceColor) {
                            ChessMove newMove=new ChessMove(myPosition, capPosLeft, null);
                            moveList.add(newMove);
                        }
                    }
                    if(board.getPiece(capPosRight) != null) {
                        if (board.getPiece(capPosRight).getPieceColor() != pieceColor) {
                            ChessMove newMove=new ChessMove(myPosition, capPosRight, null);
                            moveList.add(newMove);
                        }
                    }

                } else if (myPosition.getRow() == 2) {
                    ChessPosition oneMoveDown = new ChessPosition(myPosition.getRow()-1, myPosition.getColumn());
                    if(board.getPiece(oneMoveDown) == null){
                        PieceType promoPiece;
                        for(int i = 0; i < 4; i++){
                            if (i==0){
                                promoPiece = PieceType.QUEEN;
                            } else if (i == 1) {
                                promoPiece = PieceType.KNIGHT;
                            } else if (i==2) {
                                promoPiece = PieceType.ROOK;
                            }else{
                                promoPiece = PieceType.BISHOP;
                            }
                            ChessMove newMove = new ChessMove(myPosition, oneMoveDown, promoPiece);
                            moveList.add(newMove);
                        }
                    }
                    //Promotion Capture
                    ChessPosition capPosLeft = new ChessPosition(myPosition.getRow()-1, myPosition.getColumn() -1);
                    ChessPosition capPosRight = new ChessPosition(myPosition.getRow()-1, myPosition.getColumn()+1 );
                    if(board.getPiece(capPosLeft) != null) {
                        if (board.getPiece(capPosLeft).getPieceColor() != pieceColor) {
                            PieceType promoPiece;
                            for (int i=0; i < 4; i++) {
                                if (i == 0) {
                                    promoPiece=PieceType.QUEEN;
                                } else if (i == 1) {
                                    promoPiece=PieceType.KNIGHT;
                                } else if (i == 2) {
                                    promoPiece=PieceType.ROOK;
                                } else {
                                    promoPiece=PieceType.BISHOP;
                                }
                                ChessMove newMove=new ChessMove(myPosition, capPosLeft, promoPiece);
                                moveList.add(newMove);
                            }
                        }
                    }
                    if(board.getPiece(capPosRight) != null) {
                        if (board.getPiece(capPosRight).getPieceColor() != pieceColor) {
                            PieceType promoPiece;
                            for (int i=0; i < 4; i++) {
                                if (i == 0) {
                                    promoPiece=PieceType.QUEEN;
                                } else if (i == 1) {
                                    promoPiece=PieceType.KNIGHT;
                                } else if (i == 2) {
                                    promoPiece=PieceType.ROOK;
                                } else {
                                    promoPiece=PieceType.BISHOP;
                                }
                                ChessMove newMove=new ChessMove(myPosition, capPosRight, promoPiece);
                                moveList.add(newMove);
                            }
                        }
                    }

                }else{
                    ChessPosition oneMoveDown = new ChessPosition(myPosition.getRow()-1, myPosition.getColumn());
                    if(board.getPiece(oneMoveDown) == null){
                        ChessMove newMove = new ChessMove(myPosition, oneMoveDown, null);
                        moveList.add(newMove);
                    }
                    //Capture Black 2
                    ChessPosition capPosLeft = new ChessPosition(myPosition.getRow()-1, myPosition.getColumn() -1);
                    ChessPosition capPosRight = new ChessPosition(myPosition.getRow()-1, myPosition.getColumn()+1 );
                    if(board.getPiece(capPosLeft) != null) {
                        if (board.getPiece(capPosLeft).getPieceColor() != pieceColor) {
                            ChessMove newMove=new ChessMove(myPosition, capPosLeft, null);
                            moveList.add(newMove);
                        }
                    }
                    if(board.getPiece(capPosRight) != null) {
                        if (board.getPiece(capPosRight).getPieceColor() != pieceColor) {
                            ChessMove newMove=new ChessMove(myPosition, capPosRight, null);
                            moveList.add(newMove);
                        }
                    }
                }
            }

        } else if (type == PieceType.QUEEN) {
            int RowInt = 0;
            int ColInt = 0;

            for (int i=0; i < 8; i++) {
                ChessPosition currentPostion=myPosition;
                boolean collision=false;
                boolean boundBool=true;

                if (i == 0) {
                    //up-left
                    RowInt=1;
                    ColInt=-1;
                } else if (i == 1) {
                    //up-right
                    RowInt=1;
                    ColInt=1;
                } else if (i == 2) {
                    //down-left
                    RowInt=-1;
                    ColInt=-1;
                } else if(i ==3 ) {
                    //down-right
                    RowInt=-1;
                    ColInt=1;
                } else if (i == 4) {
                    //left
                    RowInt = 0;
                    ColInt = -1;
                } else if(i ==5){
                    // Up
                    RowInt = 1;
                    ColInt = 0;
                } else if(i == 6){
                    //Right
                    RowInt = 0;
                    ColInt = 1;
                } else{
                    //Down
                    RowInt = -1;
                    ColInt = 0;
                }

                while (boundBool && !collision) {
                    currentPostion=new ChessPosition(currentPostion.getRow() + RowInt, currentPostion.getColumn() + ColInt);
                    boundBool=inBounds(currentPostion);
                    if (!boundBool) {
                        //don't add move
                    } else if (board.getPiece(currentPostion) != null) {
                        collision=true;
                        ChessPiece hitPiece=board.getPiece(currentPostion);
                        if (hitPiece.getPieceColor() != pieceColor) {
                            ChessMove move = new ChessMove(myPosition, currentPostion, null);
                            moveList.add(move);
                        }
                    } else {
                        ChessMove move=new ChessMove(myPosition, currentPostion, null);
                        moveList.add(move);
                    }
                }
            }


        } else {
            //Bishop and Rook
            int RowInt = 0;
            int ColInt = 0;

            for (int i=0; i < 4; i++) {
                ChessPosition currentPostion=myPosition;
                boolean collision=false;
                boolean boundBool=true;

                if (i == 0) {
                    if(type == PieceType.BISHOP) {
                        //up-left
                        RowInt=1;
                        ColInt=-1;
                    }else{
                        //Rook left
                        RowInt = 0;
                        ColInt = -1;
                    }
                } else if (i == 1) {
                    if(type == PieceType.BISHOP) {
                        //up-right
                        RowInt=1;
                        ColInt=1;
                    }else{
                        //Rook Up
                        RowInt = 1;
                        ColInt = 0;
                    }
                } else if (i == 2) {
                    if (type == PieceType.BISHOP) {
                        //down-left
                        RowInt=-1;
                        ColInt=-1;
                    }else{
                        //Rook right
                        RowInt = 0;
                        ColInt = 1;
                    }
                } else {
                    if (type == PieceType.BISHOP) {
                        //down-right
                        RowInt=-1;
                        ColInt=1;
                    }else{
                        //Rook down
                        RowInt = -1;
                        ColInt = 0;
                    }
                }

                while (boundBool && !collision) {
                    currentPostion=new ChessPosition(currentPostion.getRow() + RowInt, currentPostion.getColumn() + ColInt);
                    boundBool=inBounds(currentPostion);
                    if (!boundBool) {
                        //don't add move
                    } else if (board.getPiece(currentPostion) != null) {
                        collision=true;
                        ChessPiece hitPiece=board.getPiece(currentPostion);
                        if (hitPiece.getPieceColor() != pieceColor) {
                            ChessMove move = new ChessMove(myPosition, currentPostion, null);
                            moveList.add(move);
                        }
                    } else {
                        ChessMove move=new ChessMove(myPosition, currentPostion, null);
                        moveList.add(move);
                    }
                }
            }
        }
        return moveList;
    }








}
