package view;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;

public class GoalImage implements BasicCharacter {

	public GoalImage() {}
	
	@Override
	public void moveChar(GC gc, int pointX, int pointZ, int width, int height) 
	{
		Image i = new Image(null, "./resources/finish.png");
		gc.drawImage(i, 0, 0, 291, 299, pointX, pointZ, width, height);
	}

}