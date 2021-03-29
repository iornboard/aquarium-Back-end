남현수 - 21.03.18

    1. 프로젝트 백앤드 부분을 초기화 해서 다시 시작
    2. 그 전까지의 모든 설정 (디렉터리, git, 의존성 , DB연동)을 모두 끝냄 
    3. 간단하게 회원가입을 할 수 있도록 controller에 회원가입 서비스 로직을 간단히 만듬 
        -> 간단히  DB에 넣을 수 있도록


남현수 - 21.03.28

    1. spring security 와 oauth2 로그인(구글,페이스북,네이버)을 구현함
    2. 개성사항
        -> [PrincipalOauth2UserService]
            1) DB내용을 확인하고 중복된 계정이 있으면 막아햐 하는데, 이가 작동하지 않음 따라서 중복되어 계정이 생성됨
            2) Oauth 로그인에서 provider는 PW를 주지않기 때문에 이를 어떻게 할지 정해야 함


남현수 - 21.03.29

    1. jwt 구현 이전에 사용자 필터를 구현 할 수있도록 필터 부분을 추가함
    2. swagger를 추가함  -> [ http://localhost:8080/swagger-ui.html ]
    3. 리엑트와 연동되오록 CORS 이슈 해결을 위해서  http.cors().configurationSource 코드를 작성