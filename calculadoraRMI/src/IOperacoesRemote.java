


import java.rmi.*;

public interface IOperacoesRemote extends Remote
{
	double somar (double a, double b);
	double subtrair (double a, double b);
	double multiplicar (double a, double b);
	double dividir (double a, double b);

}
