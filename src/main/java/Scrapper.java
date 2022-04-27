import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Scrapper {

    public String addSeparator(String[] film) {
        return Stream.of(film)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining("\t"));
    }

    public void createCsvFile(List<String[]> data, String fileName) throws IOException {
        File csvOutputFile = new File(fileName.concat(".csv"));
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            for ( int i = 0; i < data.size(); i++ ) {
                String filmLine = addSeparator( data.get(i) );
                pw.println(filmLine);
            }
        }
    }

    public String escapeSpecialCharacters(Object data) {
        String strData = (String)data;
        String escapedData = strData.replaceAll("\\R", " ");
        if (strData.contains(",") || strData.contains("\"") || strData.contains("'")) {
            strData = strData.replace("\"", "\"\"");
            escapedData = "\"" + strData + "\"";
        }
        return escapedData;
    }

    public void retrieveTheNumbersData() throws IOException {
        // http://www.the-numbers.com/market/<année>/genre/<Genre>
        String[] genre = new String[]{"Adventure", "Comedy", "Drama", "Action", "Thriller-or-Suspense", "Romantic-Comedy"};
        for ( String str : genre ) {
            List data = new ArrayList();
            for ( int i = 2000; i < 2016; i++ ) {
                String url = "http://www.the-numbers.com/market/".concat( i + "/genre/" + str);
                Document doc = Jsoup.connect(url).get();
//                Elements elements = doc.select("tr");
//                for ( Element elt : elements ) {
//                    if ( elements.indexOf(elt) < 2 || elements.indexOf(elt) > elements.size() - 3 )
//                        continue;
//                    Elements f = elt.select("td");
//                    String title = f.get(1).text();
//                    String distributor = f.get(3).text();
//                    String[] film = new String[]{title, distributor, str};
//                    data.add(film);
//                }
                Elements elements = doc.select("html body div#wrap div#main div#page_filling_chart table tbody tr");
                Elements elementsTitle = doc.select("html body div#wrap div#main div#page_filling_chart table tbody tr b a");
                for ( int k = 2; k < elements.size() - 3; k++ ) {
                    Element ligne = elements.get(k);
                    String title = elementsTitle.get(k).text();
                    String distributor = ligne.child(3).text();
                    String[] film = new String[]{title, distributor, str};
                    data.add(film);
                }

//                    data.add(film);
            }
            this.createCsvFile(data, str);
        }
    }

}
