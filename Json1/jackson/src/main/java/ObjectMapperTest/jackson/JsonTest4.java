package ObjectMapperTest.jackson;

import java.io.IOException;
import java.util.Iterator;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 创建json树模型
 * 读取json树的节点中的值
 * @author yanght
 *
 */
public class JsonTest4 {

	public static void main(String[]args) throws JsonProcessingException, IOException{
		//从JSON创建树
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = "{\"name\":\"sdfgsdfg\",\"age\":21,"
				+ "\"verified\":false,\"marks\":[100,90,85]}";
		JsonNode rootNode = mapper.readTree(jsonString);
		//使用相对路径来根节点在遍历树，并处理该数据得到的每个节点。
		JsonNode nameNode = rootNode.path("name");
		System.out.println("Name:" + nameNode.getTextValue());
		System.out.println("Age:" + rootNode.path("age").getIntValue());
		System.out.println("Verified:" + (rootNode.path("verified").getBooleanValue() ? "Yes" : "No"));
		
		JsonNode marksNode = rootNode.path("marks");
		Iterator<JsonNode> iterator = marksNode.getElements();
		System.out.print("Marks: [ ");
		while(iterator.hasNext()){
			JsonNode marks = iterator.next();
			System.out.print(marks.getIntValue() + " ");
		}
		System.out.print("]");
	}
	
	
	
}
