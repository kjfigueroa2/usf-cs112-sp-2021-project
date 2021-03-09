package inclassexercise;

public abstract class Pokemon {
	protected String name;
	  private String color;
	  protected boolean hasTrainer;
	  private String setMovement;

	  // Constructor
	  public Pokemon(String nParam, String cParam) {
	    this.name = nParam;
	    this.color = cParam;
	    this.hasTrainer = false;
	  } 

	  public Pokemon(String nParam, String cParam, boolean hParam,String mParam) {
		  this.name = nParam;
		  this.color = cParam;
		  this.hasTrainer = hParam;
		  this.setMovement = mParam;
	  }
	  
	  // Default constructor
	  public Pokemon() {
		  this.name = "";
		  this.color = "";
		  this.hasTrainer = false;
		  this.setMovement = "";
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
	  
	  public String setMovement() {
		  return this.setMovement;
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
	  
	  public void setMovement(String mParam) {
		  this.setMovement = mParam;
	  }

	  // Method
	  public String toString() {
	    return "I am a Pokemon: " + this.name + " : " + this.color + " : " + this.hasTrainer();
	  }
	  
	  public boolean equals (Object obj) {
		  if (obj instanceof Pokemon) {
			  Pokemon WaterPokemon = (Pokemon) obj;
			  if (this.name == WaterPokemon.getName() &&
					 this.color == WaterPokemon.getColor()) {
				  	System.out.println("\nChecked equals under Parent's Pokemon");
				  return true;
			  }  
		  }
		  return false;
	  }
	  
	  //Abstract Methods
	  public abstract void celebrateMessage();
	  public abstract void loseMessage();
	  
	  

	}
