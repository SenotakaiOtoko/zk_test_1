package ru.hostco.ovis.zk_test_1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TodoListService {
	private static volatile TodoListService instance;

	List<Todo> todoList = new ArrayList<Todo>();

	private int todoId = 0;

	private TodoListService() {
		todoList.add(new Todo(todoId++, "Buy some milk", 0, null, null));
		todoList.add(new Todo(todoId++, "Dennis' birthday gift", 1, dayAfter(10), null));
		todoList.add(new Todo(todoId++, "Pay credit-card bill", 2, dayAfter(5), "$1,000"));
	}

	private static Date dayAfter(int d) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, d);
		return c.getTime();
	}

	public synchronized List<Todo> getTodoList() {
		List<Todo> list = new ArrayList<Todo>();
		for (Todo todo : todoList) {
			list.add(Todo.clone(todo));
		}
		return list;
	}

	public synchronized Todo getTodo(Integer id) {
		int size = todoList.size();
		for (int i = 0; i < size; ++i) {
			Todo t = todoList.get(i);
			if (t.getId().equals(id)) {
				return Todo.clone(t);
			}
		}
		return null;
	}

	public synchronized Todo saveTodo(Todo todo) {
		todo = Todo.clone(todo);
		todo.setId(todoId++);
		todoList.add(todo);
		return todo;
	}

	public synchronized Todo updateTodo(Todo todo) {
		if (todo.getId() == null) {
			throw new IllegalArgumentException("cann't save a null-id todo, save it first");
		}
		todo = Todo.clone(todo);
		int size = todoList.size();
		for (int i = 0; i < size; ++i) {
			Todo t = todoList.get(i);
			if (t.getId().equals(todo.getId())) {
				todoList.set(i, todo);
				return todo;
			}
		}
		throw new RuntimeException("Todo not found" + todo.getId());
	}

	public synchronized void deleteTodo(Todo todo) {
		if (todo.getId() != null) {
			int size = todoList.size();
			for (int i = 0; i < size; i++) {
				Todo t = todoList.get(i);
				if (t.getId().equals(todo.getId())) {
					todoList.remove(i);
					return;
				}
			}
		}
	}

	public static TodoListService getInstance() {
		TodoListService localInstance = instance;
		if (localInstance == null) {
			synchronized (TodoListService.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new TodoListService();
				}
			}
		}
		return localInstance;
	}
}
