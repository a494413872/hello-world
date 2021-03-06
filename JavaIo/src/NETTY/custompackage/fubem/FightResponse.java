package NETTY.custompackage.fubem;

import NETTY.serial.Serializer;

public class FightResponse extends Serializer {
    private int gold;

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    @Override
    protected void read() {
       this.gold=readInt();
    }

    @Override
    protected void write() {
        writeInt(gold);
    }
}
