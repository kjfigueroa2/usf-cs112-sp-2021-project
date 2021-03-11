package pokemonExersize;
//Hey!!  Kaila's comment on Alice's Pokemon code :)

public class Pokemon {
  // Can only be accessed by the inherited class
  protected String name;
  protected String color;
  protected boolean hasTrainer;

  // Constructor
  public Pokemon(String nParam, String cParam) {
    this.name = nParam;
    this.color = cParam;
    this.hasTrainer = false;
  }

  public Pokemon(String nParam, String cParam, boolean hParam) {
	  this.name = nParam;
	  this.color = cParam;
	  this.hasTrainer = hParam;
  }
  
  // Default constructor
  public Pokemon() {
	  this.name = "";
	  this.color = "";
	  this.hasTrainer = false;
  }
  
  // Accessor
  public String getName() {
    return this.name;
  }
  
  public String getColor() {
	  return this.color;
  }
  
  public boolean hasTrainer() {
	  return this.hasTrainer;
  }
  
  // Mutator
  public void setName(String nParam) {
    this.name = nParam;
  }
  
  public void setColor(String cParam) {
	  this.color = cParam;
  }
  
  public void setHasTrainer(boolean hParam) {
	  this.hasTrainer = hParam;
  }
  

  // Method
  public String toString() {
    return "I am a Pokemon: " + this.name + " : " + this.color + " : " + this.hasTrainer();
  }
}
  
  class WaterPokemon extends Pokemon{
	  public String waterAttack;
	  public String waterDodge;

	  // Constructor
	  
	 //Default Constructor
	  public WaterPokemon() {
		  this.name = "";
		  this.color = "";
		  this.hasTrainer = false;
		  this.waterAttack = "Splash";
		  this.waterDodge = "Dash";
	  }
	  public WaterPokemon(String nParam, String cParam) {
		  this.name = nParam;
		  this.color = cParam;
		  this.waterAttack = "Splash";
		  this.waterDodge = "Dash";
	  }
	  public WaterPokemon(String nParam, String cParam, String waParam, String wdParam) { 
		  this.name = nParam;
		  this.color = cParam;
		  this.waterAttack = waParam;
		  this.waterDodge = wdParam;
	  }
	  
	  //Accessors
	  public String getWaterAttack() {
		  return this.waterAttack;
	  }
	  
	  public String getWaterDodge() {
		  return this.waterDodge;
	  }
	  
	  //Mutators
	  public void setWaterAttack(String waParam) {
		  this.waterAttack = waParam;
	  }
	  
	  public void setWaterDodge(String wdParam) {
		  this.waterDodge = wdParam;
	  }
	  
	  //method
	  public String toString() {
		    return "I am a Water Pokemon: " + this.name + " : " + this.color + " : " + this.hasTrainer() + " : " + this.waterAttack + " : " + this.waterDodge;
	  } 
}
  class FirePokemon extends Pokemon{
	  public String fireAttack;
	  public String fireDodge;

	  // Constructor
	  
	 //Default Constructor
	  public FirePokemon() {
		  this.name = "";
		  this.color = "";
		  this.hasTrainer = false;
		  this.fireAttack = "Flame";
		  this.fireDodge = "Dodge";
	  }
	  public FirePokemon(String nParam, String cParam) {
		  this.name = nParam;
		  this.color = cParam;
		  this.fireAttack = "Flame";
		  this.fireDodge = "Dodge";
	  }
	  public FirePokemon(String nParam, String cParam, String faParam, String fdParam) { 
		  this.name = nParam;
		  this.color = cParam;
		  this.fireAttack = faParam;
		  this.fireDodge = fdParam;
	  }
	  
	  //Accessors
	  public String getFireAttack() {
		  return this.fireAttack;
	  }
	  
	  public String getFireDodge() {
		  return this.fireDodge;
	  }
	  
	  //Mutators
	  public void setFireAttack(String faParam) {
		  this.fireAttack = faParam;
	  }
	  
	  public void setFireDodge(String fdParam) {
		  this.fireDodge = fdParam;
	  }
	  
	  //method
	  public String toString() {
		    return "I am a Fire Pokemon: " + this.name + " : " + this.color + " : " + this.hasTrainer() + " : "+ this.fireAttack+":"+this.fireDodge;
	  } 
}  
