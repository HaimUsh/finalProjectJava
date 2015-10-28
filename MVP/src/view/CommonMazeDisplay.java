package view;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;

public abstract class CommonMazeDisplay extends Canvas 
{

	private Maze3d myMaze;

	public Maze3d getMyMaze() 
	{
		return myMaze;
	}

	public void setMyMaze(Maze3d myMaze) 
	{
		if (myMaze != null)
			this.myMaze = myMaze;
	}

	public CommonMazeDisplay(Composite parent, int style)
	{
		super(parent, style);

	}

	public abstract void moveUp();
	public abstract void moveDown(); 
	public abstract void moveLeft(); 
	public abstract void moveRight();
	public abstract void moveBack(); 
	public abstract void moveForward();
	public abstract void updateCurrentPosition(Position _p);
}
