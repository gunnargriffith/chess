package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
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

    public ChessPiece clonePiece(){
        ChessPiece retPiece = new ChessPiece(pieceColor, type);
        return retPiece;
    }


    public ChessGame.TeamColor getTeamColor() {
        return pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
    }

    /**
     * @return Which team this chess piece belongs to
     */
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
            if(pieceColor == ChessGame.TeamColor.WHITE){
                //White Pawn
                if(myPosition.getRow() != 7){
                    ChessPosition moveOne = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn());
                    if(board.getPiece(moveOne) == null){
                        ChessMove move = new ChessMove(myPosition, moveOne, null);
                        moveList.add(move);
                        if(myPosition.getRow() == 2){
                            ChessPosition moveTwo = new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn());
                            if(board.getPiece(moveTwo) == null){
                                ChessMove move2 = new ChessMove(myPosition, moveTwo, null);
                                moveList.add(move2);
                            }
                        }
                    }
                    //White Normal Capture
                    ChessPosition capLeft = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() - 1);
                    ChessPosition capRight = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 1);

                    for(int i = 0; i < 2; i++){
                        ChessPosition currentPos;
                        if(i==0){
                            currentPos = capLeft;
                        }else{
                            currentPos = capRight;
                        }
                        if(inBounds(currentPos)){
                            if(board.getPiece(currentPos) != null) {
                                ChessPiece hitPiece=board.getPiece(currentPos);
                                if (hitPiece.getTeamColor() != pieceColor && inBounds(currentPos)) {
                                    ChessMove move=new ChessMove(myPosition, currentPos, null);
                                    moveList.add(move);
                                }
                            }
                        }
                    }


                }else{
                    //Promotion Zone
                    ChessPosition movePromo = new ChessPosition(8, myPosition.getColumn());
                    if(board.getPiece(movePromo) == null){
                        for(int i = 0; i < 4; i++){
                            PieceType promo;
                            if(i==0){
                                promo = PieceType.QUEEN;
                            } else if (i==1) {
                                promo = PieceType.KNIGHT;
                            } else if (i==2) {
                                promo = PieceType.BISHOP;
                            }else{
                                promo = PieceType.ROOK;
                            }

                            ChessMove move = new ChessMove(myPosition, movePromo, promo);
                            moveList.add(move);
                        }
                    }
                    //Promo Cap
                    ChessPosition capLeft = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() - 1);
                    ChessPosition capRight = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 1);
                    for(int i = 0; i < 2; i++){
                        ChessPosition currentPos;
                        if(i==0){
                            currentPos = capLeft;
                        }else{
                            currentPos = capRight;
                        }
                        if(inBounds(currentPos)){
                            if(board.getPiece(currentPos) != null && inBounds(currentPos)) {
                                ChessPiece hitPiece=board.getPiece(currentPos);
                                if (hitPiece.getTeamColor() != pieceColor) {
                                    for(int k = 0; k < 4; k++){
                                        PieceType promo;
                                        if(k==0){
                                            promo = PieceType.QUEEN;
                                        } else if (k==1) {
                                            promo = PieceType.KNIGHT;
                                        } else if (k==2) {
                                            promo = PieceType.BISHOP;
                                        }else{
                                            promo = PieceType.ROOK;
                                        }
                                        ChessMove move = new ChessMove(myPosition, currentPos, promo);
                                        moveList.add(move);
                                    }
                                }
                            }
                        }
                    }

                }

            }else{
                //Black Pawn
                if(myPosition.getRow() != 2){
                    ChessPosition moveOne = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn());
                    if(board.getPiece(moveOne) == null){
                        ChessMove move = new ChessMove(myPosition, moveOne, null);
                        moveList.add(move);
                        if(myPosition.getRow() == 7){
                            ChessPosition moveTwo = new ChessPosition(myPosition.getRow() - 2, myPosition.getColumn());
                            if(board.getPiece(moveTwo) == null){
                                ChessMove move2 = new ChessMove(myPosition, moveTwo, null);
                                moveList.add(move2);
                            }
                        }
                    }
                    //Black Normal Capture
                    ChessPosition capLeft = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() - 1);
                    ChessPosition capRight = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() + 1);
                    for(int i = 0; i < 2; i++){
                        ChessPosition currentPos;
                        if(i==0){
                            currentPos = capLeft;
                        }else{
                            currentPos = capRight;
                        }
                        if(inBounds(currentPos)){
                            if(board.getPiece(currentPos) != null && inBounds(currentPos)) {
                                ChessPiece hitPiece=board.getPiece(currentPos);
                                if (hitPiece.getTeamColor() != pieceColor) {
                                    ChessMove move=new ChessMove(myPosition, currentPos, null);
                                    moveList.add(move);
                                }
                            }
                        }
                    }


                }else {
                    //Promotion Zone Black
                    ChessPosition movePromo = new ChessPosition(1, myPosition.getColumn());
                    if(board.getPiece(movePromo) == null){
                        for(int i = 0; i < 4; i++){
                            PieceType promo;
                            if(i==0){
                                promo = PieceType.QUEEN;
                            } else if (i==1) {
                                promo = PieceType.KNIGHT;
                            } else if (i==2) {
                                promo = PieceType.BISHOP;
                            }else{
                                promo = PieceType.ROOK;
                            }

                            ChessMove move = new ChessMove(myPosition, movePromo, promo);
                            moveList.add(move);
                        }
                    }
                    //Promo Cap
                    ChessPosition capLeft = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() - 1);
                    ChessPosition capRight = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() + 1);
                    for(int i = 0; i < 2; i++){
                        ChessPosition currentPos;
                        if(i==0){
                            currentPos = capLeft;
                        }else{
                            currentPos = capRight;
                        }
                        if(inBounds(currentPos)){
                            if(board.getPiece(currentPos) != null && inBounds(currentPos)) {
                                ChessPiece hitPiece=board.getPiece(currentPos);
                                if (hitPiece.getTeamColor() != pieceColor) {
                                    for(int k = 0; k < 4; k++){
                                        PieceType promo;
                                        if(k==0){
                                            promo = PieceType.QUEEN;
                                        } else if (k==1) {
                                            promo = PieceType.KNIGHT;
                                        } else if (k==2) {
                                            promo = PieceType.BISHOP;
                                        }else{
                                            promo = PieceType.ROOK;
                                        }
                                        ChessMove move = new ChessMove(myPosition, currentPos, promo);
                                        moveList.add(move);
                                    }
                                }
                            }
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChessPiece that)) return false;
        return getPieceColor() == that.getPieceColor() && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPieceColor(), type);
    }
}
