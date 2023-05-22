package helpers;

import com.google.common.base.Splitter;
import jakarta.xml.bind.DatatypeConverter;
import net.minidev.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Utils {

    public static void waitFor(long seconds) {
        try {
            System.out.println("Blocking thread for " + seconds + " seconds.");
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static JSONObject getHeader(String args) {
        String uri = args;
        String query = uri.split("\\?")[1];
        Map<String, String> map = Splitter.on('&').trimResults().withKeyValueSeparator('=').split(query);
        JSONObject json = new JSONObject();
        json.put("token", map.get("token"));
        json.put("iframe", map.get("iframe"));
        json.put("hideFooter", map.get("hideFooter"));
        return json;
    }

    public static String decodeHtmlContent(String args) {
        String originalInput = args;
        byte[] decodedBytes = DatatypeConverter.parseBase64Binary(originalInput);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }

    public static String convertDecimalFormat(String str) {
        String returnAmount;
        String amount = str.substring(0, str.length() - 2);
        String amountWithDecimal = str.substring(str.length() - 2);
        if (amountWithDecimal.equals("00"))
            returnAmount = amount;
        else {
            returnAmount = (amount + "." + amountWithDecimal);
        }
        return returnAmount;
    }

    public static String removeUrlSlash(String args) {
        String uri = args;
        String result = uri.replaceAll("\\\\", "");
        return result;
    }
}
