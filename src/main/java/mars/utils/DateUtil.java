package mars.utils;

import org.apache.commons.lang3.time.FastDateFormat;
import org.joda.time.DateTime;

public class DateUtil {

    private static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";
    private static final String DATE_TIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static final FastDateFormat DATE_FORMAT = FastDateFormat.getInstance(DATE_FORMAT_PATTERN);
    private static final FastDateFormat DATE_TIME_FORMAT = FastDateFormat.getInstance(DATE_TIME_FORMAT_PATTERN);

    public static String formatDate(long millis) {
        return DATE_FORMAT.format(millis);
    }

    public static String formatDateTime(long millis) {
        return DATE_TIME_FORMAT.format(millis);
    }

    /**
     * 获取某一天的第一毫秒
     */
    public static long getDayFirstMillis(int diff) {
        return getDayFirstMillis(DateTime.now().plus(diff));
    }

    /**
     * 获取某一天的最后一毫秒
     */
    public static long getDayLastMillis(int diff) {
        return getDayLastMillis(DateTime.now().plus(diff));
    }

    /**
     * 获取某一天的第一毫秒
     */
    public static long getDayFirstMillis(DateTime day) {
        return day.withTimeAtStartOfDay().getMillis();
    }

    /**
     * 获取某一天的最后一毫秒
     */
    public static long getDayLastMillis(DateTime day) {
        return day.plusDays(1).withTimeAtStartOfDay().getMillis() - 1;
    }

}
