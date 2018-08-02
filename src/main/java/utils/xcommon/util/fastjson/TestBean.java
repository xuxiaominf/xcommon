package utils.xcommon.util.fastjson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestBean {
	private String name;
	private Integer age;
	private Date brithDate;
	
	private List<TestAddress> beans = new ArrayList<>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getBrithDate() {
		return brithDate;
	}
	public void setBrithDate(Date brithDate) {
		this.brithDate = brithDate;
	}
	public List<TestAddress> getBeans() {
		return beans;
	}
	public void setBeans(List<TestAddress> beans) {
		this.beans = beans;
	}
	
	public TestBean() {
		super();
	}
	
	public TestBean(String name, Integer age, Date brithDate,
			List<TestAddress> beans) {
		super();
		this.name = name;
		this.age = age;
		this.brithDate = brithDate;
		this.beans = beans;
	}
	@Override
	public String toString() {
		return "TestBean [name=" + name + ", age=" + age + ", brithDate="
				+ brithDate + ", beans=" + beans + "]";
	}
}
