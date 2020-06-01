package com.taobao.arthas.boot;

import com.taobao.arthas.common.AnsiLog;
import com.taobao.middleware.cli.CLI;
import com.taobao.middleware.cli.CommandLine;
import com.taobao.middleware.cli.annotations.*;

import java.io.File;
import java.util.Arrays;

/**
 * 测试启动类
 */
@Name("arthas-boot")
@Summary("Bootstrap Arthas")
@Description("EXAMPLES:\n" + "  java -jar arthas-boot.jar <pid>\n" + "  java -jar arthas-boot.jar --target-ip 0.0.0.0\n"
        + "  java -jar arthas-boot.jar --telnet-port 9999 --http-port -1\n"
        + "  java -jar arthas-boot.jar --tunnel-server 'ws://192.168.10.11:7777/ws'\n"
        + "  java -jar arthas-boot.jar --tunnel-server 'ws://192.168.10.11:7777/ws' --agent-id bvDOe8XbTM2pQWjF4cfw\n"
        + "  java -jar arthas-boot.jar --stat-url 'http://192.168.10.11:8080/api/stat'\n"
        + "  java -jar arthas-boot.jar -c 'sysprop; thread' <pid>\n"
        + "  java -jar arthas-boot.jar -f batch.as <pid>\n"
        + "  java -jar arthas-boot.jar --use-version 3.3.1\n"
        + "  java -jar arthas-boot.jar --versions\n"
        + "  java -jar arthas-boot.jar --select arthas-demo\n"
        + "  java -jar arthas-boot.jar --session-timeout 3600\n" + "  java -jar arthas-boot.jar --attach-only\n"
        + "  java -jar arthas-boot.jar --repo-mirror aliyun --use-http\n" + "WIKI:\n"
        + "  https://alibaba.github.io/arthas\n")
public class BootstrapTest {
    //端口
    private static final int DEFAULT_TELNET_PORT = 3658;
    private static final int DEFAULT_HTTP_PORT = 8563;
    private static final String DEFAULT_TARGET_IP = "172.0.0.1";

    //默认包路径
    private static File ARTHAS_LIB_DIR;
    private static boolean help = false;

    private long pid = -1;
    private String targetIp = DEFAULT_TARGET_IP;
    private int telnentPort = DEFAULT_TELNET_PORT;
    private int httpPort = DEFAULT_HTTP_PORT;

    private Long sessionTimeout;
    private Integer height = null;
    private Integer width = null;

    private boolean verbose = false;

    private String arthasHome;
    private String userVersion;
    private boolean version;

    private String repoMirror;

    private boolean useHttp = false;

    private boolean attachOnly = false;

    private String command;

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getTargetIp() {
        return targetIp;
    }

    public void setTargetIp(String targetIp) {
        this.targetIp = targetIp;
    }

    public int getTelnentPort() {
        return telnentPort;
    }

    public void setTelnentPort(int telnentPort) {
        this.telnentPort = telnentPort;
    }

    public int getHttpPort() {
        return httpPort;
    }

    public void setHttpPort(int httpPort) {
        this.httpPort = httpPort;
    }

    public Long getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(Long sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public String getCommand() {
        return command;
    }

    @Option(shortName = "c", longName = "command")
    @Description("Command to execute,c")
    public void setCommand(String command) {
        this.command = command;
    }

    public static void main(String[] args) {
        //测试不加这个类
        Package bootstrapPackageTest = BootstrapTest.class.getPackage();
        System.out.println(bootstrapPackageTest);
        if (bootstrapPackageTest != null) {
            //获得版本
            String arthaasVersion = bootstrapPackageTest.getImplementationVersion();
            System.out.println(arthaasVersion);
            if (arthaasVersion != null) {
                AnsiLog.info("arthas-boot version" + arthaasVersion);

            }
        }

        String mavenMetaData = null;
        BootstrapTest bootstrapTest = new BootstrapTest();
        CLI cli = CLIConfigurator.define(BootstrapTest.class);
        CommandLine commandLine = cli.parse(Arrays.asList(args));
        System.out.println(commandLine);
  try {
      CLIConfigurator.inject(commandLine, bootstrapTest);
  }catch (Throwable e){
      e.printStackTrace();
      System.out.println(usage(CLI cli));
  }



    }
    private static String  usage(CLI cli){

    }
}
