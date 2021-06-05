package com.wkk.learn.spring.ioc.i18n;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Description
 * @Author Wangkunkun
 * @Date 2021/6/5 10:35
 */
public class Test {

    public static void main(String[] args) {
        int planet = 7;
        String event = "a disturbance in the Force";
        String pattern = "At {1,time,long} on {1,date,full}, there was {2} on planet {0,number,integer}.";
        MessageFormat format = new MessageFormat(pattern);
        String result = format.format(new Object[]{planet, new Date(), event});
        System.out.println(result);

        // 重置 Locale
        format.setLocale(Locale.ENGLISH);
        // 需要重置 pattern ？
        //format.applyPattern(pattern);
        result = format.format(new Object[]{planet, new Date(), event});
        System.out.println(result);
    }
}
