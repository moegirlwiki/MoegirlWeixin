package org.moegirlwiki.plugins.messagerobot.interfaces;

/**
 * the default robot behavior and props
 * @author xuechong
 */
public abstract class AbstractRobot implements Runnable{
	
	protected Push pusher;
	protected OriginDataGetter dataGetter;
	protected Translator translator;
	
	
	@Override
	public void run() {
		this.execute();
	}
	
	public Object execute(){
		OriginData data = this.getDataGetter().getOriginData();
	
		Message message = this.getTranslator().translate(data);
		
		return  this.getPusher().push(message);
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
