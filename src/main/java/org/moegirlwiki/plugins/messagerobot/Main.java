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
		if(robots!=null&&!robots.isEmpty()){
			for (Class<?> robotCLass : robots) {
				AbstractRobot robot=null;
				try {
					robot = (AbstractRobot) robotCLass.newInstance();
				} catch (Exception e) {
					e.printStackTrace();
				} 
				if(robot.selfCheck()){
					new Thread(robot).start();
				}
			}
		}
	}
}
