package Test;

import java.util.Map;

import domain.DataMatcher;
import domain.DataPair;
import domain.DataSource;
import domain.Resolution;
import InputData.HockeyGoalsSource;
import InputData.RainVolume;
/**
 * @author Alexander Eriksson nbt12aen
 */
public class HockeyRainVolumeTest {
	
	
	public static void main(String[] args) {
		DataSource source1 = new HockeyGoalsSource();
		DataSource source2 = new RainVolume();
		DataMatcher yearDm = new DataMatcher(source1, source2, Resolution.YEAR);
		Map<String, DataPair> yearResult = yearDm.searchDataForMatch().getData();
		DataMatcher monthDm = new DataMatcher(source1, source2, Resolution.MONTH);
		Map<String, DataPair> monthResult = monthDm.searchDataForMatch().getData();
		DataMatcher quarterDm = new DataMatcher(source1, source2, Resolution.QUARTER);
		Map<String, DataPair> quarterResult = quarterDm.searchDataForMatch().getData();

		
		System.out.println("Källa nr1: New York Rangers mål/hemmamatch");
		System.out.println(source1.getData());
		System.out.println("Källa nr2: RainVolume");
		System.out.println(source2.getData().toString() + "\n");
		System.out.println(yearResult.size());
		System.out.println("Resolution YEAR: ");
		System.out.println("Antal mål år 2014: " + yearResult.get("2014").getX());
		System.out.println("RainVolume 2014: " + yearResult.get("2014").getY() + "\n");
		
		
		System.out.println(monthResult.size());
		System.out.println("Resolution MONTH: ");
		System.out.println("Antal mål 2014-10: " + monthResult.get("2014-10").getX());
		System.out.println("RainVolume 2014-10: " + monthResult.get("2014-10").getY());
		System.out.println("Antal mål 2014-11: " + monthResult.get("2014-11").getX());
		System.out.println("RainVolume 2014-11: " + monthResult.get("2014-11").getY());
		System.out.println("Antal mål 2014-12: " + monthResult.get("2014-12").getX());
		System.out.println("RainVolume 2014-12: " + monthResult.get("2014-12").getY() + "\n");
		
		
		System.out.println(quarterResult.size());
		System.out.println("Resolution QUARTER: ");
		System.out.println("Antal mål 2014-Q4: " + quarterResult.get("2014-Q4").getX());
		System.out.println("RainVolume 2014-Q4: " + quarterResult.get("2014-Q4").getY());
		
		
	}

}
