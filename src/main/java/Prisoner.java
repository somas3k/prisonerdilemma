public class Prisoner {
    private final Integer id;
    private PrisonerStrategy strategy;

    public Prisoner(Integer id, PrisonerStrategy strategy) {
        this.id = id;
        this.strategy = strategy;
    }

    public Integer getId() {
        return id;
    }

    public PrisonerStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(PrisonerStrategy strategy) {
        this.strategy = strategy;
    }
}
