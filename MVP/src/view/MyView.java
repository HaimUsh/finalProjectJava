package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import solution.Solution;
import algorithms.mazeGenerators.Maze3d;

public class MyView extends Observable implements View {

	
	BufferedReader in;
	
	PrintWriter out;
	
	ArrayList<Observer> Observers;
	
	Observable me;
	
	public MyView() {
		Observers = new ArrayList<Observer>();
		me = this;
	}
	
	@Override
	public void start() {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		new Thread(new Runnable()
		{
			@Override
			public void run() 
			{

				System.out.print("Enter command (for cmd list, type \"menu\"): ");
				try {
					String line = in.readLine();

					while (!line.equals("exit")) {
						
						notifyObservers(me, line);
						System.out.print("Enter command: ");
						line = in.readLine();
					}

					
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						notifyObservers(me, "exit");
						System.out.println("bye bye");
						in.close();
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		}
			}).start();

	}

	@Override
	public void display(String s) {
			
		System.out.println(s);
	}

	
	@Override
	public void displayCrossSection(int[][] crossed) {
		System.out.println("Your CrossSection:");
		System.out.println();
		for (int i = 0; i < crossed.length; i++)
		{
			System.out.print("    ");
			for (int j = 0; j < crossed.length; j++)
			{
				System.out.print(crossed[j][crossed.length-i-1]);
				if (j+1 < crossed.length)
				{
					System.out.print(",");
				}
			}
			System.out.println();
		}
		System.out.println();

	}

	@Override
	public void displaySize(String name, double Size) {
		System.out.println("for maze:" +name+"the size is:");
		System.out.println(Size+"bytes");

	}

	@Override
	public void displayMaze(Maze3d maze) {
		maze.displayMaze(maze);

	}

	@Override
	public void displaySolution(Solution s) {
		s.print();

	}

	@Override
	public void displayDirectory(String[] path) {
		System.out.println("the files in this directory are:");
		for (int i = 0; i < path.length; i++) 
		{
			System.out.println(path[i]);
		}	

	}

	@Override
	public void displayMenu() {
		System.out.println("\nList of commands:");
		System.out.println("  dir <path>");
		System.out.println("  generate 3d maze <name> <size>");
		System.out.println("  display <name>");
		System.out.println("  display cross section by <x/y/z> <index> for <name>");
		System.out.println("  save maze <name> <file name>");
		System.out.println("  load maze <file name> <file>");
		System.out.println("  maze size <name>");
		System.out.println("  file size <name>");
		System.out.println("  solve <name> <algorithm (BFS / ASTARAIR / ASTARMAN>");
		System.out.println("  display solution <name>");
		System.out.println("  menu");
		System.out.println("  exit");

	}

	public void addObserver(Observer o){
		if(!Observers.contains(o))
			Observers.add(o);
	}
	
	public void notifyObservers(Observable cameFrom, Object arg){
		for (Observer observer : Observers) {
			observer.update(cameFrom, arg);
		}
	}


	@Override
	public void setMazeName(String name) {}

	@Override
	public void displayMazeGUI(Maze3d maze3d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSolution(Solution solution) {
		// TODO Auto-generated method stub
		
	}
}
