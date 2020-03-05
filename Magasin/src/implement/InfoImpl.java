package implement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import interfaces.Information;

public class InfoImpl extends UnicastRemoteObject implements Information{
	private static final long serialVersionUID = 2674880711467464646L;
	
	public InfoImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getInfo() throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("Invocation de la méthode getInformation()");
		return "bonjour";
	}
}
