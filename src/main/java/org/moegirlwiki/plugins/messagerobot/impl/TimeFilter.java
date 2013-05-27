package org.moegirlwiki.plugins.messagerobot.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import org.moegirlwiki.plugins.messagerobot.interfaces.DataFilter;
import org.moegirlwiki.plugins.messagerobot.interfaces.RobotContext;
import org.moegirlwiki.plugins.messagerobot.model.FeedEntry;

/**
 * filter by the publish time
 * @author xuechong
 */
public class TimeFilter implements DataFilter<FeedEntry>{

	private static final long longestTime = 1*1000l*60l*60l;
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddhh:mm:ss");
	
	@Override
	public Collection<FeedEntry> filter(Collection<FeedEntry> datas,
			RobotContext context) {
		Collection<FeedEntry> result = new LinkedList<FeedEntry>();
		for (FeedEntry feedEntry : datas) {
			if(available(feedEntry.getUpdateTime(),context.getServerTimeZone())){
				result.add(feedEntry);
			}
		}
		return result;
	}
	
	
	private boolean available(String updateTime, String serverTimeZone) {
		Long utc =System.currentTimeMillis()- 
			Long.parseLong(serverTimeZone)*1000l*60l*60l;
		Date earliestDate =new Date(utc-longestTime);
		
		updateTime = updateTime.replaceAll("[a-zA-Z]", "");
		System.out.println(earliestDate);//TODO
		
		try {
			Date pubDate = sdf.parse(updateTime);
			System.out.println(pubDate);//TODO
			return pubDate.after(earliestDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
