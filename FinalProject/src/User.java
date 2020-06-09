/**
 *  user class, it's a super will inherited by player and playgroundOwner
 *  
 * @author Omar Hany (20180184)
 * 		   Abd Elhadi Omar (20180373)
 *         Sherif Hisham (20180134)
 */

public class User {
	protected String name,email,password,phone_number;
	protected eWallet wallet=new eWallet();
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
}
