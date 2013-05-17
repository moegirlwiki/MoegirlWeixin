package org.moegirlwiki.plugins.messagerobot.interfaces;

import java.util.Collection;

/**
 * the behavior of all pusher
 * @author xuechong
 */
public interface Push {
	public Object push(Message msg);
	public Object push(Collection<Message> msgs);
}
