package view;

public class ProfileEvent {
	private boolean loggedOut;
	private boolean delete;
	private char[] password;

	public ProfileEvent(Object source, boolean loggedOut) {
		this.loggedOut = loggedOut;
	}
	
	public ProfileEvent(Object source, boolean deleteAccount, char[] password) {
		this.delete = deleteAccount;
		this.password = password;
	}

	public boolean getLoggedOut() {
		return loggedOut;
	}
	public boolean getDeleteRequest() {
		return delete;
	}
	public char[] getConfirmPass() {
		return password;
	}
	public char[] getPassConfirm() {
		return password;
	}
}
