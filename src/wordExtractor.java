import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class wordExtractor {

	public static void main(String[] args) throws IOException {
		String charSet = "UTF-8";
		String filePath = new File("").getAbsolutePath();
		String input = filePath + "/" + args[0];
		String output = filePath + "/" + args[1];
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(input), charSet));
		PrintStream out = new PrintStream(new FileOutputStream(output), true, charSet);
		//Skip first line
		String line = br.readLine();
		int wordCount = 0;
		int outputCounter = 100;
		String s = "";
		while ((line = br.readLine()) != null){
			if(++wordCount % outputCounter == 0){			
				if (wordCount > outputCounter + 1){
					for (int i = 0;i < s.length();i++){
						System.out.print("\b");
					}
				}
				s = Integer.toString(wordCount);
				System.out.print(s);
			}
			String[] delimitedLine = line.split(",");
			out.println(delimitedLine[0]);
		}
		System.out.println("");
		br.close();
		out.close();
		System.exit(0);
	}

}
