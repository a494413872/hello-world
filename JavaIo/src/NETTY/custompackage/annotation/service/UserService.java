package NETTY.custompackage.annotation.service;

import NETTY.custompackage.annotation.SocketCmd;
import NETTY.custompackage.annotation.SocketModule;

@SocketModule(module=1)
public interface UserService {
    @SocketCmd(cmd=1)
    public void login();

    @SocketCmd(cmd=2)
    public void  getInfo();
}
