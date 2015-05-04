import java.util.ArrayList;
import java.util.Iterator;


public class SearchingGames {
	static String inputKey = "game_name";
	
	public static void main(String[] args) {
		ArrayList<ArrayList<String>> listOfNameDescription = new ArrayList<ArrayList<String>>();
		ArrayList<String> nameDescription = new ArrayList<String>();
		nameDescription.add("game_name");
		nameDescription.add("game_description");
		listOfNameDescription.add(nameDescription);
		Iterator<ArrayList<String>> main_itr = listOfNameDescription.iterator();
		while(main_itr.hasNext()){
			ArrayList<String> nameDesc = main_itr.next();
			Iterator<String> nameDesc_itr = nameDesc.iterator();
			while(nameDesc_itr.hasNext()){
					System.out.println("Yes");
			}
		}
	}
};
