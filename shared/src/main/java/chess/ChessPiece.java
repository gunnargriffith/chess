package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private ChessGame.TeamColor pieceColor;
    private ChessPiece.PieceType type;

    private ChessPosition piecePosition;

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
        return pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
    }

    public ChessPosition getPiecePosition() {
        return piecePosition;
    }

    public void setPiecePosition(ChessPosition piecePosition) {
        this.piecePosition=piecePosition;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> possibleMoves = new ArrayList<>();
        int initalRow =myPosition.getRow();
        int initalCol =myPosition.getColumn();
        
        if(type == PieceType.KING){
            for(int i = 0; i < 8; i++){
                ChessPosition newPos = new ChessPosition();
                if(i == 0){
                    newPos = new ChessPosition(initalRow + 1, initalCol -1);
                } else if (i == 1) {
                    newPos = new ChessPosition(initalRow + 1, initalCol);
                }else if (i == 2) {
                    newPos = new ChessPosition(initalRow + 1, initalCol + 1);
                }else if (i == 3) {
                    newPos = new ChessPosition(initalRow, initalCol - 1);
                }else if (i == 4) {
                    newPos = new ChessPosition(initalRow, initalCol + 1);
                }else if (i == 5) {
                    newPos = new ChessPosition(initalRow - 1, initalCol + 1);
                }else if (i == 6) {
                    newPos = new ChessPosition(initalRow - 1, initalCol);
                }else{
                    newPos = new ChessPosition(initalRow - 1, initalCol - 1);
                }

                if(newPos.inBonds()){
                    ChessPiece hitPiece = board.getPiece(newPos);
                    if(hitPiece != null){
                        if(hitPiece.getTeamColor() != pieceColor) {
                            ChessMove move=new ChessMove(myPosition, newPos);
                            possibleMoves.add(move);
                        }
                    }else{
                        ChessMove move=new ChessMove(myPosition, newPos);
                        possibleMoves.add(move);
                    }
                }
            }
        } else if (type == PieceType.KNIGHT) {
            for(int i = 0; i < 8; i++){
                ChessPosition newPos = new ChessPosition();
                if(i == 0){
                    newPos = new ChessPosition(initalRow + 2, initalCol -1);
                } else if (i == 1) {
                    newPos = new ChessPosition(initalRow + 2, initalCol+1);
                }else if (i == 2) {
                    newPos = new ChessPosition(initalRow + 1, initalCol + 2);
                }else if (i == 3) {
                    newPos = new ChessPosition(initalRow - 1, initalCol + 2);
                }else if (i == 4) {
                    newPos = new ChessPosition(initalRow - 2, initalCol - 1);
                }else if (i == 5) {
                    newPos = new ChessPosition(initalRow - 2, initalCol + 1);
                }else if (i == 6) {
                    newPos = new ChessPosition(initalRow - 1, initalCol - 2);
                }else{
                    newPos = new ChessPosition(initalRow + 1, initalCol - 2);
                }

                if(newPos.inBonds()){
                    ChessPiece hitPiece = board.getPiece(newPos);
                    if(hitPiece != null){
                        if(hitPiece.getTeamColor() != pieceColor) {
                            ChessMove move=new ChessMove(myPosition, newPos);
                            possibleMoves.add(move);
                        }
                    }else{
                        ChessMove move=new ChessMove(myPosition, newPos);
                        possibleMoves.add(move);
                    }
                }
            }
        } else if (type == PieceType.PAWN) {
            
        }else{
            int l1 = 0;
            int l2 = 0;
            if(type == PieceType.QUEEN){
                l1 = 0;
                l2 = 8;
            } else if (type == PieceType.ROOK) {
                l1 = 0;
                l2 = 3;
            } else if (type == PieceType.BISHOP) {
                l1 = 4;
                l2 = 8;
            }
            ChessPosition newPos = new ChessPosition();
            for(int i = l1; i < l2; i++){
                boolean edgeHit = false;
                int currentRow=initalRow;
                int currentCol=initalCol;
                while(!edgeHit) {
                    if (i == 0) {
                        currentRow += 1;
                    } else if (i == 1) {
                        currentCol += 1;
                    }else if (i == 2) {
                        currentRow -= 1;
                    }else if (i == 3) {
                        currentCol -= 1;
                    }else if (i == 4) {
                        currentRow += 1;
                        currentCol -= 1;
                    }else if (i == 5) {
                        currentRow += 1;
                        currentCol += 1;
                    }else if (i == 6) {
                        currentRow -= 1;
                        currentCol += 1;
                    }else{
                        currentRow -= 1;
                        currentCol -= 1;
                    }
                    newPos=new ChessPosition(currentRow, currentCol);


                    if(newPos.inBonds()){
                        ChessPiece hitPiece = board.getPiece(newPos);
                        if(hitPiece != null){
                            if(hitPiece.getTeamColor() != pieceColor) {
                                ChessMove move=new ChessMove(myPosition, newPos);
                                possibleMoves.add(move);
                            }
                            edgeHit = true;
                        }else{
                            ChessMove move=new ChessMove(myPosition, newPos);
                            possibleMoves.add(move);
                        }
                    }else{
                        edgeHit = true;
                    }
                }
            }
        }


        return possibleMoves;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChessPiece that)) return false;
        return pieceColor == that.pieceColor && type == that.type && Objects.equals(getPiecePosition(), that.getPiecePosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, type, getPiecePosition());
    }
}
