package Pokemon;

public class Moves {
    public String name;
    public int pp;
    public int damage;
    public int maxPp;
    public String type;
    public boolean stat;
    public int priority;
    public int reps;

    public Moves(String type, String name, int pp, int damage, boolean stat, int priority, int reps){
        this.type = type;
        this.priority = priority;
        this.name = name;
        this.damage = damage;
        this.maxPp = pp;
        this.pp = pp;
        this.stat = stat;
        this.reps = reps;
    }

    public String getName() {
        return name;
    }

    public int useMove(){
        if (pp > 0){
            pp--;
            return damage;
        } else {
            return -1;
        }

    }



}
