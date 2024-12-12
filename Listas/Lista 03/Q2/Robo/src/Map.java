public class Map {

	private char[][] map;
	private int width;
	private int height;

	// Construtor para inicializar o map com paredes nas extremidades
	public Map(int width, int height) {
		this.width = width;
		this.height = height;
		map = new char[height][width];

		// Inicializa o map com paredes e espaços vazios
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
					map[i][j] = '#'; // Paredes
				} else {
					map[i][j] = ' '; // Espaços vazios
				}
			}
		}
	}

	// Método para desenhar o map
	public void drawMap() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}


	public void addRobotPosition(int x, int y) {
		if (x > 0 && x < width - 1 && y > 0 && y < height - 1) {
			map[y][x] = 'x'; // Define 'x' na posição especificada
		} else {
			System.out.println("Posição inválida! O 'x' não pode ser colocado nas paredes.");
		}
	}
	

	public void movimentRobot(int newX, int newY) {
		if (newX > 0 && newX < width - 1 && newY > 0 && newY < height - 1) {
			// Localiza o 'x' atual e o remove
			for (int i = 1; i < height - 1; i++) {
				for (int j = 1; j < width - 1; j++) {
					if (map[i][j] == 'x') {
						map[i][j] = ' '; // Remove o 'x' da posição atual
						break;
					}
				}
			}
			// Adiciona o 'x' na nova posição
			map[newY][newX] = 'x';
		} else {
			System.out.println("Posição inválida! O 'x' não pode ser movido para as paredes.");
		}
	}

	public char[][] getMap() {
		return map;
	}

	public void setMap(char[][] map) {
		this.map = map;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
}
