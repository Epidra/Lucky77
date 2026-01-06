package mod.lucky77.custom.button;

import mod.lucky77.custom.vector.Vector2;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class ButtonSet {
	
	private List<Button> buttons = new ArrayList<>();
	private ResourceLocation texture;
	private int index = -1;
	
	
	
	
	
	//   -------- -------- -------- --------     CONSTRUCTOR     -------- -------- -------- --------   //
	
	public ButtonSet(){
	
	}
	
	
	
	
	
	//   -------- -------- -------- --------     ADD     -------- -------- -------- --------   //
	
	/** Adds a basic Button with no switch function **/
	public void addButton(int id, Vector2 position, Vector2 texturePositionBASE, Vector2 texturePositionLIGHT, Vector2 size, FunctionActivator activator, FunctionCommand command){
		buttons.add(new Button(id, position, texturePositionBASE, texturePositionBASE, texturePositionLIGHT, size, -1, activator, command));
	}
	
	/** Adds a switchable Button **/
	public void addButton(int id, Vector2 position, Vector2 texturePositionOFF, Vector2 texturePositionON, Vector2 texturePositionLIGHT, Vector2 size, int timerLength, FunctionActivator activator, FunctionCommand command){
		buttons.add(new Button(id, position, texturePositionOFF, texturePositionON, texturePositionLIGHT, size, timerLength, activator, command));
	}
	
	/** Adds a ResourceLocation for rendering **/
	public void addTexture(ResourceLocation rl){
		texture = rl;
	}
	
	
	
	
	
	//   -------- -------- -------- --------     INTERACTION     -------- -------- -------- --------   //
	
	/** Checks for each Button if the Mouse is inside them **/
	public void interact(int leftPos, int topPos, double mouseX, int mouseY){
		for(Button b : buttons){
			b.intersectWithMouse(leftPos, topPos, mouseX, mouseY);
		}
	}
	
	
	
	
	
	//   -------- -------- -------- --------     UPDATE     -------- -------- -------- --------   //
	
	/** Updates timer and visibility of each Button **/
	public void update(int leftPos, int topPos, double mouseX, double mouseY){
		for(Button b : buttons){
			b.update(leftPos, topPos, mouseX, mouseY);
		}
	}
	
	
	
	
	
	//   -------- -------- -------- --------     RENDER     -------- -------- -------- --------   //
	
	/** Renders every Buttons to the screen, if allowed **/
	public void render(GuiGraphics guiGraphics, int leftPos, int topPos){
		for(Button b : buttons){
			b.render(guiGraphics, texture, leftPos, topPos);
		}
	}
	
	
	
	
	
	//   -------- -------- -------- --------     SUPPORT     -------- -------- -------- --------   //
	
	/** Sets the SWITCH flag of a specific Button with the given value **/
	public void setToggle(int compareID, boolean flag){
		for(Button b : buttons){
			b.setSwitched(compareID, flag);
		}
	}
	
	/** Resets the SWITCH flag for all Buttons to FALSE **/
	public void releaseToggle(){
		for(Button b : buttons){
			b.setSwitched(-1, false);
		}
	}
	
	/** Increments the index by 1 and checks if a button exists on that position **/
	public boolean next(){
		index++;
		if(index >= buttons.size()){
			index = -1;
			return false;
		}
		return true;
	}
	
	
	
}
