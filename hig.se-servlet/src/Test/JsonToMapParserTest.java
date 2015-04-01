package Test;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import InputData.JsonToMapParser;
import InputData.UrlFetcher;
/**
 * @author Alexander Eriksson nbt12aen
 */
public class JsonToMapParserTest {
	private JsonToMapParser instance;
	private String urlFetcher;

	@Before
	public void setUp() throws Exception {
		urlFetcher = new UrlFetcher(
				"http://api.everysport.com/v1/events?apikey=1769e0fdbeabd60f479b1dcaff03bf5c&league=63925&limit=2")
				.getContent();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetEmptyResult() {
		instance = new JsonToMapParser("{}");
		assertEquals(instance.getResult().size(), 0);
	}

	@Test
	public void testGetSingleResult() {
		Map<String, Object> resultingMap = new JsonToMapParser(
				"{ \"key\": \"value\" }").getResult();
		assertEquals(resultingMap.size(), 1);
		assertEquals("value", resultingMap.get("key"));
	}

	@Test
	public void testurlFetcherResult() {
		Map<String, Object> resultingMap = new JsonToMapParser(urlFetcher)
				.getResult();
		System.out.println("Hela listan: "+resultingMap+"\n");
		System.out.println("Hashnycklarna: " + resultingMap.keySet()+"\n");
		System.out.println("Listan ur events"+resultingMap.get("events")+"\n");
		assertEquals(resultingMap.size(), 3);

	}

}
