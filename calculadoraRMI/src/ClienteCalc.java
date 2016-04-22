import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.RemoteException;



public class ClienteCalc {
	static int cont= 0;
	static IOperacoesRemote opera = null;
    public static void main(String[] args) throws IOException {
    	
        // interface is dealed with like a class!
//        if (args.length != 2) {
//            System.err.println("\nUsage:\t java ClienteCalc <host_servidor> <objname>\n");
//            System.exit(1);
//        } // if()
//        try {
//            String objname = "//" + args[0] + ":1099/" + args[1];
//            System.out.println("Procurando por objeto " + objname);
//            op = (IOperacoesRemote) Naming.lookup(objname);// rmiregistry must be running!
//        } // try()
//        catch (Exception e)//Seja mais especifico!
//        {
//            System.err.println("Problemas encontrados! " + e);
//            e.printStackTrace();
//            System.exit(2);
//        } // catch()
        try {
        	BufferedReader in  = new BufferedReader(new InputStreamReader(System.in));
            String n;// could use a random number...
            
            System.out.println("digite: ");
            n = in.readLine();
            
            double resp = 0;
           
            String saida= "";
            
            for (int i = 0; i < n.length(); i++) {
				if(n.charAt(i) == '('){
					saida += removerParenteses(i, n);
					i+=cont;
					cont=0;
					
				}else if(isOperator(n.charAt(i))){
					saida = operatorWhat(n.charAt(i))+saida;
				}else if(isNumber(n.charAt(i))){
					saida += ","+n.charAt(i)+"),";
				}
			}        
            saida +=")";
            
            
            System.out.println("Resposta: " + saida+ "\n");

        } // try()
        catch (RemoteException re) {
            System.err.println("Problemas na chamada remota! " + re);
            re.printStackTrace();
            System.exit(3);
        } // catch()
    } // main()
    
    private static String removerParenteses(int i, String n) {
		i++;
		String saida = "";
		while(n.charAt(i)!=')'){
			saida += n.charAt(i);
			i++;
			cont++;
		}

		saida = formarMetodo(saida);
		
		return saida;
	}

	private static boolean isOperator(char op){
		return op == '+' || op == '-' || op == '/' || op == '*' ;    	
    }
	private static String operatorWhat(char op){
		String saida = "";
		switch (op) {
			case '/':
				saida = "dividir(";
				break;
			case '*':
				saida = "multiplicar(";
				break;
			case '-':
				saida = "subtrair(";
				break;
			case '+':
				saida = "somar(";
				break;
		}
		return saida;
	}

    private static boolean isParenteses(char op){
		return op == '(' || op == ')' ;    	
    }
    
    private static boolean isNumber(char op){
		return op == '0' || op == '1' || op == '2' || op == '3' || op == '4'
				|| op == '5' || op == '6' || op == '7' || op == '8' || op == '9';    	
    }
    
    private static boolean prioriteMore(char op){
    	return op == '*' || op =='/';
    }
    
    private static String formarMetodo(String op){
    	String saida ="", valor = op;
    	String num1="", num2="";
    	String metodo = "";
    	boolean flag = false;
    	int tamanho = valor.length();
    	
    	for (int i = 0; i < tamanho; i++) {
    		
			if(isNumber(valor.charAt(i)) ){
				if(num1.equals("") && saida.equals(""))
					num1 = String.valueOf(valor.charAt(i));
				else
					num2 = String.valueOf(valor.charAt(i));
			}else if( isOperator(valor.charAt(i)) ){
				if(prioriteMore(valor.charAt(i))){
					metodo = operatorWhat(valor.charAt(i));
				}else
					metodo = operatorWhat(valor.charAt(i));
			}//fim do isOperator
			if(!metodo.equals("")  && !num1.equals("") && !num2.equals("")){
				saida = metodo + num1+ "," + num2 +")" + saida;
				
				metodo = "";
				num1 = "";
				num2 = "";
			}
			if(!metodo.equals("")  && num1.equals("") && !num2.equals("")){
				
				num1=saida;
				saida = metodo + num1+ "," + num2 +")";
				
				metodo = "";
				num1 = "";
				num2 ="";
				
			}
		}// fim do for
    	
    	return saida;
    }// fim do formarMetodo
    
} // ClientWalk
