package cn.jianml.foundation.client;

import cn.jianml.foundation.enums.ResultEnum;
import cn.jianml.foundation.exception.BizException;
import com.jcraft.jsch.*;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * JSch客户端
 *
 * @author wujian
 * @date 2022年01月23日
 */
public class JSchClient {
    private String host;

    private int port;

    private String username;

    private String password;

    /**
     * 超时时间： 30s
     */
    private int timeOut = 30 * 1000;

    private JSch jSch;

    private Session session;

    public JSchClient(String host, int port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.session = this.getSession();
    }

    /**
     * 执行命令
     *
     * @param command 命令
     * @return 执行结果
     */
    public String executeCommand(String command) {
        String result = null;
        ChannelExec channelExec = null;
        try {
            channelExec = (ChannelExec) session.openChannel("exec");
        } catch (JSchException e) {
            throw new BizException(ResultEnum.JSCH_ERROR.getCode(), e.getMessage());
        }
        StringBuilder inputStringSB = new StringBuilder();
        try (InputStream inputStream = channelExec.getInputStream();
             BufferedReader inputReader = new BufferedReader(new InputStreamReader(inputStream));
             InputStream errorStream = channelExec.getErrStream();
             BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream))) {
            channelExec.setCommand(command);
            channelExec.connect();
            String line = null;
            while ((line = inputReader.readLine()) != null) {
                inputStringSB.append(line);
            }

            result = inputStringSB.toString();
            if (StringUtils.isBlank(result)) {
                StringBuilder errorStreamSB = new StringBuilder();
                while ((line = errorReader.readLine()) != null) {
                    errorStreamSB.append(line);
                }
                result = errorStreamSB.toString();
            }
        } catch (Exception e) {
            throw new BizException(ResultEnum.JSCH_ERROR.getCode(), e.getMessage());
        }

        this.disconnectChannel(channelExec);
        return result;
    }

    /**
     * 关闭会话
     */
    public void close() {
        if (this.session != null && this.session.isConnected()) {
            this.session.disconnect();
        }
    }

    private Session getSession() {
        this.jSch = new JSch();
        Session session = null;
        try {
            session = jSch.getSession(this.username, this.host, this.port);
            session.setPassword(this.password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect(this.timeOut);
        } catch (JSchException e) {
            throw new BizException(ResultEnum.JSCH_ERROR.getCode(), e.getMessage());
        }

        return session;
    }

    private void disconnectChannel(Channel channel) {
        if (channel.isConnected()) {
            channel.disconnect();
        }
    }
}
