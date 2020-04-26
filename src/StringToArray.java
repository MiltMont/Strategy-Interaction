import java.util.*;

public class StringToArray {
	//Atributes
	int numOfPlayers;
	String names;
	String[] playersNames;
	int k;

	public void setData(int numOfPlayers, String names){
		this.numOfPlayers = numOfPlayers;
		this.names = names;
		this.k = 0;
		this.playersNames = new String[this.numOfPlayers];
	}

	//Debugging purposes
	public void printData(){
		System.out.println("Number of players " + this.numOfPlayers);
		System.out.println("Names: " + this.names);
		System.out.println("k " + this.k);
		System.out.println("len " + this.playersNames.length);
	}

	//String to arr.
	public void stringToArray(){
		for(int i = 0; i < this.numOfPlayers; i++){
			List<Character> word = new ArrayList<Character>();
			for(k = k; k < this.names.length(); k++){
				if(this.names.charAt(k) != ','){
					word.add(this.names.charAt(k));
				} else {
					k++;
					break;
				}
			}
			StringBuilder sb = new StringBuilder();
			for(Character ch: word){
				sb.append(ch);
			}
			String string = sb.toString();
			this.playersNames[i] = string;
			//Debugging
			//System.out.println(playersNames[i]);
			//System.out.println(this.k);
		}
	}

	//get Array
	public String[] getArray(){
		return this.playersNames;
	}
	

}