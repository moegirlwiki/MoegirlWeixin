package org.moegirlwiki.plugins.messagerobot.test;

import java.io.IOException;
import java.net.URL;

import org.junit.Test;
import org.moegirlwiki.plugins.messagerobot.interfaces.OriginDataGetter;
import org.moegirlwiki.plugins.messagerobot.interfaces.RobotContext;
import org.moegirlwiki.plugins.messagerobot.model.FeedEntry;
import org.moegirlwiki.plugins.messagerobot.robots.WeixinRobot;
import org.moegirlwiki.plugins.messagerobot.utils.http.UrlConnectionUtil;

public class FeedTests {

	public void getDocument(){
		WeixinRobot robot = new WeixinRobot();
		robot.execute();
	}
	@Test
	public void getString(){
		try {
			String result = UrlConnectionUtil.postReuqest("http://zh.moegirl.org/index.php?title=Special:%E6%9C%80%E6%96%B0%E9%A1%B5%E9%9D%A2&feed=atom&namespace=0", null);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
