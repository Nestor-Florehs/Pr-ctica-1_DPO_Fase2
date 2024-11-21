package Business;

public class Member {
    private Long id;
    private String strategy;

    public Member(Long id, String strategy) {
        this.id = id;
        this.strategy = strategy;
    }

    public long getId() {
        return id;
    }

    public String toString() {
        return id + " " + strategy;
    }
}
