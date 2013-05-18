package org.moegirlwiki.plugins.messagerobot.utils.feed;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.moegirlwiki.plugins.messagerobot.interfaces.OriginDataGetter;
import org.moegirlwiki.plugins.messagerobot.interfaces.RobotContext;
import org.moegirlwiki.plugins.messagerobot.model.FeedEntry;
import org.moegirlwiki.plugins.messagerobot.utils.StringUtil;
import org.moegirlwiki.plugins.messagerobot.utils.http.UrlConnectionUtil;

public class FeedGetter implements OriginDataGetter<FeedEntry>{

	@Override
	@SuppressWarnings("unchecked")
	public List<FeedEntry> getOriginData(RobotContext context) {
		String rawString;
		try {
			rawString = this.getRawData(context.getDataSource());
			return this.analyze(rawString);

		} catch (IOException e) {
			e.printStackTrace();
			return Collections.EMPTY_LIST;
		}
	}

	@SuppressWarnings("unchecked")
	private List<FeedEntry> analyze(String rawString) {
		if(!StringUtil.isNotBlank(rawString)){
			return Collections.EMPTY_LIST;
		}
		return null;
	}

	private String getRawData(String dataSource) throws IOException {
		return UrlConnectionUtil.postReuqest(dataSource, null);
	}
	
}
