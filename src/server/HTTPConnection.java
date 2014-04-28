package server;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import org.java_websocket.*;
import org.java_websocket.handshake.ClientHandshake;

import server.Message.commands;

import board.Board;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//! \details handles server connection
public class HTTPConnection extends WebSocketServer
{
	private Board board;
	private Gson inobject;
	private GsonBuilder builder;
//TODO: I might replace board with another class for controlling the game
	public HTTPConnection(int port,Board ref) throws UnknownHostException
	{
		super(new InetSocketAddress(port));
		builder = new GsonBuilder();
		inobject = builder.create();
		this.board = ref;
	}
	
	public HTTPConnection(InetSocketAddress address,Board ref)
	{
		super(address);
		builder = new GsonBuilder();
		inobject = builder.create();
		this.board = ref;
	}

	public void onClose(WebSocket conn, int code, String reason, boolean remote)
	{
		System.out.println("Connection closed");		
	}

	public void onError(WebSocket conn, Exception ex)
	{
		ex.printStackTrace();		
	}
	/*!
	 * Function: onMessage
	 * \param WebSocket conn
	 * \param String inmessage
	 * \details handles in coming String based message
	 */
	public void onMessage(final WebSocket conn, String inmessage)
	{
		Message command;
		command = inobject.fromJson(inmessage, Message.class);
		
		try {
			switch (command.getCommand()) {
			case NEW_GAME:
				//TODO: start new game
				break;
			case PLAY_PIECE:
				//TODO: play piece
				break;
			case PASS:
				//TOOD: pass
				break;
			case INVALID:
				//TODO: pass back that command was invalid
				break;			
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void onOpen(WebSocket conn, ClientHandshake handshake)
	{
		System.out.println("Connection opened");
	}
	static public void sendText(WebSocket conn,String message)
	{
		conn.send(message);
	}
}
