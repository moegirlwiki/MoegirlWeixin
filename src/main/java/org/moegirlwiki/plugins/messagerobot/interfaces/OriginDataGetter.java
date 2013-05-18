package org.moegirlwiki.plugins.messagerobot.interfaces;

import java.util.List;

public interface OriginDataGetter <D extends OriginData>{
	public List<D> getOriginData(RobotContext context);
}
