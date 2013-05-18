package org.moegirlwiki.plugins.messagerobot.interfaces;

import java.util.Collection;

public interface Translator<D extends OriginData,M extends Message> {
	public M translate(D origin);
	
	public Collection<M> translate(Collection<D> origin);
}
