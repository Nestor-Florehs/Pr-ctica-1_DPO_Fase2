public class Character {
    private long id;
    private String name;
    private int weight;

    public Character(long id, String name, int weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    public String toString() {
        return id + " " + name + " " + weight;
    }
}
