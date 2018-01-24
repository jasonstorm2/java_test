package Chapter17_NetWork;

public class note1_descrip {
	/**
	 * 通信协议：计算机网络中现实通信必须遵守的协议 通信协议 负责对传输速率，传输代码，代码结构，传输控制步骤，出错控制等制定处理标准 通信协议
	 * 有三个部分组成：1.语义部分 2.语法部分 3.变换规则，用于决定通信双方的应答关系
	 * 
	 * ISO 的 OSI 模型，open system interconnection 即，开放系统互连参考模型，模型有七层
	 * TCP/IP 分层模型 四层
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
	 * IP 分为A,B,C,D,E五类 
	 * A类：10.0.0.0~10.255.255.255 
	 * B类：172.16.0.0~172.31.255.255
	 * C类：192.168.0.0~192.168.255.255
	 * 
	 * 端口：一个IP标识唯一的通讯实体，但通讯实体可以有 多个通讯程序同时提供网络服务，以端口区分。 端口
	 * 端口 是一个16位的整数，用于表示数据交给哪个通信程序处理。 因此，端口就是 应用程序与外界交流的出入口。 
	 * 端口 是一个抽象的软件结构，包括一些 数据结构 和 I/O缓冲区 
	 * 不同的应用程序处理不同的端口上的数据，同一台机器上，不能有两个程序使用同一个端口。 
	 * 端口号0~65535，分为三类：
	 * 1.公认端口 （Well Known Ports）：0~1023，它们绑定Binding 一些特定的服务。 
	 * 2.注册端口 （Registered
	 * Ports）：1024~49151 它们松散地绑定一些服务。应用程序通常应该使用这个范围内的端口。 
	 * 3.动态 和/或 私有端口 （Dynamic and/or Private Ports）: 49152~65535,应用程序使用的动态端口，一般程序不会主动使用这些端口。
	 * 
	 * IP+端口号，确定了一个程序在网络中的具体地址
	 * 
	 * 
	 */

}
