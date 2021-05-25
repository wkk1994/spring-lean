package sun.net.www.protocol.x;

import org.springframework.util.StreamUtils;

import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * @Description 自定义Java资源扩展测试
 * @Author Wangkunkun
 * @Date 2021/5/26 21:00
 * @see Handler
 * @see XURLConnection
 */
public class XHandlerTest {

    public static void main(String[] args) throws Exception {
        URL url = new URL("x:///META-INF/default.properties"); // 类似于 classpath:/META-INF/default.properties
        InputStream inputStream = url.openStream();
        System.out.println(StreamUtils.copyToString(inputStream, Charset.forName("UTF-8")));
    }
}
