package domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * @author Alexander Eriksson nbt12aen
 */
public enum Resolution {

	DAY {
		@Override
		public String getDateString(String key) {
			return key;
		}

	},
	YEAR {
		@Override
		public String getDateString(String key) {
			return key.substring(0,4);

		}

	},
	MONTH {
		@Override
		public String getDateString(String key) {
			return key.substring(0,7);

		}

	},
	QUARTER {
		@Override
		public String getDateString(String key) { 
			String s = key.substring(5,7);
			int st = Integer.parseInt(s);
			return ("" + key.substring(0,4) + "-Q" + (st + 2) / 3);
		}

	},
	WEEK {
		@Override
		public String getDateString(String key) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = sdf.parse(key);
			} catch (ParseException e) {
				e.printStackTrace();
			}
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        int week = cal.get(Calendar.WEEK_OF_YEAR);
			return (key.substring(0,4) + "-W" + week);
		}
	};
	public abstract String getDateString(String key);
}
