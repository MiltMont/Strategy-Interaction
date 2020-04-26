public class StrategyMatrix {

    //Attributes.
    int[][][] StrategyMatrix = new int [5][5][2];

    //set StrategyMatrix
    public void setStrategyMatrix (int x, int y, int z, int value) {

        this.StrategyMatrix[x][y][z] = value;
    }

    //Iterate on strategyMatrix.
    public int iterate (int x, int y, int z) {
        //System.out.println(this.StrategyMatrix[x][y][z]);
        return this.StrategyMatrix[x][y][z];
    }

    //Get
    public int[][][] getStrategyMatrix() {
        return this.StrategyMatrix;
    }
}
