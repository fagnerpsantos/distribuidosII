


import java.rmi.*;

public interface IOperacoesRemote extends Remote
{
	double somar (double a, double b) throws RemoteException;
	double subtrair (double a, double b) throws RemoteException;
	double multiplicar (double a, double b) throws RemoteException;
	double dividir (double a, double b) throws RemoteException;

}
