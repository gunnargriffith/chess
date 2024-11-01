package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {
    private ChessBoard gameBoard;
    private TeamColor currentTurn;

    public ChessGame() {
        gameBoard = new ChessBoard();
        gameBoard.resetBoard();
        currentTurn = TeamColor.WHITE;
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return currentTurn;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        currentTurn = team;
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
        //needs in-check filter still
        Collection<ChessMove> retMoves = new ArrayList<>();
        Collection<ChessMove> possibleMoves;
        ChessPiece startPiece = gameBoard.getPiece(startPosition);
        if(startPiece == null){
            return retMoves;
        }
        possibleMoves = startPiece.pieceMoves(gameBoard, startPosition);

        //fake game
        ChessGame copyGame = new ChessGame();
        ChessBoard copyBoard = gameBoard.boardCopy(gameBoard);
        copyGame.setBoard(copyBoard);

        for (ChessMove move: possibleMoves) {
            ChessPosition startPos = move.getStartPosition();
            ChessPosition endPos = move.getEndPosition();
            //get hit piece
            ChessPiece hitPiece = copyBoard.getPiece(endPos);

            copyBoard.addPiece(startPos, null);
            copyBoard.addPiece(endPos, startPiece);

            //can't be in check
            if(!copyGame.isInCheck(startPiece.getTeamColor())){
                retMoves.add(move);
            }

            //Move back
            copyBoard.addPiece(startPos, startPiece);
            if(hitPiece != null){
                copyBoard.addPiece(endPos, hitPiece);
            }else{
                copyBoard.addPiece(endPos, null);
            }
        }




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
        ChessPiece movedPiece = gameBoard.getPiece(startPos);
        Collection<ChessMove> vaildMoves = validMoves(startPos);
        if(movedPiece == null || movedPiece.getTeamColor() != currentTurn){
            throw new InvalidMoveException("This is not your piece");
        } else if (!vaildMoves.contains(move)) {
            throw new InvalidMoveException("Not a valid move");
        }else{
            //move is valid
            if(move.getPromotionPiece() != null){
                //promotion move
                ChessPiece promoPiece = new ChessPiece(currentTurn, move.getPromotionPiece());
                gameBoard.addPiece(startPos, null);
                gameBoard.addPiece(endPos, promoPiece);
            }else{
                //normal Move
                gameBoard.addPiece(startPos, null);
                gameBoard.addPiece(endPos, movedPiece);
            }

            //change turn
            if(currentTurn == TeamColor.WHITE){
                setTeamTurn(TeamColor.BLACK);
            }else{
                setTeamTurn(TeamColor.WHITE);
            }

            //check checks
            if(isInCheck(currentTurn)){
                //checkMate Check
                if(isInCheckmate(currentTurn)){
                    gameOver(currentTurn);
                }
            }else{
                isInStalemate(currentTurn);
                System.out.println("Stalemate!");
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
        boolean check = false;
        ChessPosition kingPos = new ChessPosition();
        Collection<ChessMove> allEnemyMoves = new ArrayList<>();
        for(int i = 1; i< 9; i++){
            for(int j = 1; j< 9; j++){
                ChessPosition currentPos = new ChessPosition(i, j);
                ChessPiece currentPiece = gameBoard.getPiece(currentPos);
                if(currentPiece != null){
                    if(currentPiece.getTeamColor() != teamColor){
                        Collection<ChessMove> moves = currentPiece.pieceMoves(gameBoard, currentPos);
                        allEnemyMoves.addAll(moves);
                    } else if (currentPiece.getPieceType() == ChessPiece.PieceType.KING) {
                        kingPos = currentPos;

                    }
                }
            }
        }

        for (ChessMove move: allEnemyMoves) {
            ChessPosition endPos = move.getEndPosition();
            if(endPos.getRow() == kingPos.getRow() && endPos.getColumn() == kingPos.getColumn()){
                check = true;
                //break;
            }
        }
        return check;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        //Assume I've already confirmed I'm in check
        Collection<ChessMove> allMoves = new ArrayList<>();
        for(int i = 1; i< 9; i++){
            for(int j = 1; j< 9; j++){
                ChessPosition currentPos = new ChessPosition(i, j);
                ChessPiece currentPiece = gameBoard.getPiece(currentPos);
                if(currentPiece != null){
                    if(currentPiece.getTeamColor() == teamColor){
                        Collection<ChessMove> moves = validMoves(currentPos);
                        allMoves.addAll(moves);
                    }
                }
            }
        }
        //moves on the fake board?

        if(allMoves.isEmpty()){
            return true;
        }else{
            return false;
        }

    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        if(isInCheck(teamColor)){
            return false;
        }
        Collection<ChessMove> allMoves = new ArrayList<>();
        for(int i = 1; i< 9; i++){
            for(int j = 1; j< 9; j++){
                ChessPosition currentPos = new ChessPosition(i, j);
                ChessPiece currentPiece = gameBoard.getPiece(currentPos);
                if(currentPiece != null){
                    if(currentPiece.getTeamColor() == teamColor){
                        Collection<ChessMove> moves = validMoves(currentPos);
                        allMoves.addAll(moves);
                    }
                }
            }
        }

        if(allMoves.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    public void gameOver(TeamColor losingColor){
        if(losingColor == TeamColor.WHITE){
            System.out.println("Black wins");
        }else{
            System.out.println("White wins");
        }

    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        gameBoard = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return gameBoard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChessGame chessGame)) return false;
        return Objects.equals(gameBoard, chessGame.gameBoard) && currentTurn == chessGame.currentTurn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameBoard, currentTurn);
    }
}
