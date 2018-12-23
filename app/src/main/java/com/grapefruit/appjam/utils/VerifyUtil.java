package com.grapefruit.appjam.utils;

import android.widget.EditText;

public class VerifyUtil {

    public static boolean verifyString(String str) {
        if (str == null || str.length() == 0) return false;
        return true;
    }

    public static boolean verifyStrings(String... ss) {
        for (int ti = 0; ti < ss.length; ti++) {
            if (!verifyString(ss[ti])) return false;
        }
        return true;
    }

    public static boolean verifyStringsFromEditText(EditText... edts) {
        for (int ti = 0; ti < edts.length; ti++) {
            if (!verifyString(edts[ti].getText().toString())) return false;
        }
        return true;
    }
}
