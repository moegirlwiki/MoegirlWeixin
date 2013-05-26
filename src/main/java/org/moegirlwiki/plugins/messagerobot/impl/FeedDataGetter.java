package org.moegirlwiki.plugins.messagerobot.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.moegirlwiki.plugins.messagerobot.interfaces.OriginDataGetter;
import org.moegirlwiki.plugins.messagerobot.interfaces.RobotContext;
import org.moegirlwiki.plugins.messagerobot.model.FeedEntry;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class FeedDataGetter implements OriginDataGetter<FeedEntry>{

	@Override
	@SuppressWarnings("unchecked")
	public List<FeedEntry> getOriginData(RobotContext context) {
		Document document;
		try {
			document = this.read(context.getDataSource());
			return this.analyze(document);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.EMPTY_LIST;
		}
	}

	@SuppressWarnings("unchecked")
	private List<FeedEntry> analyze(Document document) {
		List<FeedEntry> result = new LinkedList<FeedEntry>();
		if(document==null){
			return Collections.EMPTY_LIST;
		}
		
		Node root = document.getElementsByTagName("feed").item(0);
		if(root.getChildNodes().getLength()>0){
			for (int i = 0, end = root.getChildNodes().getLength(); i < end; i++) {
				
				if("entry".equalsIgnoreCase(root.getChildNodes().item(i).getNodeName())){
					FeedEntry feedEntry = new FeedEntry();
					Node entry = root.getChildNodes().item(i);
					
					for (int j = 0;j< entry.getChildNodes().getLength();j++) {
						Node prop = entry.getChildNodes().item(j);
						
						//you could use "switch" here if you doing this with Java7
						if(prop.getNodeName().equalsIgnoreCase("title")){
							feedEntry.setTitle(prop.getTextContent());
						}
						if(prop.getNodeName().equalsIgnoreCase("id")){
							feedEntry.setId(prop.getTextContent());
						}
						if(prop.getNodeName().equalsIgnoreCase("author")){
							feedEntry.setAuthor(prop.getTextContent());
						}
						if(prop.getNodeName().equalsIgnoreCase("updated")){
							feedEntry.setUpdateTime(prop.getTextContent());
						}
						if(prop.getNodeName().equalsIgnoreCase("link")){
							feedEntry.setLink(prop.getAttributes().
									getNamedItem("href").getNodeValue());
						}
						
					}
					
					result.add(feedEntry);
				}
			}
		}
		return result;
	}
	
	Document read(String dataSource)
			throws ParserConfigurationException, SAXException ,IOException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		URL url =null;
		InputStream stream=null;
		try {
			url = new URL(dataSource);
			stream = url.openStream();
			return builder.parse(stream);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(stream!=null){stream.close();}
		}
		return null;
	}
	
}
