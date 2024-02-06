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
        Collection<ChessMove> retMoves = new HashSet<>();

        ChessPiece currentPiece = theBoard.getPiece(startPosition);
        Collection<ChessMove> moves = currentPiece.pieceMoves(theBoard, startPosition);

        ChessGame copyGame = new ChessGame();
        ChessBoard copyBoard = theBoard.deepCopyBoard();
        copyGame.setBoard(copyBoard);

        for (ChessMove move: moves) {
            ChessPosition currentStart = move.getStartPosition();
            ChessPosition currentEnd = move.getEndPosition();
            ChessPiece hitPiece = copyBoard.getPiece(currentEnd);
            //make move
            copyBoard.addPiece(currentStart, null);
            copyBoard.addPiece(currentEnd, currentPiece);
            //set board again?
            if(!copyGame.isInCheck(currentPiece.getTeamColor())){
                retMoves.add(move);
            }
            //Move back
            copyBoard.addPiece(currentStart, currentPiece);
            if(hitPiece != null){
                copyBoard.addPiece(currentEnd, hitPiece);
            }else{
                copyBoard.addPiece(currentEnd, null);
            }

        }
        //can't put yourself in check
        //Must move yourself out of check
        return retMoves;
    }


    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        ChessPosition startPos = move.getStartPosition();
        ChessPosition endPos = move.getEndPosition();
        ChessPiece thePiece = theBoard.getPiece(startPos);
        Collection<ChessMove> validMoves = validMoves(startPos);
        if(!validMoves.contains(move)){
            throw new InvalidMoveException("Not in the valid move list");
        }else{
            //Move is valid
            //Check for promotion
            if(move.getPromotionPiece() != null){
                theBoard.addPiece(startPos, null);
                if(move.getPromotionPiece() == ChessPiece.PieceType.QUEEN){
                    ChessPiece queen = new ChessPiece(thePiece.getTeamColor(), ChessPiece.PieceType.QUEEN);
                    theBoard.addPiece(endPos, queen);
                } else if (move.getPromotionPiece() == ChessPiece.PieceType.BISHOP) {
                    ChessPiece bishop = new ChessPiece(thePiece.getTeamColor(), ChessPiece.PieceType.BISHOP);
                    theBoard.addPiece(endPos, bishop);
                } else if (move.getPromotionPiece() == ChessPiece.PieceType.KNIGHT) {
                    ChessPiece knight = new ChessPiece(thePiece.getTeamColor(), ChessPiece.PieceType.KNIGHT);
                    theBoard.addPiece(endPos, knight);
                } else if (move.getPromotionPiece() == ChessPiece.PieceType.ROOK) {
                    ChessPiece rook = new ChessPiece(thePiece.getTeamColor(), ChessPiece.PieceType.ROOK);
                    theBoard.addPiece(endPos,rook);
                }

            }else{
                theBoard.addPiece(startPos, null);
                theBoard.addPiece(endPos, thePiece);
            }


            //Change turn
            if(teamTurn == TeamColor.WHITE){
                setTeamTurn(TeamColor.BLACK);
            }else{
                setTeamTurn(TeamColor.WHITE);
            }

            //Move made - do other checks
            //Check/mate/stale loop
            if(isInCheck(teamTurn)){
                //Checkmate check
                if(isInCheckmate(teamTurn)){
                    System.out.println("Checkmate " + teamTurn + " wins!");
                }
            }else{
                //Stalemate
                if(isInStalemate(teamTurn)){
                    System.out.println("Stalemate!");
                }
            }



        }
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
                if(currentPiece != null) {
                    if (currentPiece.getPieceColor() != teamColor) {
                        Collection<ChessMove> newMoves=currentPiece.pieceMoves(theBoard, currentPos);
                        allMoves.addAll(newMoves);
                    } else if (currentPiece.getPieceType() == ChessPiece.PieceType.KING ) {
                        if(currentPiece.getTeamColor() == teamColor) {
                            kingPos=currentPos;
                        }
                    }
                }
            }
        }

        if(kingPos != null) {
            for (ChessMove currentMove : allMoves) {
                if (currentMove.getEndPosition().getRow() == kingPos.getRow()) {
                    if (currentMove.getEndPosition().getColumn() == kingPos.getColumn())
                        checkRet=true;
                }
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

        Collection<ChessMove> allValidMoves = new HashSet<>();

        for(int i = 0; i< 8; i++){
            for(int k = 0; k < 8; k++){
                ChessPosition currentPos = new ChessPosition(i+1,k+1);
                ChessPiece currentPiece = theBoard.getPiece(currentPos);
                if(currentPiece != null) {
                    if (currentPiece.getPieceColor() == teamColor) {
                        Collection<ChessMove> tempSet = validMoves(currentPos);
                        allValidMoves.addAll(tempSet);
                    }
                }
            }
        }
        if(allValidMoves.isEmpty()){
            return true;
        }else{
            return false;
        }

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
