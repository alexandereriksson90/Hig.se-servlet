package InputData;

import java.util.Map;
import java.util.TreeMap;

import domain.DataSource;

public class RainVolumeSource implements DataSource {
	@Override
	public String getName() {
		return "RainVolume";
	}

	@Override
	public String getUnit() {
		return "Mm";
	}

	@Override
	public Map<String, Double> getData() {
		Map<String, Double> result = new TreeMap<>();
		for (int year = 2014; year < 2015; year++) {
			for (int month = 1; month < 13; month++) {
				String s;
				if(month < 10) {
					s = new String(year+"-0"+month+"-01");
				} else {
					s = new String(year+"-"+month+"-01");
				}
				result.put(s, 7.0);
				
			}
		}
		return result;
	}

}
