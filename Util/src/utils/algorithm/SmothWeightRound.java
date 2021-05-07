package utils.algorithm;

import java.util.ArrayList;
import java.util.List;

public class SmothWeightRound {
    // 平滑加权轮询算法
    // 放置对象的容器
    private List<Entity> objects = new ArrayList<>();
    private int totalWeight = 0;

    //模拟spring 注入
    public void initObjects(){
        Entity entity1 = new Entity();
        entity1.setWeight(7);
        entity1.setCurWeight(7);
        entity1.setObject("to oracle");
        objects.add(entity1);

        Entity entity2 = new Entity();
        entity2.setWeight(2);
        entity2.setCurWeight(2);
        entity2.setObject("to mysql");
        objects.add(entity2);

        Entity entity3 = new Entity();
        entity3.setWeight(1);
        entity3.setCurWeight(1);
        entity3.setObject("to es");
        objects.add(entity3);

        totalWeight = 10;
    }

    public Entity getObject(){
        //获取权重最大的对象,的下标
        int maxIndex = 0;
        for(int i=0;i<objects.size();i++){
                if(objects.get(i).getCurWeight()>objects.get(maxIndex).getCurWeight()){
                    maxIndex=i;
                }
        }

        for(int i=0;i<objects.size();i++){
            if(i==maxIndex){
                objects.get(maxIndex).setCurWeight(objects.get(maxIndex).getWeight()+(objects.get(maxIndex).getCurWeight()-totalWeight));
            }else {
                objects.get(i).setCurWeight(objects.get(i).getCurWeight()+objects.get(i).getWeight());
            }
        }
        return objects.get(maxIndex);
    }

    public static void main(String[] args) {
        SmothWeightRound round = new SmothWeightRound();
        round.initObjects();
        for (int i = 0; i < 20; i++) {
            round.doSome();
        }
    }

    private void doSome() {
        System.out.println(getObject().getObject());
    }


    class Entity{
        //配置权重
        private int weight;
        //当前权重,随着调用次数增多，慢慢减少当前权重。
        private int curWeight;
        //对象
        private String object;

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getCurWeight() {
            return curWeight;
        }

        public void setCurWeight(int curWeight) {
            this.curWeight = curWeight;
        }

        public String getObject() {
            return object;
        }

        public void setObject(String object) {
            this.object = object;
        }
    }
}
