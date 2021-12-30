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

    public XMLParser() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void AzioneXML(String XML) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(XML));
        Document doc;
        doc = (Document) dBuilder.parse(is);
        doc.getDocumentElement().normalize();
        //POKEMON
        String Comando = doc.getElementsByTagName("comando").item(0).getTextContent();
        switch (Comando) {
            case "m":
                String Allenatore = doc.getElementsByTagName("nome").item(0).getTextContent();
                break;
            case "s":
                String Nome = doc.getElementsByTagName("nome").item(0).getTextContent();
                String Vita = doc.getElementsByTagName("vita").item(0).getTextContent();
                int V = Integer.parseInt(Vita);
                Pokemon PKM = new Pokemon(Nome, V);
                //mancano le mosse e tipo
                break;
            case "a":
                String NomeMossa = doc.getElementsByTagName("nomeMossa").item(0).getTextContent();
                String TipoMossa = doc.getElementsByTagName("tipoMossa").item(0).getTextContent();
                String Danni = doc.getElementsByTagName("danni").item(0).getTextContent();
                String Tipo = doc.getElementsByTagName("tipo").item(0).getTextContent();
                String Prob = doc.getElementsByTagName("prob").item(0).getTextContent();
                break;
            case "r":
                String VR = doc.getElementsByTagName("vitaRimanente").item(0).getTextContent();
                int VitaRimanente = Integer.parseInt(VR);
                String MP = doc.getElementsByTagName("moltiplicatore").item(0).getTextContent();
                int Moltiplicatore = Integer.parseInt(MP);
                String Status = doc.getElementsByTagName("status").item(0).getTextContent();
                String Note = doc.getElementsByTagName("note").item(0).getTextContent();
                break;
            case "i":
                String Oggetto = doc.getElementsByTagName("oggetto").item(0).getTextContent();
                String Pokemon = doc.getElementsByTagName("pokemon").item(0).getTextContent();
                String VA = doc.getElementsByTagName("vitaAttuale").item(0).getTextContent();
                int VitaAttuale = Integer.parseInt(VA);
                break;
            case "c":
                String NomeCambio = doc.getElementsByTagName("nome").item(0).getTextContent();
                String VitaCambio = doc.getElementsByTagName("vita").item(0).getTextContent();
                int VCambio = Integer.parseInt(VitaCambio);
                Pokemon PKMCambio = new Pokemon(NomeCambio, VCambio);
                //mancano le mosse e tipo
                break;
            case "l":
                String PokemonSconfitto = doc.getElementsByTagName("pokemon").item(0).getTextContent();
                break;
            case "e":
                String PokemonSelezionato = doc.getElementsByTagName("pokemon").item(0).getTextContent();
                String StatusPKM = doc.getElementsByTagName("status").item(0).getTextContent();
                String AggiuntaPKM = doc.getElementsByTagName("aggiunta").item(0).getTextContent();
                String DpsPKM = doc.getElementsByTagName("dps").item(0).getTextContent();
                String TipoPKM = doc.getElementsByTagName("tipo").item(0).getTextContent();
                break;
            case "f":
                //FINE LOTTA
                break;
            default:
                break;
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
            for (int i = 0; i < ContaTipi; i++) {
                NodeList TIPI = listTipi.item(0).getChildNodes();
                NodeList TIPO = TIPI.item(i).getChildNodes();
                Node childNode = TIPO.item(0);
                tipi[i] = childNode.getTextContent();
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
            for (int i = 0; i < ContaDebolezze; i++) {
                NodeList TIPI = listDebolezze.item(0).getChildNodes();
                NodeList TIPO = TIPI.item(i).getChildNodes();
                Node childNode = TIPO.item(0);
                debolezze[i] = childNode.getTextContent();
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
            Mossa[] mosse = new Mossa[ContaMosse];
            for (int i = 0; i < ContaMosse; i++) {
                NodeList TIPI = listMosse.item(0).getChildNodes();
                NodeList TIPO = TIPI.item(i).getChildNodes();
                Node nomeM = TIPO.item(0);
                Node tipo = TIPO.item(1);
                Node danni = TIPO.item(2);
                Node utilizzi = TIPO.item(3);

                System.out.println("nome: " + nomeM.getTextContent());
                System.out.println("tipo: " + tipo.getTextContent());
                System.out.println("danni: " + danni.getTextContent());
                System.out.println("utilizzi: " + utilizzi.getTextContent());

                mosse[i] = new Mossa(nomeM.getTextContent(),
                        tipo.getTextContent(),
                        Integer.parseInt(danni.getTextContent()),
                        Integer.parseInt(utilizzi.getTextContent()));
            }

            pokemon = new Pokemon(nome, Integer.parseInt(vita), immagineFront, immagineBack, tipi, debolezze, mosse);
            return pokemon;
        } catch (SAXException | IOException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pokemon;
    }
}
