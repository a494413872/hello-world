package NETTY.custompackage.fubem;

import NETTY.serial.Serializer;

public class FightRequest  extends Serializer {
    private int funbenId;
    private int count;

    public int getFunbenId() {
        return funbenId;
    }

    public void setFunbenId(int funbenId) {
        this.funbenId = funbenId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    protected void read() {
        this.funbenId = readInt();
        this.count = readInt();
    }

    @Override
    protected void write() {
        writeInt(funbenId);
        writeInt(count);
    }
}
