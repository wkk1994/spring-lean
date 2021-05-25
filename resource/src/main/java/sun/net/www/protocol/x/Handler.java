package sun.net.www.protocol.x;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/**
 * @Description 自定义Java协议扩展，支持x://开头的协议
 * @Author Wangkunkun
 * @Date 2021/5/26 20:54
 */
public class Handler extends URLStreamHandler {

    @Override
    protected URLConnection openConnection(URL url) throws IOException {
        return new XURLConnection(url);
    }
}
