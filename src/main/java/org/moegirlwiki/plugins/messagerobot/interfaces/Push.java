package org.moegirlwiki.plugins.messagerobot.interfaces;

import java.util.Collection;

/**
 * the behavior of all pusher
 * @author xuechong
 */
public interface Push <M extends Message>{
	public Object push(M msg);
	public Object push(Collection<M> msgs);
}
