public class Test {
    public static void main(String[] args){
        Moves flamethrower = new Moves("flamethrower", "fire", 100, 100, 20);
        Moves seismic_toss = new Moves("seismic toss", "fighting", 100, 100, 5);
        Moves dragon_claw = new Moves("dragon claw", "dragon", 100, 100, 20);
        Moves roost = new Moves("roost", "flying", 0, 100, 20);

        Pokemon poke = new Pokemon("Charizard", "fire", "flying", "naive", "indimidate", 80, flamethrower, seismic_toss, dragon_claw, roost );

        poke.useMove(0);
        poke.useMove(1);
        poke.useMove(2);
        poke.useMove(3);
        poke.useMove(4);
        poke.useMove(5);
        poke.useMove(2);
        poke.useMove(2);
        poke.useMove(2);
        poke.useMove(2);
        poke.useMove(2);
    }
}
