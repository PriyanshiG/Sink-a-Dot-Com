import java.util.*;
import java.util.Scanner;
import java.io.*;
class DotComBust{
	private GameHelper helper = new GameHelper();
	private ArrayList<DotCom> dotcomslist = new ArrayList<DotCom>();
	private int numofguesses = 0;
	
	private void setupgame(){
		DotCom one = new DotCom();
		one.setname("codeforces.com");
		DotCom two = new DotCom();
		two.setname("facebook.com");
		DotCom three = new DotCom();
		three.setname("google.com");
		dotcomslist.add(one);
		dotcomslist.add(two);
		dotcomslist.add(three);
		
		System.out.println("Your goal is to sink three dot coms.");
		System.out.println("..........GAME BEGINS.........");
		for(DotCom d : dotcomslist){
			ArrayList<String> newlocation = helper.placedotcom(3);
			d.setlocationcells(newlocation);
		}
	}
	
	private void startplaying(){
		while(!dotcomslist.isEmpty()){
		System.out.println("Enter a guess!");
		Scanner sc = new Scanner(System.in);
		String userguess = sc.nextLine();
		checkuserguess(userguess);
		}
		finishgame();
	}
	private void finishgame(){
		System.out.println("All DotComs are dead!");
		System.out.print("Score : ");
		System.out.println(numofguesses);
		if(numofguesses <= 18){
			System.out.println("|You hit the barn with bazooka|");
		}
		else{
			System.out.println("duh! slowbie :(");
		}
	}
	private void checkuserguess(String userguess){
		
		numofguesses++;
		
		String result = "miss";
		for(DotCom d : dotcomslist){
			result = d.checkyourself(userguess);
			if(result.equals("hit")){
				break;
			}
			if(result.equals("kill")){
				dotcomslist.remove(d);
				break;
			}
		}
		System.out.println(result);
	}

	public static void main(String args[]){
		DotComBust game = new DotComBust();
		game.setupgame();
		game.startplaying();
	}
	
}


 class DotCom{
	private ArrayList<String> locationcells;
	private String name;
	public void setname(String n){
		name = n;
	}
	
	public void setlocationcells(ArrayList<String> loc){
	locationcells = loc;
	}
	public String checkyourself(String userinput){
		String result = "miss";
		int index = locationcells.indexOf(userinput);
		if(index  >= 0){
		locationcells.remove(index);
			if(locationcells.isEmpty()){
				result = "kill";
				System.out.println("Ouch! you sunk " + name + "  :(");
			}
			else{
			result = "hit";
			}
		}
		return result;
	}
}
class GameHelper{
	private int gridlength = 7;
	private static int gridsize = 49;
	static final int grid[]  = new int[gridsize];
	private int comcount = 0;

	public ArrayList<String> placedotcom(int comsize){
	
		ArrayList<String> alphacells = new ArrayList<String>();
		String temp = null;
		int attempts = 0;
		int location = 0 ;
		boolean success = false;
		int coords[] = new int[comsize];
		String alphacoords[] = new String[comsize];
		int inc = 1;
		comcount++;
		if((comcount % 2)==1){
			inc = gridlength;
		}
		
		while(!success && attempts++ < 200){
			location = (int) (Math.random()*gridsize);
			int x = 0;
			success = true;
			while(success && x < comsize){
				if(grid[location] == 0){
					coords[x++]=location;
					location += inc;
					if(location >= gridsize){
						success = false;
					}
					if(x>0 &&(location % gridlength)==0)
					success = false;
				}
					else success = false;

			}
		}
			int row = 0,column = 0,y = 0;
			while(y < comsize){
			 grid[coords[y]] = 1;
			// System.out.println(coords[y]);
			 row = (int)(coords[y] / gridlength);
			 column = coords[y] % gridlength;
			 char t = (char)('A' + column);
			 temp = Character.toString(t);
			 alphacells.add(temp.concat(Integer.toString(row)));
			 System.out.println(temp.concat(Integer.toString(row)));
			 y++;
			}

		return alphacells;
	}

}