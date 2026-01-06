package mod.lucky77.custom.button;

import mod.lucky77.custom.vector.Vector2;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public class Button {
	
	private boolean isActive;
	private boolean isSwitched;
	private boolean isHighlighted;
	private Vector2 position;
	
	private final int ID;
	private final Vector2 size;
	private final Vector2 texposOFF;
	private final Vector2 texposON;
	private final Vector2 texposLIGHT;
	private final FunctionCommand command;
	private final FunctionActivator activator;
	private final int switchID;
	
	private int timer;
	
	
	
	
	
	//   -------- -------- -------- --------     CONSTRUCTOR     -------- -------- -------- --------   //
	
	public Button(int id, Vector2 position, Vector2 texturePositionON, Vector2 texturePositionOFF, Vector2 texturePositionLIGHT, Vector2 size, int timerLength, FunctionActivator activator, FunctionCommand command){
		this.ID = id;
		this.position = position;
		this.texposOFF = texturePositionOFF;
		this.texposON = texturePositionON;
		this.texposLIGHT = texturePositionLIGHT;
		this.size = size;
		this.switchID = timerLength;
		this.activator = activator;
		this.command = command;
		isActive = true;
		isSwitched = false;
		timer = 0;
	}
	
	
	
	
	
	//   -------- -------- -------- --------     INTERACTION     -------- -------- -------- --------   //
	
	public void intersectWithMouse(int leftPos, int topPos, double mouseX, double mouseY){
		if(!isActive) return;
		if(leftPos + position.X < mouseX && mouseX < leftPos + position.X + size.X){
			if(topPos + position.Y < mouseY && mouseY < topPos + position.Y + size.Y){
				command.run();
				if(switchID == 0){
					isSwitched = !isSwitched;
				} else if(switchID > 0){
					isSwitched = true;
					timer = switchID;
				}
			}
		}
	}
	
	
	
	
	
	//   -------- -------- -------- --------     UPDATE     -------- -------- -------- --------   //
	
	public void update(int leftPos, int topPos, double mouseX, double mouseY){
		isActive = activator.run();
		if(isSwitched && switchID > 0){
			timer--;
			if(timer == 0){
				isSwitched = false;
			}
		}
		
		if(isActive){
			isHighlighted = false;
			if(leftPos + position.X < mouseX && mouseX < leftPos + position.X + size.X){
				if(topPos + position.Y < mouseY && mouseY < topPos + position.Y + size.Y){
					isHighlighted = true;
				}
			}
		}
	}
	
	
	
	
	
	//   -------- -------- -------- --------     RENDER     -------- -------- -------- --------   //
	
	public void render(GuiGraphics guiGraphics, ResourceLocation rl, int leftPos, int topPos){
		if(isActive && !isSwitched){ guiGraphics.blit(rl, leftPos + position.X, topPos + position.Y, texposOFF.X,   texposOFF.Y,   size.X, size.Y); }
		if(isActive &&  isSwitched){ guiGraphics.blit(rl, leftPos + position.X, topPos + position.Y, texposON.X,    texposON.Y,    size.X, size.Y); }
		if(isHighlighted          ){ guiGraphics.blit(rl, leftPos + position.X, topPos + position.Y, texposLIGHT.X, texposLIGHT.Y, size.X, size.Y); }
	}
	
	
	
	
	
	//   -------- -------- -------- --------     SUPPORT     -------- -------- -------- --------   //
	
	public void setSwitched(int compareID, boolean flag){
		if(ID == compareID || ID == -1){
			isSwitched = flag;
		}
	}
	
	
	
}
