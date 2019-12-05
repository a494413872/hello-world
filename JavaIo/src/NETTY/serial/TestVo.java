package NETTY.serial;

import java.util.ArrayList;
import java.util.List;

public class TestVo extends Serializer {

    private long playerId;
    private int age;
    private String name;

    private List<Integer> skills = new ArrayList<>();


    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Integer> getSkills() {
        return skills;
    }

    public void setSkills(List<Integer> skills) {
        this.skills = skills;
    }

    @Override
    protected void read() {
        this.playerId = readLong();
        this.age =readInt();
        this.name =readString();
        this.skills = readList(Integer.class);
    }

    @Override
    protected void write() {
        writeLong(playerId);
        writeInt(age);
        writeString(name);
        writeList(skills);
    }
}
