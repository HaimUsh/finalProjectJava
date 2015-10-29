package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class CLI extends CommonView {

	private BufferedReader in;

	private PrintWriter out;

	public CLI(BufferedReader in,PrintWriter out)
	{
		this.in = in;
		this.out = out;
	}

	@Override
	public void start() 
	{
		new Thread(new Runnable() 
		{
			@Override
			public void run() {
				String input;
				out.println(" Server - Command Line");
				out.println(" \ntype \"start\" to start it\ntype \"close\" to close it");
				out.flush();
				try 
				{

					input = in.readLine();

					do 
					{
						setChanged();
						notifyObservers(input);
						out.flush();
						input = in.readLine();
					}
					while (!input.equals("close"));
					setChanged();
					notifyObservers(input);
					out.println("--- EXIT ---");
				} 
				catch (IOException e)
				{

					setChanged();
					notifyObservers("Invalid command");
				}
				out.close();
				try 
				{
					in.close();
				} 
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}).start();
	}

	@Override
	public void displayMessage(String msg)
	{
		out.println(msg);
		out.flush();
	}

	@Override
	public void displayException(Exception e) 
	{
		e.printStackTrace();
	}


	@Override
	public void writeToConsole(String string) 
	{
		out.println(string);
		out.flush();
	}
	
	
	
}
