import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;


/**
 *  this will implement the player class 
 * @author Omar Hany (20180184)
 * 		   Abd Elhadi Omar (20180373)
 *         Sherif Hisham (20180134)
 */

public class Player extends User{
	Scanner in = new Scanner(System.in);
	public Player(String name, String password, String email, String phone_number,eWallet wallet) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone_number = phone_number;
		this.wallet=wallet;
	}
	public Player() {}
	//player menu options
	public void mainMenu() {
		System.out.println("[1] Book a playground");
		System.out.println("[2] Filter Playgrounds by location");
		System.out.println("[3] Filter Playgrounds by price");
		System.out.println("[4] Show your mailbox");
		System.out.println("[5] Add new player to your favorite team");
		System.out.println("[6] Close");
		System.out.print("Your choice: ");
	}
	public void setWallet() throws FileNotFoundException {
		File filewallet=new File("C:\\Users\\PC\\Desktop\\SWProject\\Users\\Players\\"+this.email+"\\wallet.txt");
		Scanner reader = new Scanner(filewallet);
		this.wallet.setaccountNumber(reader.nextLine());
		this.wallet.setPassword(reader.nextLine());
		this.wallet.setBalance(Double.parseDouble(reader.nextLine()));
		reader.close();
	}
	//this will implement the booking a playground option
	public void Booking() throws IOException {
		File dir = new File("C:\\Users\\PC\\Desktop\\SWProject\\Playgrounds");
		File[] directoryListing = dir.listFiles();
		for(File child:directoryListing) {
			File myObj = new File("C:\\Users\\PC\\Desktop\\SWProject\\Playgrounds\\"+child.getName());
			Scanner myReader = new Scanner(myObj);
			String numberOfAvailableSlots = "";
			ArrayList<String> content = new ArrayList<String>();

			//approval will check if the current playground is approved or not
			String approval = myReader.nextLine();
			boolean completed = false; 
			//if the playground is not approved yet then escape it
			if(approval.equals("false"))
				continue;
			
			content.add(approval);
			while(myReader.hasNextLine()) {
				String data = myReader.nextLine();
				content.add(data);
				if(data.equals("completed"))
					completed = true;
				if(data.equals("Slots")) {
					numberOfAvailableSlots = myReader.nextLine();
					content.add(numberOfAvailableSlots);
					System.out.println("Number of available slots "+numberOfAvailableSlots);
					for(int i = 1; i <= Integer.parseInt(numberOfAvailableSlots); i++) {
						data = myReader.nextLine();
						content.add(data);
						System.out.println(i+")"+data);
					}
				}
				else
					System.out.println(data);
			}
			if(completed)
				continue;
			String choice = "";
			System.out.println("To book a slot on this playground (yes/no)");
			System.out.print("Your input: ");
			String flag = in.nextLine();
			if(flag.equalsIgnoreCase("yes")) {
				while(true) {
					System.out.println("enter the number of slot you want to choose");
					choice = in.nextLine();
					if(Integer.parseInt(choice)>Integer.parseInt(numberOfAvailableSlots)) {
						System.out.println("there is no slot with this number");
						System.out.println("do you want to cancel the booking(yes/no)");
						flag = in.nextLine();
						if(flag.equalsIgnoreCase("yes")) {
							break;
						}
					}
					else {
						break;
					}
				}
				if(this.wallet.sendMoneyTo(this.email,content.get(1),Double.parseDouble(content.get(4)))){
					
					if(content.get(6).equals("1")) {
						content.remove(5);
						content.remove(6);
						content.set(5,new String("completed"));
					}
					else {
						int temp = Integer.parseInt(numberOfAvailableSlots)-1;
						content.set(6,new String(Integer.toString(temp)));
						content.remove(6+Integer.parseInt(choice));
					}
					FileWriter myWriter = new FileWriter("C:\\Users\\PC\\Desktop\\SWProject\\Playgrounds\\"+child.getName());
					for(String newContent:content)
						myWriter.write(newContent+'\n');
					myWriter.close();
					myReader.close();
					System.out.println("Successful booking");
					System.out.println("To send an invitation to your favorite team. (yes/no)");
					System.out.print("Your input: ");
					String ans = in.nextLine();
					if(ans.equalsIgnoreCase("yes"))
						this.sendMailsForFavoriteTeam(content.get(2));
					return;
				}
			}
		}
	}
	//showMailBox function
	public void showMailbox() throws IOException {
		File mailbox=new File("C:\\Users\\PC\\Desktop\\SWProject\\Users\\Players\\"+this.email+"\\mail.txt");
		Scanner myReader = new Scanner(mailbox);
		while(myReader.hasNextLine()) {
			System.out.println(myReader.nextLine());
		}
		FileWriter myWriter = new FileWriter(mailbox);
		myWriter.write("");
		myWriter.close();
		System.out.println("**************");
	}
	
	//filterByLocation function
	public void FilterByLocation(String location) throws IOException {
		int cnt = 1;
		File dir = new File("C:\\Users\\PC\\Desktop\\SWProject\\Playgrounds");
		File[] directoryListing = dir.listFiles();
		for(File child:directoryListing) {
			File myObj = new File("C:\\Users\\PC\\Desktop\\SWProject\\Playgrounds\\"+child.getName());
			Scanner myReader = new Scanner(myObj);
			String name = child.getName();
			boolean flag = false;
			String loc;
			try (Stream<String> all_lines = Files.lines(Paths.get("C:\\Users\\PC\\Desktop\\SWProject\\Playgrounds\\"+child.getName()))) {
				loc = all_lines.skip(3).findFirst().get();
			}
			if(loc.equalsIgnoreCase(location)) {
				flag = (myReader.nextLine().equals("true"));
			}
			if(flag)
				System.out.println(cnt+++")");
			while(myReader.hasNextLine() && flag) {
				System.out.println(myReader.nextLine());	
			}
			if(flag) {
				System.out.println("******************");
			}
		}
	}
	//filterbyPrince function
	public void FilterByPrice(Double price) throws IOException {
		int cnt=1;
		File dir = new File("C:\\Users\\PC\\Desktop\\SWProject\\Playgrounds");
		File[] directoryListing = dir.listFiles();
		for(File child:directoryListing) {
			File myObj = new File("C:\\Users\\PC\\Desktop\\SWProject\\Playgrounds\\"+child.getName());
			Scanner myReader = new Scanner(myObj);
			boolean flag = false;
			String CurPrice;
			try (Stream<String> all_lines = Files.lines(Paths.get("C:\\Users\\PC\\Desktop\\SWProject\\Playgrounds\\"+child.getName()))) {
				CurPrice = all_lines.skip(4).findFirst().get();
			}
			if(Double.parseDouble(CurPrice) <= price) {
				
				flag = (myReader.nextLine().equals("true"));
			}
			if(flag)
				System.out.println(cnt+++")");
			while(myReader.hasNextLine() && flag) {
				System.out.println(myReader.nextLine());	
			}
			if(flag) {
				System.out.println("******************");
			}
		}
	}
	
	//add member to player's favorite team
	public void addNewPlayersToFavoriteTeam() throws IOException {
		while(true) {
			FileWriter myWriter = new FileWriter("C:\\Users\\PC\\Desktop\\SWProject\\Users\\Players\\"+this.email+"\\favoriteTeam.txt",true);
			System.out.println("Enter the email you want to add it to your favorite team list");
			String email = in.nextLine();
			File dir = new File("C:\\Users\\PC\\Desktop\\SWProject\\Users\\Players\\");
			File[] directoryListing = dir.listFiles();
			boolean exist = false;
			boolean addedBefore = false;
			for(File child:directoryListing) {
				if(child.getName().equalsIgnoreCase(email)) {
					File myObj = new File("C:\\Users\\PC\\Desktop\\SWProject\\Users\\Players\\"+this.email+"\\favoriteTeam.txt");
					Scanner myReader = new Scanner(myObj);
					exist = true;
					while(myReader.hasNextLine()) {
						String user = myReader.nextLine();
						if(user.equals(email)) {
							System.out.println("You already added this email.");
							addedBefore = true;
							break;
						}
					}
					if(!addedBefore) {
						myWriter.write(child.getName()+"\n");
						break;
					}
				}
			}
			if(!exist) {
				System.out.println("This email is not exist");
			}
			else if(!addedBefore){
				System.out.println(email+" has been added to your favorite list successefully");
			}
			System.out.println("Do you want to add another one (yes/no)");
			System.out.print("Your input: ");
			String flag = in.nextLine();
			myWriter.close();
			if(!flag.equalsIgnoreCase("yes")) {
				break;
			}
		}
	}

	//send invitation function
	public void sendMailsForFavoriteTeam(String nameOfPlayground) throws IOException {
		File Fav = new File("C:\\Users\\PC\\Desktop\\SWProject\\Users\\Players\\"+this.email+"\\favoriteTeam.txt");
		Scanner myReader = new Scanner(Fav);
		while(myReader.hasNextLine()) {
			String data = myReader.nextLine();
			FileWriter mail = new FileWriter("C:\\Users\\PC\\Desktop\\SWProject\\Users\\Players\\"+data+"\\mail.txt",true);
			mail.write("your friend "+this.email+" inviteed you to play on "+nameOfPlayground+'\n');
			mail.close();
		}
		myReader.close();
	}
}
