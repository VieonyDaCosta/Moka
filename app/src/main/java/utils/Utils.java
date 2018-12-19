package utils;

import android.text.TextUtils;

public class Utils {

    public static int convertStringToInt(String value){
        int val = 0;

        if(TextUtils.isEmpty(value)){
            return val;
        }
        try {
            val = Integer.parseInt(value);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        return val;
    }
}
