

import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Stack;
import java.util.StringTokenizer;

/**Performs infix computations with +,-,*,/
 * Your task: Add the grouping operators ().
 * The rules to apply for resolving the infix 
 * stacks with grouping:
 * while(true)
 * 0. If there is zero or one operator on the
 * operator stack then terminate loop.
 * 1. If 0 does not apply and if the top two 
 * operators on the operator stack are ) and ( 
 * respectively, remove them.
 * 2. If 0 and 1 do not apply and either of 
 * the top two operators on the operator stack 
 * are (, then terminate loop.
 * 3. If 0-2 do not apply and either the top 
 * operator has less priority (see note below)
 * than the operator beneath it or the top 
 * operator is ), 
 * 	a. pop 1st value v1
 * 	b. pop 2nd value v2
 * 	c. pop 1st operator op1
 * 	d. pop 2nd operator op2
 * 	e. push value v2 op2 v1
 * 	f. push operator op1
 *  4. If 0-3 do not apply, terminate loop.
 * Note: priority 1 has the highest priority, 
 * 2 is next highest and so forth
 * @author Jam Jenkins
 */
public class Infix
{
	private ClienteCalc cliente = new ClienteCalc();
	
	private String remove(String n){
		String  tempP = "";
		if(n.contains(")")){
			int i = 0;
			while (n.charAt(i) != ')'){
				tempP = String.valueOf(n.charAt(i));
				i++;
			}
			n = tempP;

		}
		return tempP;
	}

	private String removerParenteses(String n) throws RemoteException {
		int j = 0;
		String saida = "",temp = "", temp2 = "";
		
		for (int i = 0; i < n.length(); i++) {
			if(n.charAt(i) == '('){
				j = i + 1;
				while(n.charAt(j)!=')'){
					if(n.charAt(j) == '('){
						j++;												
					}
					temp += n.charAt(j);
					j++;
				}
				if(temp.contains(")"))
					temp = remove(temp);
				saida += String.valueOf(infix(temp));
				temp="";
				i=j;
			}else{
				saida = saida + String.valueOf(n.charAt(i));
			}
		}
		
		return saida;
	}
	
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public double infix(String expression) throws RemoteException
    {
    	if(expression.contains("(")){
    		expression = removerParenteses(expression);
    		if(expression.contains(")"))
    			expression = remove(expression);
    	}
        //remove white space and add evaluation operator
        expression=expression.replaceAll("[\t\n ]", "")+"=";
        String operator="*/+-=";
        //split up the operators from the values
        StringTokenizer tokenizer=new StringTokenizer(expression, operator, true);
        Stack operatorStack=new Stack();
		Stack valueStack=new Stack();
        while(tokenizer.hasMoreTokens())
        {
            //add the next token to the proper stack
            String token=tokenizer.nextToken();
            if(operator.indexOf(token)<0)
                valueStack.push(token);
            else
                operatorStack.push(token);
            //perform any pending operations
            resolve(valueStack, operatorStack);
        }
        //return the top of the value stack
        String lastOne=(String)valueStack.pop();
        
        double numero = Double.parseDouble(lastOne);
        DecimalFormat formato = new DecimalFormat("#.##");      
        numero = Double.valueOf(formato.format(numero));
                
        return numero;   
    }
        
    public int getPriority(String op)
    {
    	if (op.equals("("))
    		return 1;
    	else if(op.equals("*") || op.equals("/"))
            return 2;
        else if(op.equals("+") || op.equals("-"))
            return 3;
        else if(op.equals("="))
            return 4;
        else
            return Integer.MIN_VALUE;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void resolve(Stack values, 
            Stack operators) throws RemoteException
    {
        while(operators.size()>=2)
        {
            String first=(String)operators.pop();
            String second=(String)operators.pop();
            if(getPriority(first)<getPriority(second))
            {
                operators.push(second);
                operators.push(first);
                return;
            }
            else
            {
                String firstValue=(String)values.pop();
                String secondValue=(String)values.pop();
                values.push(getResults(secondValue, second, firstValue));
                operators.push(first);
            }
        }
    }
    
    public String getResults(String operand1, String operator, String operand2) throws RemoteException
    {
        System.out.println("Performing "+
               operand1+operator+operand2);
        double op1=Double.parseDouble(operand1);
        double op2=Double.parseDouble(operand2);
        double res;
        /*
         * 			VEI, FIZ O MÉTODO AÍ
         * 			EMBAIXO. A GENTE PRECISA
         * 			SABER COMO INCREMENTAR O
         * 			ID DO USUÁRIO QUANDO CRIAR O OBJETO
         * */
        int idCliente = randomizar();
        res = ClienteCalc.distribuir(op1,operator,op2, idCliente); //passando o id do cliente
        
		return ""+res;
        
    }
    
    public int randomizar(){
    	Random numero = new Random();    	
    	int resultado = numero.nextInt(4) + 1;
    	return resultado;	
    }
    
    /*
    
    public static void main(String[] args)
    {
        Infix fix=new Infix();
        String expression="2+8-(5.3-5*4/7+5+3)";
        System.out.println(expression+"="+fix.infix(expression));
    } */
}