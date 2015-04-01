package Test;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import InputData.JsonToMapParser;
import InputData.UrlFetcher;

/**
 * @author Alexander Eriksson nbt12aen
 */
public class HockeyGoalsTest {

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public static void main(String[] args) {
		UrlFetcher urlFetcher = new UrlFetcher(
				"http://api.everysport.com/v1/events?apikey=1769e0fdbeabd60f479b1dcaff03bf5c&league=68416&limit=547");
		JsonToMapParser parser = new JsonToMapParser(urlFetcher.getContent());
		Map<String, Object> data = parser.getResult();
		List<Map> events = (List) data.get("events");
		Map<String, Double> result = new TreeMap<>();

		List<Map> homeTeamGames = (List) data.get("events");
		for (Map event : (List<Map>) data.get("events")) {
			String date = event.get("startDate").toString().substring(0, 10);
			int goals = Integer.parseInt(event.get("homeTeamScore").toString());
			Map<String, Object> hej = (Map<String, Object>) event
					.get("homeTeam");
			if (hej.get("name").toString().equals("New York Rangers")) {
				System.out.println("New York Rangers mål gjorda hemma: " + date
						+ " Mål: " + goals);
			}

		}

	}

}
