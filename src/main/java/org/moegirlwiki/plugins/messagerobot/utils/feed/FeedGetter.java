package org.moegirlwiki.plugins.messagerobot.utils.feed;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.moegirlwiki.plugins.messagerobot.interfaces.OriginDataGetter;
import org.moegirlwiki.plugins.messagerobot.interfaces.RobotContext;
import org.moegirlwiki.plugins.messagerobot.model.FeedEntry;
import org.moegirlwiki.plugins.messagerobot.utils.StringUtil;
import org.moegirlwiki.plugins.messagerobot.utils.http.UrlConnectionUtil;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class FeedGetter implements OriginDataGetter<FeedEntry>{

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
		if(document==null){
			return Collections.EMPTY_LIST;
		}
		return null;
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
