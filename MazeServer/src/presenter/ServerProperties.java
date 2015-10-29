package presenter;

import java.io.Serializable;

public class ServerProperties implements Serializable {

	private static final long serialVersionUID = 1L;

	private int numOfClients;
	
	
	private int port;
	
	
	public ServerProperties()
	{
		this.numOfClients = 10;
		this.port = 5400;
	}
	
	public ServerProperties(int _num, int _port)
	{
		this.numOfClients = _num;
		this.port = _port;
	}
	
	public ServerProperties(String _num, String _port)
	{
		this.numOfClients = Integer.parseInt(_num);
		this.port = Integer.parseInt(_port);
	}
	public int getNumOfClients()
	{
		return numOfClients;
	}

	public void setNumOfClients(int numOfClients)
	{
		this.numOfClients = numOfClients;
	}

	public int getPort()
	{
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
