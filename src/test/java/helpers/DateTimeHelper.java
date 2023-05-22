package helpers;

import org.json.simple.JSONObject;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateTimeHelper {

    public static String getCurrentDateTime() {
        String dateformat = "yyyy-MM-dd\'T\'HH:mm:ss.sss\'Z\'";
        Calendar cal = getYear();
        SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
        return "" + sdf.format(cal.getTime());
    }

    public static Calendar getYear() {
        Date d = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return c;
    }

    public static long getCurrentEpocTime() {
        long currentTimestamp = Instant.now().toEpochMilli();
        return currentTimestamp;
    }

    public static String getCurrentDate() {
        String dateformat = "dd/MM/yyyy";
        Calendar cal = getYear();
        SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
        return "" + sdf.format(cal.getTime());
    }

    public static JSONObject getDateListForDeliverySlot() {
        int lastDeliveryHourWeekDay = 22;
        int lastDeliveryHourWeekend = 24;
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Format dayFormat = new SimpleDateFormat("EEEE");
        LocalDateTime now = LocalDateTime.now();
        String currentDayName = dayFormat.format(new Date());
        int currentHour = now.getHour();
        int currentMinute = now.getMinute();
        boolean todayDelivery = true;
        if (currentDayName.equals("Saturday") || currentDayName.equals("Sunday")) {
            if (currentHour == (lastDeliveryHourWeekend - 1) && currentMinute == 59) {
                todayDelivery = false;
            }
        } else {
            if (currentHour == (lastDeliveryHourWeekDay - 1) && currentMinute == 59) {
                todayDelivery = false;
            }
        }
        JSONObject jo = new JSONObject();
        jo.put("currentDay", df.format(now));
        jo.put("nextDay", df.format(now.plusDays(1)));
        jo.put("lastDay", df.format(now.plusDays(2)));
        jo.put("todayDelivery", todayDelivery);
        return jo;
    }
}

