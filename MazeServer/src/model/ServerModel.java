package model;

import java.beans.XMLDecoder;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import presenter.PropertiesManage;
import presenter.ServerProperties;

public class ServerModel extends CommonServerModel {
	

	public int numOfClients;
	
	public int port;


	public static boolean stop;


	private ExecutorService threadPool;


	private ServerSocket server;


	private Thread mainServerThread;


	private Maze3DClientHandler clientHandler;

	
	public XMLDecoder decoder;

	
	private int numberOfClients;

	
	private boolean isServerClosed;

	
	private ServerProperties cp;

	public ServerModel() 
	{
		setServerProperties();
		this.numberOfClients = 0;
		this.clientHandler = new Maze3DClientHandler();
		threadPool=Executors.newFixedThreadPool(cp.getNumOfClients());
		ServerModel.stop = false;
		this.isServerClosed = true;
	}


	public void setServerProperties()
	{
		ServerProperties prop = new PropertiesManage().loadProp();
		this.cp = prop;
		this.port = cp.getPort();
		this.numOfClients = cp.getNumOfClients();
	}

	@Override
	public void open()
	{
		try
		{
			server=new ServerSocket(this.cp.getPort());
			server.setSoTimeout(7*1000);
			this.isServerClosed = false;
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}

		mainServerThread=new Thread(new Runnable()
		{			
			@Override
			public void run()
			{
				while(!ServerModel.stop)
				{
					try 
					{
						final Socket someClient=server.accept();
						if(someClient!=null)
						{
							threadPool.execute(new Runnable()
							{									
								@Override
								public void run() 
								{
									try
									{										
										numberOfClients++;
										setChangedAndNotify("Now handling client number: "+numberOfClients);
										clientHandler.handleClient(someClient.getInputStream(), someClient.getOutputStream());
										setChangedAndNotify("Finished handling client "+numberOfClients);
										someClient.close();
									}
									catch(IOException e)
									{
										setChanged();
										notifyObservers(e);
									}									
								}
							});								
						}
					}
					catch (SocketTimeoutException e)
					{
						setChangedAndNotify("No clients connected...");
					} 
					catch (IOException e)
					{
						setChanged();
						notifyObservers(e);
					}
				}
				setChangedAndNotify("No more new clients");
			} 
		});
		mainServerThread.start();
	}

	
	@Override
	public void close()
	{
		if(this.isServerClosed == false)
		{
			this.isServerClosed = true;
			ServerModel.stop=true;	
			try 
			{
				this.clientHandler.saveZip();
				setChangedAndNotify("Prepering for shut down");
				threadPool.shutdown();
				while(!(threadPool.awaitTermination(10, TimeUnit.SECONDS)));
				setChangedAndNotify("Tasks are done");
				mainServerThread.join();		
				server.close();
				threadPool.shutdownNow();
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	private void setChangedAndNotify(String msg)
	{
		setChanged();
		notifyObservers(msg);
	}

}
