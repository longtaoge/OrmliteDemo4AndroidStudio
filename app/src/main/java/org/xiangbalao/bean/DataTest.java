package org.xiangbalao.bean;

import com.j256.ormlite.field.DatabaseField;

public class DataTest {
	@DatabaseField(columnName = "_id", generatedId = true, allowGeneratedIdInsert = true)
	// 如果不指定id将自动生成
	private int id;

	@DatabaseField
	private String name;

	@DatabaseField
	private String age;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "DataTest [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

}
