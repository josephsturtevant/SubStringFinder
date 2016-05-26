import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

public class finder {

	public static void main(String[] args) throws IOException {
		String searchTerm = args[1];
		String charSet = "UTF-8";
		String filePath = new File("").getAbsolutePath();
		String input = filePath + "/" + args[0];
		String output = filePath + "/" + "out.txt";
		List<String> exceptions = null;
		if (args.length > 2){
			exceptions = Arrays.asList(Arrays.copyOfRange(args, 2, args.length));
		}
		if (exceptions != null) System.out.println(exceptions.toString());
				
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(input), charSet));
		PrintStream out = new PrintStream(new FileOutputStream(output), true, charSet);
		out.println("List of accepted display names containing \"" + searchTerm + "\"");
		if (exceptions != null) out.println("Filtered out display names containing " + exceptions.toString());
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
			//Label to break out to if there are exceptions you don't want in your results
			outerif:
			if(line.toLowerCase().contains(searchTerm)){
				if (args.length > 2){
					for (String entry : exceptions){
						if (line.toLowerCase().contains(entry)){
							break outerif;
						}
					}
				}
				out.println(line);
			}
		}
		br.close();
		out.close();

	}

}
