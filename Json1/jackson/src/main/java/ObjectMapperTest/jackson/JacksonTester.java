package ObjectMapperTest.jackson;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JacksonTester {

	public static void main(String[]args) throws Exception{
		JacksonTester tester = new JacksonTester();
		Student student = new Student();
		student.setName("Masesh");
		student.setAge(11);
		tester.writeJSON(student);
		Student stu = tester.readJSON();
		System.out.println(stu);
		
	}
	private void writeJSON(Student student) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File("student.json"), student);
	}
	
	private Student readJSON() throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(new File("student.json"), Student.class);
	}
}

