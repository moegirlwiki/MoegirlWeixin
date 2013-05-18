package org.moegirlwiki.plugins.messagerobot.interfaces;

import java.util.List;

public interface OriginDataGetter <T extends OriginData>{
	public List<T> getOriginData();
}
