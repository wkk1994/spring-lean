package com.wkk.learn.spring.ioc.i18n;


import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Description {@link MessageFormat} 使用示例
 * @Author Wangkunkun
 * @Date 2021/6/5 10:26
 * @see MessageFormat
 */
public class MessageFormatDemo {

    public static void main(String[] args) {
        int planet = 7;
        String event = "a disturbance in the Force";
        String messageFormatPattern = "At {1,time,long} on {1,date,full}, there was {2} on planet {0,number,integer}.";
        MessageFormat messageFormat = new MessageFormat(messageFormatPattern);
        String result = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println(result);

        // 重置消息格式 Pattern
        messageFormat.applyPattern("This is a Text {0}, {1}, {2}");
        result = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println(result);

        // 重置locale
        messageFormat.setLocale(Locale.ENGLISH);
        // 必须要重置Pattern，新设置的Locale才能生效
        // MesaageForamt 类定义了一个 Format[] formats 数组用于保存当前 pattern 格式化时所使用的 java.text.Format 实例.
        // 而这个数组的赋值在 java.text.MessageFormat#makeFormat(） 方法中， 但这个方法的唯一调用者是java.text.MessageFormat#applyPattern()方法。
        // 所以当调用applyPattern()方法格式化新的文案，这个方法会根据Locale变量解析好Format并保存在formats 中，当再这个方法之后再调用setLocale()方法更改地区，
        // 是不会触发 formats 数组重新赋值的， 只有等到再次调用 applyPattern()时， 设置的 Locale才会生效。
        // 不过如果定义的文本没有format，会根据参数的类型进行格式化，这样新设置的locale会生效
        //messageFormatPattern = "At {1,time,long} on {1,date,full}, there was {2} on planet {0,number,integer}.";
        //messageFormat.applyPattern(messageFormatPattern);
        result = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println(result);

        // 重置Format
        // 根据参数索引来设置Pattern
        messageFormat.setFormat(1, new SimpleDateFormat("YYYY-MM-dd"));
        result = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println(result);
    }
}
