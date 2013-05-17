package org.moegirlwiki.plugins.messagerobot.interfaces;

import java.util.Collection;
import java.util.List;

/**
 * the default robot behavior and props
 * @author xuechong
 */
public abstract class AbstractRobot implements Runnable{
	
	protected Push pusher;
	protected OriginDataGetter dataGetter;
	protected Translator translator;
	protected DataFilter filter;
	
	@Override
	public void run() {
		this.execute();
	}
	
	public Object execute(){
		List<OriginData> data = this.getDataGetter().getOriginData();
		Collection<OriginData> filteData = filter.filter(data);
		if(filteData!=null&&!filteData.isEmpty()){
			Collection<Message> messages = this.getTranslator().translate(filteData);
			return  this.getPusher().push(messages);
		}
		return null;
	}
	
	public abstract Push getPusher();
	public abstract Translator getTranslator();
	public abstract OriginDataGetter getDataGetter();
	
	public void setPusher(Push pusher) {
		this.pusher = pusher;
	}
	public void setDataGetter(OriginDataGetter dataGetter) {
		this.dataGetter = dataGetter;
	}
	public void setTranslator(Translator translator) {
		this.translator = translator;
	}
	
}
