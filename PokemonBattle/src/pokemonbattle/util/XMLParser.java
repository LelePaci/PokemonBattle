package pokemonbattle.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLParser {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        //XML POKEMON
        String POKEMON = "<pokemon>"
                + "<nome>Charizard</nome>"
                + "<vita>300</vita>"
                + "<tipi>"
                + "<tipo>Fuoco</tipo>"
                + "<tipo>Volante</tipo>"
                + "</tipi>"
                + "<debolezze>"
                + "<debolezza>Acqua</debolezza>"
                + "<debolezza>Elettro</debolezza>"
                + "<debolezza>Roccia</debolezza>"
                + "</debolezze>"
                + "<immagineFront>res/pokedex/images/charizard-front.png</immagineFront>"
                + "<immagineBack>res/pokedex/images/charizard-back.png</immagineBack>"
                + "<mosse>"
                + "<mossa>"
                + "<nome>Graffio</nome>"
                + "<tipo>Normale</tipo>"
                + "<danni>15</danni>"
                + "<utilizziMax>35</utilizziMax>"
                + "</mossa>"
                + "<mossa>"
                + "<nome>Braciere</nome>"
                + "<tipo>Fuoco</tipo>"
                + "<danni>20</danni>"
                + "<utilizziMax>40</utilizziMax>"
                + "</mossa>"
                + "<mossa>"
                + "<nome>Lanciafiamme</nome>"
                + "<tipo>Fuoco</tipo>"
                + "<danni>30</danni>"
                + "<utilizziMax>15</utilizziMax>"
                + "</mossa>"
                + "<mossa>"
                + "<nome>Dragospiro</nome>"
                + "<tipo>Drago</tipo>"
                + "<danni>30</danni>"
                + "<utilizziMax>20</utilizziMax>"
                + "</mossa>"
                + "</mosse>"
                + "</pokemon>";

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        InputSource is = new InputSource(new StringReader(POKEMON));
        Document doc;
        doc = (Document) dBuilder.parse(is);
        doc.getDocumentElement().normalize();

        //POKEMON
        String nome = doc.getElementsByTagName("nome").item(0).getTextContent();
        String vita = doc.getElementsByTagName("vita").item(0).getTextContent();

        System.out.println("Nome: " + nome);
        System.out.println("Vita: " + vita);
        int ContaTipi = 0;
        NodeList listTipi = doc.getElementsByTagName("tipi");
        for (int i = 0; i < listTipi.getLength(); i++) {
            NodeList childList = listTipi.item(i).getChildNodes();
            for (int j = 0; j < childList.getLength(); j++) {
                Node childNode = childList.item(j);
                if ("tipo".equals(childNode.getNodeName())) {
                    ContaTipi++;
                }
            }
        }
        System.out.println("TIPI:");
        for (int i = 0; i < ContaTipi; i++) {
            NodeList TIPI = listTipi.item(0).getChildNodes();
            NodeList TIPO = TIPI.item(i).getChildNodes();
            Node childNode = TIPO.item(0);
            System.out.println(childNode.getTextContent());

        }
        //DEBOLEZZE
        System.out.println("DEBOLEZZE:");
        int ContaDebolezze = 0;
        NodeList listDebolezze = doc.getElementsByTagName("debolezze");
        for (int i = 0; i < listDebolezze.getLength(); i++) {
            NodeList childList = listDebolezze.item(i).getChildNodes();
            for (int j = 0; j < childList.getLength(); j++) {
                Node childNode = childList.item(j);
                if ("debolezza".equals(childNode.getNodeName())) {
                    ContaDebolezze++;
                }
            }
        }
        for (int i = 0; i < ContaDebolezze; i++) {
            NodeList TIPI = listDebolezze.item(0).getChildNodes();
            NodeList TIPO = TIPI.item(i).getChildNodes();
            Node childNode = TIPO.item(0);
            System.out.println(childNode.getTextContent());

        }

        //MOSSE
        System.out.println("MOSSE:");
        int ContaMosse = 0;
        NodeList listMosse = doc.getElementsByTagName("mosse");
        for (int i = 0; i < listMosse.getLength(); i++) {
            NodeList childList = listMosse.item(i).getChildNodes();
            for (int j = 0; j < childList.getLength(); j++) {
                Node childNode = childList.item(j);
                if ("mossa".equals(childNode.getNodeName())) {
                    ContaMosse++;
                }
            }
        }
        for (int i = 0; i < ContaMosse; i++) {
            NodeList TIPI = listMosse.item(0).getChildNodes();
            NodeList TIPO = TIPI.item(i).getChildNodes();
            Node childNode = TIPO.item(0);
            System.out.println(childNode.getTextContent());

        }
    }

}
