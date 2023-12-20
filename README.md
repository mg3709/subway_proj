<div align=center><h1>📚STACKS</h1></div>

<div align=center>
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
  <img src="https://img.shields.io/badge/spring boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
  <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">
  <img src="https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=white">
  <br>

  <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white">
  <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white">
  <img src="https://img.shields.io/badge/styled%20components-DB7093?style=for-the-badge&logo=styled-components&logoColor=white">
  <br>

  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
  <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
  <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
  <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">
  <img src="https://img.shields.io/badge/socket.io-010101?style=for-the-badge&logo=socket.io&logoColor=white">
</div>

<h2>🚇 프로젝트 소개</h2>

**<길 찾기 프로젝트>**

<p>◽ 막차 시간 이후의 경로 검색</p>
<p>◽ 지하철 외 다른 교통수단의 활용</p>
<p>◽ 길 찾기 알고리즘 활용</p>
<p>◽ 배포 주소 </p>


<h2>🚇 설계 주안점</h2>

  <p>1. 지하철역의 정보 CSV파일을 DB테이블에 저장</p>
  <p>2. 불러온 시간표로 최단거리와 최소환승을 구현하기 위해 다익스트라 알고리즘을 사용해 역간이동 소요시간을 실시간으로 계산</p>
  <p>3. 지하철역 이름을 검색하고 자동완성도 가능한 검색기능 제공</p>
  <p>4. 지하철 노선도 화면을 축소 / 확대 하고 드래그로 이동할 수 있는 기능 제공</p>
  <p>5. Socket을 이용한 실시간 채팅 구현</p>
  <p>6. KakaoMap을 이용한 대여가능한 자전거의 위치정보 제공</p>


<h2>🚇 구현 기능</h2>

- 자세한 설명
  - https://drive.google.com/file/d/1_LszlZ8HR7r0SiCoBhqRSRmRdR9piAsy/view?usp=sharing

- 홈
  
  - 배경화면에 동영상 삽입
  - nav bar 삽입


- About 페이지
  
  - 웹사이트에 대한 설명들이 적혀 있는 페이지
  - Framer-motion을 사용한 애니메이션 기능
 

- Subway 페이지
  
  - 지하철 노선도를 표시
  - PanZoom을 이용한 지하철 노선도 조작 기능
  - 지하철역을 Hover 했을 경우 표시 되는 노선도 내부 좌표 기능
  - Hover 클릭시 Modal 창에 지하철역의 정보 표시
  - 출발역과 도착역 클릭시 최소환승, 최소시간 정보 출력
  - 지하철 역 검색 기능
 

- Chat 페이지
  
  - socket을 사용한 실시간 채팅 기능


- Kakamap 페이지

  - KakaoMap의 api를 이용한 지도 표시
  - KakaoMap의 api를 이용한 대여가능한 자전거 표시


<h2>🚇 개발환경</h2>

  <p>◽ OS : <img src="https://img.shields.io/badge/windows 10-0078D6?style=for-the-badge&logo=windows10&logoColor=white" align=center> </p>
  <p>◽ IDE : <img src="https://img.shields.io/badge/vs code-007ACC?style=for-the-badge&logo=visual-studio-code&logoColor=white" align=center> <img src="https://img.shields.io/badge/intelli j-000000?style=for-the-badge&logo=intellij-idea&logoColor=white" align=center></p>
  <p>◽ Language : <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white" align=center> <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black" align=center></p>
  <p>◽ FrontEnd : <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white" align=center> <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white" align=center></p>
  <p>◽ FrameWork / Library : <img src="https://img.shields.io/badge/spring boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" align=center> <img src="https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=white" align=center></p>
  <p>◽ DB : <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white" align=center></p>


<h2>🚇 사용 라이브러리</h2>



```
yarn add framer-motion
yarn add hangul
yarn add react-easy-panzoom
yarn add react-router-dom
yarn add styled-components
```

