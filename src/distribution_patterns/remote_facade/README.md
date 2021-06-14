※ 클라이언트-서버 분산 환경.
※ 원격 파사드를 세션빈으로 구성해야 하지만
※ 테스트를 용이하게 하기 위해 원격 호출 상황을 RMI 로 구현했다.
※ 따라서 EJB 컨테이너가 없어 세션빈을 생성하지 못함.
※ POJO 를 EJB 처럼 흉내내어 작성함.

### 실행방법
1. run rmic
  1) open terminal
  2) cd ...\EnterprizeApplicationArchitecture\target\classes
  3) rmic distribution_patterns.remote_facade.RemoteServer
2. run rmiregistry
  1) open terminal
  2) cd ...\EnterprizeApplicationArchitecture\target\classes
  3) rmiregistry
3. run RemoteClient.java
  1) start new terminal to execute below command and cd ...\EnterprizeApplicationArchitecture\target\classes
  2) java distribution_patterns.remote_facade.RemoteServer
3. run GumballMonitorTestDrive.java
  1) java distribution_patterns.remote_facade.RemoteClient 1
  2) java distribution_patterns.remote_facade.RemoteClient 2





