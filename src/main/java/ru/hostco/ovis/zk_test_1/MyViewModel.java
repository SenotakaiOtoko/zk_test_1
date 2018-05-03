package ru.hostco.ovis.zk_test_1;

import java.io.Serializable;
import java.util.List;

import org.zkoss.bind.Form;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.Validator;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.zul.ListModelList;


public class MyViewModel implements Serializable {

	private static int count = 100;

	private String subject;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	private ListModelList<Todo> todoListModel;

	private Todo selectedTodo;

	public Todo getSelectedTodo() {
		return selectedTodo;
	}

	public void setSelectedTodo(Todo selectedTodo) {
		this.selectedTodo = selectedTodo;
	}

	public List<String> getCountryList() {
		return CommonInfoService.getCountryList();
	}

	@Init
	public void init() {
		// count = 100;
		List<Todo> todoList = TodoListService.getInstance().getTodoList();
		todoListModel = new ListModelList<Todo>(todoList);
		
		//TODO: What the f_ck is going on?
		//selectedTodo = TodoListService.getInstance().getTodo(0);
	}

	@Command
	@NotifyChange("count")
	public void cmd() {
		++count;
	}
	
	@Command
	@NotifyChange("selectedTodo")
	public void updateTodo() {
		selectedTodo = TodoListService.getInstance().updateTodo(selectedTodo);
		todoListModel.set(todoListModel.indexOf(selectedTodo), selectedTodo);
	}
	
	@Command
	@NotifyChange("selectedTodo")
	public void reloadTodo() {
		//Do nothing
	}
	
	
	@Command
	@NotifyChange({ "selectedTodo", "subject" })
	public void addTodo() {
		if (org.zkoss.lang.Strings.isBlank(subject)) {
			System.out.println("Subject is blank, nothing to do ?");
			throw new RuntimeException("TODO: Finish this branch :)");
		} else {
			selectedTodo = TodoListService.getInstance().saveTodo(new Todo(subject));

			todoListModel.add(selectedTodo);
			todoListModel.addToSelection(selectedTodo);

			subject = null;
		}
	}

	public int getCount() {
		return count;
	}
	
	public ListModelList<Todo> getTodoListModel() {
		return todoListModel;
	}

}
