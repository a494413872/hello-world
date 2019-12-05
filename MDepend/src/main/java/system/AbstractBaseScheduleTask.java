//package system;
//
//import com.taobao.pamirs.schedule.IScheduleTaskDealSingle;
//import com.taobao.pamirs.schedule.strategy.ScheduleStrategy;
//import com.taobao.pamirs.schedule.taskmanager.ScheduleTaskType;
//
///**
// * Created by songjian on 6/7/2018.
// */
//public abstract class AbstractBaseScheduleTask<T> implements IScheduleTaskDealSingle<T> {
//    /**
//     * 调度任务的配置
//     */
//    private ScheduleTaskType scheduleTaskType;
//    /**
//     * 调度策略的配置
//     */
//    private ScheduleStrategy scheduleStrategy;
//
//    public ScheduleTaskType getScheduleTaskType() {
//        return scheduleTaskType;
//    }
//
//    public void setScheduleTaskType(ScheduleTaskType scheduleTaskType) {
//        this.scheduleTaskType = scheduleTaskType;
//    }
//
//    public ScheduleStrategy getScheduleStrategy() {
//        return scheduleStrategy;
//    }
//
//    public void setScheduleStrategy(ScheduleStrategy scheduleStrategy) {
//        this.scheduleStrategy = scheduleStrategy;
//    }
//}