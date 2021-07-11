# 암시적 오프라인 잠금 테스트
### 1. 잠금 얻기
http://localhost:8080/lock?command=EditCustomer&customer_id=1
### 2. 얻은 잠금으로 비즈니스 로직 수행하고 잠금 해제
http://localhost:8080/lock?command=SaveCustomer&customer_name=수정된이름
