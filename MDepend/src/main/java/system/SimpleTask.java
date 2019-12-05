//package system;
//
//import com.taobao.pamirs.schedule.TaskItemDefine;
//
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.Date;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by songjian on 6/7/2018.
// */
//public class SimpleTask extends AbstractBaseScheduleTask<Date> {
//
//    /**
//     * 执行单个任务
//     * @param task Object
//     * @param ownSign 当前环境名称
//     * @throws Exception
//     */
//    public boolean execute(Date task, String ownSign) throws Exception{
//        System.out.println("dispose task : "+task.getTime());  //当前任务处理器内的线程处理数据
//        return true;
//    }
//
//    /**
//     * 根据条件，查询当前调度服务器可处理的任务
//     * @param taskParameter 任务的自定义参数
//     * @param ownSign 当前环境名称
//     * @param taskItemNum 当前任务类型的任务队列数量
//     * @param taskItemList 当前调度服务器，分配到的可处理队列
//     * @param eachFetchDataNum 每次获取数据的数量
//     * @return
//     * @throws Exception
//     */
//    public List<Date> selectTasks(String taskParameter, String ownSign, int taskItemNum, List<TaskItemDefine> taskItemList, int eachFetchDataNum) throws Exception {
//        System.out.println("select task >>>>>>>>>>>>>>>>");
//        List<Date> dateList = new ArrayList<Date>();
//
//        List<Long> taskIdList = new ArrayList<Long>();
//        for(TaskItemDefine t : taskItemList){ //确定当前任务处理器需处理的任务项id
//            taskIdList.add(Long.valueOf(t.getTaskItemId()));
//        }
//
//        for(int i=0;i<eachFetchDataNum;i++){ // 添加最多指定数量的待处理数据
//            Date date = new Date(); //生成待处理数据
//            Long remainder = date.getTime() % taskItemNum ;
//            if(taskIdList.contains(remainder)){  //根据数据取模，判断当前待处理数据，是否应由当前任务处理器处理
//                dateList.add(date);
//            }
//            TimeUnit.SECONDS.sleep(1);
//        }
//        return dateList;  //返回当前任务处理器需要处理的数据
//    }
//
//    /**
//     * 获取任务的比较器,主要在NotSleep模式下需要用到
//     * @return
//     */
//    public Comparator<Date> getComparator() {
//        return null;
//    }
//}