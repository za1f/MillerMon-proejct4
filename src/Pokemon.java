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
        setMovelists();
    }

    public void setMovelists(){
        movelists[0] = move1;
        movelists[1] = move2;
        movelists[2] = move3;
        movelists[3] = move4;
    }

    public void useMove(int move){
        if ( move < 1 || move > 4){
            System.out.println("Thats not a valid move.");
        } else {
            if (!(movelists[move - 1].getPp() == 0)){
                System.out.println(pokeName + " used " + movelists[move - 1].getName() + "!");
                movelists[move - 1].setPp(movelists[move - 1].getPp() - 1);
            } else {
                System.out.println("You've ran out of pp for " + movelists[move - 1].getName());
            }
        }
    }

    public String getPokeName() {
        return pokeName;
    }

}
