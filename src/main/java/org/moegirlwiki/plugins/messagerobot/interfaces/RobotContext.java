package org.moegirlwiki.plugins.messagerobot.interfaces;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.moegirlwiki.plugins.messagerobot.utils.StringUtil;

public class RobotContext {
	
	private static final String SERVER_TIME = "servertimezone";
	
	Map<String, String> context;
	
	private RobotContext(String configName) throws IOException{
		loadProperties(configName);
	}
	
	/**
	 * load the properties to the context
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	private void loadProperties(String configName) throws FileNotFoundException, IOException {
		Properties property = new Properties();
		property.load(new FileInputStream(configName));
		this.context = (Map<String, String>) property.clone();
	}

	/**
	 * get the context by the properties name
	 * @param configName
	 * @return
	 * @throws IOException if the properties file not found
	 */
	public static RobotContext getContext(String configName) throws IOException{
		return new RobotContext(configName);
	}
	
	public String getServerTimeZone(){
		String result = this.context.get(SERVER_TIME);
		return StringUtil.isNotBlank(result)?result:"+8";
	}
}
