import java.io.*;
import java.net.*;

class Server {
	public static void main(String argv[]) throws Exception {
		String clientSentence = "";
		String capitalizedSentence;
		/* definição do numero da porta */
		int porta = 6789;
		/* atribuicao do numero da porta ao socket */
		ServerSocket welcomeSocket = new ServerSocket(porta);
		System.out.println("Esperando conexoes na porta " + porta);
		/* laco infinito para o socket aguardar conexao */
		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			System.out.println("Conexao aceita….");
			BufferedReader inFromClient = new BufferedReader(
					new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(
					connectionSocket.getOutputStream());
			try {
				/*
				 * servidor fica aguardando msg de cliente ate chegar o caracter
				 * quebra de linha ‘\n’
				 */
				
				clientSentence = inFromClient.readLine();
				//Cria um objeto Infix e retorna o resultado pro cliente
				Infix fix=new Infix();
				
				outToClient.writeBytes("Resultado=" + fix.infix(clientSentence) + '\n'); 
				

			} catch (Exception e) {
				System.out.println("PERDA DA CONEXAO");
			}
			/* fecha a conexão aberta */
			connectionSocket.close();
			System.out.println("Conexao fechada");
		}
	}
}