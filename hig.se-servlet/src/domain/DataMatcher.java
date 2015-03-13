package domain;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alexander Eriksson nbt12aen
 */
public class DataMatcher {
	private Resolution resolution;
	private final DataSource source1;
	private final DataSource source2;
	private Map<String, DataPair> finalResult;

	public DataMatcher(DataSource source1, DataSource source2,
			Resolution resolution) {
		this.source1 = source1;
		this.source2 = source2;
		this.resolution = resolution;
	}

	private boolean matches(String key1, String key2) {
		return resolution.getDateString(key1).equals(
				resolution.getDateString(key2));
	}

	private String getTitle() {
		return source1.getName() + " vs " + source2.getName();
	}

	public ResultingData searchDataForMatch() {
		finalResult = new HashMap<>();
		Map<String, Double> src1 = source1.getData();
		Map<String, Double> src2 = source2.getData();
		for (String key1 : src1.keySet()) {
			for (String key2 : src2.keySet()) {
				if (matches(key1, key2)) {
					if (!finalResult
							.containsKey(resolution.getDateString(key1))) {
						finalResult.put(resolution.getDateString(key1),
								new DataPair(findDoubles(source1, key1),
										findDoubles(source2, key2)));
					}
				}
			}
		}
		ResultingData resultData = new ResultingData(getTitle(),
				source1.getUnit(), source2.getUnit(), finalResult);
		return resultData;
	}

	public Double findDoubles(DataSource src, String date) {
		Double value = 0.0;
		int count = 0;
		for (String key1 : src.getData().keySet()) {
			if (matches(key1, date)) {
				value += src.getData().get(key1).doubleValue();
				count++;

			}
		}
		value = value / count;
		return value;
	}

}
