package org.moegirlwiki.plugins.messagerobot.robots;

import org.moegirlwiki.plugins.messagerobot.annotations.Robot;
import org.moegirlwiki.plugins.messagerobot.interfaces.AbstractRobot;
import org.moegirlwiki.plugins.messagerobot.interfaces.OriginDataGetter;
import org.moegirlwiki.plugins.messagerobot.interfaces.Push;
import org.moegirlwiki.plugins.messagerobot.interfaces.Translator;

@Robot
public class WeixinRobot extends AbstractRobot{

	@Override
	public Push getPusher() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Translator getTranslator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OriginDataGetter getDataGetter() {
		// TODO Auto-generated method stub
		return null;
	}

}
