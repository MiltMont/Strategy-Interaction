public class Strategies {



    /////////////////
    /// C = cooperation
    /// D = defection
    /////////////////

    //Strategies.(Attributes)
    char[] sC; //Always cooperate
    char[] sD; //Always defection
    char[] tD; //Tip and tap
    char[] gD; //Grudger
    char[] dE; //Detective
    char[] detectiveStructure;

    char[] dE_tD_dE;
    char[] dE_tD_tD;

    //set detectiveStructure
    public void setDetectiveStructure() {
        detectiveStructure = new char[5];

        this.detectiveStructure[0] = 'C';
        this.detectiveStructure[1] = 'D';
        this.detectiveStructure[2] = 'C';
        this.detectiveStructure[3] = 'C';
        this.detectiveStructure[4] = 'D';

    }

    public char getDetectiveStructure(int i) {
        return this.detectiveStructure[i];
    }

    public char[] getDetectiveStruct() {
        return this.detectiveStructure;
    }



    //set sC
    public void setSC(int n ) {

        sC = new char[n];

        for(int i = 0; i < n; i++) {
            this.sC[i] = 'C';
        }
    }

    //iterate over sC
    public char itOnsC(int i) {
        return this.sC[i];
    }

    //get sC
    public char[] getSC() {
        return this.sC;
    }


    //set sD
    public void setSD(int n) {

        sD = new char[n];

        for(int i = 0; i < n; i++) {
            this.sD[i] = 'D';
        }
    }

    //iterate over sD
    public char itOnsD(int i) {
        return this.sD[i];
    }

    //get sD
    public char[] getSD() {
        return this.sD;
    }



    //set TD
    public void setTD(int n ,char[] cont) {

        tD = new char[n];

        this.tD[0] = 'C';
        for(int i = 1; i < n; i++) {
            //When opponent cooperates
            if (cont[i-1] == 'C') {
                this.tD[i] = 'C';
            }
            //When opponent cheats
            if (cont[i-1] == 'D') {
                this.tD[i] = 'D';
            }
        }
    }

    //it on TD
    public char itOntD(int i) {
        return this.tD[i];
    }

    //get tD
    public char[] getTD() {
        return this.tD;
    }


    //set GD
    public void setGD(int n ,char[] cont) {

        gD = new char[n];

        this.gD[0] = 'C';
        for (int i = 1; i < n; i++) {

            if (cont[i-1] == 'C') {
                this.gD[i] = 'C';
            }

            //If cont cheats, gD cheats for the rest of the round.
            if (cont[i-1] == 'D') {
                for(int j = i; j < n; j++) {
                    this.gD[j] = 'D';
                }
                break;
            }

        }
    }

    //iterate on GD
    public char itOngD(int i) {
        return this.gD[i];
    }

    //get gD
    public char[] getGD() {
        return this.gD;
    }


    //set DE
    public void setDE(int n, char[] cont) {

        this.setDetectiveStructure();


        this.dE = new char[n];



        if(n < 5) {

            //Copy detectiveStructure from 0 to n.
            for(int i = 0; i < n; i++) {
                this.dE[i] = this.getDetectiveStructure(i);
            }

        }

        if(n >= 5) {

            //Copy detectiveStructure.
            for(int i = 0; i < 5; i++) {
                this.dE[i] = detectiveStructure[i];
            }
            //Behave as tipTap
            if(cont[4] == 'C') {
                for(int i = 5; i < n; i++) {

                    if (cont[i-1] == 'C') {
                        this.dE[i] = 'C';
                    }

                    if (cont[i-1] == 'D') {
                        this.dE[i] = 'D';
                    }
                }
            }
            //Behave as always cheat
            if(cont[4] == 'D') {
                for(int i = 5; i < n; i++) {
                    this.dE[i] = 'D';
                }
            }

        }
    }


    //iterate On Detective.
    public char itOndE(int i) {
        return this.dE[i];
    }



    //get Detective
    public char[] getDE() {
        return this.dE;
    }


    //setDe and TD
    public void setDe_GD(int n) {

        this.setDetectiveStructure();

        this.dE = getDetectiveStruct();

        this.tD = new char[5];

        this.tD[0] = 'C';

        for(int i = 1; i < 5; i++) {
            if(this.dE[i-1] == 'C') {
                this.tD[i] = 'C';
            }
            if(this.dE[i-1] == 'D') {
                this.tD[i] = 'D';
            }

        }


        dE_tD_dE = new char[n];
        dE_tD_tD = new char[n];

        for(int i = 0; i < 5; i++) {
            dE_tD_dE[i] = this.dE[i];
        }

        for(int i = 0; i < 5; i++) {
            dE_tD_tD[i] = this.tD[i];
        }

        //System.out.println(dE_tD_dE.length);



        //Both behave as tD for the next rounds.
        for(int i = 5; i < n; i++) {
            //For tD
            if(this.dE_tD_dE[i-1] == 'C') {
                this.dE_tD_tD[i] = 'C';
            }
            if(this.dE_tD_dE[i-1] == 'D') {
                this.dE_tD_tD[i] = 'D';
            }

            //For dE
            if(this.dE_tD_tD[i-1] == 'C') {
                this.dE_tD_dE[i] = 'C';
            }
            if(this.dE_tD_tD[i-1] == 'D') {
                this.dE_tD_dE[i] = 'D';
            }


        }




    }

    //get dE_tD_dE
    public char[] getdE_tD_dE() {
        return this.dE_tD_dE;
    }
    //iterate on dE_tD_dE
    public char itOntD_dE(int i) {
        return this.dE_tD_dE[i];
    }

    //get dE_tD_tD
    public char[] getdE_tD_tD() {
        return this.dE_tD_tD;
    }
    //iterate on dE_tD_dE
    public char itOndE_tD(int i) {
        return this.dE_tD_tD[i];
    }

}

