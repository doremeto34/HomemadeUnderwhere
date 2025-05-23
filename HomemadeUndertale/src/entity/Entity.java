package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {

	GamePanel gp;
	public int worldX,worldY;
	public int speed;
	public BufferedImage up1,up2,up3,up4,down1,down2,down3,down4,left1,left2,right1,right2;
	public String direction;
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle solidArea = new Rectangle(0,0,60,60);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = true;
	public int actionLockCounter = 0;
	String dialogues[] = new String[20];
	int dialogueIndex = 0;
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	public void speak() {
		if(dialogues[dialogueIndex] == null) {
			dialogueIndex = 0;
		}
		gp.ui.currentDialogue = dialogues[dialogueIndex];
		dialogueIndex++;
	}
	public void setAction() {
		
	}
	public void update() {
		
		setAction();
		
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkPlayer(this);
		//IF COLLISION IS FALSE, PLAYER CAN MOVE
		if(collisionOn == false) {
			
			switch(direction) {
			case "up":worldY -= speed;break;
			case "down":worldY += speed;break;
			case "left":worldX -= speed;break;
			case "right":worldX += speed;break;
			}
		}
		
		spriteCounter++;
		if(spriteCounter > 10) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}else
			if(spriteNum == 2) {
				spriteNum = 3;
			}else
			if(spriteNum == 3) {
				spriteNum = 4;
			}else
			if(spriteNum == 4) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}
	public void draw(Graphics2D g2) {
		
		BufferedImage  image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
		   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
		   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
		   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			
			switch(direction) {
			case "up":
				if(spriteNum == 1) {
					image = up1;
				}
				if(spriteNum == 2) {
					image = up2;
				}
				if(spriteNum == 3) {
					image = up3;
				}
				if(spriteNum == 4) {
					image = up4;
				}
				break;
			case "down":
				if(spriteNum == 1) {
					image = down1;
				}
				if(spriteNum == 2) {
					image = down2;
				}
				if(spriteNum == 3) {
					image = down3;
				}
				if(spriteNum == 4) {
					image = down4;
				}
				break;
			case "left":
				if(spriteNum == 1 || spriteNum == 3) {
					image = left1;
				}
				if(spriteNum == 2 || spriteNum == 4) {
					image = left2;
				}
				break;
			case "right":
				if(spriteNum == 1 || spriteNum == 3) {
					image = right1;
				}
				if(spriteNum == 2 || spriteNum == 4) {
					image = right2;
				}
				break;
			}
			
			g2.drawImage(image, screenX, screenY, null);

		}		
	}
	public BufferedImage setup(String imagePath,int scaleX,int scaleY) {
		
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, scaleX*gp.scale,scaleY*gp.scale);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
