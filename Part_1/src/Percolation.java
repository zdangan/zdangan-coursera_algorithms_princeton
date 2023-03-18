import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;
    private int numOpen;
    private boolean[][] grid;
    private WeightedQuickUnionUF wquf;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than zero");
        }  
        
        this.n = n;
        this.grid = new boolean[n][n];
        this.wquf = new WeightedQuickUnionUF(n * n + 2);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = false;
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if ((row < 1) || (row > this.n)) {
            throw new IllegalArgumentException("open: row " + row + " must be between 1 and " + this.n);
        } 
        if ((col < 1) || (col > this.n)) {
            throw new IllegalArgumentException("open: col " + col + " must be between 1 and " + this.n);
        } 

        if (isOpen(row, col)) {
            return;
        }

        // set row * cell cell to open
        this.grid[row - 1][col - 1] = true;
        // increment num_open
        this.numOpen += 1;
        // connect row * col cell to neighboring open cells
        if (row < this.n) {
            if (isOpen(row + 1, col)) {
                this.wquf.union(this.findIndex(row, col), this.findIndex(row + 1, col)); 
            }         
        }
        if (row > 1) {
            if (isOpen(row - 1, col)) {
                this.wquf.union(this.findIndex(row, col), this.findIndex(row - 1, col)); 
            }        
        }
        if (col > 1) {
            if (isOpen(row, col - 1)) {
                this.wquf.union(this.findIndex(row, col), this.findIndex(row, col - 1)); 
            }          
        }
        if (col < this.n) {
            if (isOpen(row, col + 1)) {
                this.wquf.union(this.findIndex(row, col), this.findIndex(row, col + 1)); 
            }          
        }

        if (row == 1) {
            this.wquf.union(this.findIndex(row, col), 0);
        }
        if (row == this.n) {
            this.wquf.union(this.findIndex(row, col), this.n * this.n + 1);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if ((row < 1) || (row > this.n)) {
            throw new IllegalArgumentException("isOpen: row " + row + " must be between 1 and " + this.n);
        } 
        if ((col < 1) || (col > this.n)) {
            throw new IllegalArgumentException("isOpen: col " + col + " must be between 1 and " + this.n);
        } 

        return this.grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if ((row < 1) || (row > this.n)) {
            throw new IllegalArgumentException("isFull: row " + row + " must be between 1 and " + this.n);
        } 
        if ((col < 1) || (col > this.n)) {
            throw new IllegalArgumentException("isFull: col " + col + " must be between 1 and " + this.n);
        } 


        return this.wquf.find(this.findIndex(row, col)) == this.wquf.find(0) && this.isOpen(row, col);
    }

    // returns index of row*col in wquf
    private int findIndex(int row, int col) {
        return (row - 1) * this.n + col;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.numOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return this.wquf.find(this.n * this.n + 1) == this.wquf.find(0);
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}
