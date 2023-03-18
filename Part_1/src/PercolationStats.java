import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private int n;
    private int trials;
    private double[] numOpen;
    

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        } 
        if (trials <= 0) {
            throw new IllegalArgumentException("trials must be greater than 0");
        } 
        this.n = n;
        this.trials = trials;
        this.numOpen = new double[trials];

        for (int t = 0; t <  trials; t++) {
            Percolation perc = new Percolation(this.n);

            while (!perc.percolates()) {
                int row = StdRandom.uniformInt(1, this.n + 1);
                int col = StdRandom.uniformInt(1, this.n + 1);
                if (!perc.isOpen(row, col)) {
                    perc.open(row, col);
                }              
            }

            this.numOpen[t] = (double) perc.numberOfOpenSites() / (this.n * this.n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.numOpen);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.numOpen);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - CONFIDENCE_95 * this.stddev() / this.trials;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + CONFIDENCE_95 * this.stddev() / this.trials;
    }

   // test client (see below)
   public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats percstats = new PercolationStats(n, trials);
        System.out.println("mean                    = " + percstats.mean());
        System.out.println("stddev                  = " + percstats.stddev());
        System.out.println("95% confidence interval = [" + percstats.confidenceLo() + ", " + 
                                                           percstats.confidenceHi() + "]");
        
   }

}