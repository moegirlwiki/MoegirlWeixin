package org.moegirlwiki.plugins.messagerobot.interfaces;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

/**
 * the default robot behavior and props
 * @author xuechong
 */
public abstract class AbstractRobot<D extends OriginData,M extends Message> implements Runnable{
	
	protected Push<M> pusher;
	protected OriginDataGetter<D> dataGetter;
	protected Translator<D,M> translator;
	protected RobotContext context;
	protected Queue<DataFilter<D>> dataFilters;
	
	public abstract Push<M> getPusher();
	public abstract Translator<D,M> getTranslator();
	public abstract OriginDataGetter<D> getDataGetter();
	protected abstract RobotContext getContext();
	protected abstract List<DataFilter<D>> getDataFilters();
	
	protected Timer timer = new Timer();
	
	@Override
	public void run() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				execute();
			}
		};
		Long timePeriod ;
		Double interval = Double.valueOf(this.getContext().getPushTimeInterval());
		timePeriod = (long)(interval*1000*60*60);
		timer.schedule(task, 0l, timePeriod);
	}
	
	public void execute(){
		if(availableTime()){
			sendMessage();
		}
	}
	
	public boolean availableTime(){
		Integer curTime = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		Integer timeStart = Integer.parseInt(this.getContext().getPushTimeStart());
		Integer timeEnd = Integer.parseInt(this.getContext().getPushTimeEnd());
		Integer timeDiff = Integer.parseInt(this.getContext().getTargetTimeZone())
							- Integer.parseInt(this.getContext().getServerTimeZone());
		
		int targetTime = (curTime+timeDiff)%24;
		return (targetTime>timeStart && targetTime<timeEnd);
	}
	
	public Object sendMessage(){
		List<D> data = this.getDataGetter().getOriginData(this.getContext());
		Collection<D> filteData = filterDatas(data);
		if(filteData!=null&&!filteData.isEmpty()){
			Collection<M> messages = this.getTranslator().translate(filteData);
			return  this.getPusher().push(messages);
		}
		return null;
	}
	/**
	 * filter the datas
	 * @param datas
	 * @return
	 * @author xuechong
	 */
	protected Collection<D> filterDatas(Collection<D> datas){
		Iterator<DataFilter<D>> it = this.getDataFilters().iterator();
		while (it.hasNext()) {
			DataFilter<D> dataFilter = (DataFilter<D>) it.next();
			datas= dataFilter.filter(datas);
			if(datas==null||datas.isEmpty()){
				break;
			}
		}
		return datas;
	}

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
	public void setDataFilters(Queue<DataFilter<D>> dataFilters) {
		this.dataFilters = dataFilters;
	}
	
}
