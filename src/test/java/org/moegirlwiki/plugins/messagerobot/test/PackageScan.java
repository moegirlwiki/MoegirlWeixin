package org.moegirlwiki.plugins.messagerobot.test;

import java.util.Set;

import org.moegirlwiki.plugins.messagerobot.annotations.RobotsHandler;

public class PackageScan {
	public static void main(String[] args) {
		Set<Class<?>> classSet = RobotsHandler.getAllRobotClass();
		for (Class<?> class1 : classSet) {
				System.out.println(class1);
		}
	
	}
}
