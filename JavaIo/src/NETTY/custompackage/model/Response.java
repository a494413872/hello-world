package NETTY.custompackage.model;

//自定义返回对象
//
public class Response {
    //请求模块
    private short module;
    //命令号
    private  short cmd;
    //数据部分
    private byte[] data;
    //返回状态码
    private int stateCode;

    public int getStateCode() {
        return stateCode;
    }

    public void setStateCode(int stateCode) {
        this.stateCode = stateCode;
    }

    public short getModule() {
        return module;
    }

    public void setModule(short module) {
        this.module = module;
    }

    public short getCmd() {
        return cmd;
    }

    public void setCmd(short cmd) {
        this.cmd = cmd;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getDataLength(){
        if(this.data == null) return 0;
        return this.data.length;
    }
}
