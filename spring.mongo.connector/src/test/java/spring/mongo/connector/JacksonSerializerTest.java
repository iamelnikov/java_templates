package spring.mongo.connector;

import java.io.IOException;
import java.util.UUID;

import org.junit.Test;

import spring.mongo.connector.domain.Company;
import spring.mongo.connector.domain.reference.Industry;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonSerializerTest {

	@Test
	public void serializeObjectTest() {
		ObjectMapper om = new ObjectMapper();
		Industry ind = new Industry(UUID.randomUUID().toString(), "val");
		Company cmp = new Company(UUID.randomUUID().toString(), "friendlyUrl", "FullName",
				"ShortName", null, null, null, null,
				"example.com", ind);
		try {
			om.writeValue(System.out, cmp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
