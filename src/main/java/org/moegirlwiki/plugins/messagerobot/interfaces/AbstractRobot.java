package org.moegirlwiki.plugins.messagerobot.interfaces;

import java.util.Collection;
import java.util.List;

/**
 * the default robot behavior and props
 * @author xuechong
 */
public abstract class AbstractRobot<D extends OriginData,M extends Message> implements Runnable{
	
	protected Push<M> pusher;
	protected OriginDataGetter<D> dataGetter;
	protected Translator<D,M> translator;
	protected DataFilter<D> filter;
	protected RobotContext context;
	
	@Override
	public void run() {
		this.execute();
	}
	
	public void execute(){
		sendMessage();
	}
	
	public Object sendMessage(){
		
		List<D> data = this.getDataGetter().getOriginData(this.getContext());
		Collection<D> filteData = filter.filter(data);
		if(filteData!=null&&!filteData.isEmpty()){
			Collection<M> messages = this.getTranslator().translate(filteData);
			return  this.getPusher().push(messages);
		}
		return null;
	}
	
	public abstract Push<M> getPusher();
	public abstract Translator<D,M> getTranslator();
	public abstract OriginDataGetter<D> getDataGetter();
	protected abstract RobotContext getContext();
	/**
	 * check the robot config & status etc.<br/>
	 * the check method will be invoke before the main progress <br>
	 * try to start the robot thread<br/>
	 * if this method returns flase .the robot will not be started
	 * @return
	 */
	public abstract boolean selfCheck();
	
	public void setPusher(Push<M> pusher) {
		this.pusher = pusher;
	}
	public void setDataGetter(OriginDataGetter<D> dataGetter) {
		this.dataGetter = dataGetter;
	}
	public void setTranslator(Translator<D,M> translator) {
		this.translator = translator;
	}
	
}
