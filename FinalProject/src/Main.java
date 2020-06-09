import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * the main target is to the Go Football system
 *  this is the main class for the program
 *  
 * @author Omar Hany (20180184)
 * 		   Abd Elhadi Omar (20180373)
 *         Sherif Hisham (20180134)
 */

public class Main {

	public static void main(String[] args) throws IOException {
		//object form the controlFlow class
		ControlFlow control = new ControlFlow();
		int role = 0;
		try {
			role = control.start();
		}
		catch(IOException e) {
		}
		try {
			control.main(role);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
