package com.adapter.adapter;

import com.adapter.socket.ThreeSocket;
import com.adapter.socket.TwoSocket;

/**
 * Created by songjian on 9/10/2018.
 */
public class ObjAdapter implements TwoSocket {

    private ThreeSocket threeSocket;

    public ObjAdapter(ThreeSocket threeSocket){
           this.threeSocket = threeSocket;
    }


    @Override
    public void voltage200() {
        threeSocket.voltage300();
    }
}
