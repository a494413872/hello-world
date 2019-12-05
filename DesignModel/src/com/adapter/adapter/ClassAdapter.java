package com.adapter.adapter;

import com.adapter.socket.ThreeSocketImpl;
import com.adapter.socket.TwoSocket;

/**
 * Created by songjian on 9/10/2018.
 */
public class ClassAdapter extends ThreeSocketImpl implements TwoSocket {
    @Override
    public void voltage200() {
        super.voltage300();
    }
}
