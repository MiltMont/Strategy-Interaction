import java.io.*;
import java.net.*;
import java.util.*;

class ServerObj {
		
	//Attributes
	String fromClient;

	int numOfPlayers;
	String initialScores;
	String choosePreset;
	int numOfRounds;
	String names;
	String[] playersNames;
	String strategies;
	String[] playersStrategies;

	StringToArray toArr = new StringToArray();

	//initializeLoop
	public void InitServer() throws Exception {
		ServerSocket server = new ServerSocket(8080);
		//System.out.println("Waiting for connection in port 8080");

		boolean run = true;
		while(run) {

			Socket client = server.accept();
			//System.out.println("Got connection on port 8080");
			//System.out.println("Waiting for data...");

			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

			//Obtaining data
			
			//numOfPlayers
			this.fromClient = in.readLine();
			this.numOfPlayers = Integer.parseInt(fromClient);
			//System.out.println(numOfPlayers);
		
			//initialScores
			this.fromClient = in.readLine();
			this.initialScores = fromClient;
			//System.out.println(initialScores);

			//coosePreset
			this.fromClient = in.readLine();
			this.choosePreset = fromClient;
			//System.out.println(choosePreset);

			//numOfRounds
			this.fromClient = in.readLine();
			this.numOfRounds = Integer.parseInt(fromClient);
			//System.out.println(numOfRounds);

			//playersNames
			this.fromClient = in.readLine();
			this.names = fromClient;
			toArr.setData(numOfPlayers, names);
			toArr.stringToArray();
			this.playersNames = toArr.getArray();
			//System.out.println(names);

			//playersStrategies
			this.fromClient = in.readLine();
			this.strategies = fromClient;
			toArr.setData(numOfPlayers, strategies);
			toArr.stringToArray();
			this.playersStrategies = toArr.getArray();
			//System.out.println(strategies);

			run = false;
			//System.out.println("Connection ended");

		}
	}

	//getNumOfPlayers
	public int getNumOfPlayers(){
		return this.numOfPlayers;
	}

	//getInitialScores
	public String getInitialScores(){
		return this.initialScores;
	}

	//getChoosePreset
	public String getPreset(){
		return this.choosePreset;
	}
	
	//getNumOfRounds
	public int getNumOfRounds(){
		return this.numOfRounds;
	}

	//getPlayersNames
	public String[] getPlayersNames(){
		return this.playersNames;
	}

	//getPlayersStrategies
	public String[] getPlayersStrategies(){
		return this.playersStrategies;
	}
}