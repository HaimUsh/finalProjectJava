package presenter;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;

public class Properties implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;





	private int numOfThreads;

	private String generateAlgo;

	private String solveAlgo;

	private String gameInterface;

	public Properties() {
		this.numOfThreads=10;
		this.generateAlgo="my";
		this.solveAlgo="bfs";
		this.gameInterface="cli";
	}



	public String getGenerateAlgo() {
		return generateAlgo;
	}


	public void setGenerateAlgo(String generateAlgo) {
		this.generateAlgo = generateAlgo;
	}


	public String getSolveAlgo() {
		return solveAlgo;
	}


	public void setSolveAlgo(String solveAlgo) {
		this.solveAlgo = solveAlgo;
	}


	public String getGameInterface() {
		return gameInterface;
	}


	public void setGameInterface(String gameInterface) {
		this.gameInterface = gameInterface;
	}


	public int getNumOfThreads() {
		return numOfThreads;
	}


	public void setNumOfThreads(int numOfThreads) {
		this.numOfThreads = numOfThreads;
	}



	/**
	 * Load preferences.
	 * 
	 * Loads the preferences that were set in a previous run of the program,
	 * which are encoded in XML format.
	 * 
	 */
	public void loadPreferences(){
		try {
			XMLDecoder xmlDe = new XMLDecoder(new FileInputStream("preferences.xml"));
			Properties p  = (Properties) xmlDe.readObject();
			xmlDe.close();

			setNumOfThreads(p.getNumOfThreads());
			setGameInterface(p.getGameInterface());
			setGenerateAlgo(p.getGenerateAlgo());
			setSolveAlgo(p.solveAlgo);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Save preferences.
	 * 
	 * Saves the preferences in XML format.
	 */
	public void savePreferences(){
		try {
			XMLEncoder xmlEn = new XMLEncoder(new FileOutputStream("preferences.xml"));
			xmlEn.writeObject(this);
			xmlEn.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
