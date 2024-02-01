
import com.github.sisyphsu.dateparser.DateParserUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DateConvertor {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    public String convertDate(String from) throws DateTimeParseException {
        Date date = DateParserUtils.parseDate(from);
        DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        return formatter.format(date);
    }
}
