import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        int option;
        Scanner scanner = new Scanner(System.in);
        do {
            Game game = new Game();
            game.showRanking();
            System.out.println("Pressione Enter para começar o jogo...");
            scanner.nextLine();
            
            System.out.println("Quantos jogadores irão participar? (Máximo 11)");
            int numJogadores = scanner.nextInt();
            scanner.nextLine(); 
            
            for (int i = 0; i < numJogadores; i++) {
                System.out.printf("Nome do jogador %d: ", i + 1);
                String nome = scanner.nextLine();
                
                System.out.printf("Número para aposta (1 a 12) do jogador %d: ", i + 1);
                int aposta = scanner.nextInt();
                scanner.nextLine();
                
                game.addPlayer(nome, aposta);
            }
            
            game.play();
            do {
                System.out.println("Jogar novamente? 0-Sim 1-Nao");
                System.out.println("Sua opção");
                option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 0:
                    System.out.println("Iniciando nova partida");
                        break;
                    case 1:
                    System.out.println("Fechando Jogo");
                        break;

                    default:
                    System.out.println("Opcao Invalida");
                        break;
                }
                option = Math.abs(option);
            } while (option > 1 );
        } while (option != 1);
        scanner.close();
    }
}
