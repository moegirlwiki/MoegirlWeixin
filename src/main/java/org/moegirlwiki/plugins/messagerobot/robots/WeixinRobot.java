package org.moegirlwiki.plugins.messagerobot.robots;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.moegirlwiki.plugins.messagerobot.annotations.Robot;
import org.moegirlwiki.plugins.messagerobot.impl.FeedDataGetter;
import org.moegirlwiki.plugins.messagerobot.impl.ForbiddenWordFilter;
import org.moegirlwiki.plugins.messagerobot.impl.TimeFilter;
import org.moegirlwiki.plugins.messagerobot.impl.WeiXinFeedTranslator;
import org.moegirlwiki.plugins.messagerobot.impl.WeiXinPusher;
import org.moegirlwiki.plugins.messagerobot.interfaces.AbstractRobot;
import org.moegirlwiki.plugins.messagerobot.interfaces.DataFilter;
import org.moegirlwiki.plugins.messagerobot.interfaces.OriginDataGetter;
import org.moegirlwiki.plugins.messagerobot.interfaces.Push;
import org.moegirlwiki.plugins.messagerobot.interfaces.RobotContext;
import org.moegirlwiki.plugins.messagerobot.interfaces.Translator;
import org.moegirlwiki.plugins.messagerobot.model.FeedEntry;
import org.moegirlwiki.plugins.messagerobot.model.WeiXinMessage;

@Robot
public class WeixinRobot extends AbstractRobot<FeedEntry,WeiXinMessage>{

	private static final String CONFIG_NAME = "weixinpusherconfig.properties";
	public WeixinRobot(){
		try {
			this.context = RobotContext.getContext(CONFIG_NAME);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.dataGetter = new FeedDataGetter();
		this.pusher = new WeiXinPusher();
		this.translator = new WeiXinFeedTranslator();
		this.dataFilters = new ArrayList<DataFilter<FeedEntry>>(3);
		this.dataFilters.add(new TimeFilter());
		this.dataFilters.add(new ForbiddenWordFilter());
	}
	@Override
	public Push<WeiXinMessage> getPusher() {
		return this.pusher;
	}

	@Override
	public Translator<FeedEntry,WeiXinMessage> getTranslator() {
		return this.translator;
	}

	@Override
	public OriginDataGetter<FeedEntry> getDataGetter() {
		return this.dataGetter;
	}
	@Override
	protected RobotContext getContext() {
		return this.context;
	}
	@Override
	public boolean selfCheck() {
		try {
			new WeixinRobot();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	protected List<DataFilter<FeedEntry>> getDataFilters() {
		return this.dataFilters;
	}

}
