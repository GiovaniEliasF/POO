import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
   	private static final int MAX_PLAYERS = 11;
    private static final String RANKING_FILE = "ranking.txt";
    private List<Player> players;
    private Dice dice1;
    private Dice dice2;

    public Game() {
        players = new ArrayList<>();
        dice1 = new Dice();
        dice2 = new Dice();
    }

    public void showRanking() {
        System.out.println("\n*** RANKING TOP 5 ***");
        List<Player> ranking = loadRanking();
        ranking.sort((p1, p2) -> Integer.compare(p2.getWins(), p1.getWins()));
        for (int i = 0; i < Math.min(5, ranking.size()); i++) {
            Player p = ranking.get(i);
            System.out.printf("%d. %s - %d vitória(s)%n", i + 1, p.getName(), p.getWins());
        }
    }

    public void addPlayer(String name, int bet) {
        if (players.size() >= MAX_PLAYERS) {
            System.out.println("O número máximo de jogadores foi atingido.");
            return;
        } boolean duplicatedBets;
        do {
            duplicatedBets = false;
            for (Player p : players) {
                if (p.getBet() == bet) {
                    System.out.println("Aposta duplicada! Escolha outro número.");
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Novo número para aposta: ");
                    bet = scanner.nextInt();
                    scanner.nextLine();
                    duplicatedBets = true;
                    break;
                }
            }
        } while (duplicatedBets);
        players.add(new Player(name, bet));
    }

    public void play() {
        int sumDice = dice1.roll() + dice2.roll();
        System.out.println("\nNúmero sorteado: " + sumDice);

        Player winner = null;
        for (Player jogador : players) {
            if (jogador.getBet() == sumDice) {
                winner = jogador;
                break;
            }
        }

        if (winner != null) {
            System.out.println("Parabéns, " + winner.getName() + "! Você venceu!");
            registerWinners(winner);
        } else {
            System.out.println("A máquina venceu!");
        }
    }

    private void registerWinners(Player winner) {
        List<Player> ranking = loadRanking();
        boolean playerExists = false;

        for (Player p : ranking) {
            if (p.getName().equals(winner.getName())) {
                p.increaseWins();
                playerExists = true;
                break;
            }
        }

        if (!playerExists) {
            winner.increaseWins();
            ranking.add(winner);
        }

        saveRanking(ranking);
    }

    private List<Player> loadRanking() {
        List<Player> ranking = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(RANKING_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] partes = line.split(",");
                String nome = partes[0];
                int wins = Integer.parseInt(partes[1]);
                Player player = new Player(nome, 0);
                for (int i = 0; i < wins; i++) {
                    player.increaseWins();
                }
                ranking.add(player);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar o ranking.");
        }
        return ranking;
    }

    private void saveRanking(List<Player> ranking) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(RANKING_FILE))) {
            for (Player p : ranking) {
                writer.printf("%s,%d%n", p.getName(), p.getWins());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar o ranking.");
        }
    }
}