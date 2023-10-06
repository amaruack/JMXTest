package org.example.connector;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.net.MalformedURLException;

public class Server {

    public static void main(String[] args) {
        try {

            MBeanServer mbs = MBeanServerFactory.createMBeanServer();
            waitForEnterPressed();

            String domain = mbs.getDefaultDomain();
            waitForEnterPressed();

            String mbeanClassName = "SimpleStandard";
            String mbeanObjectNameStr = domain + ":type=" + mbeanClassName + ",name=1";
            ObjectName mbeanObjectName = createSimpleMBean(mbs, mbeanClassName, mbeanObjectNameStr);
            waitForEnterPressed();

            printMBeanInfo(mbs, mbeanObjectName, mbeanClassName);
            waitForEnterPressed();

            manageSimpleMBean(mbs, mbeanObjectName, mbeanClassName);
            waitForEnterPressed();

            mbeanClassName = "SimpleDynamic";
            mbeanObjectNameStr = domain + ":type=" + mbeanClassName + ",name=1";
            mbeanObjectName = createSimpleMBean(mbs, mbeanClassName, mbeanObjectNameStr);
            waitForEnterPressed();

            printMBeanInfo(mbs, mbeanObjectName, mbeanClassName);
            waitForEnterPressed();

            manageSimpleMBean(mbs, mbeanObjectName, mbeanClassName);
            waitForEnterPressed();

            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/server");
            JMXConnectorServer cs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mbs);
            cs.start();
            waitForEnterPressed();
            cs.stop();



        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }