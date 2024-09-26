import java.util.Scanner;
public class App {
	public static void main(String[] args) throws Exception {
        int op = 0;
        Caneta Caneta1 = new Caneta("Compaq", "Azul", 0.5f, 100, true);
        Scanner cin = new Scanner(System.in);
        do {
            System.out.println("Escolha uma opcao:");
            System.out.println("1 - Status");
            System.out.println("2 - Escrever");
            System.out.println("3 - Tampar");
            System.out.println("4 - Destampar");
            System.out.println("5 - IsTampada");
            System.out.println("6 - Sair");
            System.out.println("Escolha uma Opcao:");
            op = cin.nextInt();
            switch (op) {
                case 1:
                    Caneta1.status();
                    break;
                case 2:
                    Caneta1.escrever();
                    break;
                case 3:
                    Caneta1.tampar();
                    break;
                case 4:
                    Caneta1.destampar();
                    break;
                case 5:
                    Caneta1.isTampada();
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    break;
            }
        } while (op != 6);

    }
}

