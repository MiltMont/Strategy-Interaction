import java.io.FileWriter;
import java.io.IOException;

class GameData {

	//Attributes.
	int numOfPlayers;
	String initialScores;
	int numOfRounds;
	String[] playersNames;
	String[] playersStrategies;
	String preset;
	String[][] playersData;

	int[][][] actionsMat;

	Strategies strategies = new Strategies();
	StrategiesCombination combination = new StrategiesCombination();
	StrategyMatrix strategyMatrix = new StrategyMatrix();
	PlayersCombination playersCombination = new PlayersCombination();

	
	//Constructor
	public GameData(
		int num, String init, int rounds,
		String[] names, String[] strats, 
		String pre
		){

		this.numOfPlayers = num;
		this.initialScores = init;
		this.numOfRounds = rounds;
		this.playersNames = names;
		this.playersStrategies = strats;
		this.preset = pre;
	}

	//Flow control of activities.
	public void execActivities(){
		setPlayersData();
		setActionMatrix();
		//Writing the initial state of the players.
		writePlayersState("Initial");
		setStrategies();
		setPlayerCombinations();
		writeActionsMatrix();
		writeStrategyMatrix();
		//Writing the final state of the players.
		writePlayersState("Final");
	}

	//setPlayersData
	private void setPlayersData(){
		//Initializing array
		playersData = new String[numOfPlayers][3];

		//Setting names.
		for(int i = 0; i < numOfPlayers; i++){
			this.playersData[i][0] = playersNames[i];
		}

		//Setting initialScore.
		for(int i = 0; i < numOfPlayers; i++){
			this.playersData[i][1] = initialScores;
		}

		//Setting strategy.
		for(int i = 0; i < numOfPlayers; i++){
			this.playersData[i][2] = playersStrategies[i];
		}
	}

	//Method to set ActionMatrix using preset.
	private void setActionMatrix(){
		ActionsMatrix actionsMatrix = new ActionsMatrix();
		this.actionsMat = actionsMatrix.getActionsMatrix(this.preset);
	}

	//Setting Strategy Matrix.
	///////////////////////////

	//Set strategies
	private void setStrategies(){
		int n = numOfRounds;

		    //Setting sC
		    strategies.setSC(n);
		    //Setting SD
		    strategies.setSD(n);

		    //sC against sC
		    CombineStrategies(strategies.getSC(), strategies.getSC());

		    // 0,0 = 0,1 = 1,0 = 1,1 = 0,3 = 3,0 = 1,3 = 3,1 = 3,3.
		    setPlayers(0,0);
		    setPlayers(0,1);
		    setPlayers(1,0);
		    setPlayers(1,1);
		    setPlayers(0,3);
		    setPlayers(3,0);
		    setPlayers(1,3);
		    setPlayers(3,1);
		    setPlayers(3,3);


		
		    //setting gD with sD
		    strategies.setGD(n, strategies.getSD());

		    //sD against gD
		    CombineStrategies(strategies.getSD(), strategies.getGD());
		    //2,3
		    setPlayers(2,3);
		    //2,0
		    setPlayers(2,0);
		    //gD against sD
		    CombineStrategies(strategies.getGD(), strategies.getSD());
		    //3,2
		    setPlayers(3,2);
		    //0,2
		    setPlayers(0,2);




		    //sD against sD
		    CombineStrategies(strategies.getSD(), strategies.getSD());
		    //2,2
		    setPlayers(2,2);

		    //sD against sC
		    CombineStrategies(strategies.getSD(), strategies.getSC());
		    //2,1
		    setPlayers(2,1);
		    //sC against SD
		    CombineStrategies(strategies.getSC(), strategies.getSD());
		    //1,2
		    setPlayers(1,2);



		    //dE against dE
		    strategies.setDetectiveStructure();
		    strategies.setDE(n, strategies.getDetectiveStruct());

		    //dE against dE
		    CombineStrategies(strategies.getDE(), strategies.getDE());

		    //4,4
		    setPlayers(4,4);

		    //sC against dE
		    //set dE
		    strategies.setDE(n, strategies.getSC());
		    //sC against dE
		    CombineStrategies(strategies.getSC(), strategies.getDE());
		    //1,4
		    setPlayers(1,4);
		    //dE against sC
		    CombineStrategies(strategies.getDE(), strategies.getSC());
		    //4,1
		    setPlayers(4,1);

		    //sD against dE
		    strategies.setDE(n, strategies.getSD());
		    //sD against dE
		    CombineStrategies(strategies.getSD(), strategies.getDE());
		    //2,4
		    setPlayers(2,4);
		    //dE against sDe
		    CombineStrategies(strategies.getDE(), strategies.getSD());
		    //4,2
		    setPlayers(4,2);

		    //gD against dE
		    strategies.setDetectiveStructure();
		    strategies.setDE(n, strategies.getDetectiveStruct());
		    strategies.setGD(n, strategies.getDE());
		    //gD against dE
		    CombineStrategies(strategies.getGD(), strategies.getDE());
		    //3,4
		    setPlayers(3,4);
		    //dE against gD
		    CombineStrategies(strategies.getDE(), strategies.getGD());
		    //4,3
		    setPlayers(4,3);

		    if(n>=5){
		        //tD against dE
		        strategies.setDe_GD(n);
		        //tD against dE
		        CombineStrategies(strategies.getdE_tD_tD(), strategies.getdE_tD_dE());
		        //0,4
		        setPlayers(0,4);
		        //dE against tE
		        CombineStrategies(strategies.getdE_tD_dE(), strategies.getdE_tD_tD());
		        //4,0
		        setPlayers(4,0);
		    } else if (n<5) {
		        //tD against dE
		        strategies.setDetectiveStructure();
		        strategies.setDE(n, strategies.getDetectiveStruct());
		        strategies.setTD(n, strategies.getDE());

		        CombineStrategies(strategies.getTD(),strategies.getDE());
		        //0,4
		        setPlayers(0,4);

		        //dE against tD
		        CombineStrategies(strategies.getDE(), strategies.getTD());
		        //4,0
		        setPlayers(4,0);

		    }
	}

