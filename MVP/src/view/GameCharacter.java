package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;

import algorithms.mazeGenerators.Moves;

public class GameCharacter implements BasicCharacter {


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

	@Override
	public void moveChar(GC gc, int pointX, int pointZ, int width, int height) 
	{

		gc.drawImage(charFront, 0, 0, 256, 256, pointX, pointZ, width, height);
		


	}



	public void moveChar2(GC gc, int pointX, int pointZ, int width, int height) {
		switch(moves){

		case FORWARD: 

			gc.drawImage(charFront, 0, 0, 256, 256, pointX, pointZ, width, height);
			break;

		case BACK: 

			gc.drawImage(charFront, 0, 0, 256, 256, pointX, pointZ, width, height);
			break;

		case LEFT: 

			gc.drawImage(charFront, 0, 0, 256, 256, pointX, pointZ, width, height);
			break;

		case RIGHT: 

			gc.drawImage(charFront, 0, 0, 256, 256, pointX, pointZ, width, height);
			break;
		default:
			break;

		}

	}




}


