

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;




public class Operacoes extends UnicastRemoteObject implements IOperacoesRemote
{	
	public Operacoes() throws RemoteException{}

	@Override
	public double somar(double a, double b) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double subtrair(double a, double b) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double multiplicar(double a, double b) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double dividir(double a, double b) {
		// TODO Auto-generated method stub
		return 0;
	}

}



