package utils.xcommon.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import utils.xcommon.util.string.StringUtils;

/**
 * 日期处理类
 * @author xuxiaoming
 * @version $Id: DateUtils.java, v 0.1 2016年8月1日 下午4:49:57 xuxiaoming Exp $
 */
public final class DateUtils {
    
    /**
     * 
     *  dow 是一周中的某一天 (Sun, Mon, Tue, Wed, Thu, Fri, Sat)。 
        mon 是月份 (Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec)。 
        dd 是一月中的某一天（01 至 31），显示为两位十进制数。 
        hh 是一天中的小时（00 至 23），显示为两位十进制数。 
        mm 是小时中的分钟（00 至 59），显示为两位十进制数。 
        ss 是分钟中的秒数（00 至 61），显示为两位十进制数。 
        zzz 是时区（并可以反映夏令时）。标准时区缩写包括方法 parse 识别的时区缩写。如果不提供时区信息，则 zzz 为空，即根本不包括任何字符。 
        yyyy 是年份，显示为 4 位十进制数。 
     * 
     */
    /** 日期格式 */
    public static final DateFormat DAY_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final DateFormat SEC_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    public static final DateFormat MIN_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
    
    /** 日期格式  2002-05-11 05:23:22 下午 */
    public static final DateFormat TWE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd KK:mm:ss a");
    
    /** 日期格式  2002-1-1 AD at 22:10:59 PSD*/
    public static final DateFormat GDA_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd G 'at' hh:mm:ss z");
    
    /** 日期格式 Wed Aug 10 16:07:10 CST 2016*/
    public static final DateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",Locale.US);
    
    
    /**
     * 获取当前时间 / 1000
     * @return
     */
    public static int currentTime() {
        return (int) (System.currentTimeMillis() / 1000L);
    }

    public static int formatDate2Timestamp(String formatDateText) {
        if (StringUtils.isNotEmpty(formatDateText))
            try {
                return Long.valueOf(DAY_DATE_FORMAT.parse(formatDateText.trim()).getTime() / 1000L).intValue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        return 0;
    }

    /**
     * 格式化当前时间
     * @return
     */
    public static String currentFomatDate() {
        return SEC_DATE_FORMAT.format(new Date());
    }

    public static String formatDate2TimestampString(String formatDateText) {
        return String.valueOf(formatDate2Timestamp(formatDateText));
    }

    /**
     * 事件加减
     * @param field
     * @param amount
     * @return
     */
    public static String addDate(int field, int amount) {
        int temp = 0;
        if (field == 1)
            temp = 1;
        if (field == 2)
            temp = 2;
        if (field == 3)
            temp = 5;
        String Time = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance(TimeZone.getDefault());
            cal.add(temp, amount);
            Time = sdf.format(cal.getTime());
            return Time;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Date getDayBegin(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(11, 0);
        ca.set(12, 0);
        ca.set(13, 0);
        ca.set(14, 0);
        return ca.getTime();
    }

    public static Date getDayEnd(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(11, 0);
        ca.set(12, 0);
        ca.set(13, 0);
        ca.set(14, 0);
        ca.add(5, 1);
        return ca.getTime();
    }
}