package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;

import algorithms.mazeGenerators.Moves;

public class GameCharacter {


	int x, y;

	Image charFront, charBack, charLeft, charRight;

	Moves moves;

	public GameCharacter(int x, int y) {
		this.x=x;
		this.y=y;

		try {
			charFront= new Image(null, new FileInputStream("resources/front.png"));
			charBack= new Image(null, new FileInputStream("resources/back.png"));
			charLeft= new Image(null, new FileInputStream("resources/left.png"));
			charRight= new Image(null, new FileInputStream("resources/right.png"));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


		

	}
	
	public void paint(GC gc, int w, int h) {

		switch (moves) {
		case FORWARD:
			gc.drawImage(charFront, 0, 0, charFront.getBounds().width, charFront.getBounds().height,
					x - 3, y , w,h);
			break;
		case BACK:
			gc.drawImage(charBack, 0, 0, charBack.getBounds().width, charBack.getBounds().height,
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
