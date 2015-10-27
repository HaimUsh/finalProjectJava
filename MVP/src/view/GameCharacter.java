package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;

import algorithms.mazeGenerators.Moves;

public class GameCharacter {


	int x, y;

	Image charDown, charUp, charLeft, charRight;

	Moves moves;

	public GameCharacter(int x, int y) {
		this.x=x;
		this.y=y;

		try {
			charDown= new Image(null, new FileInputStream(""));
			charUp= new Image(null, new FileInputStream(""));
			charLeft= new Image(null, new FileInputStream(""));
			charRight= new Image(null, new FileInputStream(""));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


		

	}
	
	public void paint(GC gc, int w, int h) {

		switch (moves) {
		case DOWN:
			gc.drawImage(charDown, 0, 0, charDown.getBounds().width, charDown.getBounds().height,
					x - 3, y , w,h);
			break;
		case UP:
			gc.drawImage(charUp, 0, 0, charUp.getBounds().width, charUp.getBounds().height,
					x - 3, y , w,h);
			break;
		case LEFT:
			gc.drawImage(charLeft, 0, 0, charLeft.getBounds().width, charLeft.getBounds().height,
					x - 3, y , w,h);
		break;
		case RIGHT:
			gc.drawImage(charRight, 0, 0, charRight.getBounds().width, charRight.getBounds().height,
					x - 3, y , w,h);
		break;
		
		default:
		break;
		}
	}

}
