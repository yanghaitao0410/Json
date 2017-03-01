package ObjectMapperTest.jackson;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 * TypeReference -- 让Jackson Json在List/Map中识别自己的Object
 */
public class JsonTest3 {

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, UserData> userDataMap = new HashMap<String, UserData>();
		UserData studentData = new UserData();
		int[]marks = {1, 2, 3};
		Student student = new Student();
		student.setAge(10);
		student.setName("affas");
		studentData.setStudent(student);
		studentData.setName("dfgedrhre");
		studentData.setVerified(Boolean.FALSE);
		studentData.setMarks(marks);
		TypeReference<Map<String, UserData>> ref = new TypeReference<Map<String, UserData>>(){};
		userDataMap.put("studentData1", studentData);
		mapper.writeValue(new File("student.json"), userDataMap);
//		{
//           "studentData1":
//        	 {
//        		"student":
//        		{
//        			"name":"Mahesh",
//        			"age":10
//              },
//              "name":"Mahesh Kumar",
//              "verified":false,
//              "marks":[1,2,3]
//           }
//        }
		userDataMap = mapper.readValue(new File("student.json"), ref);
		System.out.println(userDataMap.get("studentData1").getStudent());
		System.out.println(userDataMap.get("studentData1").getName());
		System.out.println(userDataMap.get("studentData1").getVerified());
		System.out.println(Arrays.toString(userDataMap.get("studentData1").getMarks()));
	}
	
}
class UserData{
	private Student student;
	private String name;
	private Boolean verified;
	private int[] marks;
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getVerified() {
		return verified;
	}
	public void setVerified(Boolean verified) {
		this.verified = verified;
	}
	public int[] getMarks() {
		return marks;
	}
	public void setMarks(int[] marks) {
		this.marks = marks;
	}
	
}