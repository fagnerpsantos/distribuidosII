package response;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import static response.DummyResponse.HTTP_DATE_FORMAT;


public class FileResponse implements Response{

	@Override
	public String respond() {
		StringBuilder contentBuilder = new StringBuilder();
                
                // Cria primeira linha do status code, no caso sempre 200 OK
		contentBuilder.append("HTTP/1.1 200 OK").append("\r\n");
		
		// Cria os cabeçalhos
		contentBuilder.append("Date: ").append(HTTP_DATE_FORMAT.format(new Date()))
				.append("\r\n");
		contentBuilder.append("Server: Test Server - localhost")
				.append("\r\n");
		contentBuilder.append("Connection: Close").append("\r\n");
		contentBuilder.append("Content-Type: text/html; charset=UTF-8").append("\r\n");
		contentBuilder.append("\r\n");
                
		try {
		    BufferedReader in = new BufferedReader(new FileReader("./src/index.html"));
		    String str;
		    while ((str = in.readLine()) != null) {
		        contentBuilder.append(str);
		    }
		    in.close();
		} catch (IOException e) {
			
		}
		String content = contentBuilder.toString();
		return content;
	}

}
