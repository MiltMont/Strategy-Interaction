public class ActionsMatrix {

	//Presets
	int[][][] Cooperative = {
		{{4,4},{-4,4}},
		{{4,-4},{-4,-4}}
	};

	int[][][] Prissioner = {
		{{-9,-9},{0,-10}},
		{{-10,0},{-1,-1}}
	};

	int[][][] Unbalanced = {
		{{1,2},{0,0}},
		{{0,0},{2,1}}
	};

	//Sets action matrix based in selected preset.
	public int[][][] getActionsMatrix(String preset){
		if(preset.equals("cooperative"))
			return Cooperative;
		if(preset.equals("prissioner"))
			return Prissioner;
		if(preset.equals("unbalanced"))
			return Unbalanced;

		return Cooperative; //default preset
	}	
}