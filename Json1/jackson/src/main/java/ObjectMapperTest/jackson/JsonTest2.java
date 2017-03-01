package ObjectMapperTest.jackson;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 使用ObjectMapper 进行java对象和json之间的转换，写入文件
 * @author yanght
 *
 */
public class JsonTest2 {

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		int[]marks = {1, 2, 3};
		
		Student student = new Student();
		student.setName("dsg");
		student.setAge(10);
		map.put("student", student);
		map.put("name", "sdfbgsdfg");
		map.put("verified", Boolean.FALSE);
		map.put("marks", marks);
		
		mapper.writeValue(new File("student.json"), map);
		
		Map map2 = mapper.readValue(new File("student.json"), Map.class);
		System.out.println(map2.get("student"));
		System.out.println(map2.get("name"));
		System.out.println(map2.get("verified"));
		System.out.println(map2.get("marks"));
		
	}
	
}
