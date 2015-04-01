
package Test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.DataMatcher;
import domain.DataPair;
import domain.DataSource;
import domain.Resolution;
import domain.ResultingData;
/**
 * @author Alexander Eriksson nbt12aen
 */
public class DataMatcherTest {
	private DataSource source1, source2;
	private DataMatcher dm;

	@Before
	public void setUp() throws Exception {
		source1 = new DataSource() {

			@Override
			public String getUnit() {
				return "Enhet 1";
			}

			@Override
			public String getName() {
				return "Data 1";
			}

			@Override
			public Map<String, Double> getData() {
				Map<String, Double> result = new HashMap<>();
				result.put("2015-11-23", 7.0);
				result.put("1994-02-05", 8.0);
				result.put("1994-01-23", 10.0);
				return result;
			}
		};
		source2 = new DataSource() {

			@Override
			public String getUnit() {
				return "Enhet 2";
			}

			@Override
			public String getName() {
				return "Data 2";
			}

			@Override
			public Map<String, Double> getData() {
				Map<String, Double> result = new HashMap<>();
				result.put("2015-11-23", 13.0);
				result.put("1994-01-07", 5.0);
				result.put("1994-01-05", 11.0);
				result.put("1995-04-12", 11.0);
				return result;
			}
		};
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testResultingData() {
		dm = new DataMatcher(source1, source2, Resolution.DAY);
		ResultingData rsd = dm.searchDataForMatch();
		assertEquals(rsd.getTitle(), "Data 1 vs Data 2");
		assertEquals(rsd.getXUnit(), "Enhet 1");
		assertEquals(rsd.getYUnit(), "Enhet 2");
	}

	@Test
	public void testDay() {
		dm = new DataMatcher(source1, source2, Resolution.DAY);
		Map<String, DataPair> result = dm.searchDataForMatch().getData();
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(new Double(7.0), result.get("2015-11-23").getX());
		assertEquals(new Double(13.0), result.get("2015-11-23").getY());
	}

	@Test
	public void testMonth() {
		dm = new DataMatcher(source1, source2, Resolution.MONTH);
		Map<String, DataPair> result = dm.searchDataForMatch().getData();
		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals(new Double(7.0), result.get("2015-11").getX());
		assertEquals(new Double(13.0), result.get("2015-11").getY());
		assertEquals(new Double(10.0), result.get("1994-01").getX());
		assertEquals(new Double(8.0), result.get("1994-01").getY());
	}

	@Test
	public void testYearAndDoubleData() {
		dm = new DataMatcher(source1, source2, Resolution.YEAR);
		Map<String, DataPair> result = dm.searchDataForMatch().getData();
		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals(new Double(7.0), result.get("2015").getX());
		assertEquals(new Double(13.0), result.get("2015").getY());
		assertEquals(new Double(9.0), result.get("1994").getX());
		assertEquals(new Double(8.0), result.get("1994").getY());
	}

	@Test
	public void testQuarterAndDouble() {
		dm = new DataMatcher(source1, source2, Resolution.QUARTER);
		Map<String, DataPair> result = dm.searchDataForMatch().getData();
		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals(new Double(9.0), result.get("1994-Q1").getX());
		assertEquals(new Double(8.0), result.get("1994-Q1").getY());
		assertEquals(new Double(7.0), result.get("2015-Q4").getX());
		assertEquals(new Double(13.0), result.get("2015-Q4").getY());
	}

	@Test
	public void testWeek() {
		dm = new DataMatcher(source1, source2, Resolution.WEEK);
		Map<String, DataPair> result = dm.searchDataForMatch().getData();
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(new Double(7.0), result.get("2015-W48").getX());
		assertEquals(new Double(13.0), result.get("2015-W48").getY());
	}

}
