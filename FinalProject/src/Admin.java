import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * the main target is to implement the Go Football system
 *  this class will implement the admin who will control the system
 * @author Omar Hany (20180184)
 * 		   Abd Elhadi Omar (20180373)
 *         Sherif Hisham (20180134)
 */

public class Admin {
	Scanner in = new Scanner(System.in);
	protected
	//email and password of the admin
	String email, password;
	//empty constructor
	public Admin() {}
	//parameterized constructor
	public Admin(String email, String password) {
		this.email = email;
		this.password = password;
	}
	//the admin menu function
	public void mainMenu() {
		System.out.println("[1] View all non-Approved Playgrounds");
		System.out.println("[2] Close");
		System.out.print("Your choice: ");
	}
	
	//this will print all non-Approved playgrounds
	public void printnonApproved() throws IOException {
		//dir holds the directory where the playgrounds stored
		File dir = new File("C:\\Users\\Omar\\Desktop\\SWProject\\Playgrounds");
		File[] directoryListing = dir.listFiles();
		//loop through all the playgrounds that stored in the Playgrounds file
		for(File child:directoryListing) {
			File myObj = new File("C:\\Users\\PC\\Desktop\\SWProject\\Playgrounds\\"+child.getName());
			//String myReader will reads the current file data 
			Scanner myReader = new Scanner(myObj);
			
			/*
			 * isApproved, emailOfOwner and nameOfPlayground will hold
			 * the email, the name of the playground and string either true or false that indicates
			 * weather the current playGround is approved or not, respectively
			 * 
			 */
			String isApproved = myReader.nextLine();
			String emailOfOwner = myReader.nextLine();
			String nameOfPlayground = myReader.nextLine();
			
			//if the current playground is not approved yet, then print its data.
			if(isApproved.equals("false")) {
				System.out.println(nameOfPlayground);
				while (myReader.hasNextLine()) {
					String data = myReader.nextLine();
					System.out.println(data);
				}
				myReader.close();
				System.out.println("[1] To approve");
				System.out.println("[2] To reject");
				System.out.print("Your choice: ");
				String choice=in.next();
			
				/*
				 * if the admin chose to approve the current playground
				 * loop the file containing the data of the current and re-write the indicator 
				 * of the approvement to true
				 */
				if(choice.equals("1")) {
					Scanner sc = new Scanner(new File("C:\\Users\\PC\\Desktop\\SWProject\\Playgrounds\\"+child.getName()));
					StringBuffer buffer = new StringBuffer();
					while (sc.hasNextLine()) 
						buffer.append(sc.nextLine()+System.lineSeparator());
					String fileContents = buffer.toString();
					sc.close();
					
					//fileContents will replace the indicator of the approvement from false to true
					fileContents = fileContents.replaceAll("false", "true");
					FileWriter writer = new FileWriter("C:\\Users\\PC\\Desktop\\SWProject\\Playgrounds\\"+child.getName());
					writer.append(fileContents);
					writer.flush();
					
					//this will sends a confirmation message to the current playground owner
					File OwnerMail = new File("C:\\Users\\PC\\Desktop\\SWProject\\Users\\PlaygroundsOwner\\"+emailOfOwner+"\\mail.txt");
					FileWriter mailWriter = new FileWriter(OwnerMail,true);
					BufferedWriter br = new BufferedWriter(mailWriter);
					br.write("Your playground "+nameOfPlayground+" has been approved successfully\n");
					br.close();
					mailWriter.close();
				}
				//if the admin didn't approved the current playground 
				//then this will delete the containing the information of that playground
				else if (choice.equals("2")){
					child.delete();
					File OwnerMail=new File("C:\\Users\\PC\\Desktop\\SWProject\\Users\\PlaygroundsOwner\\"+emailOfOwner+"\\mail.txt");
					FileWriter mailWriter = new FileWriter(OwnerMail,true);
					BufferedWriter br = new BufferedWriter(mailWriter);
					br.write("Your playground "+nameOfPlayground+" has been rejected\n");
					br.close();
					mailWriter.close();
				}
				else {
					System.out.println("Invalid choice.");
				}
				System.out.println("************");
			}
		}
	}
}
