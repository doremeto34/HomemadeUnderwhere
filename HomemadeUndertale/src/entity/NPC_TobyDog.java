package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class NPC_TobyDog extends Entity{
	
	public NPC_TobyDog(GamePanel gp) {
		super(gp);
		
		direction = "up";
		speed = 0;
		getImage();
		setDialogue();
	}
	public void getImage() {
		
		up1 = setup("/npc/tbdog_0",27,19);
		up2 = setup("/npc/tbdog_1",27,19);
		up3 = setup("/npc/tbdog_2",27,19);
		up4 = setup("/npc/tbdog_3",27,19);
		
	}
	public void setDialogue() {
		
		dialogues[0] = "Wolf wolf.";
		dialogues[1] = "Nice to meet you.";
		dialogues[2] = "I lie here from afternoon";
		dialogues[3] = "Lorem ipsum dolor sit amet, consectetur\n adipiscing elit, sed do eiusmod tempor incididunt\n ut labore et dolore magna aliqua";
	}
	public void setAction() {
		
		actionLockCounter++;
		
		if(actionLockCounter == 120) {
			
			Random random = new Random();
			int i = random.nextInt(100)+1;
			if(i <= 25) {
				direction = "up";
			}else
			if(i <= 50) {
				direction = "down";
			}else
			if(i <= 75) {
				direction = "left";
			}else
			if(i <= 100) {
				direction = "right";
			}
			actionLockCounter = 0;
		}
	}
	public void speak() {
		
		super.speak();
	}
}
