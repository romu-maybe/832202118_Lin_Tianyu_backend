package com.easytest.utils;

import com.easytest.entity.enums.VerifyRegexEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName VerifyUtils
 * @Description TODO
 * @Author Administrator
 * @Date 2023/8/30 21:59
 */
public class VerifyUtils {

    public static boolean verify(String regex,String value){
        if(StringTools.isEmpty(value)){
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public static boolean verify(VerifyRegexEnum regexEnum,String value){
        return verify(regexEnum.getRegex(),value);
    }
}
