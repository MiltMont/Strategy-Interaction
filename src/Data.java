import java.io.FileWriter;
import java.io.IOException;

public class Data {
	
	ServerObj server = new ServerObj();

	//Attributes
	String[][] playersData;
	String[] playersNames;
	String[] playersStrategies;
	


	//Obtaining data generated in python
	private void obtainData() throws Exception {
		//Server connects and obtains data
		server.InitServer();	
	}


	//Set all data.
	public void setAll() throws Exception {

		obtainData();

		GameData gameData = new GameData(
			server.getNumOfPlayers(),
			server.getInitialScores(),
			server.getNumOfRounds(),
			server.getPlayersNames(),
			server.getPlayersStrategies(),
			server.getPreset()
			);

		System.out.println();
		System.out.println("Process finished succesfully");
		System.out.println();
		

		gameData.execActivities();

	}

}


