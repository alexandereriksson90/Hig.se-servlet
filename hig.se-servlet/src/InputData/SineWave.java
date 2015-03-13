package InputData;

import java.util.Map;
import java.util.TreeMap;

import domain.DataSource;

/**
 * @author Alexander Eriksson nbt12aen
 */
public class SineWave implements DataSource {

	@Override
	public String getName() {
		return "Sinewave";
	}

	@Override
	public String getUnit() {
		return "undefinedForSinewave";
	}

	@Override
	public Map<String, Double> getData() {
		Map<String, Double> result = new TreeMap<>();
		for (int year = 2012; year < 2015; year++) {
			for (int month = 1; month < 13; month++) {
//				LocalDate key = LocalDate.of(year, month, 1);
				String s;
				if(month < 10) {
					s = new String(year+"-0"+month+"-01");
				} else {
					s = new String(year+"-"+month+"-01");
				}
				result.put(s, Math.sin(2));
			}
		}
		return result;
	}

}