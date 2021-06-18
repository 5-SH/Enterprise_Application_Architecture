※ 클라이언트-서버 분산 환경.
※ 원격 파사드를 세션빈으로 구성해야 하지만
※ 테스트를 용이하게 하기 위해 원격 호출 상황을 RMI 로 구현했다.
※ 따라서 EJB 컨테이너가 없어 세션빈을 생성하지 못함.
※ POJO 를 EJB 처럼 흉내내어 작성함.
<br/>
### 실행방법
1. run rmic
  1-1. open terminal
  1-2. cd ...\EnterprizeApplicationArchitecture\target\classes
  1-3. rmic distribution.remote_facade.AlbumServiceBean
2. run rmiregistry
  2-1. open terminal
  2-2. cd ...\EnterprizeApplicationArchitecture\target\classes
  2-3. rmiregistry
3. run RemoteServer.java
  3-1. start new terminal to execute below command and cd ...\EnterprizeApplicationArchitecture\target\classes
  3-2. java distribution.remote_facade.RemoteServer
4. run RemoteClient.java
  4-1. java distribution.remote_facade.RemoteClient
  4-2. 한 줄 씩 사용자 입력 받음. 커맨드 종류는 getAlbum, createAlbum, updateAlbum 이 있고
   id 는 1, 2 를 사용






