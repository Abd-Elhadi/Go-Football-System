import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

/**
 *  this class will be the control flow of the system
 * @author Omar Hany (20180184)
 * 		   Abd Elhadi Omar (20180373)
 *         Sherif Hisham (20180134)
 */

public class ControlFlow {
	Scanner in = new Scanner(System.in);
	
	/*
	 * curentP, currentOwner and currentAdmin will keep track for the current player, 
	 * playgroundOwner and the current admin respectively
	 */
	Player currentP = new Player();
	PlayGroundOwner currentOwner = new PlayGroundOwner();
	Admin currentAdmin = new Admin();
	
	//start is function to start the runing the class
	public int start() throws IOException {
		
		/*
		 * while the user didn't choose to close the program 
		 */
		while(true) {
			
			//ask the user if s/he wants to login, create a new account or close to program
			System.out.println("WELCOME to Go Football Sytem");
			System.out.println("[1] Login");
			System.out.println("[2] Register");
			System.out.println("[3] Close");
			System.out.print("Your choice: ");
			String choice = in.nextLine();
			
			//login function
			if(choice.equals("1")) {
				System.out.println("[1] Player");
				System.out.println("[2] PLayGround Owner");
				System.out.println("[3] Admin");
				System.out.print("Your choice: ");
				choice = in.nextLine();
				
				//Player login process
				if(choice.equals("1")) {
					System.out.print("Your email: ");
					String email = in.nextLine();
					System.out.print("Your password: ");
					String pass = in.nextLine();
					File dir = new File("C:\\Users\\PC\\Desktop\\SWProject\\Users\\Players");
					File[] directoryListing = dir.listFiles();
					boolean exist=false;
					for(File child:directoryListing) {
						if(child.getName().equals(email)) {
							exist=true;
							break;
						}
					}
					if(!exist) {
						System.out.println("there is no acount with this data please try again");
						continue;
					}
					boolean valid = false;
					File data = new File("C:\\Users\\PC\\Desktop\\SWProject\\Users\\Players\\"+email+"\\data.txt");
					Scanner myReader = new Scanner(data);
					String storedEmail = myReader.nextLine();
					String storedPass = myReader.nextLine();
					if(storedEmail.equals(email)&&storedPass.equals(pass)) {
						valid=true;
						System.out.println("Loged in successfully");
						currentP.setEmail(email);
						currentP.setPassword(pass);
						currentP.setName(myReader.nextLine());
						currentP.setPhone_number(myReader.nextLine());
						currentP.setWallet();
					}
					myReader.close();
					if(!valid) {
						System.out.println("The email address or password is invalid. Try again.");
					}
					else {
						return 1;
					}
				}
				//PlayroungOwner login process
				else if(choice.contentEquals("2")){
					System.out.print("Your email: ");
					String email = in.nextLine();
					System.out.print("Your password: ");
					String pass = in.nextLine();
					File dir = new File("C:\\Users\\PC\\Desktop\\SWProject\\Users\\PlaygroundsOwner");
					File[] directoryListing = dir.listFiles();
					boolean exist=false;
					for(File child:directoryListing) {
						if(child.getName().equals(email)) {
							exist=true;
							break;
						}
					}
					if(!exist) {
						System.out.println("there is no acount with this data please try again");
						continue;
					}
					boolean valid = true;
					File dataAboutUser = new File("C:\\Users\\PC\\Desktop\\SWProject\\Users\\PlaygroundsOwner\\"+email+"\\data.txt");
							Scanner myReader = new Scanner(dataAboutUser);
							String StoredEmail = myReader.nextLine();
							if(!StoredEmail.equalsIgnoreCase(email)) {
								System.out.println("Invalid email address");
								valid = false;
								continue;
							}
							String StoredPass = myReader.nextLine();
							if(!StoredPass.equalsIgnoreCase(pass)) {
								System.out.println("Invalid password");
								valid = false;
								continue;
							}
							if(valid){
								currentOwner.setEmail(email);
								currentOwner.setPassword(pass);
								String data = myReader.nextLine();
								currentOwner.setName(data);
								data = myReader.nextLine();
								currentOwner.setPhone_number(data);
								data = myReader.nextLine();
								currentOwner.setDefault_location(data);
								currentOwner.setWallet();
								myReader.close();
								System.out.println("Loged in successfully");
								return 2;
							}
							if(!valid) {
								System.out.println("The email address or password is invalid. Try again.");
							}
						}
				//Admin login process
				else if (choice.equals("3")){
					System.out.print("Your email: ");
					String email = in.nextLine();
					System.out.print("Your password: ");
					String pass = in.nextLine();
					File dir = new File("C:\\Users\\PC\\Desktop\\SWProject\\Users\\admins");
					File[] directoryListing = dir.listFiles();
					for (File child : directoryListing) {
						if(child.getName().equals(email+".txt")) {
							Scanner myReader = new Scanner(child);
							String storedEmail = myReader.nextLine();
							String storedPass = myReader.nextLine();
							if(storedPass.equals(pass)) {
								System.out.println("Loged in successfully");
								currentAdmin = new Admin(email,pass);
								return 3;
							}
						}
					}
					System.out.println("The email address or passwrod is invalid. Try again");
					continue;
				}
				else System.out.println("Invalid input. Try again.");
			}
			//registration process
			else if (choice.contentEquals("2")){
				System.out.println("[1] Player");
				System.out.println("[2] playground owner");
				System.out.print("Your choice: ");
				String flag = in.nextLine();
				
				//register a new player
				if(flag.equals("1")) {
					System.out.println("Your email: like(example@gmail.com)");
					String email = in.nextLine();
					while(true){
						String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
						Pattern pat = Pattern.compile(EMAIL_PATTERN);
						boolean validPattern=false;
						if(pat.matcher(email).matches()){
							validPattern=true;
						}
						File dir = new File("C:\\Users\\PC\\Desktop\\SWProject\\Users\\Players");
						File[] directoryListing = dir.listFiles();
						boolean exist=false;
						for(File child:directoryListing){
							if(child.getName().equals(email))
								exist=true;
						}
						if(!exist&&validPattern)
							break;
						else if(exist)
							System.out.println("This email is already exist re-enter another one");
						else
							System.out.println("invalid email re-enter correct one");
						email=in.nextLine();
					}
					System.out.print("Your password: ");
					String pass = in.nextLine();
					System.out.print("Your username: ");
					String name = in.nextLine();
					System.out.print("Your phone number: ");
					String phone = in.nextLine();
					String captcha = "";
					for(int i=0; i<6; i++) {
						captcha += (char)((Math.random()*26) + 97);
					}
					System.out.print("Enter the verification code above: ");
					System.out.println(captcha);
					String ans = in.nextLine();
					if(ans.equals(captcha)) {
						System.out.println("Correct");
						new File("C:\\Users\\PC\\Desktop\\SWProject\\Users\\Players\\"+email).mkdir();
						File fileForNewPlayer = new File("C:\\Users\\PC\\Desktop\\SWProject\\Users\\Players\\"+email+"\\data.txt");
						fileForNewPlayer.createNewFile();
						File mail = new File("C:\\Users\\PC\\Desktop\\SWProject\\Users\\Players\\"+email+"\\mail.txt");
						mail.createNewFile();
						File favoriteTeam = new File("C:\\Users\\PC\\Desktop\\SWProject\\Users\\Players\\"+email+"\\favoriteTeam.txt");
						favoriteTeam.createNewFile();
						File filewallet = new File("C:\\Users\\PC\\Desktop\\SWProject\\Users\\Players\\"+email+"\\wallet.txt");
						filewallet.createNewFile();
						FileWriter WriterForNewData = new FileWriter(fileForNewPlayer);
						WriterForNewData.write(email+"\n");
						WriterForNewData.write(pass+"\n");
						WriterForNewData.write(name+"\n");
						WriterForNewData.write(phone+"\n");
						WriterForNewData.close();
						System.out.print("Your eWallet account number: ");
						eWallet wallet=new eWallet();
						String accountNumber=in.nextLine();
						wallet.setaccountNumber(accountNumber);
						System.out.print("Your eWallet password: ");
						String passWallet=in.nextLine();
						wallet.setPassword(passWallet);
						FileWriter WriterforWallet = new FileWriter(filewallet);
						WriterforWallet.write(accountNumber+'\n');
						WriterforWallet.write(passWallet+'\n');
						WriterforWallet.write("1000\n");
						WriterforWallet.close();
						Player current = new Player(name,pass,email,phone,wallet);
						this.currentP = current;
						return 1;
					}
					else {
						System.out.println("Invalid input");
						continue;
					}
				}
				//register a new playroundOwner
				else if (flag.equals("2")){
					System.out.println("Your email: like(example@gmail.com)");
					String email = in.nextLine();
					while(true){
						String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
						Pattern pat = Pattern.compile(EMAIL_PATTERN);
						boolean validPattern=false;
						if(pat.matcher(email).matches()){
							validPattern=true;
						}
						File dir = new File("C:\\Users\\PC\\Desktop\\SWProject\\Users\\PlaygroundsOwner");
						File[] directoryListing = dir.listFiles();
						boolean exist=false;
						for(File child:directoryListing){
							if(child.getName().equals(email))
								exist=true;
						}
						if(!exist&&validPattern)
							break;
						else if(exist)
							System.out.println("This email is already exist re-enter another one");
						else
							System.out.println("invalid email re-enter correct one");
						email=in.nextLine();
					}
					System.out.print("Your password: ");
					String pass = in.nextLine();
					System.out.print("Your username: ");
					String name = in.nextLine();
					System.out.print("Your phone number: ");
					String phone = in.nextLine();
					System.out.print("Your user default location: ");
					String loc = in.nextLine();
					String captcha = "";
					for(int i=0;i<6;i++) {
						captcha += (char)((Math.random()*26) + 97);
					}
					System.out.print("Enter the verification code above: ");
					System.out.println(captcha);
					String ans = in.nextLine();
					if(ans.equals(captcha)) {
						System.out.println("Correct");
						new File("C:\\Users\\PC\\Desktop\\SWProject\\Users\\PlaygroundsOwner\\"+email).mkdirs();
						File fileForNewOwner = new File("C:\\Users\\PC\\Desktop\\SWProject\\Users\\PlaygroundsOwner\\"+email+"\\data.txt");
						fileForNewOwner.createNewFile();
						File mail = new File("C:\\Users\\PC\\Desktop\\SWProject\\Users\\PlaygroundsOwner\\"+email+"\\mail.txt");
						mail.createNewFile();
						FileWriter WriterForNewData = new FileWriter("C:\\Users\\PC\\Desktop\\SWProject\\Users\\PlaygroundsOwner\\"+email+"\\data.txt");
						WriterForNewData.write(email+"\n");
						WriterForNewData.write(pass+"\n");
						WriterForNewData.write(name+"\n");
						WriterForNewData.write(phone+"\n");
						WriterForNewData.write(loc+"\n");
						System.out.println("enter your accountNumber in eWallet");
						eWallet wallet=new eWallet();
						String accountNumber=in.nextLine();
						wallet.setaccountNumber(accountNumber);
						File filewallet = new File("C:\\Users\\PC\\Desktop\\SWProject\\Users\\PlaygroundsOwner\\"+email+"\\wallet.txt");
						filewallet.createNewFile();
						System.out.println("enter your password in eWallet");
						String passWallet=in.nextLine();
						wallet.setPassword(passWallet);
						FileWriter WriterforWallet = new FileWriter(filewallet);
						WriterforWallet.write(accountNumber+'\n');
						WriterforWallet.write(passWallet+'\n');
						WriterforWallet.write("0\n");
						WriterforWallet.close();
						PlayGroundOwner current=new PlayGroundOwner(name,pass,email,phone,loc,wallet);
						this.currentOwner = current;
						WriterForNewData.close();
						return 2;
					}
					else {
						System.out.println("Invalid input");
						continue;
					}
				}
				else {
					System.out.println("Invalid input. Try again.");
					continue;
				}

			}
			else if (choice.equals("3")){
				System.out.println("System Shuting down.");
				return 4;
			}
			else {
				System.out.println("Invalid input. Try again.");
				continue;
			}
		}
	}
	//this will print the appropriate menu for each user whether Player, PlaygoundOwner or Admin
	void main(int role) throws IOException {
		if(role == 1) {
			while(true) {
				//print the menu of the player
				currentP.mainMenu();
				int choice = in.nextInt();
				//book a playground
				if(choice == 1) {
					currentP.Booking();
				}
				//filter the playgrounds by location
				else if(choice == 2) {
					System.out.print("Your location: ");
					String loc = in.next();
					currentP.FilterByLocation(loc);
				}
				//this will filter the playgrounds by price
				else if(choice == 3) {
					System.out.print("Your price: ");
					Double price = in.nextDouble();
					currentP.FilterByPrice(price);
				}
				//show the mailBox of the player
				else if(choice == 4){
					currentP.showMailbox();
				}
				//add a member the player's favorite team
				else if(choice == 5){
					currentP.addNewPlayersToFavoriteTeam();
				}
				else if (choice == 6) {
					System.out.println("Program terminated.");
					break;
				}
				else {
					System.out.println("Invalid choice.");
					continue;
				}
			}
		}
		/*
		 * if the role is playground owner, print the his appropriate menu
		 * and perform his choices
		 */
		else if(role == 2){
			while(true) {
				currentOwner.mainMenu();
				int choice=in.nextInt();
				if(choice == 1) {
					try {
						currentOwner.addPlayGround();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(choice == 2){
					currentOwner.showMailbox();
				}
				else if (choice == 3) {
					System.out.println("Program terminated.");
					break;
				}
				else {
					System.out.println("Invalid choice.");
					continue;
				}
			}
		}
		//print the appropriate menu for the admin 
		else if(role == 3){
			while(true) {
				currentAdmin.mainMenu();
				int choice = in.nextInt();
				if(choice == 1) {
					System.out.println("The list of non approved playgrounds");
					currentAdmin.printnonApproved();

				}
				else if (choice == 2) {
					System.out.println("Program terminated.");
					break;
				}
				else {
					System.out.println("Invalid choice.");
					continue;
				}
			}
		}
	}
}
