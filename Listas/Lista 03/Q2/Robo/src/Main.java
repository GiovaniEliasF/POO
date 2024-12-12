import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        Map map = new Map(20,40);
        Robot robot = new Robot(1,1,map);
        map.addRobotPosition(1, 1);
        int option;
        do {
            System.out.println("Robo");
            System.out.println("1 - Movimentando para Cima");
            System.out.println("2 - Movimentando para Baixo");
            System.out.println("3 - Movimentando para Esquerda");
            System.out.println("4 - Movimentando para Direita");
            System.out.println("5 - Mostrar mapa");
            System.out.println("0 - Sair");
            System.out.println("Selecione a Opcao desejada: ");
            option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1:
                    robot.frontWalk();
                break;
                case 2:
                    robot.backWalk();
                break;
                case 3:
                    robot.leftWalk();
                break;
                case 4:
                    robot.rightWalk();
                break;
                case 5:
                    map.drawMap();
                break;

                default:
                break;
            }
        } while (option != 0);
        input.close();
    }
}
