
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

	public static void main(String[] args) throws ParseException {
		String yourString = "2022-02-22 16:04:44.444";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
	    Date parsedDate = dateFormat.parse(yourString);
	    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
	    
	    System.out.println(timestamp);
	    System.out.println(parsedDate);
	    System.out.println(dateFormat.format(parsedDate));
	    System.out.println(dateFormat.format(timestamp));
	    
	}
}
