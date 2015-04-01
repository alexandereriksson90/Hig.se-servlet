package InputData;

import domain.DataSource;

public class DataSourceFactory {
    
    public static DataSource getDataSource(String id) {
        try {
            return (DataSource) Class.forName("InputData." + id + "Source").newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }
}