package Game;

public class Location{

int index;
int shot;
int[] role;
int[] moves;
String name;

 public Location(int index, String name, int[] moves){
    this.index = index;
    this.name = name;
    this.moves = moves;
  }


  public Location(int index, String name, int[] moves, int[] roles, int shot){
    this.index = index;
    this.name = name;
    this.moves = moves;
    this.shot = shot;
    this.role = roles;
  }

  public int[] getRoles(){
    return this.role;
  }

  public void updateRoles(int index){
    this.role[index] = -1;
  }

  public void updateShot(){
    this.shot = this.shot - 1;
  }

  public int getShot(){
    return this.shot;
  }

  public String getName(){
    return this.name;
  }

  public int[] getMoves(){
    return this.moves;
  }
  
  public int getIndex() {
	  return this.index;
  }
}
