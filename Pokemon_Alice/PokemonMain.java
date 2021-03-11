package pokemonExersize;

// This is a Driver Class
public class PokemonMain {

	public static void main(String[] args) {

	    Pokemon pickachu = new Pokemon("Pikachu", "Yellow", true);
	    Pokemon charmander = new Pokemon("Charmander", "Red");
	    Pokemon defaultPokemon = new Pokemon();
	    
	    //WaterPokemon
	    WaterPokemon fishdude = new WaterPokemon("fishdude","blue");
	    WaterPokemon OceanMan = new WaterPokemon("OceanMan", "Aqua","killkillkill","zip on outta there");
	    WaterPokemon defaultwater = new WaterPokemon();
	    
	    //FirePokemon
	    FirePokemon flamegirl = new FirePokemon("flamegirl", "orange");
	    FirePokemon FireWoman = new FirePokemon("FireWoman", "red","death rain", "flee");
	    FirePokemon defaultfire = new FirePokemon();
	    
	    // Will print first constructor output
	    System.out.println(pickachu.toString());
	    System.out.println(fishdude.toString());
	    System.out.println(flamegirl.toString());

	    // Will print second constructor output
	    System.out.println(charmander.toString());
	    System.out.println(OceanMan.toString());
	    System.out.println(FireWoman.toString());
	    
	    //Will print defaults
	    System.out.println(defaultPokemon.toString());
	    System.out.println(defaultwater.toString());
	    System.out.println(defaultfire.toString());
	    
	    //Change Color (mutators)
	    pickachu.setColor("Orange");
	    fishdude.setColor("Fish Colored");
	    flamegirl.setColor("Flame Color");
	    
	    //get color (accessors)
	    System.out.println(pickachu.getColor());
	    System.out.println(fishdude.getColor());
	    System.out.println(flamegirl.getColor());
	}

}
