import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
public class Main {
    public static final String SITE = "https://www.flipkart.com/search?q=watches&otracker=search&otracker1=search&marketplace=FLIPKART&as-show=on&as=off";
    public static void main(String[] args) {
        crawl(1, SITE, new ArrayList<>());
    }
    private static void crawl (int level, String url, ArrayList<String> visited) {
        if(level <=10 ) {
            Document doc = request(url, visited);
            if (doc!= null) {
//                for (Element link : doc.select("a[href]")) {
//                    String next_link = link.absUrl("href");
//                    if(!visited.contains(next_link)) {
//                        crawl(level++, next_link, visited);
//                    }
//                }
                Elements elements=doc.getElementsByClass("_1xHGtK _373qXS");
                for(Element element : elements){
                    String img = element.select("img").attr("src");
                    String title=element.getElementsByClass("IRpwTa").attr("title");
                    String price=element.getElementsByClass("_30jeq3").text();
                    String orgPrice=element.getElementsByClass("_3I9_wc").text();
                    String offer=element.getElementsByClass("_3Ay6Sb").text();
                    System.out.println();
                    System.out.println("------------------------");
                    System.out.println("Title : "+title);
                    System.out.println("Original Price : "+orgPrice);
                    System.out.println("Discount : "+offer);
                    System.out.println("Final Price : "+price);
                    System.out.println("Image : "+img);
                    System.out.println("------------------------");
                    System.out.println();
                }

            }
        }
    }
    private static Document request(String url, ArrayList<String> v) {
        try {
            Connection con = Jsoup.connect(url);
            Document doc = con.get();
            if(con.response().statusCode() == 200) {
                System.out.println("Link: " + url);
                System.out.println(doc.title());
                v.add(url);
                return doc;
            }
            return null;
        } catch (IOException e) {
            return null;
        }
    }
}