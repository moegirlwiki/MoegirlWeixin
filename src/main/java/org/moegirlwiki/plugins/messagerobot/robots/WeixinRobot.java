package org.moegirlwiki.plugins.messagerobot.robots;

import java.io.IOException;

import org.moegirlwiki.plugins.messagerobot.annotations.Robot;
import org.moegirlwiki.plugins.messagerobot.impl.WeiXinPusher;
import org.moegirlwiki.plugins.messagerobot.interfaces.AbstractRobot;
import org.moegirlwiki.plugins.messagerobot.interfaces.OriginDataGetter;
import org.moegirlwiki.plugins.messagerobot.interfaces.Push;
import org.moegirlwiki.plugins.messagerobot.interfaces.RobotContext;
import org.moegirlwiki.plugins.messagerobot.interfaces.Translator;
import org.moegirlwiki.plugins.messagerobot.model.FeedEntry;
import org.moegirlwiki.plugins.messagerobot.model.WeiXinMessage;

@Robot
public class WeixinRobot extends AbstractRobot<FeedEntry,WeiXinMessage>{

	private static final String CONFIG_NAME = "feedpusherconfig.properties";
	public WeixinRobot(){
		try {
			this.context = RobotContext.getContext(CONFIG_NAME);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	@Override
	public Push<WeiXinMessage> getPusher() {
		return new WeiXinPusher();
	}

	@Override
	public Translator<FeedEntry,WeiXinMessage> getTranslator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OriginDataGetter<FeedEntry> getDataGetter() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected RobotContext getContext() {
		return this.context;
	}

}
