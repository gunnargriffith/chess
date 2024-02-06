package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {
    private ChessBoard theBoard = new ChessBoard();
    private TeamColor teamTurn;

    public ChessGame() {
        theBoard.resetBoard();
        teamTurn = TeamColor.WHITE;
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return teamTurn;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        teamTurn = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        ChessPiece currentPiece = theBoard.getPiece(startPosition);
        Collection<ChessMove> moves = currentPiece.pieceMoves(theBoard, startPosition);

        //can't put yourself in check
        //Must move yourself out of check
        return moves;
    }


    private ChessBoard deepCopyBoard(ChessBoard ogBoard){
        ChessBoard copyBoard = new ChessBoard();

        for(int i = 0; i < 8; i++){
            for(int k = 0; k < 8; k++){
                ChessPosition currentPos = new ChessPosition(i+1,k+1);
                ChessPiece currentPiece = ogBoard.getPiece(currentPos);
                ChessPiece clonedPiece = currentPiece.clonePiece();
                copyBoard.addPiece(currentPos, clonedPiece);
            }
        }
        return copyBoard;
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        boolean checkRet = false;
        Collection<ChessMove> allMoves = new HashSet<ChessMove>();
        ChessPosition kingPos = null;
        //If any piece of the other team can capture the King, it's in check
        for(int i = 0; i< 8; i++){
            for(int k = 0; k < 8; k++){
                ChessPosition currentPos = new ChessPosition(i+1,k+1);
                ChessPiece currentPiece = theBoard.getPiece(currentPos);
                if(currentPiece.getPieceColor() != teamColor){
                    Collection<ChessMove> newMoves = currentPiece.pieceMoves(theBoard,currentPos);
                    allMoves.addAll(newMoves);
                } else if (currentPiece.getPieceType() == ChessPiece.PieceType.KING) {
                    kingPos = currentPos;
                }
            }
        }

        for (ChessMove currentMove: allMoves) {
            if(currentMove.getEndPosition() == kingPos){
                checkRet = true;
            }
        }

        return checkRet;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        theBoard = board;
        //need a deepcopy?
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return theBoard;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessGame chessGame = (ChessGame) o;
        return Objects.equals(theBoard, chessGame.theBoard) && teamTurn == chessGame.teamTurn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(theBoard, teamTurn);
    }
}
