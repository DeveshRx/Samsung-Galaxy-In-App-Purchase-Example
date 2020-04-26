package com.samsung.android.sdk.iap.lib.vo.util;

import android.text.format.DateFormat;

public class VoUtil {
    public static String getDateString(long _timeMills) {
        String result = "";
        String dateFormat = "yyyy.MM.dd HH:mm:ss";

        try {
            result = DateFormat.format(dateFormat, _timeMills).toString();
        } catch (Exception e) {
            e.printStackTrace();
            result = "";
        }

        return result;
    }
}
