import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

class Client {
	public static void main(String argv[]) throws Exception {
		String sentence;
		/* cliente insere string no buffer para envio ao servidor */
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(
				System.in));
		/* definicao da porta conforme conforme porta do servidor */
		int porta = 6789;
		String servidor = "localhost";
		System.out.println("Conectando ao servidor " + servidor + ":" + porta);
		Socket clientSocket = new Socket(servidor, porta);
		try {
			/* conexao com o servidor */
			
		} catch (Exception e) {
			System.out.println("Servidor com problemas de conexao!");
			return;
		}
		
		System.out.println("Digite a sentenca: ");
		DataOutputStream outToServer = new DataOutputStream(
				clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));
		/*
		 * aguarda resposta do servidor (string com quebra de linha no final
		 * \n’)
		 */
		sentence = inFromUser.readLine();
		try {
			outToServer.writeBytes(sentence + '\n');
		} catch (Exception e) {
			System.out.println("Acabaram com o servidor!");
			return;
		}
		/* imprime string recebida do servidor */
		System.out.println(inFromServer.readLine());
		/* fecha conexao com servidor */
		clientSocket.close();
	}
}