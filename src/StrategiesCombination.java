public class StrategiesCombination {

    //Attributes.
    int player1Score, player2Score;

    //Set playerScores.
    public void setScores(int initialScores) {
        this.player1Score = initialScores;
        this.player2Score = initialScores;
    }

    public void Combine(char[] player1, char[] player2, int n, int[][][] actionsMat) {

        for (int i = 0; i < n; i++) {
            //C & C
            if(player1[i] == 'C' && player2[i] == 'C') {
                this.player1Score += actionsMat[0][0][0];
                this.player2Score += actionsMat[0][0][1];
            }
            //C & D
            if(player1[i] == 'C' && player2[i] == 'D') {
                this.player1Score += actionsMat[0][1][0];
                this.player2Score += actionsMat[0][1][1];
            }
            //D & C
            if(player1[i] == 'D' && player2[i] == 'C') {
                this.player1Score += actionsMat[1][0][0];
                this.player2Score += actionsMat[1][0][1];
            }
            //D & D
            if(player1[i] == 'D' && player2[i] == 'D') {
                this.player1Score += actionsMat[1][1][0];
                this.player2Score += actionsMat[1][1][1];
            }
        }
    }

    //Get player1 Score
    public int getPlayer1Score() {
        //System.out.println("Player 1 score: " + player1Score);
        return this.player1Score;
    }

    //Get player2 Score
    public int getPlayer2Score() {
        //System.out.println("Player 2 score: " + player2Score);
        return this.player2Score;
    }

    public String toString() {
        return "Player 1 score: " + this.player1Score +
                "\nPlayer 2 score: " + this.player2Score;
    }

}
