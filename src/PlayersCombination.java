
public class PlayersCombination {

    // CombinationsAlgorithm.
    /////////////////////7
    // Function to swap values from place i to place j.
    public void swapPlaces(int i, int j) {

        String temp;
        temp = this.strategiesArray[i];
        this.strategiesArray[i] = this.strategiesArray[j];
        this.strategiesArray[j] = temp;

        //return this.strategiesArray;

    }



    //Attributes.
    int tempScore;
    String[] strategiesArray;



    //List of conditionals to obtain scores from strategy matrix.
    public void Conditionals(int[][][] strategyMatrix) {
        for(int i = 1; i < strategiesArray.length; i++) {



            //System.out.println("AT : " + i);
            //System.out.println(strategiesArray[0] + " " + strategiesArray[i]);

            //Writing strategies with equal scores.

            if(     strategiesArray[0].equals("TD")  && strategiesArray[i].equals("TD") ||
                    strategiesArray[0].equals("SC")  && strategiesArray[i].equals("TD") ||
                    strategiesArray[0].equals("TD")  && strategiesArray[i].equals("SC") ||
                    strategiesArray[0].equals("TD")  && strategiesArray[i].equals("GD") ||
                    strategiesArray[0].equals("GD")  && strategiesArray[i].equals("TD") ||

                    strategiesArray[0].equals("SC")  && strategiesArray[i].equals("SC") ||

                    strategiesArray[0].equals("SC")  && strategiesArray[i].equals("GD") ||
                    strategiesArray[0].equals("GD")  && strategiesArray[i].equals("SC") ||

                    strategiesArray[0].equals("GD")  && strategiesArray[i].equals("GD")

            ) {
                tempScore += strategyMatrix[0][0][0];
            }

            // SD-TD && TD-SD
            if(strategiesArray[0].equals("SD")  && strategiesArray[i].equals("TD")) {
                tempScore += strategyMatrix[2][0][0];
            } else if (strategiesArray[0].equals("TD")  && strategiesArray[i].equals("SD")) {
                tempScore += strategyMatrix[2][0][1];
            }

            //TD-DE && DE-TD
            if(strategiesArray[0].equals("TD")  && strategiesArray[i].equals("DE")) {
                tempScore += strategyMatrix[0][4][0];
            } else if (strategiesArray[0].equals("DE")  && strategiesArray[i].equals("TD")) {
                tempScore += strategyMatrix[0][4][1];
            }

            // SC-SD && SD-SC
            if(strategiesArray[0].equals("SC")  && strategiesArray[i].equals("SD")) {
                tempScore += strategyMatrix[1][2][0];
            } else if (strategiesArray[0].equals("SD")  && strategiesArray[i].equals("SC")) {
                tempScore += strategyMatrix[1][2][1];
            }

            // SC-DE && DE-SC
            if(strategiesArray[0].equals("SC")  && strategiesArray[i].equals("DE")) {
                tempScore += strategyMatrix[1][4][0];
            } else if (strategiesArray[0].equals("DE")  && strategiesArray[i].equals("SC")) {
                tempScore += strategyMatrix[1][4][1];
            }


            // SD-SD
            if(strategiesArray[0].equals("SD")  && strategiesArray[i].equals("SD")) {
                tempScore += strategyMatrix[2][2][0];
            }

            // SD-GD && GD-SD
            if(strategiesArray[0].equals("SD")  && strategiesArray[i].equals("GD")) {
                tempScore += strategyMatrix[2][3][0];
            } else if (strategiesArray[0].equals("GD")  && strategiesArray[i].equals("SD")) {
                tempScore += strategyMatrix[2][3][1];
            }

            //SD-DE && DE-SD
            if(strategiesArray[0].equals("SD")  && strategiesArray[i].equals("DE")) {
                tempScore += strategyMatrix[2][4][0];
            } else if (strategiesArray[0].equals("DE")  && strategiesArray[i].equals("SD")) {
                tempScore += strategyMatrix[2][4][1];
            }

            //GD-DE && DE-GD
            if(strategiesArray[0].equals("GD")  && strategiesArray[i].equals("DE")) {
                tempScore += strategyMatrix[3][4][0];
            } else if (strategiesArray[0].equals("DE")  && strategiesArray[i].equals("GD")) {
                tempScore += strategyMatrix[3][4][1];
            }

            //DE-DE
            if(strategiesArray[0].equals("GD")  && strategiesArray[i].equals("DE")) {
                tempScore += strategyMatrix[4][4][0];
            }

            //System.out.println("SCORE + " + tempScore);



        }
    }

    //setTempArray
    public void setTempArray(int numOfPlayers, String[][] strategies) {

        strategiesArray = new String[numOfPlayers];

        //System.out.println("Temp array length " + strategiesArray.length);

        for(int i = 0; i < numOfPlayers; i++) {
            this.strategiesArray[i] = strategies[i][2];
        }

    }

    //printTempArray.
    public void printTempArray() {
        for (int i = 0; i < this.strategiesArray.length; i++) {
            //System.out.println();
            System.out.print(this.strategiesArray[i] + "  ");
        }
        System.out.println();
    }

    //setTempScore.
    public void setTempScore(String initialScores) {
        this.tempScore = Integer.parseInt(initialScores);
    }

    //getTempScore
    public int getTempScore() {
        return this.tempScore;
    }



    public void info() {
        System.out.println("Temporal score: " + this.tempScore);
        printTempArray();
    }

}
