/*
 * Gerencia.java criado em 28/10/2012
 */


/**
 * @author 
 * Rejaine Farias
 * Paula Urbinati
 * Francisco Franco
 * Gustavo Maeda
 */
public class Gerencia {
    
    //retorna a prioridade do operador
    public static int prioridade(char operador) {
        int prioridade = 0;

        switch (operador) {
          case '(':
              prioridade = 3;
              break;

            case '*':
            case '/':
                prioridade = 2;
                break;
            
            case '+':
            case '-':
                prioridade = 1;
        }

        return (prioridade);
    }
    
    public static boolean numero(char numero){
        return (numero == '0' || numero == '1' || numero == '2' || numero == '3' ||
                numero == '4' || numero == '5' || numero == '6' || numero == '7' ||
                numero == '8' || numero == '9');
    }
    
    
    public static boolean operador(char operador) {
        return (operador == '(' || operador == ')' || operador == '+'|| 
                operador == '-' || operador == '*' || operador == '/');
    }
    
}