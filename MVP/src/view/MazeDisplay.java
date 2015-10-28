package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Moves;
import algorithms.mazeGenerators.Position;

public class MazeDisplay extends CommonMazeDisplay 
{
	GameCharacter troll = new GameCharacter(0, 0);
	GoalImage finish = new GoalImage();
	FinishGesture win = new FinishGesture();
	
	private boolean flag = false;
	private Position myTempPosition;

	MazeDisplay(Composite composite, int style) 
	{
		super(composite, style);
		


		addPaintListener(new PaintListener()
		{
			@Override
			public void paintControl(PaintEvent e)
			{
				int startFromX = 0;
				int startFromZ = 0;

				int min=0;

				if(getSize().y < getSize().x)
					min=getSize().y+2;
				else 
					min = getSize().x+2;

				if(getMyMaze() != null)
				{

					int levelSelected = getMyMaze().getCurrentPosition().getZ();

					int cellSize = min / getMyMaze().getMaze3d().length;

					e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_BLACK));
					e.gc.fillRectangle(0, 0, cellSize-1, cellSize-1);

					for(int x = 0;x<getMyMaze().getRows();x++)
					{
						for (int z = 0; z < getMyMaze().getCols(); z++)
						{
							{
								startFromX = x*cellSize;
								startFromZ = z*cellSize;
								e.gc.drawRectangle(startFromX, startFromZ, cellSize, cellSize);

								if(getMyMaze().getValue(x, z, levelSelected)==0)
								{
									e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_WHITE));
									e.gc.fillRectangle(startFromX, startFromZ, cellSize, cellSize);
									if (levelSelected < getMyMaze().getLevels())
										if(getMyMaze().getValue(x, z, levelSelected+1)==0)
										{
											e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_GRAY));
											e.gc.fillRectangle(startFromX, startFromZ, cellSize, cellSize);
										}
										else{}
									else{}
									if (levelSelected > 0)
										if(getMyMaze().getValue(x, z, levelSelected-1)==0)
										{
											e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_GRAY));
											e.gc.fillRectangle(startFromX, startFromZ, cellSize, cellSize);
										}
										else{}
									else{}
									if ((levelSelected < getMyMaze().getLevels())&&(levelSelected > 0))
										if((getMyMaze().getValue(x, z, levelSelected-1)==0)&&(getMyMaze().getValue(x, z, levelSelected+1)==0))
										{
											e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_GRAY));
											e.gc.fillRectangle(startFromX, startFromZ, cellSize, cellSize);
										}
										else{}
									else{}
								}
								else
								{
									e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_BLACK));
									e.gc.fillRectangle(startFromX+1, startFromZ+1, cellSize-1, cellSize-1);
								}
							}
						}
					}
					if((getMyMaze().getStartPosition().getZ()) == levelSelected)
					{
						e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_GREEN));
						e.gc.fillRectangle(getMyMaze().getStartPosition().getX()*cellSize, getMyMaze().getStartPosition().getZ()*cellSize, cellSize, cellSize);
					}
					if ((getMyMaze().getCurrentPosition().getZ()) == levelSelected) 
					{
						troll.moveChar(e.gc, getMyMaze().getCurrentPosition().getX()*cellSize, getMyMaze().getCurrentPosition().getZ()*cellSize, cellSize, cellSize);
					}
					if ((getMyMaze().getGoalPosition().getZ()) == levelSelected) 
					{
						finish.moveChar(e.gc, getMyMaze().getGoalPosition().getX()*cellSize, getMyMaze().getGoalPosition().getZ()*cellSize, cellSize, cellSize);
					}

					if (((getMyMaze().getCurrentPosition().getX()) == (getMyMaze().getGoalPosition().getX()))&&((getMyMaze().getCurrentPosition().getY()) == (getMyMaze().getGoalPosition().getY()))&&((getMyMaze().getCurrentPosition().getZ()) == (getMyMaze().getGoalPosition().getZ())))
					{
						win.moveChar(e.gc, 0, 0, ((getMyMaze().getRows())*cellSize), ((getMyMaze().getCols())*cellSize));
					}

					setBackground(e.display.getSystemColor(SWT.COLOR_WHITE));
				}
			}
		});
	}

	@Override
	public void moveUp()
	{
		myTempPosition = new Position(getMyMaze().getCurrentPosition());
		flag = getMyMaze().isAvailable(Moves.UP	,myTempPosition);
		if (flag)
		{
			getMyMaze().movePosition(myTempPosition, Moves.UP);
			redraw();
		}
	}

	@Override
	public void moveDown()
	{
		myTempPosition = new Position(getMyMaze().getCurrentPosition());
		flag = getMyMaze().isAvailable(Moves.DOWN, myTempPosition);
		if (flag)
		{
			getMyMaze().movePosition(myTempPosition, Moves.DOWN);
			redraw();
		}
	}

	@Override
	public void moveLeft()
	{
		myTempPosition = new Position(getMyMaze().getCurrentPosition());
		flag = getMyMaze().isAvailable(Moves.LEFT,myTempPosition);
		if (flag)
		{
			getMyMaze().movePosition(myTempPosition, Moves.LEFT);
			redraw();
		}

	}

	@Override
	public void moveRight()
	{
		myTempPosition = new Position(getMyMaze().getCurrentPosition());
		flag = getMyMaze().isAvailable(Moves.RIGHT, myTempPosition);
		if (flag)
		{
			getMyMaze().movePosition(myTempPosition, Moves.RIGHT);
			redraw();
		}
	}

	@Override
	public void moveForward() 
	{
		myTempPosition = new Position(getMyMaze().getCurrentPosition());
		flag = getMyMaze().isAvailable(Moves.FORWARD, myTempPosition);
		if (flag)
		{
			getMyMaze().movePosition(myTempPosition, Moves.FORWARD);
			redraw();
		}

	}

	@Override
	public void moveBack() 
	{
		myTempPosition = new Position(getMyMaze().getCurrentPosition());
		flag = getMyMaze().isAvailable(Moves.BACK, myTempPosition);
		if (flag)
		{
			getMyMaze().movePosition(myTempPosition, Moves.BACK);

			redraw();
		}
	}

	@Override
	public void updateCurrentPosition(Position _p) 
	{
		getMyMaze().setCurrentPosition(_p.getX(), _p.getZ(), _p.getY());
		redraw();
	}
}