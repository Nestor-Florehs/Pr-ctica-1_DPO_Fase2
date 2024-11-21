package Business;

public class Member {
    private Long id;
    private String strategy;
    private String name;

    public Member(Long id, String strategy) {
        this.id = id;
        this.strategy = strategy;
    }

    public long getId() {
        return id;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return id + " " + strategy;
    }
}
