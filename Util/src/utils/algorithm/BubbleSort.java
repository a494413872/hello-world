package utils.algorithm;

/**
 * Created by songjian on 1/23/2019.
 * 比较相邻的两个数，把大的元素放到右端
 */
public class BubbleSort {
    public static void main(String[] args) {
        int compareCount = 0;
        int[] arrays = {7,5,2,1,9,6,4,3,8,9};
        //外层控制总共需要进行多少层比较
        for (int i = 0; i < arrays.length-1; i++) {
            //内层控制每层比较具体需要比较几次，因为每次外层循环都会让一个最大的数处在最右端，所以下一次内层循环就需要减少一次，因为最后一次不需要比较。
            for(int j=0;j<arrays.length-1-i;j++){
                if(arrays[j]>arrays[j+1]){
                    int tmp = arrays[j];
                    arrays[j]=arrays[j+1];
                    arrays[j+1]=tmp;
                }
                compareCount++;
            }
        }
        for (int array : arrays) {
            System.out.println(array);
        }
        System.out.println("循环次数："+compareCount);
    }
}
