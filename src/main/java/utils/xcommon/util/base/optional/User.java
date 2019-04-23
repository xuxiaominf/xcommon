package utils.xcommon.util.base.optional;

import java.util.Optional;

public class User {
	private String name;
	private Integer age;
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
	
	public User() {
		super();
	}
	public User(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	public void addAge(User user){
		user.setAge(user.getAge()+1);
	}
	
	public Integer getAddAge(){
		return 3;
	}
	
	public Optional<Integer> getOptionalAddAge(){
		return Optional.of(5);
	}
}
