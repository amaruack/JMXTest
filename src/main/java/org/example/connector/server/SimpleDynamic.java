package org.example.connector.server;

import javax.management.*;

public class SimpleDynamic extends NotificationBroadcasterSupport implements DynamicMBean {
 
    public SimpleDynamic() { 
        buildDynamicMBeanInfo(); 
    }

    private void buildDynamicMBeanInfo() {
        System.out.println("!! buildDynamicMBeanInfo");
    }


    @Override
    public Object getAttribute(String attribute) throws AttributeNotFoundException, MBeanException, ReflectionException {
        return null;
    }

    @Override
    public void setAttribute(Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {

    }

    @Override
    public AttributeList getAttributes(String[] attributes) {
        return null;
    }

    @Override
    public AttributeList setAttributes(AttributeList attributes) {
        return null;
    }

    @Override
    public Object invoke(String actionName, Object[] params, String[] signature) throws MBeanException, ReflectionException {
        return null;
    }

    @Override
    public MBeanInfo getMBeanInfo() {
        // Attributes
        MBeanAttributeInfo[] attributes = new MBeanAttributeInfo[]{
                new MBeanAttributeInfo("Attribute", "int", "An example attribute", true, true, false)
        };

        // Constructors
        MBeanConstructorInfo[] constructors = new MBeanConstructorInfo[]{
                new MBeanConstructorInfo("Default constructor", "The default constructor", new MBeanParameterInfo[]{})
        };

        // Operations
        MBeanOperationInfo[] operations = new MBeanOperationInfo[]{
                new MBeanOperationInfo("reset", "Reset the attribute to 0", null, "void", MBeanOperationInfo.ACTION)
        };

        // Create and return the MBeanInfo
        return new MBeanInfo(
                this.getClass().getName(),
                "Simple Example MBean",
                attributes,
                constructors,
                operations,
                null  // notifications (none in this example)
        );
    }
}