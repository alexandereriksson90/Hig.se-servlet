
package domain;

import java.util.Map;
/**
 * @author Alexander Eriksson nbt12aen
 */
public class ResultingData  {
	private final Map<String, DataPair> data;
	private final String title;
	private final String yUnit;
	private final String xUnit;
	
	public ResultingData(String title, String xUnit, String yUnit, Map<String,DataPair> data) {
		this.xUnit = xUnit;
		this.yUnit = yUnit;
		this.title = title;
		this.data = data;
	}
	
	public String getXUnit() {
		return xUnit;
		
	}
	public String getYUnit() {
		return yUnit;
		
	}
	public String getTitle() {
		return title;
	}
	public Map<String, DataPair> getData() {
		return data;
	}
}
