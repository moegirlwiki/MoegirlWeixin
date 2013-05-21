package org.moegirlwiki.plugins.messagerobot.interfaces;

import java.util.Collection;

/**
 * the data filter<br/>
 * filter the forbidden words and expired and so on
 * @author xuechong
 */
public interface DataFilter<D extends OriginData> {
	public Collection<D> filter(Collection<D> datas,RobotContext context);
}
