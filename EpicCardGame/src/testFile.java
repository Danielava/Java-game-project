import java.io.*;

public class testFile {
	public testFile() {
		BufferedReader in = null;
		try{
			in = new BufferedReader(new FileReader("CuteGirl"));
		}
		catch(FileNotFoundException e) {
			System.out.println("Did not work1");
		}
		if(in == null) return;
		String line;
		try {
			while((line = in.readLine()) != null)
				{
					System.out.println(line);
				}
			in.close();
		}
		catch(IOException e) {
			System.out.println("Did not work2");
		}
	}
}
