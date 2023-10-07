// IDUtil.java
package com.hit.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IDUtil {
	public static String generateTenderId(){
        String tid = null;
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); // Use 24-hour format
        tid = sdf.format(new Date());
        tid = "T" + tid;
        
        return tid;
    }

    public static String generateVendorId(String vemail, String vmob) {
        String vid = "V";

        // Extract the first 5 characters of vemail (in uppercase)
        if (vemail.length() >= 5) {
            vid += vemail.substring(0, 5).toUpperCase();
        } else {
            // If vemail has fewer than 5 characters, append it and pad with 'X'
            vid += vemail.toUpperCase() + "XXXXX".substring(0, 5 - vemail.length());
        }

        // Append the first 10 digits of vmob (if available)
        Pattern digitPattern = Pattern.compile("\\d+");
        Matcher matcher = digitPattern.matcher(vmob);
        while (matcher.find() && vid.length() < 16) {
            vid += matcher.group();
        }

        // If vid is still shorter than 16 characters, pad with '0'
        while (vid.length() < 16) {
            vid += "0";
        }

        return vid;
    }




    public static String generateApplicationId(){
        String appId = null;
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); // Use 24-hour format
        appId = sdf.format(new Date());
        appId = "A" + appId;
        
        return appId;
    }

    public static String generateBidderId(){
        String bidderId = null;
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); // Use 24-hour format
        bidderId = sdf.format(new Date());
        bidderId = "B" + bidderId;
        
        return bidderId;
    }
}
