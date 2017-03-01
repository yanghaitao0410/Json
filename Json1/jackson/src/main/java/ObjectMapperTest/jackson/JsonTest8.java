package ObjectMapperTest.jackson;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

/**
 * 利用输入流JsonParser 读取JSON
 * @author yanght
 * 使用JsonFactory.createJsonParser()方法创建JsonParser，
 * 并使用它的nextToken()方法来读取每个JSON字符串作为标记。
 * 检查每个令牌和相应的过程。
 */
public class JsonTest8 {

	public static void main(String[] args) throws IOException {
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
		
		//获取输入流
		JsonParser jsonParser = factory.createJsonParser(
				new File("student.json"));
		//JsonToken: 枚举类型 里面有对应的各种状态
		while(jsonParser.nextToken() != JsonToken.END_OBJECT){
			//获取当前的token的值
			String fieldName = jsonParser.getCurrentName();
			if("name".equals(fieldName)){
				//移动到下一个token
				jsonParser.nextToken();
				System.out.println(jsonParser.getText());
			}
			
			if("age".equals(fieldName)){
				jsonParser.nextToken();
				System.out.println(jsonParser.getNumberValue());
			}
			if("verified".equals(fieldName)){
				jsonParser.nextToken();
				System.out.println(jsonParser.getBooleanValue());
			}
			if("marks".equals(fieldName)){
				jsonParser.nextToken();
				//遍历数组
				while(jsonParser.nextToken() != JsonToken.END_ARRAY){
					System.out.println(jsonParser.getNumberValue());
				}
			}
		}
		jsonParser.close(); //关闭输入流
	}
	
}
