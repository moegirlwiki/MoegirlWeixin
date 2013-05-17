package org.moegirlwiki.plugins.messagerobot.interfaces;

import java.util.Collection;

public interface Translator {
	public Message translate(OriginData origin);
	
	public Collection<Message> translate(Collection<OriginData> origin);
}
