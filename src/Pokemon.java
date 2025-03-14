public class Pokemon {
    String type1;
    String type2;
    String nature;
    String ability;
    int level;
    String pokeName;
    Moves[] movelists = new Moves[4];
    Moves move1;
    Moves move2;
    Moves move3;
    Moves move4;

    public Pokemon(String pokeName, String type1, String type2, String nature, String ability, int level, Moves move1, Moves move2, Moves move3, Moves move4){
        this.pokeName = pokeName;
        this.type1 = type1;
        this.type2 = type2;
        this.nature = nature;
        this.ability = ability;
        this.level = level;
        this.move1 = move1;
        this.move2 = move2;
        this.move3 = move3;
        this.move4 = move4;
    }

    public void setMovelists(){
        movelists[1] = move1;
        movelists[2] = move2;
        movelists[3] = move3;
        movelists[4] = move4;
    }

    public String getPokeName() {
        return pokeName;
    }

}
