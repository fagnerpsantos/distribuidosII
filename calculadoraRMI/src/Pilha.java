/*
 * Pilha.java criado em 28/10/2012
 */


/**
 * @author 
 * Rejaine Farias
 * Paula Urbinati
 * Francisco Franco
 * Gustavo Maeda
 */
public class Pilha {

    private final char[] vetorPilha;
    private int topo;

    public Pilha(int maxSize) {
        vetorPilha = new char[maxSize];
        topo = -1;
    }

    public boolean isEmpty() {
        return (topo == -1);
    }

    public boolean isFull() {
        return (topo == this.vetorPilha.length - 1);
    }

    // retorna o valor no topo da Pilha
    public char peek() {
        if (!isEmpty()) {
            return vetorPilha[topo];
        } else {
            return '0';
        }
    }
    
    // empilha
    public void push(char j){
        if (!isFull()) {
            topo++;
            vetorPilha[topo] = j;
        }
    }

    // desempilha
    public char pop() {
        if (!isEmpty()) {
            return vetorPilha[topo--];
        } else {
            return '0';
        }
    }
    
}