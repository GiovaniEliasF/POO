public class Robot {
    private int xPosition;
    private int yPosition;
    private int step = 1;
    private Map map; 

    public Robot(int xPosition, int yPosition, Map map) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.map = map;
        map.addRobotPosition(xPosition, yPosition);
    }

    public void showPosition() {
        System.out.println("Posição atual: (" + xPosition + ", " + yPosition + ")");
    }

    public void frontWalk() {
        if (yPosition - step > 0) {
            yPosition -= step;
            map.movimentRobot(xPosition, yPosition);
        } else {
            System.out.println("Movimento inválido! O robô não pode sair do mapa.");
        }
    showPosition();
	}

    public void backWalk() {
        if (yPosition + step < map.getHeight() - 1) {
            yPosition += step;
            map.movimentRobot(xPosition, yPosition);
        } else {
            System.out.println("Movimento inválido! O robô não pode sair do mapa.");
        }
    showPosition();
	}

    public void leftWalk() {
        if (xPosition - step > 0) {
            xPosition -= step;
            map.movimentRobot(xPosition, yPosition);
        } else {
            System.out.println("Movimento inválido! O robô não pode sair do mapa.");
        }
    showPosition();
	}

    public void rightWalk() {
        if (xPosition + step < map.getWidth() - 1) {
            xPosition += step;
            map.movimentRobot(xPosition, yPosition);
        } else {
            System.out.println("Movimento inválido! O robô não pode sair do mapa.");
        }
    showPosition();
	}
}
