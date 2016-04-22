



/*
 * Pos.java criado em 28/10/2012
 */


import java.util.Scanner;

/**
 * @author 
 * Rejaine Farias
 * Paula Urbinati
 * Francisco Franco
 * Gustavo Maeda
 */
public class Pos {

    public String posFixa(String entrada) {


        
        String saida = "";
        char resp;

        Pilha pilha = new Pilha(100);

        for (int i = 0; i < entrada.length(); i++) {
        	if (Gerencia.numero(entrada.charAt(i))) {
                saida += entrada.charAt(i);

            }else if(entrada.charAt(i) == '.'){
            	saida += '.';            
            }
            else if (entrada.charAt(i) == '(') {
                pilha.push(entrada.charAt(i));

            } else if (entrada.charAt(i) == ')') {

                while (!pilha.isEmpty()) {
                    resp = pilha.pop();
                    if (resp != '(') {
                        saida += resp;
                    } else {
                        break;
                    }
                }

            } else if (Gerencia.operador(entrada.charAt(i))) {
            	saida += '_';

                if (pilha.isEmpty()) {
                    pilha.push(entrada.charAt(i));
                } else {
                	
                    while (!pilha.isEmpty()) {
                        resp = pilha.pop();

                        if (resp == '(') {
                            pilha.push(resp);
                            break;

                        } else if (Gerencia.operador(resp)) {
                        	
                            if (Gerencia.prioridade(resp) < 
                                Gerencia.prioridade(entrada.charAt(i))) {
                                pilha.push(resp);
                                break;
                                
                            } else if (Gerencia.prioridade(resp) >= 
                                       Gerencia.prioridade(entrada.charAt(i))) {
                                saida += resp;
                                
                            } else if (Gerencia.prioridade(entrada.charAt(i)) >=
                                       Gerencia.prioridade(resp) || resp == '(') {
                                break;
                            }
                        }
                    }

                    pilha.push(entrada.charAt(i));

                }
            }
        }

        while (!pilha.isEmpty()) {
            resp = pilha.pop();
            saida += resp;
        }
        
       return saida;

    }
}