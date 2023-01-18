import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class WebCrawler {
    private Queue<String> urlQueue;
    private List<String> visitedURLs;
    public WebCrawler() {
        urlQueue = new LinkedList<>();
        visitedURLs = new ArrayList<>();
    }
    public void crawl(String rootURL, int breakpoint){
        urlQueue.add(rootURL);
        visitedURLs.add(rootURL);
        while(!urlQueue.isEmpty()){
            String s = urlQueue.remove();
            String rawHTML = "";
            try{
                URL url = new URL(s);
                BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                String inputLine = in.readLine();
                while(inputLine  != null){
                    rawHTML += inputLine;
                    inputLine = in.readLine();
                }
                in.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            String urlPattern = "(www|http:|https:)+[^\s]+[\\w]";
            Pattern pattern = Pattern.compile(urlPattern);
            Matcher matcher = pattern.matcher(rawHTML);
            breakpoint = getBreakpoint(breakpoint, matcher);
            if(breakpoint == 0){
                break;
            }
        }
    }
    private int getBreakpoint(int breakpoint, Matcher matcher) {
        while(matcher.find()){
            String actualURL = matcher.group();
            if(!visitedURLs.contains(actualURL)){
                visitedURLs.add(actualURL);
                System.out.println("Website found with URL " + actualURL);
                urlQueue.add(actualURL);
            }
            if(breakpoint == 0){
                break;
            }
            breakpoint--;
        }
        return breakpoint;
    }
    public static void main(String[] args) {
        System.out.println(Double.compare(5.0, 6.0));
    }
}