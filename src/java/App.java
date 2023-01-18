public class App {
    public static void main(String[] args) {
        WebCrawler crawler = new WebCrawler();
        String rootURL = "https://nmap.org/";
        crawler.crawl(rootURL, 200);
    }
}