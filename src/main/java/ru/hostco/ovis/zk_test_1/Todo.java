package ru.hostco.ovis.zk_test_1;

import java.io.Serializable;
import java.util.Date;

/**
 * Todo entity
 * @author rybakov
 *
 */
public class Todo implements Serializable, Cloneable{
	private static final long serialVersionUID = 1L;
	
	boolean complete;
	String subject;
	Integer priority;
	Date date;
	String description;
	Integer id;
	
	public Todo(Integer id, String subject, Integer priority, Date date, String description) {
		this.id = id;
		this.subject = subject;
		this.priority = priority;
		this.date = date;
		this.description = description;
	}
	public Todo(String subject) {
		this.subject = subject;
		this.priority = 0;
	}
	
	@Override 
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Todo other = (Todo) obj;
		if (id == null) {
			if (other.id == null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public static Todo clone(Todo todo) {
		try {
			return (Todo) todo.clone();
		} catch (CloneNotSupportedException e) {
			// not possible
		}
		return null;
	}

	public boolean isComplete() {
		return complete;
	}
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
