# JMXTest

JMX 에 대해서 상세히 공부해 보자
https://docs.oracle.com/en/java/javase/17/jmx

### JMX 란 ?

#### 주요 구성요소
1. MBean (Managed Bean)
- 애플리케이션의 특정 로직 또는 기능을 나타내는 Java 객체. 
- MBeans는 JMX 클라이언트에 의해 접근 및 조작될 수 있는 리소스의 Java 클래스를 의미함
- MBeans는 표준 MBean, MXBean, 동적 MBean, open MBean, 모델 MBean 등 여러 유형이 있음
2. MBean Server
- 모든 MBeans는 MBean 서버에 등록되어야 하며, 이 서버는 JMX 에이전트의 핵심 컴포넌트로 MBeans의 관리를 담당함
- MBean 서버는 MBeans에 대한 액세스를 제공하고, 이들 MBeans의 관리 작업을 수행함
3. JMX Agent
- JMX 에이전트는 MBean 서버와 이를 포함하는 다른 프로토콜 서버와 관련된 다른 서비스로 구성되어 있음
- JMX 에이전트는 원격 관리 애플리케이션과 MBean 사이의 다리 역할을 하며, 원격 클라이언트가 MBeans를 관리하고 모니터링할 수 있음 
4. JMX Connector
- JMX 커넥터는 원격 JMX 클라이언트와 JMX 에이전트 간의 통신 메커니즘을 제공
- 다양한 통신 프로토콜을 사용하여 JMX API를 원격 클라이언트에게 노출
5. Instrumentation
- 애플리케이션 또는 장치에 대한 통계 및 상태 정보를 제공하는 메커니즘
- 측정 및 제어 작업을 제공하여 자원의 상태를 모니터링하고 관리가 가능함

#### 주요기능 및 이점
- **모니터링**: 실행 중인 애플리케이션의 상태와 성능을 모니터링
- **알림**: 정의된 임계값을 초과하거나 특정 이벤트가 발생할 때 알림
- **리소스 관리**: 애플리케이션의 구성 속성을 수정하고, 서비스를 시작/중지하고, 에러를 대응하는 등의 관리 작업을 수행
- **분산 네트워크**: JMX는 원격 및 분산된 네트워크에서의 자원 관리를 지원
- **어플리케이션 확장성**: 애플리케이션에 새로운 기능을 추가하거나 기존 기능을 변경할 때 JMX 아키텍처를 사용하면 관련 관리 및 모니터링 기능도 쉽게 업데이트하거나 확장 가능 함

### 공부 ! 순서 !
1. MBean
   1. MBean
   2. MXBean
   3. MBeanInfo(DynamicMBean) 
2. Connector
   1. MbeanServer
   2. JMX Connector Server
   3. JMX Connector Client
   4. Client Listener

