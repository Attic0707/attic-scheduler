package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import gui.FormEvent;
import model.Assignee;
import model.Database;
import model.Issue;
import model.Priority;
import model.Status;
import model.Type;

public class Controller {
	Database db = new Database();
	
	public List<Issue> getIssueList() {
		return db.getIssues();
	}

	public void addIssue(FormEvent fv) {
		String issueName = fv.getIssueName();
		String issueStatus = fv.getStatus();
		String issueDesc = fv.getDesc();
		String dueDate = fv.getDate();
		String issueAssignee = fv.getAssignee();
		String issueType = fv.getType();
		String issuePriority = fv.getPriority();
		int issueDifficulty = fv.getDifficulty();
		String subTaskId = fv.getSubTaskId();

		Status status = null;
		if (issueStatus.equals("Backlog")) {
			status = Status.Backlog;
		} else if (issueStatus.equals("Selected for Development")) {
			status = Status.SelectedForDevelopment;
		} else if (issueStatus.equals("In Progress")) {
			status = Status.InProgress;
		} else if (issueStatus.equals("Development Done")) {
			status = Status.DevelopmentDone;
		} else if (issueStatus.equals("Peer Review")) {
			status = Status.PeerReview;
		} else if (issueStatus.equals("Finished")) {
			status = Status.Finished;
		}

		Assignee assignee = null;
		if (issueAssignee.equals("Admin")) {
			assignee = Assignee.Admin;
		} else if (issueAssignee.equals("User 1")) {
			assignee = Assignee.User1;
		} else if (issueAssignee.equals("User 2")) {
			assignee = Assignee.User2;
		} else if (issueAssignee.equals("User 3")) {
			assignee = Assignee.User3;
		} else if (issueAssignee.equals("User 4")) {
			assignee = Assignee.User4;
		} else if (issueAssignee.equals("User 5")) {
			assignee = Assignee.User5;
		}

		Type type = null;
		if (issueType.equals("Bug")) {
			type = Type.Bug;
		} else if (issueType.equals("Feature")) {
			type = Type.Feature;
		} else if (issueType.equals("Story")) {
			type = Type.Story;
		}

		Priority priority = null;
		if (issuePriority.equals("Low")) {
			priority = Priority.Low;
		} else if (issuePriority.equals("Medium")) {
			priority = Priority.Medium;
		} else if (issuePriority.equals("High")) {
			priority = Priority.High;
		} else if (issuePriority.equals("Urgent")) {
			priority = Priority.Urgent;
		}

		Issue issue = new Issue(issueName, status, issueDesc, dueDate, assignee, type, priority, issueDifficulty, subTaskId);
		db.addIssue(issue);
	}
	
	public void saveToFile(File file) throws IOException {
		db.saveToFile(file);
	}
	
	public void loadFromFile(File file) throws IOException {
		db.loadFromFile(file);
	}
	
	public void loadCSVFile(File file) throws IOException {
		db.loadCSVFile(file);
	}

	public void removeIssue(int index) {
		db.removeIssue(index);
	}
	
	public void editIssue(int index, Object editedValue) {
		db.editIssue(index, editedValue);
	}
}