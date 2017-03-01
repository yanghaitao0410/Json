package ObjectMapperTest.jackson;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

/**
 * 树和json这两者之间的转换
 * @author yanght
 *
 */
public class JsonTest5 {

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		/*
		 * 根据要创建的结点的类型分为对象Node和数组Node
		 * 对象Node：正常情况下的json例如：{"name" : "zhangsan"}
		 * 数组Node：json的值对应的是一个数组
		 * 	例如：{"marks" : [100, 90, 85]}
		 */
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
		/*
		 *{	"name":"sdfgsdg",
		 *	"age":21,
		 *	"verified":false,
		 *	"marks":[100,90,85]} 
		 */
		//从文件中读回
		rootNode = mapper.readTree(new File("student.json"));
		JsonNode nameNode = rootNode.path("name");
		JsonNode ageNode = rootNode.path("age");
		JsonNode verNode = rootNode.path("verified");
		JsonNode markNode = rootNode.path("marks");
		System.out.println("Name:" + nameNode.getTextValue());
		System.out.println("Age:" + ageNode.getIntValue());
		System.out.println("verified:" + verNode.getBooleanValue());
		Iterator<JsonNode> it = markNode.getElements();
		System.out.print("marks:[");
		while(it.hasNext()){
			JsonNode marks = it.next();
			System.out.print(marks.getIntValue() + " ");
		}
		System.out.print("]");
		
	}
	
}
