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
import pokemonbattle.objects.Mossa;
import pokemonbattle.objects.Pokemon;

public class XMLParser {

    private Document doc;
    private DocumentBuilder dBuilder;
    private String xml = "";

    public XMLParser() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Pokemon getPokemon(String toParse) {
        Pokemon pokemon = null;
        try {
            InputSource is = new InputSource(new StringReader(toParse));
            doc = (Document) dBuilder.parse(is);
            doc.getDocumentElement().normalize();

            String nome = doc.getElementsByTagName("nome").item(0).getTextContent();
            String vita = doc.getElementsByTagName("vita").item(0).getTextContent();
            String immagineFront = doc.getElementsByTagName("immagineFront").item(0).getTextContent();
            String immagineBack = doc.getElementsByTagName("immagineBack").item(0).getTextContent();
            //TIPI
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
            String[] tipi = new String[ContaTipi];
            int IndiceTipo = 0;
            for (int i = 0; i < listTipi.getLength(); i++) {
                NodeList childList = listTipi.item(i).getChildNodes();
                for (int j = 0; j < childList.getLength(); j++) {
                    Node childNode = childList.item(j);
                    if ("tipo".equals(childNode.getNodeName())) {
                        tipi[IndiceTipo] = childNode.getTextContent();
                        IndiceTipo++;
                    }
                }
            }
            //DEBOLEZZE
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
            String[] debolezze = new String[ContaDebolezze];
            int IndiceDebolezza = 0;
            for (int i = 0; i < listDebolezze.getLength(); i++) {
                NodeList childList = listDebolezze.item(i).getChildNodes();
                for (int j = 0; j < childList.getLength(); j++) {
                    Node childNode = childList.item(j);
                    if ("debolezza".equals(childNode.getNodeName())) {
                        debolezze[IndiceDebolezza] = childNode.getTextContent();
                        IndiceDebolezza++;
                    }
                }
            }
            //MOSSE
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
            Mossa[] mosse = new Mossa[ContaMosse];
            int IndiceMossa = 0;
            for (int i = 0; i < listMosse.getLength(); i++) {
                NodeList childList = listMosse.item(i).getChildNodes();

                for (int j = 0; j < ContaMosse; j++) {
                    Node childNode = childList.item(j * 2 + 1);
                    if ("mossa".equals(childNode.getNodeName())) {
                        Element e = (Element) childNode;
                        String nomeM = e.getElementsByTagName("nome").item(0).getTextContent();
                        String tipo = e.getElementsByTagName("tipo").item(0).getTextContent();
                        String danni = e.getElementsByTagName("danni").item(0).getTextContent();
                        String utilizziMax = e.getElementsByTagName("utilizziMax").item(0).getTextContent();
                        mosse[IndiceMossa] = new Mossa(nomeM, tipo, Integer.parseInt(danni), Integer.parseInt(utilizziMax));
                    }
                    IndiceMossa++;
                }
            }
            pokemon = new Pokemon(nome, Integer.parseInt(vita), immagineFront, immagineBack, tipi, debolezze, mosse);

        } catch (SAXException | IOException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pokemon;
    }

    public void parseString(String xml) {
        try {
            this.xml = xml;
            InputSource is = new InputSource(new StringReader(this.xml));
            doc = (Document) dBuilder.parse(is);
            doc.getDocumentElement().normalize();
        } catch (SAXException | IOException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getComando() {
        String Comando = doc.getElementsByTagName("comando").item(0).getTextContent();
        return Comando;
    }

    public String getNomeAllenatore() {
        String Allenatore = doc.getElementsByTagName("nome").item(0).getTextContent();
        return Allenatore;
    }

    public String getNomePokemon() {
        String Nome = "";
        if (doc.getElementsByTagName("nome").getLength() > 0) {
            Nome = doc.getElementsByTagName("nome").item(0).getTextContent();
        } else {
            Nome = doc.getElementsByTagName("pokemon").item(0).getTextContent();
        }
        return Nome;
    }

    public int getVitaPokemon() {
        String Vita = doc.getElementsByTagName("vita").item(0).getTextContent();
        return Integer.parseInt(Vita);
    }

    public String getTipoPokemon() {
        String Tipo = doc.getElementsByTagName("tipo").item(0).getTextContent();
        return Tipo;
    }

    public String getNomeMossa() {
        String NomeMossa = doc.getElementsByTagName("nomeMossa").item(0).getTextContent();
        return NomeMossa;
    }

    public String getTipoMossa() {
        String TipoMossa = doc.getElementsByTagName("tipoMossa").item(0).getTextContent();
        return TipoMossa;
    }

    public int getDanniMossa() {
        String Danni = doc.getElementsByTagName("danni").item(0).getTextContent();
        return Integer.parseInt(Danni);
    }

    public String getTipoStatus() {
        String tipo = doc.getElementsByTagName("tipo").item(0).getTextContent();
        return tipo;
    }

    public int getProbStatus(){
        String prob = doc.getElementsByTagName("prob").item(0).getTextContent();
        return Integer.parseInt(prob);
    }
    
    
    public String getVitaRimanente() {
        String vitaRimanente = doc.getElementsByTagName("vitaRimanente").item(0).getTextContent();
        return (vitaRimanente);
    }

    public String getMoltiplicatore() {
        String moltiplicatore = doc.getElementsByTagName("moltiplicatore").item(0).getTextContent();
        return (moltiplicatore);
    }

    public String getStatus() {
        String status = doc.getElementsByTagName("status").item(0).getTextContent();
        return status;
    }

    public String getNote() {
        String note = doc.getElementsByTagName("note").item(0).getTextContent();
        return (note);
    }

    public String getOggetto() {
        String oggetto = doc.getElementsByTagName("oggetto").item(0).getTextContent();
        return (oggetto);
    }

    public String getVitaAttuale() {
        String VitaAttuale = doc.getElementsByTagName("vitaAttuale").item(0).getTextContent();
        return (VitaAttuale);
    }

    public boolean getDOTAggiunta(){
        String Aggiunta = doc.getElementsByTagName("aggiunta").item(0).getTextContent();
        return Boolean.parseBoolean(Aggiunta);
    }
    
    public int getDOTDps(){
        String dps = doc.getElementsByTagName("dps").item(0).getTextContent();
        return Integer.parseInt(dps);
    }
    
    public String getDOTTipo(){
        String tipo = doc.getElementsByTagName("tipo").item(0).getTextContent();
        return tipo;
    }
}
