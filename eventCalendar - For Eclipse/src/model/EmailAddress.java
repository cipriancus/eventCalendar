package model;
/**
 * 
 * @author Grosu
 *
 */
public class EmailAddress {
	private String address;
	private String password;
	private int read;
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRead() {
		return read;
	}

	public void setRead(int read) {
		this.read = read;
	}

	@Override
	public String toString(){
		return "        "+address;
	}
	
}
