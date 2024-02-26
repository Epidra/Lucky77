package mod.lucky77.util.button;

import mod.lucky77.util.Vector2;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ButtonSet {
	
	private List<Button> buttons = new ArrayList<>();
	private int index = -1;
	
	
	
	
	
	// ---------- ---------- ---------- ----------  CONSTRUCTOR  ---------- ---------- ---------- ---------- //
	
	public ButtonSet(){
	
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  ADD BUTTON  ---------- ---------- ---------- ---------- //
	
	public void addButton(int id, Vector2 position, Vector2 map, Vector2 mapLIGHT, Vector2 size, FunctionActivator activator, FunctionCommand command){
		buttons.add(new Button(id, position, map, map, mapLIGHT, size, -1, activator, command));
	}
	
	public void addButton(int id, Vector2 position, Vector2 mapOFF, Vector2 mapON, Vector2 mapLIGHT, Vector2 size, int timerStart, FunctionActivator activator, FunctionCommand command){
		buttons.add(new Button(id, position, mapOFF, mapON, mapLIGHT, size, timerStart, activator, command));
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  INTERACTION  ---------- ---------- ---------- ---------- //
	
	public void interact(int left, int top, double mouseX, double mouseY){
		for(Button b : buttons){
			b.collideWithMouse(left, top, mouseX, mouseY);
		}
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  UPDATE  ---------- ---------- ---------- ---------- //
	
	public void update(int left, int top, double mouseX, double mouseY){
		for(Button b : buttons){
			b.update(left, top, mouseX, mouseY);
		}
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  SUPPORT  ---------- ---------- ---------- ---------- //
	
	public void setToggle(int compare, boolean flag){
		for(Button b : buttons){
			b.setSwitched(compare, flag);
		}
	}
	
	public void releaseToggle(){
		for(Button b : buttons){
			b.isSwitched = false;
		}
	}
	
	public boolean next(){
		index++;
		if(index >= buttons.size()){
			index = -1;
			return false;
		}
		return true;
	}
	
	public Vector2 pos(){
		return buttons.get(index).position;
	}
	
	public Vector2 map(){
		return buttons.get(index).map();
	}
	
	public int sizeX(){
		return buttons.get(index).width();
	}
	
	public int sizeY(){
		return buttons.get(index).height();
	}
	
	public Vector2 highlight(){
		return buttons.get(index).highlight();
	}
	
	public boolean isVisible(){
		return buttons.get(index).isActive;
	}
	
	public boolean isHighlighted(){
		return isVisible() && buttons.get(index).isHighlighted;
	}
	
	
	
}
