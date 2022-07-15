public class MainPage {
    public static void main(String[] args) {
        System.out.println(reformatArticleByLength("Volvo released a new car with the following spec: V6 236HP. It will cost $22647 and going to be sold in New York only"));
    }

    public static String reformatArticleByLength(String article) {
        int maxsize = 25;
        if (article ==null || article.isEmpty()) {
            throw new NullPointerException("Article should not be blank");
        }
        if (article.length() > maxsize) {
                try {
                    int lastWordIndex = article.lastIndexOf(" ", maxsize);
                    article = article.substring(0, lastWordIndex) + "...";
                }
                catch (StringIndexOutOfBoundsException e) {
                    throw new StringIndexOutOfBoundsException("The article cannot be shorted with full world. " +
                            "Because first world is longet then 25");
                }
        }
        return article;
    }
}
