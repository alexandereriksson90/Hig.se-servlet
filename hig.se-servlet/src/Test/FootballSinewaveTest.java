package Test;

import java.util.Map;

import domain.DataMatcher;
import domain.DataPair;
import domain.DataSource;
import domain.Resolution;
import InputData.FootballGoalsSource;
import InputData.SineWaveSource;
/**
 * @author Alexander Eriksson nbt12aen
 */
public class FootballSinewaveTest {
	
	
	public static void main(String[] args) {
		DataSource source1 = new FootballGoalsSource();
		DataSource source2 = new SineWaveSource();
		DataMatcher yearDm = new DataMatcher(source1, source2, Resolution.YEAR);
		Map<String, DataPair> yearResult = yearDm.searchDataForMatch().getData();
		DataMatcher monthDm = new DataMatcher(source1, source2, Resolution.MONTH);
		Map<String, DataPair> monthResult = monthDm.searchDataForMatch().getData();
		DataMatcher quarterDm = new DataMatcher(source1, source2, Resolution.QUARTER);
		Map<String, DataPair> quarterResult = quarterDm.searchDataForMatch().getData();

		
		System.out.println("Källa nr1: Fotbollsmål");
		System.out.println(source1.getData());
		System.out.println("Källa nr2: Sinewave");
		System.out.println(source2.getData().toString() + "\n");
		System.out.println("Resolution YEAR: ");
		System.out.println("Antal mål år 2014: " + yearResult.get("2014").getX());
		System.out.println("Sinusvärdet 2014: " + yearResult.get("2014").getY() + "\n");
		
		System.out.println("Resolution MONTH: ");
		System.out.println("Antal mål 2014-03: " + monthResult.get("2014-03").getX());
		System.out.println("Sinusvärdet 2014-03: " + monthResult.get("2014-03").getY());
		System.out.println("Antal mål 2014-04: " + monthResult.get("2014-04").getX());
		System.out.println("Sinusvärdet 2014-04: " + monthResult.get("2014-04").getY() + "\n");
		
		System.out.println("Resolution QUARTER: ");
		System.out.println("Antal mål 2014-Q1: " + quarterResult.get("2014-Q1").getX());
		System.out.println("Sinusvärdet 2014-Q1: " + quarterResult.get("2014-Q1").getY());
		System.out.println("Antal mål 2014-Q2: " + quarterResult.get("2014-Q2").getX());
		System.out.println("Sinusvärdet 2014-Q2: " + quarterResult.get("2014-Q2").getY());
		
	}

}
