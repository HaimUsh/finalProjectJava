package view;

import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class MazeWindow extends BasicWindow {

	Timer timer;
	TimerTask task;
		
	public MazeWindow(String title, int width, int height) {
		super(title, width, height);
		
	}

	@Override
	void initWidgets() {
		shell.setLayout(new GridLayout(3,false));
		 Menu menuBar = new Menu(shell, SWT.BAR);

		
		    Menu fileMenu = new Menu(menuBar);

	
		    MenuItem fileItem = new MenuItem(menuBar, SWT.CASCADE);
		    fileItem.setText("File");
		    fileItem.setMenu(fileMenu);


		    MenuItem newMazeItem = new MenuItem(fileMenu, SWT.CASCADE);
		    newMazeItem.setText("Generate New Maze");
		    
		    Menu popupMenu = new Menu(newMazeItem);
		    newMazeItem.setMenu(popupMenu);
		    
		    Menu newMenu = new Menu(popupMenu);
		    newMazeItem.setMenu(newMenu);
		    
		    MenuItem maze3DItem = new MenuItem(newMenu, SWT.NONE);
		    maze3DItem.setText("Generate 3D Maze");
		    
//		    MenuItem maze2DItem = new MenuItem(newMenu, SWT.NONE);
//		    maze2DItem.setText("Generate 2D Maze");

		    
		    MenuItem openItem = new MenuItem(fileMenu, SWT.NONE);
		    openItem.setText("Open Properties");
		    openItem.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					FileDialog dlg = new FileDialog(shell, SWT.OPEN);
				    @SuppressWarnings("unused")
					String fileName = dlg.open();
					
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
					FileDialog dialog = new FileDialog(shell, SWT.SAVE);
				    dialog.setFilterNames(new String[] { "Batch Files", "All Files (*.*)" });
				    dialog.setFilterExtensions(new String[] { "*.bat", "*.*" }); 
				                                                                 
				    dialog.setFilterPath("c:\\"); 
				    dialog.setFileName("yourFile.bat");
				    System.out.println("Save to: " + dialog.open());
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
					System.exit(0);
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
		    
		    MenuItem m3DSolveItem = new MenuItem(solveMenu , SWT.NONE);
		    m3DSolveItem.setText("Solve Maze");
		    m3DSolveItem.setEnabled(false);
		    
		    shell.setMenuBar(menuBar);
		    
	}
		
	

	public static void main(String[] args) {
		MazeWindow win=new MazeWindow("maze example", 820, 400);
		win.run();
	}

}
