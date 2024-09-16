package chess;

import java.util.Objects;

/**
 * Represents a single square position on a chess board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPosition {

    private int row;
    private int col;

    public ChessPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * @return which row this position is in
     * 1 codes for the bottom row
     */
    public int getRow() {
        return row;
    }

    /**
     * @return which column this position is in
     * 1 codes for the left row
     */
    public int getColumn() {
        return col;
    }

    public boolean inBonds(){
        boolean inbonds = true;
        if(row < 1 || row > 8){
            inbonds = false;
        }
        if(col < 1 || col > 8){
            inbonds = false;
        }
        return inbonds;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChessPosition that)) return false;
        return getRow() == that.getRow() && col == that.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRow(), col);
    }
}
