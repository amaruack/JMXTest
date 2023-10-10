package org.example.connector.client;

import org.example.connector.server.SimpleStandardMBean;

import javax.management.Attribute;
import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class Client {
 
  public static void main(String[] args) { 
    try { 
      // Create an RMI connector client 
      // 
      JMXServiceURL url = new JMXServiceURL(
         "service:jmx:rmi:///jndi/rmi://localhost:9999/server"); 
      JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
      MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
      waitForEnterPressed();       
       
      // Get domains from MBeanServer 
      // 
      String domains[] = mbsc.getDomains(); 
      for (int i = 0; i < domains.length; i++) { 
          System.out.println("Domain[" + i + "] = " + domains[i]); 
      } 
      waitForEnterPressed();       
 
//      String domain = mbsc.getDefaultDomain();
      String domain = "org.example";

      // Create SimpleStandard MBean
      ObjectName stdMBeanName =
             new ObjectName(domain +":type=SimpleStandard,name=2"); 
      mbsc.createMBean("org.example.connector.server.SimpleStandard", stdMBeanName, null, null);
      waitForEnterPressed();       
             
      // Create SimpleDynamic MBean 
      ObjectName dynMBeanName = 
          new ObjectName(domain +":type=SimpleDynamic,name=2"); 
      echo("\nCreate SimpleDynamic MBean..."); 
      mbsc.createMBean("org.example.connector.server.SimpleDynamic", dynMBeanName, null, null);
      waitForEnterPressed(); 
       
      // Get MBean count 
      echo("\nMBean count = " + mbsc.getMBeanCount()); 
 
      // Query MBean names 
      echo("\nQuery MBeanServer MBeans:"); 
      Set names = mbsc.queryNames(null, null);
      for (Iterator i = names.iterator(); i.hasNext(); ) {
      echo(     "ObjectName = " + (ObjectName) i.next()); 
      } 
      waitForEnterPressed(); 
       
      mbsc.setAttribute(stdMBeanName, 
                        new Attribute("State", "changed state"));
 
      SimpleStandardMBean proxy = JMX.newMBeanProxy(
          mbsc, stdMBeanName, SimpleStandardMBean.class, true); 
      echo("\nState = " + proxy.getState()); 
 
      ClientListener listener = new ClientListener(); 
      mbsc.addNotificationListener(stdMBeanName, listener, null, null); 
 
      mbsc.invoke(stdMBeanName, "reset", null, null);

      waitForEnterPressed();
//      mbsc.removeNotificationListener(stdMBeanName, listener);
//      mbsc.unregisterMBean(stdMBeanName);

//      [...]

//      jmxc.close();
    } catch (Exception e) { 
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

} 
