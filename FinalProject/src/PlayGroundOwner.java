import java.util.*;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *  this will implement the playgroundOwner class
 * @author Omar Hany (20180184)
 * 		   Abd Elhadi Omar (20180373)
 *         Sherif Hisham (20180134)
 */

public class PlayGroundOwner extends User{
	Scanner in = new Scanner(System.in);
	protected 
		String Default_location;
	//parameterized constructor
	public PlayGroundOwner(String name,String password,String email,String phone_number,String loc,eWallet wallet) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone_number = phone_number;
		this.Default_location = loc;
		this.wallet=wallet;
		wallet.setBalance(0);
	}
	public void setWallet() throws FileNotFoundException {
		File filewallet=new File("C:\\Users\\PC\\Desktop\\SWProject\\Users\\PlaygroundsOwner\\"+this.email+"\\wallet.txt");
		Scanner reader = new Scanner(filewallet);
		this.wallet.setaccountNumber(reader.nextLine());
		this.wallet.setPassword(reader.nextLine());
		this.wallet.setBalance(Double.parseDouble(reader.nextLine()));
		reader.close();
	}
	public String getDefault_location() {
		return Default_location;
	}
	public void setDefault_location(String default_location) {
		Default_location = default_location;
	}
	public  PlayGroundOwner() {}
	public void mainMenu() {
		System.out.println("[1] Add new playgroud");
		System.out.println("[2] show your mailbox");
		System.out.println("[3] Close");
	}
	//this will show the mailBox of the playground owner
	public void showMailbox() throws IOException {
		File myObj = new File("C:\\Users\\PC\\Desktop\\SWProject\\Users\\PlaygroundsOwner\\"+this.email+"\\mail.txt");
		Scanner myReader = new Scanner(myObj);
		int cnt = 1;
		while(myReader.hasNextLine()) {
			System.out.println(cnt+++")"+myReader.nextLine());
		}
		FileWriter myWriter = new FileWriter(myObj);
		myWriter.write("");
		myWriter.close();
		System.out.println("**************");
	}

	//this will add a new playground to playgroundOwner's file
	public void addPlayGround() throws IOException {
		PlayGround New = new PlayGround();
		New.setCurrrentOwner(this);
		String data;
		System.out.print("Enter the name of the playground: ");
		data = in.nextLine();
		New.setName(data);

		File AllPlaygrounds = new File("C:\\Users\\PC\\Desktop\\SWProject\\Playgrounds\\"+data+".txt");
		AllPlaygrounds.createNewFile();
		FileWriter InPlaygrounds = new FileWriter("C:\\Users\\PC\\Desktop\\SWProject\\Playgrounds\\"+data+".txt");
		InPlaygrounds.write("false\n");
		InPlaygrounds.write(this.email+"\n");
		InPlaygrounds.write(data+"\n");
		System.out.print("Enter the location: ");
		data = in.nextLine();
		New.setLocation(data);

		InPlaygrounds.write(data+"\n");
		System.out.print("Enter the price per hour: ");
		data = in.nextLine();
		New.setPricePerHour(Double.parseDouble(data));

		InPlaygrounds.write(data+"\n");
		System.out.print("The number of slots: ");
		String num = in.nextLine();
		InPlaygrounds.write("Slots\n");
		InPlaygrounds.write(num+'\n');
		for(int i = 0; i < Integer.parseInt(num); i++) {
			System.out.print("Enter the slot: ");
			String slot = in.nextLine();
			InPlaygrounds.write(slot+"\n");
		}
		System.out.print("Enter the size: ");
		data = in.nextLine();
		New.setSize(Integer.parseInt(data));

		InPlaygrounds.write(data+"\n");
		System.out.print("Enter the cancellation period in hours: ");
		data = in.nextLine();
		New.setCancellationPeriod(Double.parseDouble(data));

		InPlaygrounds.write(data+"\n");
		System.out.println("Wait for admin approvement");
		InPlaygrounds.close();
	}
}
