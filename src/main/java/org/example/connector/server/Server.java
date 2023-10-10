package org.example.connector.server;

import org.example.mbeans.Hello;

import javax.management.*;
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

            ObjectName name = new ObjectName("org.example:type=SimpleStandard,name=1");
            SimpleStandard mbean = new SimpleStandard();
            mbs.registerMBean(mbean, name);

//            String domain = mbs.getDefaultDomain();
            String domain = "org.example";

            String mbeanClassName = "org.example.connector.server.SimpleStandard";
            String mbeanObjectNameStr = domain + ":type=" + mbeanClassName + ",name=1";
            ObjectName mbeanObjectName = createSimpleMBean(mbs, mbeanClassName, mbeanObjectNameStr);
            waitForEnterPressed();

            printMBeanInfo(mbs, mbeanObjectName, mbeanClassName);
            waitForEnterPressed();

            manageSimpleMBean(mbs, mbeanObjectName, mbeanClassName);
            waitForEnterPressed();

            mbeanClassName = "org.example.connector.server.SimpleDynamic";
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
        } catch (MalformedObjectNameException e) {
            throw new RuntimeException(e);
        } catch (NotCompliantMBeanException e) {
            throw new RuntimeException(e);
        } catch (InstanceAlreadyExistsException e) {
            throw new RuntimeException(e);
        } catch (MBeanRegistrationException e) {
            throw new RuntimeException(e);
        }
    }


    private static ObjectName createSimpleMBean(MBeanServer mbs,
                                                String mbeanClassName,
                                                String mbeanObjectNameStr) {
        echo("\n>>> Create the " + mbeanClassName +
                " MBean within the MBeanServer");
        echo("ObjectName = " + mbeanObjectNameStr);
        try {
            ObjectName mbeanObjectName =
                    ObjectName.getInstance(mbeanObjectNameStr);
            mbs.createMBean(mbeanClassName, mbeanObjectName);
            return mbeanObjectName;
        } catch (Exception e) {
            echo("!!! Could not create the " +
                    mbeanClassName + " MBean !!!");
            e.printStackTrace();
            echo("\nEXITING...\n");
            System.exit(1);
        }
        return null;
    }

    private static void printMBeanInfo(MBeanServer mbs,
                                       ObjectName mbeanObjectName,
                                       String mbeanClassName) {
        MBeanInfo info = null;
        try {
            info = mbs.getMBeanInfo(mbeanObjectName);
        } catch (Exception e) {
            echo("!!! Could not get MBeanInfo object for " +
                    mbeanClassName + " !!!");
            e.printStackTrace();
            return;
        }

        MBeanAttributeInfo[] attrInfo = info.getAttributes();
        if (attrInfo.length > 0) {
            for (int i = 0; i < attrInfo.length; i++) {
                echo(" ** NAME:    " + attrInfo[i].getName());
                echo("    DESCR:   " + attrInfo[i].getDescription());
                echo("    TYPE:    " + attrInfo[i].getType() +
                        "READ: " + attrInfo[i].isReadable() +
                        "WRITE: " + attrInfo[i].isWritable());
            }
        } else echo(" ** No attributes **");
    }


    private static void manageSimpleMBean(MBeanServer mbs,
                                          ObjectName mbeanObjectName,
                                          String mbeanClassName) {
        try {
            printSimpleAttributes(mbs, mbeanObjectName);

            Attribute stateAttribute = new Attribute("State",
                    "new state");
            mbs.setAttribute(mbeanObjectName, stateAttribute);

            printSimpleAttributes(mbs, mbeanObjectName);

            echo("\n    Invoking reset operation...");
            mbs.invoke(mbeanObjectName, "reset", null, null);

            printSimpleAttributes(mbs, mbeanObjectName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printSimpleAttributes(
            MBeanServer mbs,
            ObjectName mbeanObjectName) {
        try {
            String State =
                    (String) mbs.getAttribute(mbeanObjectName, "State");
            Integer NbChanges =
                    (Integer) mbs.getAttribute(mbeanObjectName,
                            "NbChanges");
        } catch (Exception e) {
            echo("!!! Could not read attributes !!!");
            e.printStackTrace();
        }
    }


    public static void echo(String message) {
        System.out.println(message);
    }

    public static void waitForEnterPressed(){
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    delete from contentinstance where timeofoccurrencelong > 1675236554000 and timeofoccurrencelong < 1676446154000;
}