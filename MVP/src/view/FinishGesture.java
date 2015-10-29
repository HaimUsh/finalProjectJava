package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;

public class FinishGesture implements BasicCharacter {

	public FinishGesture() {}
	
	@Override
	public void moveChar(GC gc, int pointX, int pointY, int width, int height) 
	{
		try{
		Image i = new Image(null,new FileInputStream("./resources/win.png"));
		gc.drawImage(i, 0, 0, 400, 226, pointX, pointY, width, height);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}