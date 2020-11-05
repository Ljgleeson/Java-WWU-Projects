package Game;

public class Scene{

  int sceneNum;
  int budget;
  int[] roles;
  String name;
  boolean use;

  public Scene(int num, String name, int budget, int[] roles){
    this.name = name;
    this.budget = budget;
    this.roles = roles;
    this.use = true;
    this.sceneNum = num;
  }


public void updateUse(){
    this.use = false;
  }

  public boolean getUse(){
    return this.use;
  }

  public void updateRoles(int index){
    this.roles[index] = -1;
  }

  public int[] sceneRoles(){
    return this.roles;
  }

  public int getBudget(){
    return this.budget;
  }

  public String getName(){
    return this.name;
  }
}
