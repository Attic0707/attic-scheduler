package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Database {
	private List<Issue> issues;
	private List<User> users;
	private Map<String, char[]> userMap;
	private Map<String, String> issueMap;
	private String loggedInUser;

	public Database() {
		issues = new LinkedList<Issue>();
		users = new LinkedList<User>();
		userMap = new HashMap<String, char[]>();
		issueMap = new HashMap<String, String>();
	}

	public void putToUserMap(String userName, char[] password) {
		userMap.put(userName, password);
	}
	
	public void deleteAccount() {
	}
	
	public void setLoggedInUser(String currentUser) {
		this.loggedInUser = currentUser;
	}
	
	public void logOutUser() {
		this.loggedInUser = null;
	}
	
	public String getLoggedInUser() {
		return loggedInUser;
	}

	public void putToIssueMap(String issueId, String issueName) {
		issueMap.put(issueId, issueName);
	}

	public void removeFromIssueMap(String issueId) {
		issueMap.remove(issueId);
	}

	public Map<String, String> getIssueMap() {
		return issueMap;
	}

	public Map<String, char[]> getUserMap() {
		return userMap;
//		return Collections.unmodifiableSortedMap((SortedMap<String, char[]>) userMap);
	}

	public void addIssue(Issue issue) {
		issues.add(issue);
	}

	public void addUser(User user) {
		users.add(user);
	}

	public List<Issue> getIssues() {
		return Collections.unmodifiableList(issues);
	}

	public List<User> getUsers() {
		return Collections.unmodifiableList(users);
	}
	public ArrayList<String> getUserNameList() {
		ArrayList<String> currentUserNames = new ArrayList<String>();
		for(User s: users) {
			currentUserNames.add(s.getUserName());
		}
		return currentUserNames;
	}

	public void exportUsers(File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		User[] userList = users.toArray(new User[users.size()]);
		oos.writeObject(userList);
		oos.close();
	}

	public void saveToFile(File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		Issue[] issueList = issues.toArray(new Issue[issues.size()]);
		oos.writeObject(issueList);
		oos.close();
	}

	public void loadCSVFile(File file) throws IOException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		try {
			Issue[] issueList = (Issue[]) ois.readObject();
			issues.clear();
			issues.addAll(Arrays.asList(issueList));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ois.close();
	}

	public void loadFromFile(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		try {
			Issue[] issueList = (Issue[]) ois.readObject();
			issues.clear();
			issues.addAll(Arrays.asList(issueList));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		ois.close();
	}

	public void removeIssue(int index) {
		issues.remove(index);
	}

	public void removeUser(int index) {
		users.remove(index);
	}

	public void editIssue(int index, Object editedValue) {
		issues.get(index).setDesc(editedValue.toString());
//		issues.edit(index);
	}
}