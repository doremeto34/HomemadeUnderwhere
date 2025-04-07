package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity{
	
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp,KeyHandler keyH) {
		
		super(gp);
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 5 * gp.scale;
		solidArea.y = 22 * gp.scale;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 12*gp.scale;
		solidArea.height = 8*gp.scale;
		
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		
		worldX=7*gp.tileSize;
		worldY=7*gp.tileSize;
		speed=4;
		direction = "down";
	}
	public void getPlayerImage() {
		
		up1 = setup("/player/spr_f_maincharau_0", 20, 30);
		up2 = setup("/player/spr_f_maincharau_1", 20, 30);
		up3 = setup("/player/spr_f_maincharau_2", 20, 30);
		up4 = setup("/player/spr_f_maincharau_3", 20, 30);
		down1 = setup("/player/spr_f_maincharad_0", 20, 30);
		down2 = setup("/player/spr_f_maincharad_1", 20, 30);
		down3 = setup("/player/spr_f_maincharad_2", 20, 30);
		down4 = setup("/player/spr_f_maincharad_3", 20, 30);
		left1 = setup("/player/spr_f_maincharal_0", 20, 30);
		left2 = setup("/player/spr_f_maincharal_1", 20, 30);
		right1 = setup("/player/spr_f_maincharar_0", 20, 30);
		right2 = setup("/player/spr_f_maincharar_1", 20, 30);

	}
	public void update() {
		if(keyH.upPressed == true || keyH.downPressed == true ||
				keyH.leftPressed == true || keyH.rightPressed == true) {
			if(keyH.upPressed == true) {
				direction = "up";
			}else
			if(keyH.downPressed == true) {
				direction = "down";
			}else
			if(keyH.leftPressed == true) {
				direction = "left";
			}else
				if(keyH.rightPressed == true) {
				direction = "right";
			}
			
			//CHECK TILE COLLISION
			collisionOn = false;
			gp.cChecker.checkTile(this);
			//CHECK OBJECT COLLISION
			int objIndex = gp.cChecker.checkObject(this, true);
			//CHECK NPC COLLISION
			int npcIndex = gp.cChecker.checkEntity(this,gp.npc);
			interactNPC(npcIndex);
			//pickUpObject(objIndex);
			
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
		else {
			spriteNum = 1; // Stand sprite
		}
	}
	public void pickUpObject(int i) {
		if(i != 999) {
			gp.obj[i] = null;
		}
	}
	public void interactNPC(int i) {
		if(i != 999) {
			
			if(gp.keyH.fPressed == true) {
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}
		}
		gp.keyH.fPressed = false;
	}
	public void draw(Graphics2D g2) {
		BufferedImage  image = null;
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
		g2.drawImage(image, screenX, screenY,null);
	}
}
