public class Moves {
    String name;
    int damage;
    int accuracy;
    String type;
    int pp;
    int maxpp;

    public Moves(String name, String type, int damage, int accuracy, int pp){
        this.name = name;
        this.type = type;
        this.damage = damage;
        this.accuracy = accuracy;
        this.pp = pp;
        maxpp = pp;
    }

    public int getMaxpp() {
        return maxpp;
    }

    public void setPp(int pp) {
        this.pp = pp;
    }

    public String getName() {
        return name;
    }

    public int getPp() {
        return pp;
    }
}
