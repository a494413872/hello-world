package com.adapter.main;

import com.adapter.adapter.ClassAdapter;
import com.adapter.adapter.ObjAdapter;
import com.adapter.socket.ThreeSocketImpl;
import com.adapter.socket.TwoSocket;
import com.adapter.socket.TwoSocketImpl;

/**
 * Created by songjian on 9/10/2018.
 */
public class Main {
    public static void main(String[] args) {
        //1.主程序直接调用两孔插头
        TwoSocket twoSocket = new TwoSocketImpl();
        twoSocket.voltage200();

        //2.需求变更导致主程序实际要使用3孔插头的逻辑这时候就需要适配器。
        //这个是对象是配置
        twoSocket = new ObjAdapter(new ThreeSocketImpl());
        twoSocket.voltage200();

        //3.这个是类适配器
        twoSocket = new ClassAdapter();
        twoSocket.voltage200();

    }
}
