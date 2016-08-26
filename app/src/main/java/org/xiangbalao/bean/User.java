package org.xiangbalao.bean;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@DatabaseField(columnName = "_id", generatedId = true, allowGeneratedIdInsert = true)//必须为int
	private int id;
	@DatabaseField
	public String firstname;
	@DatabaseField
	public String lastname;

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("User{");
		sb.append("id='").append(id).append('\'');
		sb.append(",firstname='").append(firstname).append('\'');
		sb.append(", lastname='").append(lastname).append('\'');
		sb.append('}');
		return sb.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
