package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {
	
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[100];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		loadMap("/maps/world03.txt",40,12);
	}
	public void getTileImage() {	
	
		setup(0,"blank",false);
		setup(1, "tile_001", true);
		setup(2, "tile_002", true);
		setup(3, "tile_003", true);
		setup(4, "tile_004", true);
		setup(5, "tile_005", true);
		setup(6, "tile_006", true);
		setup(7, "tile_007", true);
		setup(17, "tile_017", true);
		setup(18, "tile_018", true);
		setup(19, "tile_019", true);
		setup(20, "tile_020", true);
		setup(21, "tile_021", true);
		setup(22, "tile_022", true);
		setup(23, "tile_023", true);
		setup(32, "tile_032", false);
		setup(37, "tile_037", true);
		setup(38, "tile_038", true);
		setup(39, "tile_039", true);
		setup(50, "tile_050", false);
		setup(53, "tile_053", true);
		setup(54, "tile_054", true);
		setup(55, "tile_055", false);
		setup(66, "tile_066", false);
		setup(69, "tile_069", true);
		setup(70, "tile_070", true);
		setup(97, "tile_097", false);

		
	}
	public void setup(int index,String imagePath,boolean collision) {
		
		UtilityTool uTool = new UtilityTool();
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imagePath + ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void loadMap(String filepath,int maxMapCol,int maxMapRow) {
		
		try {
			InputStream is = getClass().getResourceAsStream(filepath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			while(col < maxMapCol && row < maxMapRow) {
				
				String line = br.readLine();
				
				while(col < maxMapCol) {
					
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == maxMapCol) {
					col = 0;
					row++;
				}
			}
			br.close();
		}catch(Exception e) {
			
		}
	}
	public void draw(Graphics2D g2) {
		
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
			   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
			   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
			   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

				g2.drawImage(tile[tileNum].image, screenX, screenY, null);

			}
			
			worldCol++;
			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
}
