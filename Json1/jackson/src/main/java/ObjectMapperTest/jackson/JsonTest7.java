package ObjectMapperTest.jackson;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 利用输入输出流读写json数据
 * 1.利用JsonGenerator写入json字符串
 * @author yanght
 *
 */
public class JsonTest7 {
	/*
	 * 使用JsonFactory.createJsonGenerator()方法创建一个JsonGenerator，
	 * 并用write***()方法来写每一个JSON值。
	 */
	public static void main(String[] args) throws IOException {
		//step1:创建JsonGenerator对象
		JsonFactory factory = new JsonFactory();
		JsonGenerator jsonGenerator = factory.createJsonGenerator(
				new File("student.json"), JsonEncoding.UTF8);
		
		//step2:写值
		jsonGenerator.writeStartObject(); // {
		jsonGenerator.writeStringField("name", "asdfg"); //"name":"asdfg"
		jsonGenerator.writeNumberField("age", 31); // "age":"21"
		jsonGenerator.writeBooleanField("verified", false); //"verified":false
		//写数组
		jsonGenerator.writeFieldName("marks");//marks :
		jsonGenerator.writeStartArray();// [
		jsonGenerator.writeNumber(100); // 100
		jsonGenerator.writeNumber(90);  //  90
		jsonGenerator.writeNumber(85);  //  85
		jsonGenerator.writeEndArray();  // ]
		jsonGenerator.writeEndObject();// }
		jsonGenerator.close(); //关闭输出流
		// result student.json
        //{ 
        //   "name":"Mahesh Kumar",
        //   "age":21,
        //   "verified":false,
        //   "marks":[100,90,85]
        //} //此时已经将json数据写入到文件中了
		//获取文件中的json数据
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> dataMap = mapper.readValue(new File("student.json"), Map.class);
		
		System.out.println(dataMap.get("name"));
		System.out.println(dataMap.get("age"));
		System.out.println(dataMap.get("verified"));
		System.out.println(dataMap.get("marks"));
		
	}
	
}
