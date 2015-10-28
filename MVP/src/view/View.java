package view;

import solution.Solution;
import algorithms.mazeGenerators.Maze3d;


public interface View {


	public void start();

	public void display(String s);

	public void displayCrossSection (int[][] crossed);

	public void displaySize(String name, double Size);

	public void displayMaze(Maze3d maze);

	public void displaySolution (Solution s);
	
	public void displayDirectory(String[] path);

	public void displayMenu();

	public void enableSolve();

	public void setMazeName(String name);
	
	public void displayMazeGUI(Maze3d maze3d);

	public void setSolution(Solution solution);
}
