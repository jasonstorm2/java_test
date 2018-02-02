package my;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLAddDeleTest {

	/**
	 * 使用dom技术对xml进行解析
	 * 
	 * @param args
	 *            从这里我发现： Node 是 Element, document的父类， Element类主要是 增加，删除，修改，返回
	 *            等。document 创建 xml中的对象 例：document.getElementById();方法。
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// 创建一个documentBuilderFactory实例
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// 创建一个documentBuilder
		DocumentBuilder db = dbf.newDocumentBuilder();
		// 指定是那个xml文件
		Document document = db.parse("family.xml");
		// list(document);
		// red(document);
//		createXML();
//		delete(document,"family.xml");
//		add(document,"family.xml");
		modify(document);
//		modify(document);
//		update(document);
	}

	// 修改
	public static void modify(Document doc) throws Exception {
		
//		//通过名字，按xml的顺序 直接找到第一个元素，修改元素内容
//		Element ele = (Element)doc.getElementsByTagName("name3").item(0);
//		ele.setTextContent("xiaohai");
//
//		//修改属性
//		Element element = (Element)doc.getElementsByTagName("name3").item(0);
//		element.setAttribute("sex", "nv");
		
        Element per =(Element) selectSingleNode("/family/girl[@id='005']", doc.getDocumentElement());
        per.getElementsByTagName("name").item(0).setTextContent("rose");
        update(doc, "family.xml");
       
		
		
	}

	// 删除
	public static void delete(Document doc,String path) throws Exception {		
		// 从根节点删除指定的子节点
		Element root = doc.getDocumentElement();        
        Element son =(Element) selectSingleNode("/family/son[@id='1']", root);
        if(son != null){
        	 root.removeChild(son);
             update(doc, path);
        }
       
		
	}
	
	// 更新java在xml文件中操作的内容，对源文件进行覆盖
	public static void update(Document doc,String path) throws Exception {
		// 创建一个TransformerFactory实例
		TransformerFactory tff = TransformerFactory.newInstance();
		// 通过TransformerFactory 得到一个转换器
		Transformer tf = tff.newTransformer();
		// 通过Transformer类的方法 transform(Source xmlSource, Result outputTarget)
		// 将 XML Source 转换为 Result。
		tf.transform(new DOMSource(doc), new StreamResult(path));
	}

	// 遍历xml文件的元素
	public static void list(Node node) {
		if (node.getNodeType() == Node.ELEMENT_NODE)
			System.out.println(node.getNodeName());
		// 得到该结点的子结点
		NodeList nodelist = node.getChildNodes();

		for (int i = 0; i < nodelist.getLength(); i++) {
			Node n = (Node) nodelist.item(i);
			list(n);
		}
	}

	// 获取document对象的 元素的 文本
	public static void red(Document docu) {
		NodeList nodelist = docu.getElementsByTagName("xuesheng");
		Element element = (Element) nodelist.item(0);
		System.out.println(element.getAttribute("sex"));
		System.out.println(element.getTextContent());
	}
	
	
	public static void add(Document docu,String path) throws Exception{
		//获得根节点
		Element doElement = docu.getDocumentElement();	
		
		 //构建子节点
        Element girl =docu.createElement("girl");
        girl.setAttribute("id", "005");   
        //子节点的子节点
        Element name = docu.createElement("name");
        name.setTextContent("凯乐");
        girl.appendChild(name);
        Element age = docu.createElement("age");
        age.setTextContent("5");        
        girl.appendChild(age);
        // 根节点添加子节点
        doElement.appendChild(girl);
        
		update(docu, "family.xml");
		
	}
	
	public static void createXML() throws Exception{   
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// 创建一个documentBuilder
		DocumentBuilder db = dbf.newDocumentBuilder();
		// 指定是那个xml文件
		Document docu = db.newDocument();
	    // 设置XML声明中standalone为yes，即没有dtd和schema作为该XML的说明文档，且不显示该属性
         docu.setXmlStandalone(true);
		
		// 根节点
        Element family =docu.createElement("family"); 
        
        // 子节点
        Element son = docu.createElement("son");
        son.setAttribute("id", "1");
        son.setTextContent("乔治"); 
        // 根节点添加子节点
        family.appendChild(son);
        
        docu.appendChild(family);
        
		// 创建一个TransformerFactory实例
		TransformerFactory tff = TransformerFactory.newInstance();
		// 通过TransformerFactory 得到一个转换器
		Transformer tf = tff.newTransformer();
		// 通过Transformer类的方法 transform(Source xmlSource, Result outputTarget)
		// 将 XML Source 转换为 Result。
		tf.transform(new DOMSource(docu), new StreamResult("family.xml"));

	}
	
	//通过XPath来获取目标节点
	public static Node selectSingleNode(String express, Element source) {
        Node result=null;
        XPathFactory xpathFactory=XPathFactory.newInstance();
        XPath xpath=xpathFactory.newXPath();
        try {
            result=(Node) xpath.evaluate(express, source, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        
        return result;
    }
	
}
