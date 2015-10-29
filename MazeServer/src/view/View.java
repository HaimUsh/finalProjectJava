package view;

public interface View {

public void start();
	
	/**
	 * Display message.
	 *
	 * @param msg the msg
	 */
	public void displayMessage(String msg);
	
	/**
	 * Display exception.
	 *
	 * @param e the e
	 */
	public void displayException(Exception e);

	/**
	 * Write to console.
	 *
	 * @param string the string
	 */
	public void writeToConsole(String string);
	
}