	//Combine Strategies
	private void CombineStrategies(char[] strat1, char[] strat2){
		//Parsing initialScores to int.
		int initScores = Integer.parseInt(initialScores);

		//Getting the number of rounds
		int n = numOfRounds;

		//Setting the initial scores in order to obtain the players scores.
		combination.setScores(initScores);

		combination.Combine(strat1, strat2, n, actionsMat);
	}

	//set player1 and player2
	private void setPlayers(int x, int y){
		strategyMatrix.setStrategyMatrix(x,y,0,combination.getPlayer1Score());
		strategyMatrix.setStrategyMatrix(x,y,1,combination.getPlayer2Score());
	}

	//set Players combinations
	private void setPlayerCombinations(){
		//Setting initial score.
		playersCombination.setTempScore(initialScores);

		//Setting users strategies.
		playersCombination.setTempArray(numOfPlayers, playersData);

		for (int i = 0; i < numOfPlayers; i++){
			playersCombination.setTempScore(initialScores);

			playersCombination.swapPlaces(0, i);

			playersCombination.Conditionals(strategyMatrix.getStrategyMatrix());

			this.playersData[i][1] = Integer.toString(playersCombination.getTempScore());

		}
	}
	
	
	//////////////////////////
	// Writting in files.
	/////////////////////////

	//Write actions Matrix in a file located in the actual working directory
	private void writeActionsMatrix(){
		try{
			FileWriter writer = new FileWriter(System.getProperty("user.dir") 
				+ "/OutputData/ActionsMatrix.txt");

			writer.write("Actions Matrix");
			writer.write(System.lineSeparator());
			writer.write(System.lineSeparator());

			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					for (int k = 0; k < 2; k++) {
						if (k == 0)
							writer.write(this.actionsMat[i][j][k] + " , ");
						else
							writer.write(this.actionsMat[i][j][k] + "");
					}
					writer.write(";");
				}
				writer.write(System.lineSeparator());
			}
			writer.write(System.lineSeparator());

			writer.close();
		} catch (IOException e){
			System.out.println("Unable to write ActionsMatrix");
			e.printStackTrace();
		}
	}

	//Write players data
	private void writePlayersState(String state){
		try{
			FileWriter writer = new FileWriter(System.getProperty("user.dir") 
				+ "/OutputData/Players" + state + ".txt");

			writer.write("Players" + state + "State");
			writer.write(System.lineSeparator());
			writer.write(System.lineSeparator());

			for (int i = 0; i < this.numOfPlayers; i++){
				for (int j  = 0; j < 3; j++) {
					writer.write(this.playersData[i][j] + ";");
				}
				writer.write(System.lineSeparator());
			}
			writer.write(System.lineSeparator());

			writer.close();

		} catch (IOException e) {
			System.out.println("Unable to write Players" + state + "State");
			e.printStackTrace();
		}
	}

	//Write strategy matrix
	private void writeStrategyMatrix(){
		try {
			FileWriter writer = new FileWriter(System.getProperty("user.dir") 
				+ "/OutputData/StrategyMatrix.txt");

			writer.write("Strategy Matrix");
			writer.write(System.lineSeparator());
			writer.write(System.lineSeparator());
			
			for(int i = 0; i < 5; i++) {
			    if(i == 0)
			        writer.write("TD;");
			    if(i == 1)
			        writer.write("SC;");
			    if(i == 2)
			        writer.write("SD;");
			    if(i == 3)
			        writer.write("GD;");
			    if(i == 4)
			        writer.write("DE;");

			    for(int j = 0; j < 5; j++) {

			        for(int k = 0; k < 2; k++) {

			            //writer.write(System.lineSeparator());

			            if(k == 0) {
			                writer.write(strategyMatrix.iterate(i,j,k) + " , ");
			            }
			            else
			                writer.write(strategyMatrix.iterate(i,j,k) + "");

			        }
			        writer.write(";");
			    }
			    writer.write(System.lineSeparator());
			}

			writer.write(System.lineSeparator());
			
			writer.close();
		} catch (IOException e){
			System.out.println("Unable to write StrategyMatrix");
			e.printStackTrace();
		}
	}
}