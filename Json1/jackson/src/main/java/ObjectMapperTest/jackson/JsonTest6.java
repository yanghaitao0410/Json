package ObjectMapperTest.jackson;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

/**
 * 将树转换为Java对象
 * Object treeToValue(JsonNode node, Class clazz)
 * 该方法传入一个json树的根节点和要转换的类类型，返回一个java对象
 * @author yanght
 *
 */
public class JsonTest6 {

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode rootNode = mapper.createObjectNode();
		JsonNode marksNode = mapper.createArrayNode();
		//向json中添加值
		((ArrayNode)marksNode).add(100);
		((ArrayNode)marksNode).add(90);
		((ArrayNode)marksNode).add(85);
		//向根中添加节点
		((ObjectNode)rootNode).put("name", "sdfgsdg");
		((ObjectNode)rootNode).put("age", 21);
		((ObjectNode)rootNode).put("verified", false);
		((ObjectNode)rootNode).put("marks", marksNode);
		//将该树写出到文件中
		mapper.writeValue(new File("student.json"), rootNode);
		
		//将json文件树的根节点返回
		rootNode = mapper.readTree(new File("student.json"));
		//将json转换为java对象
		Student student = mapper.treeToValue(rootNode, Student.class);
		
		System.out.println("Name:" + student.getName());
		System.out.println("Age:" + student.getAge());
		System.out.println("Verified:" + (student.isVerified()  ? "Yes" : "No"));
		System.out.println("marks : " +Arrays.toString(student.getMarks()));
	}
	
}
