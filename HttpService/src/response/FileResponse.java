package response;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class FileResponse implements Response{

	@Override
	public String respond() {
		StringBuilder contentBuilder = new StringBuilder();
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
