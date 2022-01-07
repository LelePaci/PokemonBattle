package pokemonbattle.util;

import java.io.*;
import java.util.logging.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.*;
import pokemonbattle.objects.*;

public class XMLParser {

    private Document doc;
    private DocumentBuilder dBuilder;
    private String xml = "";
    static XMLParser test = new XMLParser();
    static String[] Tipi = {"tipo1", "tipo2", "tipo3"};

    public static void main(String[] args) throws TransformerException {

    }

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

    public int getProbStatus() {
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

    public boolean getDOTAggiunta() {
        String Aggiunta = doc.getElementsByTagName("aggiunta").item(0).getTextContent();
        return Boolean.parseBoolean(Aggiunta);
    }

    public int getDOTDps() {
        String dps = doc.getElementsByTagName("dps").item(0).getTextContent();
        return Integer.parseInt(dps);
    }

    public String getDOTTipo() {
        String tipo = doc.getElementsByTagName("tipo").item(0).getTextContent();
        return tipo;
    }

    public String getXMLAllenatore(String name) {
        try {
            Document doc = dBuilder.newDocument();
            Element rootElement = doc.createElement("root");
            doc.appendChild(rootElement);

            Element comando = doc.createElement("comando");
            comando.appendChild(doc.createTextNode("m"));
            rootElement.appendChild(comando);

            Element nome = doc.createElement("nome");
            nome.appendChild(doc.createTextNode(name));
            rootElement.appendChild(nome);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(domSource, result);
            return writer.toString();
        } catch (TransformerException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getXMLAttacco(String nomeMossa, String tipoMossa, int danni, String Tipo, int Prob) {
        try {
            Document doc = dBuilder.newDocument();
            Element rootElement = doc.createElement("root");

            doc.appendChild(rootElement);
            Element comando = doc.createElement("comando");
            comando.appendChild(doc.createTextNode("a"));
            rootElement.appendChild(comando);

            Element nomeM = doc.createElement("nomeMossa");
            nomeM.appendChild(doc.createTextNode(nomeMossa));
            rootElement.appendChild(nomeM);

            Element tipoM = doc.createElement("tipoMossa");
            tipoM.appendChild(doc.createTextNode(tipoMossa));
            rootElement.appendChild(tipoM);

            Element dn = doc.createElement("danni");
            String DN = String.valueOf(danni);
            dn.appendChild(doc.createTextNode(DN));
            rootElement.appendChild(dn);

            Element Status = doc.createElement("status");
            rootElement.appendChild(Status);
            Element tipo = doc.createElement("tipo");
            tipo.appendChild(doc.createTextNode(Tipo));
            Status.appendChild(tipo);
            Element prob = doc.createElement("prob");
            String PB = String.valueOf(Prob);
            prob.appendChild(doc.createTextNode(PB));
            Status.appendChild(prob);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(domSource, result);
            return writer.toString();
            //System.out.println(writer.toString());
        } catch (TransformerException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getXMLRispostaAttacco(int VitaRimanente, int Moltiplicatore, String Status, String Note) {
        try {
            Document doc = dBuilder.newDocument();
            Element rootElement = doc.createElement("root");
            doc.appendChild(rootElement);
            Element comando = doc.createElement("comando");
            comando.appendChild(doc.createTextNode("r"));
            rootElement.appendChild(comando);

            String vitar = String.valueOf(VitaRimanente);
            Element vitaR = doc.createElement("vitaRimanente");
            vitaR.appendChild(doc.createTextNode(vitar));
            rootElement.appendChild(vitaR);

            Element molti = doc.createElement("moltiplicatore");
            String Molti = String.valueOf(Moltiplicatore);
            molti.appendChild(doc.createTextNode(Molti));
            rootElement.appendChild(molti);

            Element Statu = doc.createElement("status");
            Statu.appendChild(doc.createTextNode(Status));
            rootElement.appendChild(Statu);

            Element Not = doc.createElement("note");
            Not.appendChild(doc.createTextNode(Note));
            rootElement.appendChild(Not);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(domSource, result);
            return writer.toString();
        } catch (TransformerException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getXMLInventario(String Oggetto, String Pokemon, int VitaAttuale) {
        try {
            Document doc = dBuilder.newDocument();
            Element rootElement = doc.createElement("root");
            doc.appendChild(rootElement);
            Element comando = doc.createElement("comando");
            comando.appendChild(doc.createTextNode("i"));
            rootElement.appendChild(comando);

            Element oggetto = doc.createElement("oggetto");
            oggetto.appendChild(doc.createTextNode(Oggetto));
            rootElement.appendChild(oggetto);

            Element pokemon = doc.createElement("pokemon");
            pokemon.appendChild(doc.createTextNode(Pokemon));
            rootElement.appendChild(pokemon);

            Element vitattuale = doc.createElement("vitaAttuale");
            String VA = String.valueOf(VitaAttuale);
            vitattuale.appendChild(doc.createTextNode(VA));
            rootElement.appendChild(vitattuale);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(domSource, result);
            System.out.println(writer.toString());
            return writer.toString();
        } catch (TransformerException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getXMLInvioPokemon(String cmd, String Nome, int Vita, String[] Tipo) {
        try {
            Document doc = dBuilder.newDocument();
            Element rootElement = doc.createElement("root");
            doc.appendChild(rootElement);
            Element comando = doc.createElement("comando");
            comando.appendChild(doc.createTextNode(cmd));
            rootElement.appendChild(comando);

            Element Pokemon = doc.createElement("pokemon");
            rootElement.appendChild(Pokemon);

            Element nome = doc.createElement("nome");
            nome.appendChild(doc.createTextNode(Nome));
            Pokemon.appendChild(nome);

            Element vita = doc.createElement("vita");
            String VT = String.valueOf(Vita);
            vita.appendChild(doc.createTextNode(VT));
            Pokemon.appendChild(vita);

            for (int i = 0; i < Tipo.length; i++) {
                Element tipo = doc.createElement("tipo");
                tipo.appendChild(doc.createTextNode(Tipo[i]));
                Pokemon.appendChild(tipo);

            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(domSource, result);
            //System.out.println(writer.toString());
            return writer.toString();
        } catch (TransformerException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getXMLResa() {
        try {
            Document doc = dBuilder.newDocument();
            Element rootElement = doc.createElement("root");
            doc.appendChild(rootElement);
            Element comando = doc.createElement("comando");
            comando.appendChild(doc.createTextNode("f"));
            rootElement.appendChild(comando);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(domSource, result);
            System.out.println(writer.toString());
            return writer.toString();
        } catch (TransformerException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getXMLPokemonSconfitto(String Pokemon) {
        try {
            Document doc = dBuilder.newDocument();
            Element rootElement = doc.createElement("root");
            doc.appendChild(rootElement);
            Element comando = doc.createElement("comando");
            comando.appendChild(doc.createTextNode("l"));
            rootElement.appendChild(comando);

            Element pokemon = doc.createElement("pokemon");
            pokemon.appendChild(doc.createTextNode(Pokemon));
            rootElement.appendChild(pokemon);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(domSource, result);
            return writer.toString();
        } catch (TransformerException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getXMLStatus(String Pokemon, String Status, boolean aggiunta, int Dps, String Tipo, int Vita) {
        try {
            Document doc = dBuilder.newDocument();
            Element rootElement = doc.createElement("root");

            doc.appendChild(rootElement);
            Element comando = doc.createElement("comando");
            comando.appendChild(doc.createTextNode("e"));
            rootElement.appendChild(comando);

            Element pokemon = doc.createElement("pokemon");
            pokemon.appendChild(doc.createTextNode(Pokemon));
            rootElement.appendChild(pokemon);

            Element status = doc.createElement("status");
            status.appendChild(doc.createTextNode(Status));
            rootElement.appendChild(status);

            Element DanniOverTime = doc.createElement("danniOverTime");
            rootElement.appendChild(DanniOverTime);

            Element Aggiunta = doc.createElement("aggiunta");
            String Baggiunta = String.valueOf(aggiunta);
            Aggiunta.appendChild(doc.createTextNode(Baggiunta));
            DanniOverTime.appendChild(Aggiunta);

            Element dps = doc.createElement("dps");
            String DPS = String.valueOf(Dps);
            dps.appendChild(doc.createTextNode(DPS));
            DanniOverTime.appendChild(dps);

            Element tipo = doc.createElement("tipo");
            tipo.appendChild(doc.createTextNode(Tipo));
            DanniOverTime.appendChild(tipo);

            Element vita = doc.createElement("vita");
            String VT = String.valueOf(Vita);
            vita.appendChild(doc.createTextNode(VT));
            rootElement.appendChild(vita);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(domSource, result);
            System.out.println(writer.toString());
            return writer.toString();
        } catch (TransformerException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
