package model;

import java.util.HashMap;

import solution.Solution;
import algorithms.mazeGenerators.Maze3d;

public interface Model {


	public void getFilesInDirectory(String path);

	public void generate3DMaze(String name, int size);

	public void getCrossSection (char xyz, int index, String name);

	public void saveMaze (String mazeName, String fileName);

	public void loadMaze (String mazeName, String fileName);

	public void MazeSize(String name);

	public void FileSize(String name);

	public void solveMaze(String name);

	public HashMap<String,Maze3d> getMazeList();

	public HashMap<Maze3d,Solution> getSolutionList();

	public HashMap<String, Object> getCommandData();

	public void officialExit();
	
	public void saveZip();
	
//	public void loadZip();
	
	
	

}

