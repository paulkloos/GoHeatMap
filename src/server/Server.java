package server;

import java.net.UnknownHostException;

import board.Board;

public class Server
{
	//private SocketFactory ssl; all ssl for future reference
	
	public void runServer(Board files)
	{
		HTTPConnection conn;
		try
		{
			conn = new HTTPConnection(8887,files);
			conn.start();
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
	}
}
