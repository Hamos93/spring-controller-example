# spring-controller-example
### 스프링 MVC Controller의 특징

- HttpServletRequest, HttpServletResponse를 거의 사용할 필요 없이 필요한 기능 구현
- 다양한 타입의 파라미터 처리, 다양한 타입의 리턴 타입 사용 가능
- GET 방식, POST 방식 등 전송 방식에 대한 처리를 어노테이션으로 처리 가능
- 상속/인터페이스 방식 대신 어노테이션만으로도 필요한 설정 가능



### @Controller

- 해당 어노테이션이 적용된 클래스가 **컨트롤러**임을 명시
  - 해당 클래스는 자동으로 스프링의 **객체(Bean)**으로 등록
  - servlet-context.xml에서 < context : component-scan > 태그를 이용해서 지정된 패키지를 스캔하도록 설정
  - 해당 패키지에 선언된 클래스들을 조사하면서 스프링에서 **객체(Bean)** 설정에 사용되는 어노테이션들을 가진 클래스들을 파악하고 필요하다면 이를 객체로 생성해서 관리



### @RequestMapping

- 현재 클래스의 모든 메서드들의 기본적인 URL 경로 지정
- **클래스의 선언**과 **메서드 선언**에 사용

- @RequestMapping의 경우 몇 가지 속성을 추가 가능
  - 가장 많이 사용하는 속성은 method 속성
  - **GET 방식**, **POST 방식**을 구분할 때 이용
- 스프링 4.3 버전부터는 @RequestMapping의 축약형인 **@GetMapping**, **@PostMapping** 사용 가능



### Controller의 파라미터 수집

- Controller 작성 시 가장 편리한 기능은 **파라미터 자동 수집 기능**
  - 이 기능을 이용하면 매번 request.getParameter( )를 이용하는 불편함을 제거
- Controller가 파라미터를 수집하는 방식은 파라미터 타입에 따라 자동으로 변환되는 방식을 이용
  - @RequestParam은 파라미터로 사용된 변수 이름과 전달되는 파라미터 이름이 다른 경우에 유용하게 사용



### Model

- Controller 메서드 작성 시 특별히 **Model**이라는 타입을 파라미터로 지정 가능

- Model 객체는 JSP에게 컨트롤러에서 생성된 데이터를 담아서 전달하는 역할
  - Model은 모델2 방식에서 request.setAttribute( )와 유사한 역할

- 메서드의 파라미터에 Model 타입이 지정된 경우 스프링은 특별하게 Model 타입의 객체를 만들어서 메서드에 주입

- 파라미터로 전달된 데이터는 존재하지 않지만 화면에서 필요한 데이터를 전달하기 위해 사용



### Controller의 리턴 타입

- void: 호출하는 URL과 동일한 이름의 jsp를 의미

- String: jsp를 이용하는 경우 jsp 파일의 경로와 파일명을 나타내기 위해 사용
- VO, DTO 타입: 주로 JSON 타입의 데이터를 만들어서 반환하는 용도로 사용
- ResponseEntity 타입: response 할 때 Http 헤더 정보와 내용을 가공하는 용도로 사용