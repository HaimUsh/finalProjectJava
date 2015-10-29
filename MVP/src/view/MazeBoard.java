package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;

public class MazeBoard extends Canvas {

	

		/** The maze. */
		private Maze3d maze3d;

		/** The maze data. */
		private int[][][] mazeData;

		/** The character p. */
		private Position characterP;

		/** The character. */


		/** The is done. */
		private boolean isDone;

		private boolean isSolve;

		private boolean newGame;

		private Position startP;

		/**
		 * Instantiates a new maze2d displayer. Paint listener will paint it all
		 * over again when something has changed
		 * 
		 * @param parent
		 *            the parent
		 * @param style
		 *            the style
		 * @param character
		 *            the character
		 */
		MazeBoard(Composite parent, int style) 
		{
			super(parent, style);	
		}

		public int[][][] getMazeData() {
			return mazeData;
		}

		public void setMazeData(int[][][] mazeData) {
			this.mazeData = mazeData;
		}

		public boolean isDone() {
			return isDone;
		}

		public void setDone(boolean isDone) {
			this.isDone = isDone;
		}

		public boolean isSolve() {
			return isSolve;
		}

		public void setSolve(boolean isSolve) {
			this.isSolve = isSolve;
		}

		public boolean isNewGame() {
			return newGame;
		}

		public void setNewGame(boolean newGame) {
			this.newGame = newGame;
		}

		public Position getStartP() {
			return startP;
		}

		public void setStartP(Position startP) {
			this.startP = startP;
		}

		public void setCharacterP(Position characterP) {
			this.characterP = characterP;
		}

		/**
		 * Get character position
		 */
		public Position getCharacterP() {
			return this.characterP;
		}

		/**
		 * Gets the maze. 
		 * @return the maze
		 */
		public Maze3d getMaze() {
			return maze3d;
		}

		/**
		 * Sets the maze. 
		 * @param maze
		 * the new maze
		 */
		public void setMaze(Maze3d maze) {
			this.newGame = true;
			this.isDone = false;
			this.maze3d = maze;

			getDisplay().syncExec(new Runnable() {

				@Override
				public void run() {
					redraw();
				}
			});
		}

		/**
		 * Redraw character.
		 */
		private void moveCharacter() {
			getDisplay().syncExec(new Runnable() {
				@Override
				public void run() {
					redraw();
				}
			});
		}

		/**
		 * Move character position.
		 * @param p
		 *  the p
		 */
		public void setCharacterPosition(Position p) {
			characterP.setX(p.getX());
			characterP.setZ(p.getZ());
			characterP.setY(p.getY());
			moveCharacter();
		
		}

		/**
		 * Show exception box.
		 * @param e
		 * the exception
		 */
		public void showExceptionBox(Exception e) {
			MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_ERROR | SWT.OK);
			messageBox.setMessage(e.getMessage());
			messageBox.setText("Error");
			messageBox.open();
		}

/*		public static void main(String[] args) {

			Maze3d maze;
			
			Maze3dGenerator dorGen = new MyMaze3DGenerator();
			maze = dorGen.generate(10, 10, 10);
			
			SearchableMaze dorSearch = new SearchableMaze(maze);
			Searcher mySearch = new BFS();
			mySearch.search(dorSearch);
			Shell shell = new Shell();
			MazeBoard dialog = new MazeBoard(shell, 1);
			dialog.setMaze(maze);
		}*/

		
}