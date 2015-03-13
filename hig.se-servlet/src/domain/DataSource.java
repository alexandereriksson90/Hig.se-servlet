package domain;

import java.util.Map;

/**
 * @author Alexander Eriksson nbt12aen
 */
public interface DataSource {
	
	String getName();
	
	String getUnit();
	
	Map<String, Double> getData();

}

