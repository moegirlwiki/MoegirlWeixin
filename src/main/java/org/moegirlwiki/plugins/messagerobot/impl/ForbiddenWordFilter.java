package org.moegirlwiki.plugins.messagerobot.impl;

import java.util.Collection;

import org.moegirlwiki.plugins.messagerobot.interfaces.DataFilter;
import org.moegirlwiki.plugins.messagerobot.interfaces.RobotContext;
import org.moegirlwiki.plugins.messagerobot.model.FeedEntry;

/**
 * filter the forbidden words
 * @author xuechong
 *
 */
public class ForbiddenWordFilter implements DataFilter<FeedEntry>{

	@Override
	public Collection<FeedEntry> filter(Collection<FeedEntry> datas,
			RobotContext context) {
		
		return null;
	}

	
}
