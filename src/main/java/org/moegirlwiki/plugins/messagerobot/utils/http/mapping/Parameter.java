package org.moegirlwiki.plugins.messagerobot.utils.http.mapping;

/**
 * 请求参数映射
 * @author xuechong
 */
public class Parameter implements Cloneable {  
	
	private static Parameter parameter = new Parameter();
	private String key;
	private String value;

	public static Parameter newInstance() {
		try {
			return (Parameter) parameter.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Parameter newInstance(String key, String value) {
		Parameter result = null;
		try {
			result = (Parameter) parameter.clone();
			result.setKey(key);
			result.setValue(value);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
}
