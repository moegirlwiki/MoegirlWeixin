package org.moegirlwiki.plugins.messagerobot.interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.moegirlwiki.plugins.messagerobot.utils.StringUtil;

public class RobotContext {
	
	private static final String SERVER_TIME = "servertimezone";
	private static final String TARGET_TIME = "targertimezone";
	private static final String PUSH_START = "pushtimestart";
	private static final String PUSH_END = "pushtimeend";
	private static final String PUSH_INTERVAL = "pushtimeinterval";
	private static final String DATA_SOURCE = "datasource";
	
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
		property.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(configName));
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
	
	public String get(String key)throws NullPointerException{
		if(StringUtil.isNotBlank(this.context.get(key).toString())){
			return this.context.get(key);
		}else{
			throw new NullPointerException("no such property in the context");
		}
	}
	/**
	 * default +8
	 * @return
	 */
	public String getServerTimeZone(){
		String result = this.context.get(SERVER_TIME);
		return StringUtil.isNotBlank(result)?result:"+8";
	}
	/**
	 * default +8
	 * @return
	 */
	public String getTargetTimeZone(){
		String result = this.context.get(TARGET_TIME);
		return StringUtil.isNotBlank(result)?result:"+8";
	}
	
	/**
	 * default 9
	 * @return
	 */
	public String getPushTimeStart(){
		String result = this.context.get(PUSH_START);
		return StringUtil.isNotBlank(result)?result:"9";
	}
	
	/**
	 * default 22
	 * @return
	 */
	public String getPushTimeEnd(){
		String result = this.context.get(PUSH_END);
		return StringUtil.isNotBlank(result)?result:"22";
	}
	/**
	 * default 2
	 * @return
	 */
	public String getPushTimeInterval(){
		String result = this.context.get(PUSH_INTERVAL);
		return StringUtil.isNotBlank(result)?result:"2";
	}
	/**
	 * no default value will throw a nullpointerexception <br/>
	 * if you have not type this in the properties file
	 * @return
	 */
	public String getDataSource(){
		return this.get(DATA_SOURCE);
	}
}
