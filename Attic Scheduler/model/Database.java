package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Database {
	private List<Issue> issues;

	public Database() {
		issues = new LinkedList<Issue>();
	}

	public void addIssue(Issue issue) {
		issues.add(issue);
	}

	public List<Issue> getIssues() {
		return Collections.unmodifiableList(issues);
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
	
	public void editIssue(int index, Object editedValue) {
		issues.get(index).setDesc(editedValue.toString());
		
//		issues.edit(index);
	}

}
