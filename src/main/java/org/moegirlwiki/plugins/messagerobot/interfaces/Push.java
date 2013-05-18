package org.moegirlwiki.plugins.messagerobot.interfaces;

import java.util.Collection;

/**
 * the behavior of all pusher
 * @author xuechong
 */
public interface Push <T extends Message>{
	public Object push(T msg);
	public Object push(Collection<T> msgs);
}
