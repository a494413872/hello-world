//package system;
//
//import com.taobao.pamirs.schedule.strategy.ScheduleStrategy;
//import com.taobao.pamirs.schedule.strategy.TBScheduleManagerFactory;
//import com.taobao.pamirs.schedule.taskmanager.IScheduleDataManager;
//import com.taobao.pamirs.schedule.taskmanager.ScheduleTaskType;
//import com.taobao.pamirs.schedule.zk.ScheduleStrategyDataManager4ZK;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.util.Assert;
//
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by songjian on 6/7/2018.
// */
//public class SystemTBScheduleManagerFactory extends TBScheduleManagerFactory implements ApplicationListener<ContextRefreshedEvent> {
//
//    public void onApplicationEvent(ContextRefreshedEvent event) {  //注册调度任务和调度策略
//        try {
//            super.init(); //默认初始化信息
//
//            IScheduleDataManager iScheduleDataManager = null;
//            ScheduleStrategyDataManager4ZK scheduleStrategyDataManager4ZK = null;
//            int waitSecond = 120; //默认初始化等待时间，最长120秒
//            while((null == iScheduleDataManager || null == scheduleStrategyDataManager4ZK) && waitSecond>0){
//                waitSecond--;
//                TimeUnit.SECONDS.sleep(1); //等待1秒
//                try{
//                    iScheduleDataManager = super.getScheduleDataManager();//获取调度任务管理器
//                    scheduleStrategyDataManager4ZK = super.getScheduleStrategyManager();//获取调度策略管理器
//                }catch (Exception e){
//                }
//            }
//            Assert.notNull(iScheduleDataManager,"初始化tbschedule配置信息失败"); //若仍初始化失败，则抛异常
//            Assert.notNull(scheduleStrategyDataManager4ZK,"初始化tbschedule配置信息失败"); //若仍初始化失败，则抛异常
//
//            Map<String,AbstractBaseScheduleTask> taskMap = event.getApplicationContext().getBeansOfType(AbstractBaseScheduleTask.class);
//            for(Map.Entry<String,AbstractBaseScheduleTask> m : taskMap.entrySet()){
//                String key = m.getKey();
//                AbstractBaseScheduleTask task = m.getValue();
//
//                ScheduleTaskType taskType = task.getScheduleTaskType();
//                taskType.setBaseTaskType("task_"+key); //任务类型(任务名称)
//                taskType.setDealBeanName(key);
//
//                ScheduleStrategy scheduleStrategy = task.getScheduleStrategy();
//                scheduleStrategy.setStrategyName("strategy_"+key); //策略名称
//                scheduleStrategy.setTaskName(taskType.getBaseTaskType()); //任务名称
//                scheduleStrategy.setKind(ScheduleStrategy.Kind.Schedule);
//
//                iScheduleDataManager.updateBaseTaskType(taskType);
//                scheduleStrategyDataManager4ZK.updateScheduleStrategy(scheduleStrategy);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
