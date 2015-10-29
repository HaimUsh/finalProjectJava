package view;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import solution.Solution;
import states.State;


 public class MazeWindow extends BasicWindow implements Closeable {
	
	
	private String propertiesFilePath;
	private Text Level;
	private MessageBox msg;
	private CommonMazeDisplay mazeDisplay;
	private Maze3d maze;
	private String mazeName;
	private String floorY;
	private Solution Solution;
	private boolean verifyData1 = false;
	private boolean verifyData2 = false;
	private Button hint;
	private Button solve;
	private Timer time;
	private TimerTask myTask;
	Image backImage; 

	public MazeWindow(String title, int width, int height)
	{
		super(title, width, height);
		this.mazeName = new String(" No Maze");
		this.floorY = new String("");
		
	}

	@Override
	public void start()
	{
		run();
	}

	@Override
	public void display(String userInput){} 
	@Override
	public void displayDirectory(String[] path){}
	@Override
	public void displayMenu(){}
	


	@Override
	public void displayCrossSection(int[][] crossSection)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void displayMaze(Maze3d maze)
	{
		displayMazeGUI(maze);
	}

	public void displayMazeGUI(Maze3d maze)
	{
		if (maze != null) 
		{
			setMyViewMaze(maze);
			mazeDisplay.setMyMaze(maze);
			mazeDisplay.redraw();
			mazeDisplay.forceFocus();
		}
	}


	@Override
	public void displaySolution(Solution s)
	{
		// TODO Auto-generated method stub

	}

	@Override
	void initWidgets()
	{
		Color color = new Color(display, 55, 45, 35);
		shell.setLayout(new GridLayout(2, false));
		shell.setBackground(color);;
		shell.addListener(SWT.Close, new Listener() 
		{
			@Override
			public void handleEvent(Event arg0)
			{
				int style = SWT.APPLICATION_MODAL | SWT.YES | SWT.NO;
				changeAndNotify("exit");
				msg = new MessageBox(shell, style);
				msg.setText("Information");
				msg.setMessage("Are you sure?");
				arg0.doit = msg.open() == SWT.YES;
			}
		});
		
		Menu menuBar = new Menu(shell, SWT.BAR);
		Menu fileMenu = new Menu(menuBar);
		MenuItem fileItem = new MenuItem(menuBar, SWT.CASCADE);
	    
		fileItem.setText("File");
	    fileItem.setMenu(fileMenu);


	    MenuItem newItem = new MenuItem(fileMenu, SWT.CASCADE);
	    newItem.setText("New");
	    Menu popupMenu = new Menu(newItem);
	    newItem.setMenu(popupMenu);
	    Menu newMenu = new Menu(popupMenu);
	    newItem.setMenu(newMenu);
	    MenuItem maze3DItem = new MenuItem(newMenu, SWT.NONE);
	    maze3DItem.setText("Generate 3D Maze");
	    
	    maze3DItem.addSelectionListener(new SelectionListener()
		{
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0){}
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				MazeSettings fd=new MazeSettings(shell);
				fd.setText("open");
				String newValue = new String(fd.open());

				String line = new String("generate");
				line = line+" "+newValue;
				String [] splittedLine = line.split(" ");
				int num = splittedLine.length;
				System.out.println(line);
				boolean flag = true;
				if (num == 3) 
				{
					if (!splittedLine[1].equals("unNamed Maze")) 
					{
						if (splittedLine[1] == null)
						{
							flag = false;
						}
						if (Integer.parseInt(splittedLine[2]) < 1) 
						{
							flag = false;
						}
						if (flag == true)
						{
							changeAndNotify(line);
							movedSoCompute();
						}
					}
				}
			}
		});
    
	    MenuItem propItem = new MenuItem(fileMenu, SWT.CASCADE);
	    propItem.setText("Properties");
	    Menu popupMenu2 = new Menu(propItem);
	    propItem.setMenu(popupMenu2);
	    Menu openMenu = new Menu(popupMenu2);
	    propItem.setMenu(openMenu);
	    MenuItem openItem = new MenuItem(openMenu, SWT.NONE);
	    openItem.setText("Open properties");
	    openItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog dlg = new FileDialog(shell, SWT.OPEN);
			    @SuppressWarnings("unused")
				String fileName = dlg.open();
				
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
						
		});
	    MenuItem setPItem = new MenuItem(openMenu, SWT.NONE);
	    setPItem.setText("Set Properties");
	    setPItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				MazeProperties mp = new MazeProperties(shell);
				mp.setText("open");

				String newValue = new String(mp.open());

				String line = new String("changeProperties ");
				line = line+newValue;
				if (line.split(" ").length == 5)
				{
					changeAndNotify(line);
					msg = new MessageBox(shell);
					msg.setText("NOTICE");
					msg.setMessage("You must restart the apllication in order to apply new properties!");
					msg.open();
				}
			
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	    
	    
	    MenuItem saveItem = new MenuItem(fileMenu, SWT.NONE);
	    saveItem.setText("Save");
	    saveItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog fd=new FileDialog(shell,SWT.SAVE);
				fd.setText("Save");
				fd.setFilterPath("");

				String[] filterExt = { "*.maz", "*.MAZ", "*.*" };
				fd.setFilterExtensions(filterExt);
				propertiesFilePath = fd.open();
				if (propertiesFilePath != null)
				{
					String line = new String("save "+mazeName);
					//line = line+" "+propertiesFilePath;
					changeAndNotify(line);
				}
			    System.out.println("Save to: " + fd.open());
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	   
	    MenuItem exitItem = new MenuItem(fileMenu, SWT.NONE);
	    exitItem.setText("Exit");
	    exitItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shell.getDisplay().dispose();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});

	    
	    Menu solveMenu = new Menu(menuBar);
	    
	    MenuItem solveItem = new MenuItem(menuBar, SWT.CASCADE);
	    solveItem.setText("Solve Maze");
	    solveItem.setMenu(solveMenu);
	      
	    MenuItem m3DHintItem = new MenuItem(solveMenu , SWT.NONE);
	    m3DHintItem.setText("Get A Hint");
	    m3DHintItem.setEnabled(false);
	    m3DHintItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (verifyData1) 
					hint.setEnabled(true);
				if (Solution.getSolutionList().size() > 1)
				{
					String move = Solution.getSolutionList().get(1);
					State tempState = new State();
					Position tempPos = tempState.stateToPosition(move);
					mazeDisplay.updateCurrentPosition(tempPos);
					Solution.getSolutionList().remove(0);
					getLevel();
					Level.setText("Floor level: "+floorY);
					mazeDisplay.forceFocus();
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	    
	    MenuItem m3DSolveItem = new MenuItem(solveMenu , SWT.NONE);
	    m3DSolveItem.setText("Solve Maze");
	    m3DSolveItem.setEnabled(false);
	    m3DSolveItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (verifyData1) 
					m3DSolveItem.setEnabled(true);

						time = new Timer();
						myTask = new TimerTask() 
						{	
							@Override
							public void run()
							{
								display.asyncExec(new Runnable() 
								{
									@Override
									public void run() 
									{
										if (Solution.getSolutionList().size() > 1)
										{
											String move = Solution.getSolutionList().get(1);
											State tempState = new State();
											Position tempPos = tempState.stateToPosition(move);
											mazeDisplay.updateCurrentPosition(tempPos);
											Solution.getSolutionList().remove(0);
											getLevel();
											Level.setText("Floor level: "+floorY);
											mazeDisplay.forceFocus();
										}
										else
											time.cancel();
										
									}
								});
							}
						};
						time.scheduleAtFixedRate(myTask, 0, 100);
					}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
	    });
		
	    shell.setMenuBar(menuBar);

		try {
			backImage = new Image(null, new FileInputStream("resources/background.png"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
		mazeDisplay = new MazeDisplay(shell, SWT.BORDER | SWT.DOUBLE_BUFFERED);
		mazeDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 10));
		mazeDisplay.setMyMaze(maze);
		mazeDisplay.setBackgroundImage(backImage);
		mazeDisplay.redraw();
		mazeDisplay.forceFocus();
		mazeDisplay.addKeyListener(new KeyListener()
		{

			@Override
			public void keyReleased(KeyEvent arg0) {}

			@Override
			public void keyPressed(KeyEvent e) 
			{
				if (e.keyCode == SWT.ARROW_UP) 
				{
					mazeDisplay.moveBack();
					movedSoCompute();
				}
				else if (e.keyCode == SWT.ARROW_DOWN) 
				{
					mazeDisplay.moveForward();
					movedSoCompute();
				}
				else if (e.keyCode == SWT.ARROW_RIGHT)
				{
					mazeDisplay.moveRight();
					movedSoCompute();
				}
				else if (e.keyCode == SWT.ARROW_LEFT) 
				{
					mazeDisplay.moveLeft();
					movedSoCompute();
				} 
				else if (e.keyCode == SWT.PAGE_UP)
				{
					mazeDisplay.moveUp();
					getLevel();
					Level.setText("Floor level: "+floorY);
					movedSoCompute();
				}
				else if (e.keyCode == SWT.PAGE_DOWN) 
				{
					mazeDisplay.moveDown();
					getLevel();
					Level.setText("Floor level: "+floorY);
					movedSoCompute();
				}
			}
		});



	/*	
					if (maze != null) 
					{
						String line = new String("display solution");
						line = line + " " + mazeName;
						changeAndNotify(line);
					}
					else
					{
						msg = new MessageBox(shell);
						msg.setText("NOTICE");
						msg.setMessage("Can't solve if no maze");
						msg.open();
					}
			}
		});
*/


	}

	@Override
	public void close() throws IOException
	{
		changeAndNotify("exit");
		if (display!=null&&(!display.isDisposed()))
		{
			display.dispose();
		}

		if(shell!=null&&(!shell.isDisposed()))
		{
			shell.dispose();
		}
	}

	private void changeAndNotify(String command)
	{
		String [] splittedLine = command.split(" ");
		setChanged();
		notifyObservers(splittedLine);
	}

	@Override
	public void setMazeName(String name)
	{
		this.mazeName = new String (name);
	}

	private void getLevel()
	{
		this.floorY = new String((Integer.toString(maze.getCurrentPosition().getZ()+1)));
	}

	public void setSolution(Solution solution)
	{
		if (solution != null)
		{
			this.setMySolution(solution);	
			this.verifyData1 = true;;	
			hint.setEnabled(verifyData1);
			solve.setEnabled(verifyData1);
		}
		else
			System.out.println("NO SOLUTION");
	}

	private void setMyViewMaze(Maze3d maze)
	{
		this.maze = maze;
		this.verifyData2 = true;
		solve.setEnabled(verifyData2);
	}

	public Solution getMySolution() 
	{
		return Solution;
	}

	public void setMySolution(Solution mySolution) 
	{
		this.Solution = mySolution;
	}
	
	public void movedSoCompute() 
	{
		this.verifyData1 = false;	
		hint.setEnabled(verifyData1);
		solve.setEnabled(verifyData1);
		
	}



	@Override
	public void displaySize(String name, double Size) {
		// TODO Auto-generated method stub
		
	}

}