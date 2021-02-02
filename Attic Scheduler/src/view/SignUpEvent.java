package view;

public class SignUpEvent {
	private String firstName, lastName, userName;
	private char[] password;
	private String street, postalCode, city, state, country, bio, email, company, website, social;
	private int phone, memberSince;

	public SignUpEvent(Object source, String firstName, String lastName, String userName, char[] password, String street, 
			String postalCode, String city, String state, String country, String bio, String email, 
			String company, String website, String social, int phone, int memberSince) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
		this.state = state;
		this.country = country;
		this.bio = bio;
		this.email = email;
		this.company = company;
		this.website = website;
		this.social = social;
		this.phone = phone;
		this.memberSince = memberSince;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getUserName() {
		return userName;
	}
	public char[] getPassword() {
		return password;
	}
	public String getStreet() {
		return street;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getCountry() {
		return country;
	}
	public String getBio() {
		return bio;
	}
	public String getEmail() {
		return email;
	}
	public String getCompany() {
		return company;
	}
	public String getWebsite() {
		return website;
	}
	public String getSocial() {
		return social;
	}
	public int getPhone() {
		return phone;
	}
	public int getMemberSince() {
		return memberSince;
	}
}