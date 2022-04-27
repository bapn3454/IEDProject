import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.*;

public class OpenMovieClient {

    private final String URL = "http://www.omdbapi.com/?apikey=6320eeae&r=xml";

    public Film getFilmData(String title) throws XPathExpressionException, ParserConfigurationException, IOException, SAXException {
        // TODO récuperer les autres données, notamment la date pour pouvoir croiser avec la date déjà presente ene locale
        String resume = xPath(title, "/root/movie/@plot", XPathConstants.STRING);
        String year = xPath(title, "/root/movie/@year", XPathConstants.STRING);
        String retrievedTitle = xPath(title, "/root/movie/@title", XPathConstants.STRING);
        return new Film(retrievedTitle, year, null, null, null, null, null, resume);
    }

    public String xPath(String title, String requete, QName typeRetour) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
        DocumentBuilder parseur = fabrique.newDocumentBuilder();
        Document document = parseur.parse(URL.concat( "&t=".concat( title ) ));

            printDocument(document, new FileOutputStream(new File("moviesdb.xml")));

        //création de l'objet XPath
        XPathFactory xfabrique = XPathFactory.newInstance();
        XPath xpath = xfabrique.newXPath();

        //évaluation de l'expression XPath
        XPathExpression exp = xpath.compile(requete);
        return (String) exp.evaluate(document, typeRetour);
    }

    public static void printDocument(Document doc, OutputStream out) throws IOException {
        try {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        transformer.transform(new DOMSource(doc),
                new StreamResult(new OutputStreamWriter(out, "UTF-8")));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

}
