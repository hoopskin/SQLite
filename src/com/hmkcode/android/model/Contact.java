package com.hmkcode.android.model;



public class Contact {

	private int id;
	private String fName;
	private String lName;
	private String address;
	private String email;
	private String phone;

	public Contact(){}

	public Contact(String fName, String lName, String address, String email, String phone) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.address = address;
		this.email = email;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	//TODO: Alter this so cleaning up doesn't have to happen in FourthActivity
	@Override
	public String toString() {
		return lName + "," + fName +
				"\n\tAddress:" + address +
				"\n\temail:"+email +
				"\n\t" + "Phone:"+phone+"\n";
	}



}
