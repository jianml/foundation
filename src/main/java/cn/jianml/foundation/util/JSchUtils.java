package cn.jianml.foundation.util;

import cn.jianml.foundation.client.JSchClient;

/**
 * JSch工具类
 *
 * @author wujian
 * @date 2022年01月23日
 */
public class JSchUtils {
    private JSchUtils() {
    }

    /**
     * 执行命令
     */
    public static String executeCommand(String host, int port, String username, String password, String command) {
        JSchClient jSchClient = new JSchClient(host, port, username, password);
        String result = jSchClient.executeCommand(command);
        jSchClient.close();
        return result;
    }
}
