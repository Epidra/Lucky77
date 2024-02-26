package mod.lucky77.util.button;

import mod.lucky77.util.Vector2;

@SuppressWarnings("unused")
public class Button {
	
	public boolean isActive;
	public boolean isSwitched;
	public boolean isHighlighted;
	public Vector2 position;
	
	private final int ID;
	private final Vector2 size;
	private final Vector2 mapOFF;
	private final Vector2 mapON;
	private final Vector2 mapLIGHT;
	private final FunctionCommand command;
	private final FunctionActivator activator;
	private final int switchID;
	
	private int timer;
	
	
	
	
	
	// ---------- ---------- ---------- ----------  CONSTRUCTOR  ---------- ---------- ---------- ---------- //
	
	public Button(int id, Vector2 position, Vector2 mapOFF, Vector2 mapON, Vector2 mapLIGHT, Vector2 size, int timerLength, FunctionActivator activator, FunctionCommand command){
		this.ID = id;
		this.position  = position;
		this.mapOFF    = mapOFF;
		this.mapON     = mapON;
		this.mapLIGHT  = mapLIGHT;
		this.size      = size;
		this.switchID  = timerLength;
		this.activator = activator;
		this.command   = command;
		isActive       = true;
		isSwitched     = false;
		timer          = 0;
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  INTERACTION  ---------- ---------- ---------- ---------- //
	
	public void collideWithMouse(int left, int top, double mouseX, double mouseY){
		if(isActive){
			if(left + position.X < mouseX && mouseX < left + position.X + width()){
				if(top + position.Y < mouseY && mouseY < top + position.Y + height()){
					command.run();
					if(switchID == 0){
						isSwitched = !isSwitched;
					} if(switchID > 0){
						isSwitched = true;
						timer = switchID;
					}
				}
			}
		}
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  UPDATE  ---------- ---------- ---------- ---------- //
	
	public void update(int left, int top, double mouseX, double mouseY){
		isActive = activator.run();
		if(isSwitched){
			if(switchID > 0){
				timer--;
				if(timer == 0){
					isSwitched = false;
				}
			}
		}
		if(isActive){
			isHighlighted = false;
			if(left + position.X < mouseX && mouseX < left + position.X + width()){
				if(top + position.Y < mouseY && mouseY < top + position.Y + height()){
					isHighlighted = true;
				}
			}
		}
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  SUPPORT  ---------- ---------- ---------- ---------- //
	
	public void setSwitched(int compare, boolean flag){
		if(ID == compare){
			isSwitched = flag;
		}
	}
	
	public Vector2 map(){
		if(isSwitched){
			return mapON;
		} return mapOFF;
	}
	
	public Vector2 highlight(){
		return mapLIGHT;
		
	}
	
	public int width(){
		return size.X;
	}
	
	public int height(){
		return size.Y;
	}
	
	
	
}
