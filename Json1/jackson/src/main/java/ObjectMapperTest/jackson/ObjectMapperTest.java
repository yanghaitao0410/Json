package ObjectMapperTest.jackson;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

public class ObjectMapperTest {

	public static void main(String[]args) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = "{\"name\":\"Mahesh\",\"age\":\"21\"}";
		
		//map json to student
		Student student  = mapper.readValue(jsonString, Student.class);
		System.out.println(student);
		mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
		jsonString = mapper.writeValueAsString(student);
		System.out.println(jsonString);
	}
	
}
class Student{
	private String name;
	private int age;
	boolean verified;
	int[] marks;
	public Student(){}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public int[] getMarks() {
		return marks;
	}
	public void setMarks(int[] marks) {
		this.marks = marks;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + "]";
	}
	
}
