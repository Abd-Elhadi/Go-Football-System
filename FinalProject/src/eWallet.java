import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class eWallet {
	private String accountNumber,password;
	private	double balance;
	private Scanner in = new Scanner(System.in);
		public eWallet() {}
		public eWallet(String accountNumber,String pass) {
			this.accountNumber=accountNumber;
			this.password=pass;
			this.balance=1000;
		}
		public String getaccountNumber() {
			return accountNumber;
		}
		public void setaccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public double getBlance() {
			return balance;
		}
		public void setBalance(double balance) {
			this.balance = balance;
		}
		public boolean sendMoneyTo(String from,String to,double required) throws IOException {
			while(true){
				System.out.println("enter the password for your wallet");
				String pass=in.nextLine();
				if(pass.equals(this.password))
					break;
				System.out.println("incorrect pass");
				System.out.println("do you wnat to try again (yes/no)");
				String choice=in.nextLine();
				if(!choice.equals("yes"))
					return false;
			}
			if(this.balance<required) {
				System.out.println("you don't have enough money");
				return false;
			}
			else {
				this.balance-=required;
				File eWalletData=new File("C:\\Users\\PC\\Desktop\\SWProject\\Users\\Players\\"+from+"\\wallet.txt");
				FileWriter writer=new FileWriter(eWalletData);
				writer.write(this.accountNumber+'\n');
				writer.write(this.password+'\n');
				writer.write(this.balance+""+'\n');
				writer.close();
				File OwnerMail=new File("C:\\Users\\PC\\Desktop\\SWProject\\Users\\PlaygroundsOwner\\"+to+"\\mail.txt");
				FileWriter mailWriter = new FileWriter(OwnerMail,true);
				BufferedWriter br = new BufferedWriter(mailWriter);
				br.write("there is "+required+" has been transferred for you from "+from+"\n");
				br.close();
				mailWriter.close();
				File OwnerWallet=new File("C:\\Users\\PC\\Desktop\\SWProject\\Users\\PlaygroundsOwner\\"+to+"\\wallet.txt");
				Scanner reader=new Scanner(OwnerWallet);
				ArrayList<String> content = new ArrayList<String>();
				while(reader.hasNextLine())
					content.add(reader.nextLine());
				double ownerBalance=Double.parseDouble(content.get(2));
				content.remove(2);
				content.add(ownerBalance+required+"");
				FileWriter myWriter = new FileWriter(OwnerWallet);
				for(String newContent:content)
					myWriter.write(newContent+'\n');
				myWriter.close();
				reader.close();
				System.out.println("Money has been transferred ");
				
				return true;
			}
		}
}
