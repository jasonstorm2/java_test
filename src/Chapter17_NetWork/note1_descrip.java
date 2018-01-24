package Chapter17_NetWork;

public class note1_descrip {
	/**
	 * 通信协议：计算机网络中现实通信必须遵守的协议 通信协议 负责对传输速率，传输代码，代码结构，传输控制步骤，出错控制等制定处理标准 通信协议
	 * 有三个部分组成：1.语义部分 2.语法部分 3.变换规则，用于决定通信双方的应答关系
	 * 
	 * ISO 的 OSI 模型，open system interconnection 即，开放系统互连参考模型，模型有七层 TCP/IP 分层模型
	 * 四层
	 * 
	 * ip:通信协议的一种，Internet，protocol 又称互联网协议，是支持网间互联的数据包协议，提供网间连接的完善功能，包括ip数据报规定
	 * 互联网范围内的地址格式。 tcp协议：transmission control protocol，传输控制协议，他规定一种可靠的数据信息传递服务。
	 * 这两个协议一般放在一次使用，统称，TCP/IP协议。它是Internet中最常用的 基础协议
	 * 
	 * IP地址 用于唯一的标识 网络中的一个 通信实体，可以是一台主机，一个打印机，或路由的某个端口 基于IP协议网络中传输的
	 * 数据包，都必须使用ip地址来 进行标识：每一个被传输的数据包 必须包括 一个源IP地址 和 一个目标IP地址
	 * 
	 * IP地址 是一个32bit的整数，为了便于记忆 把它分成 4个8bit的二进制数，每8位用圆点隔开，每个8位整数可以转换成一个0~255
	 * 的十进制整数 如：10.163.86.113
	 * 
	 * IP 分为A,B,C,D,E五类 IP地址根据网络号和主机号来分，分为A、B、C三类及特殊地址D、E。全0和全1的都保留不用。
	 * A类：第一个字节为网络号，后三个字节为主机号。该类IP地址的最前面为“0”，所以地址的网络号取值于1~126之间。一般用于大型网络。
	 * B类：前两个字节为网络号，后两个字节为主机号。该类IP地址的最前面为“10”，所以地址的网络号取值于128~191之间。一般用于中等规模网络。
	 * C类：前三个字节为网络号，最后一个字节为主机号。该类IP地址的最前面为“110”，所以地址的网络号取值于192~223之间。一般用于小型网络。
	 * 
	 * A类：10.0.0.0~10.255.255.255 B类：172.16.0.0~172.31.255.255
	 * C类：192.168.0.0~192.168.255.255
	 * 
	 * 端口：一个IP标识唯一的通讯实体，但通讯实体可以有 多个通讯程序同时提供网络服务，以端口区分。 端口 端口
	 * 是一个16位的整数，用于表示数据交给哪个通信程序处理。 因此，端口就是 应用程序与外界交流的出入口。 端口 是一个抽象的软件结构，包括一些
	 * 数据结构 和 I/O缓冲区 不同的应用程序处理不同的端口上的数据，同一台机器上，不能有两个程序使用同一个端口。 端口号0~65535，分为三类：
	 * 1.公认端口 （Well Known Ports）：0~1023，它们绑定Binding 一些特定的服务。 2.注册端口 （Registered
	 * Ports）：1024~49151 它们松散地绑定一些服务。应用程序通常应该使用这个范围内的端口。 3.动态 和/或 私有端口 （Dynamic
	 * and/or Private Ports）: 49152~65535,应用程序使用的动态端口，一般程序不会主动使用这些端口。
	 * 
	 * IP+端口号，确定了一个程序在网络中的具体地址
	 * 
	 * 
	 * 
	 * * 127.0.0.1，localhost 和 IP的区别：
	 * 
	 * localhost 是个本地DNS解析的域名，性质跟 “www.baidu.com” 差不多，不是地址，它可以被配置为任意的 IP
	 * 地址，不过通常情况下都指向 127.0.0.1(ipv4)和 [::1](ipv6) 在 Windows 中，这个域名是预定义的，从 hosts
	 * 文件中可以看出
	 * 
	 * 整个127.* 网段通常被用作 loopback 网络接口的默认地址，按惯例通常设置为
	 * 127.0.0.1。这个地址在其他计算机上不能访问，就算你想访问，访问的也是自己，因为每台带有TCP/IP协议栈的设备基本上都有
	 * localhost/127.0.0. IP地址则是在网络中你的计算机的地址
	 * 
	 * 在有类IP地址的规定中，第一部分是1~126为A类地址，128~191为B类地址，那么中间留的127.0.0.1被称为本地回环地址或回送地址，
	 * 主要作用有两个： 一是测试本机的网络配置 ，能PING通127.0.0.1说明本机的网卡和IP协议安装都没有问题；另一个作用是某些SERVER/
	 * CLIENT的应用程序在运行时需调用服务器上的资源 ，一般要指定SERVER的IP地址
	 * ，但当该程序要在同一台机器上运行而没有别的SERVER时就可以把SERVER的资源装在本机
	 * ，SERVER的IP地址设为127.0.0.1也同样可以运行。
	 * 
	 * Windows操作系统具有自动填充 “.0”的功能，因此我就可将“127.0.0.1”变为“127.1”。
	 * 但是，这个“.0”的省略是有条件限制的，并不能任意省略
	 * 在Ping命令的应用中，只能将在IP地址的最后一部分十进制数字前出现的一个或多个“.0”省略，如把“ping
	 * 127.0.0.1”命令改写成“ping 127.1
	 * 
	 * 对于大多数习惯用localhost的来说，实质上就是指向127.0.0.1这个本地IP地址。在操作系统中有个配置文件
	 * (windows中路径为C:\
	 * WINDOWS\system32\drivers\etc\hosts,Unix/Linux路径为/etc/hosts)
	 * 将localhost与127.0.0.1 绑定在了一起。（本机次此配置有修改，改为10.163.86.113
	 * windows10.microdone.cn）
	 * 
	 * 最后，从开发度来看localhost是个域名，性质跟 “www.baidu.com”
	 * 差不多。不能直接绑定套接字，必须先gethostbyname转成IP才能绑定。127.0.0.1 是绑定在 loopback
	 * 接口上的地址，如果服务端套接字绑定在它上面，你的客户端程序就只能在本机访问。如果主机中存在多个网卡，分别连接不同的物理网络，比如
	 * 192.168.0.1/255.255.255.0 和 192.168.1.1/255.255.255.0，那么当你的服务端套接字绑到
	 * 192.168.0.1 这个地址上时，位于 192.168.1.* 网段的其他计算机是无法连接的，只有位于192.168.0.*
	 * 网段的计算机才能访问你的服务端程序
	 * 
	 * 
	 * 
	 */

}
