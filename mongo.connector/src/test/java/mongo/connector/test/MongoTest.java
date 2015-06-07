package mongo.connector.test;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import mongo.connector.impl.DemoCollectionDocument;
import mongo.connector.impl.DemoCollectionMongoCRUD;

import org.apache.commons.lang3.RandomStringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MongoTest {

	@Test
	public void test1Save() {
		DemoCollectionMongoCRUD crud = new DemoCollectionMongoCRUD();
		for (int i = 0; i < 50; i++) {
			DemoCollectionDocument doc = new DemoCollectionDocument(UUID
					.randomUUID().toString(), new Date(),
					RandomStringUtils.random(300), "ilya", false);
			crud.saveDemoCollectionDocument(doc);
		}
	}

	@Test
	public void test2Query() {
		DemoCollectionMongoCRUD crud = new DemoCollectionMongoCRUD();
		Set<DemoCollectionDocument> demoCollectionDocuments = crud.findObjects(
				null, null, null, "ilya");
		ObjectMapper om = new ObjectMapper();
		try {
			if (demoCollectionDocuments != null
					&& !demoCollectionDocuments.isEmpty()) {
				for (DemoCollectionDocument doc : demoCollectionDocuments) {
					System.out.println(om.writeValueAsString(doc));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
