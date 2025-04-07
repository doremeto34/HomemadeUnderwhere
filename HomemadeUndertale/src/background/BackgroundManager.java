package background;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class BackgroundManager {
	
	GamePanel gp;
	Background[] background;
	public int bgId;
	public int bgWidth;
	public int bgHeight;
	
	public BackgroundManager(GamePanel gp) {
		
		this.gp = gp;
		
		background = new Background[10];
		getBackgroundImage();
		loadBackground("/maps/background01.txt");
	}
	public void getBackgroundImage() {
		try {
			background[1] = new Background();
			background[1].image = ImageIO.read(getClass().getResourceAsStream("/backgrounds/bg_lastruins_1.png"));
			background[2] = new Background();
			background[2].image = ImageIO.read(getClass().getResourceAsStream("/backgrounds/bg_lastruins_2.png"));
			background[3] = new Background();
			background[3].image = ImageIO.read(getClass().getResourceAsStream("/backgrounds/bg_lastruins_3.png"));
			background[4] = new Background();
			background[4].image = ImageIO.read(getClass().getResourceAsStream("/backgrounds/bg_lastruins_4.png"));
			background[5] = new Background();
			background[5].image = ImageIO.read(getClass().getResourceAsStream("/backgrounds/bg_lastruins_5.png"));
			background[6] = new Background();
			background[6].image = ImageIO.read(getClass().getResourceAsStream("/backgrounds/bg_lastruins_6.png"));
			background[7] = new Background();
			background[7].image = ImageIO.read(getClass().getResourceAsStream("/backgrounds/bg_lastruins_7.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void loadBackground(String filepath) {
		
		try {
			InputStream is = getClass().getResourceAsStream(filepath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			for(int i=1;i<=7;++i) {
				String line = br.readLine();
				String numbers[] = line.split(" ");
				background[i].width = Integer.parseInt(numbers[1]);
				background[i].height = Integer.parseInt(numbers[2]);
			}
			br.close();
		}catch(Exception e) {
			
		}
	}
	public void draw(Graphics2D g2) {
		
		//BufferedImage png = ImageIO.read(new File(filepath));
		//int pngWidth = png.getWidth();
		//int pngHeight = png.getHeight();
		
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol < 1 && worldRow < 1) {
						
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			for(int i=1;i<=7;++i) {
				g2.drawImage(background[i].image, screenX + gp.player.worldX/(i+1), screenY, gp.tileSize*background[i].width, gp.tileSize*background[i].height, null);
				g2.drawImage(background[i].image, screenX + gp.player.worldX/(i+1) + 960, screenY, gp.tileSize*background[i].width, gp.tileSize*background[i].height, null);
				
			}
			
			worldCol++;
			
			if(worldCol == 1) {
				worldCol = 0;
				worldRow++;
			}
		}

	}
}
