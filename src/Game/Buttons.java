package Game;

import javax.swing.JButton;

public class Buttons extends JButton {
    private int col, row, count;
    private boolean mine;
    private boolean flag;
    private boolean blowup;
    
    public Buttons(int col, int row) {
        this.col = col;
        this.row = row;
        count = 0;
        mine = false;
        flag = false;
        blowup = false;
    }

    /**
     * @return the col
     */
    public int getCol() {
        return col;
    }

    /**
     * @param col the col to set
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * @param row the row to set
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    public void setCount() {
        this.count++;
    }

    /**
     * @return the mine
     */
    public boolean isMine() {
        return mine;
    }

    /**
     * @param mine the mine to set
     */
    public void setMine(boolean mine) {
        this.mine = mine;
    }

    /**
     * @return the flag
     */
    public boolean isFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    /**
     * @return the blowup
     */
    public boolean isBlowup() {
        return blowup;
    }

    /**
     * @param blowup the blowup to set
     */
    public void setBlowup(boolean blowup) {
        this.blowup = blowup;
    }
    
    
}
