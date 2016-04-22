
//https://web.fe.up.pt/~jmcruz/etc/rmi/rmi-exo.html
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class ServidorCalc {
    
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("\nUsage:\t java ServidorCalc <objname>\n");
            System.exit(1);
        } // if()
        try {
            Operacoes operacoes = new Operacoes();// create server object
            String objname = "//localhost:1099/" + args[0];// or String objname = args[0];
            //String objname = "//"+arg[0]+":1099/" + args[1];// or String objname = args[0];
            System.out.println("Registrando " + objname + "...");
            Naming.rebind(objname, operacoes);// rmiregistry must be running!
            System.out.println("Registrado!");
        } 
        catch( MalformedURLException e ) {
            System.err.println("MalformedURLException: " + e.toString());
        }
        catch( RemoteException e ) {
            System.err.println("RemoteException: " + e.toString());
        }     
        catch (Exception e)//should be more specific!
        {
            System.err.println("Error in main()! " + e);
            e.printStackTrace();
            System.exit(2);
        } // catch()


        System.out.println("Aguardando objeto");
    } // main()
} // ServerWalk
