package utils.algorithm;

/**
 * Created by songrian on 1/23/2019.
 * 快速排序算法
 * 快速排序的原理，是先取一个数字，比如第一个数字来作为基准数，然后从后往前找比它小的填充到它的空位。然后
 * 再从前往后找比它大的，填充到后面的那个空位。当i=r的时候，把基准数填进去。这样一趟循环之后。基准数左边的一定比它小，右边一定比它大。然后递归调用。分别对左边和右边进行排序
 */
public class FastSort {
    public static void main(String[] args) {
        int[] arrays = {7,5,2,1,9,6,4,3,8,9};
        int l=0;
        int r = 9;
        quickSort(arrays,l,r);
        for (int array : arrays) {
            System.out.print(array);
        }

    }
    public static void quickSort(int[] arr,int left,int right){
        if(left>=right) return;
        int l = left;
        int r= right;
        //先把arr[l]取出来做为基准数，然后arr[l]就可以空出来，把比key小的放进去
        int key = arr[l];
        //i增加r减少，一直到相遇循环结束。
        while (l<r){
            //先从右边往左边找，一直倒找比key小的值停止。
            while (arr[r]>=key&&l<r){
                r--;
            }
            //停止之后意味着找到比key小的数字了。可以放到arr[l]这个位置。
            if(arr[r]<key){
                arr[l]=arr[r];
                //同时移动一下左边l的位置
                l++;
            }
            //然后从前往后，找到比key大的，放在arr[r]的位置。
            while(arr[l]<=key&&l<r){
                l++;
            }
            if(arr[l]>key){
                arr[r]= arr[l];
                r--;
            }
        }
        arr[r] = key;
        //循环出来，意味着i==r，这时候递归调用左边和右边就好。
        quickSort(arr,left,r-1);
        quickSort(arr,l+1,right);

    }
}
