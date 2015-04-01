package InputData;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import InputData.JsonToMapParser;
import InputData.UrlFetcher;
import domain.DataSource;

/**
 * @author Alexander Eriksson nbt12aen
 */
public class HockeyGoalsSource implements DataSource {
	@Override
	public String getName() {
		return "New York Rangers gjorda mål per hemmamatch i NHL";
	}

	@Override
	public String getUnit() {
		return "Antal mål";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Double> getData() {
		UrlFetcher urlFetcher = new UrlFetcher(
				"http://api.everysport.com/v1/events?apikey=1769e0fdbeabd60f479b1dcaff03bf5c&league=68416&limit=547");
		JsonToMapParser parser = new JsonToMapParser(urlFetcher.getContent());
		Map<String, Object> data = parser.getResult();
		Map<String, Double> result = new TreeMap<>();
		for (Map event : (List<Map>) data.get("events")) {
			String date = event.get("startDate").toString().substring(0, 10);
			int goals = Integer.parseInt(event.get("homeTeamScore").toString());
			Map<String, Object> homeTeamMap = (Map<String, Object>) event
					.get("homeTeam");
			if (homeTeamMap.get("name").toString().equals("New York Rangers")) {
				addGoalsToDate(result, date, goals);
			}
		}
		return result;
	}

	private void addGoalsToDate(Map<String, Double> result, String date,
			int goals) {
		if (!result.containsKey(date)) {
			result.put(date, new Double(goals));
		} else {
			result.put(date, result.get(date) + goals);
		}
	}
}
