package SSHTest.jdbc;

import SSHTest.config.GameServerConfig;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.File;
import java.io.IOException;

/**
 * SSH端口转发
 */
public class SSHService {
    private static final String GAME_SERVER_CONFIG_XML_PATH = "." + File.separator + "resource" + File.separator + "gameServerConfig.xml";
    private GameServerConfig gameServerConfig;


     Integer lport;//本地端口
//     String rhost;//远程数据库服务器
     int rport;//远程数据库服务端口

     String user;//SSH连接用户名
     String password;//SSH连接密码
     String host;//SSH服务器
     int port;//SSH访问端口

    public static SSHService getInstance(){
        return new SSHService();
    }



    public void sshRun(String rhost) throws JDOMException, IOException {
        //开始读取配置
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(new File(GAME_SERVER_CONFIG_XML_PATH));
        Element root = doc.getRootElement();

        //加载公共配置数据
        gameServerConfig = new GameServerConfig(root.getChild("commonConfig"));

        lport = gameServerConfig.getLport();
//        rhost = gameServerConfig.getRhost();
        rport = gameServerConfig.getRport();
        user = gameServerConfig.getUser();
        password = gameServerConfig.getPassword();
        host = gameServerConfig.getHost();
        port = gameServerConfig.getPort();


        JSch jsch = new JSch();
        Session session = null;
        try {
            session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            // step1：建立ssh连接
            session.connect();
//            System.out.println("ssh版本："+session.getServerVersion());//这里打印SSH服务器版本信息
            //step2： 设置SSH本地端口转发，本地转发到远程
            int assinged_port = session.setPortForwardingL(lport, rhost, rport);
            System.out.println("localhost:" + assinged_port + " -> " + rhost + ":" + rport);

            Connect.getInstance().connectMysql("");


        } catch (Exception e) {
            if (null != session) {
                //关闭ssh连接
                session.disconnect();
            }
            e.printStackTrace();
        }finally {
            if (null != session) {
                //关闭ssh连接
                session.disconnect();
            }
        }
    }

}

