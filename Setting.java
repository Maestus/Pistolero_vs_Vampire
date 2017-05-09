
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javafx.scene.input.KeyCode;

public class Setting {

	File config = new File(System.getProperty("user.dir") + "/configuration.xml");
	public Element haut;
	public Element bas;
	public Element gauche;
	public Element droite;
	public Element attaque;
	public Element pause;

	
	public static KeyCode type(String character) {
        switch (character) {
	        case "A": return KeyCode.A;
	        case "B": return KeyCode.B;
	        case "C": return KeyCode.C;
	        case "D": return KeyCode.D;
	        case "E": return KeyCode.E;
	        case "F": return KeyCode.F;
	        case "G": return KeyCode.G;
	        case "H": return KeyCode.H;
	        case "I": return KeyCode.I;
	        case "J": return KeyCode.J;
	        case "K": return KeyCode.K;
	        case "L": return KeyCode.L;
	        case "M": return KeyCode.M;
	        case "N": return KeyCode.N;
	        case "O": return KeyCode.O;
	        case "P": return KeyCode.P;
	        case "Q": return KeyCode.Q;
	        case "R": return KeyCode.R;
	        case "S": return KeyCode.S;
	        case "T": return KeyCode.T;
	        case "U": return KeyCode.U;
	        case "V": return KeyCode.V;
	        case "W": return KeyCode.W;
	        case "X": return KeyCode.X;
	        case "Y": return KeyCode.Y;
	        case "Z": return KeyCode.Z;
	        case "Numpad 0": return KeyCode.DIGIT0;
	        case "Numpad 1": return KeyCode.DIGIT1;
	        case "Numpad 2": return KeyCode.DIGIT2;
	        case "Numpad 3": return KeyCode.DIGIT3;
	        case "Numpad 4": return KeyCode.DIGIT4;
	        case "Numpad 5": return KeyCode.DIGIT5;
	        case "Numpad 6": return KeyCode.DIGIT6;
	        case "Numpad 7": return KeyCode.DIGIT7;
	        case "Numpad 8": return KeyCode.DIGIT8;
	        case "Numpad 9": return KeyCode.DIGIT9;
	        case "Tab" : return KeyCode.TAB;
	        case "Enter" : return KeyCode.ENTER;
	        case "Esc" : return KeyCode.ESCAPE;
	        case "Space" : return KeyCode.SPACE;  
	        case "Up" : return KeyCode.UP;
	        case "Down" : return KeyCode.DOWN;
	        case "Left" : return KeyCode.LEFT;
	        case "Right" : return KeyCode.RIGHT;
        default:
            return null;
        }
    }
	
	public Setting(){
		System.out.println(config.getAbsolutePath());
	    try {

	    	DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	
			if(!config.exists()) { 
				
				Document doc = docBuilder.newDocument();
				Element rootElement = doc.createElement("configuration");
				doc.appendChild(rootElement);
				
				haut = doc.createElement("Haut");
				rootElement.appendChild(haut);
				
				haut.setAttribute("key", "Z");
				
				bas = doc.createElement("Bas");
				rootElement.appendChild(bas);
				
				bas.setAttribute("key", "S");
				
				gauche = doc.createElement("Gauche");
				rootElement.appendChild(gauche);
	
				gauche.setAttribute("key", "Q");
	
				droite = doc.createElement("Droite");
				rootElement.appendChild(droite);
	
				droite.setAttribute("key", "D");
	
				attaque = doc.createElement("Attaquer");
				rootElement.appendChild(attaque);
	
				attaque.setAttribute("key", "P");
				
				pause = doc.createElement("Pause");
				rootElement.appendChild(pause);
	
				pause.setAttribute("key", (KeyCode.ESCAPE).getName());
				
				
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(config);
				
				transformer.transform(source, result);
	
			} else {
			
				Document doc = docBuilder.parse(config);
				
				doc.getDocumentElement().normalize();
		
				Node root = doc.getDocumentElement();
				
				NodeList nList = root.getChildNodes();
					
				haut = (Element) nList.item(0);
				
				bas = (Element) nList.item(1);
				
				gauche = (Element) nList.item(2);
				
				droite = (Element) nList.item(3);
				
				attaque = (Element) nList.item(4);
				
				pause = (Element) nList.item(5);
					
				for (int temp = 0; temp < nList.getLength(); temp++) {
	
					Node nNode = nList.item(temp);
	
					System.out.println("Element :" + nNode.getNodeName());
	
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						
						Element eElement = (Element) nNode;
	
						System.out.println("value : " + eElement.getAttribute("key"));
					}
				}
				
			}
	    }catch(Exception e){
				e.printStackTrace();
		}
	}
	
	
	public void remap(){
		try{

			System.out.println("value : " + SettingMenu.items[0].value.getText());
			System.out.println("value : " + SettingMenu.items[1].value.getText());
			System.out.println("value : " + SettingMenu.items[2].value.getText());
			System.out.println("value : " + SettingMenu.items[3].value.getText());
			System.out.println("value : " + SettingMenu.items[4].value.getText());
			System.out.println("value : " + SettingMenu.items[5].value.getText());

			
			NamedNodeMap attr;
			Node nodeAttr;
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(config);
			
			Node root = doc.getDocumentElement();
			
			NodeList nList = root.getChildNodes();

			if(SettingMenu.items[0].value.getText().toString().length() >= 1){
			
				haut = (Element) nList.item(0);
		
				attr = haut.getAttributes();
				nodeAttr = attr.getNamedItem("key");
				nodeAttr.setTextContent(SettingMenu.items[0].value.getText());
			}
			
			if(SettingMenu.items[1].value.getText().toString().length() >= 1){
				
				bas = (Element) nList.item(1);
		
				attr = bas.getAttributes();
				nodeAttr = attr.getNamedItem("key");
				nodeAttr.setTextContent(SettingMenu.items[1].value.getText());
			}
				
			if(SettingMenu.items[2].value.getText().toString().length() >= 1){
			
				gauche = (Element) nList.item(2);
			
				attr = gauche.getAttributes();
				nodeAttr = attr.getNamedItem("key");
				nodeAttr.setTextContent(SettingMenu.items[2].value.getText());
			}
			
			if(SettingMenu.items[3].value.getText().toString().length() >= 1){
			
				droite = (Element) nList.item(3);
			
				attr = droite.getAttributes();
				nodeAttr = attr.getNamedItem("key");
				nodeAttr.setTextContent(SettingMenu.items[3].value.getText());
			}
			
			if(SettingMenu.items[4].value.getText().toString().length() >= 1){
			
				attaque = (Element) nList.item(4);
				
				attr = attaque.getAttributes();
				nodeAttr = attr.getNamedItem("key");
				nodeAttr.setTextContent(SettingMenu.items[4].value.getText());
			}
			
			if(SettingMenu.items[5].value.getText().toString().length() >= 1){
				
				pause = (Element) nList.item(5);
				
				attr = pause.getAttributes();
				nodeAttr = attr.getNamedItem("key");
				nodeAttr.setTextContent(SettingMenu.items[5].value.getText());
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(config);
			transformer.transform(source, result);
			
	   } catch (Exception e) {
		e.printStackTrace();
	   }
	}
	
}
