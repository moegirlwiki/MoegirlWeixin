package org.moegirlwiki.plugins.messagerobot.impl;

import java.util.Collection;

import org.moegirlwiki.plugins.messagerobot.interfaces.Push;
import org.moegirlwiki.plugins.messagerobot.interfaces.RobotContext;
import org.moegirlwiki.plugins.messagerobot.model.WeiXinMessage;


/**
 * the weixin message pusher
 * @author xuechong
 *
 */
public class WeiXinPusher implements Push<WeiXinMessage>{

	@Override
	public Object push(WeiXinMessage msg, RobotContext context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object push(Collection<WeiXinMessage> msgs, RobotContext context) {
		// TODO Auto-generated method stub
		return null;
	}


}
