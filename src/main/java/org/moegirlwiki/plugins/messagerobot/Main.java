package org.moegirlwiki.plugins.messagerobot;

import java.util.Set;

import org.moegirlwiki.plugins.messagerobot.annotations.RobotsHandler;
import org.moegirlwiki.plugins.messagerobot.interfaces.AbstractRobot;

/**
 * @author xuechong87
 */
public class Main {
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		Set<Class<?>> robots = RobotsHandler.getAllRobotClass();
		AbstractRobot robot=null;
		if(robots!=null&&!robots.isEmpty()){
			for (Class<?> class1 : robots) {
				try {
					robot = (AbstractRobot) class1.newInstance();
				} catch (Exception e) {
					e.printStackTrace();
				} 
				new Thread(robot).start();
			}
		}
	}
}
