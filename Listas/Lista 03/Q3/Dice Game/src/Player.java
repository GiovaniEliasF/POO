public class Player {
	private String name;
    private int bet;
    private int wins;

    public Player(String name, int bet) {
        this.name = name;
        this.bet = bet;
        this.wins = 0;
    }

    public String getName() {
        return name;
    }

    public int getBet() {
        return bet;
    }

    public int getWins() {
        return wins;
    }

    public void increaseWins() {
        this.wins++;
    }

}
