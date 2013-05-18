package org.moegirlwiki.plugins.messagerobot.impl;

import java.util.Collection;

import org.moegirlwiki.plugins.messagerobot.interfaces.Translator;
import org.moegirlwiki.plugins.messagerobot.model.FeedEntry;
import org.moegirlwiki.plugins.messagerobot.model.WeiXinMessage;

public class WeiXinFeedTranslator implements Translator<FeedEntry,WeiXinMessage>{

	@Override
	public WeiXinMessage translate(FeedEntry origin) {
		return null;
	}

	@Override
	public Collection<WeiXinMessage> translate(Collection<FeedEntry> origin) {
		return null;
	}

}
