package org.moegirlwiki.plugins.messagerobot.interfaces;

import java.util.Collection;

/**
 * the data filter<br/>
 * filter the forbidden words and expired and so on
 * @author xuechong
 */
public interface DataFilter {
	public Collection<OriginData> filter(Collection<OriginData> datas);
}
