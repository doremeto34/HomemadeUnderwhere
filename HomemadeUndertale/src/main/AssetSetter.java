package main;

import entity.NPC_TobyDog;
import object.OBJ_Chest;

public class AssetSetter {
	GamePanel gp;
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		gp.obj[0] = new OBJ_Chest(gp);
		gp.obj[0].worldX = 6 * gp.tileSize;
		gp.obj[0].worldY = 6 * gp.tileSize;
		gp.obj[1] = new OBJ_Chest(gp);
		gp.obj[1].worldX = 5 * gp.tileSize;
		gp.obj[1].worldY = 6 * gp.tileSize;
	}
	public void setNPC() {
		
		gp.npc[0] = new NPC_TobyDog(gp);
		gp.npc[0].worldX = 4 * gp.tileSize;
		gp.npc[0].worldY = 7 * gp.tileSize;
	}
}
