# 07단원: 화면 (Thymeleaf, 질문 목록/상세)

## 🧠 머릿속 그림 요약
브라우저 GET /question/list → QuestionController가 Service.getList()로 데이터를 얻어 Model에 담고 뷰 이름 "question_list" 반환 → ViewResolver가 templates/question_list.html을 찾음 → Thymeleaf가 템플릿 + Model 데이터를 결합(th:each 반복)해 완성 HTML 생성 → 브라우저에 표로 렌더.

## 📌 핵심 내용
- **@ResponseBody 없이 String 반환 = 뷰 이름**(2단원 "본문 vs 뷰"의 뷰 쪽). `return "question_list"` → `templates/question_list.html`.
- **Model**: 컨트롤러 → 뷰로 데이터 전달. `model.addAttribute("questionList", list)` → 템플릿 `${questionList}`.
- **Thymeleaf 문법**: `th:each`(반복), `th:text`(텍스트 치환), `@{|/question/detail/${question.id}|}`(URL 생성), `#temporals.format(date,'yyyy-MM-dd HH:mm')`(날짜 포맷).
- **@PathVariable**: `/detail/{id}`의 {id}를 메서드 인자로.
- **의존성**: `spring-boot-starter-thymeleaf` 추가.
- **시드 데이터**: TestInitData(@Configuration + @Profile("dev") + ApplicationRunner)가 dev 프로필 기동 시 질문 2건 저장. test 프로필에선 미실행 → 기존 테스트 영향 없음.
- (미룸) 상세 페이지의 답변 목록/작성 폼은 연관관계(@OneToMany) 준비 후 추가.

## 🔎 디버깅 정리 (실제 값)
- build: BUILD SUCCESSFUL. 기존 테스트 3개 유지.
- dev 기동 후:
  - `GET /question/list` → HTTP 200, text/html, 표 2행:
    - `1 | sbb가 무엇인가요? | 2026-08-19 15:38` (링크 /question/detail/1)
    - `2 | 스프링부트 모델 질문입니다. | 2026-08-19 15:38`
  - `GET /question/detail/1` → HTTP 200, 제목 "sbb가 무엇인가요?", 내용 "sbb에 대해서 알고 싶습니다."

## ✅ 완료 상태
- 빌드+실행(렌더) 검증 통과. 커밋 `7단원: Thymeleaf 화면`으로 원격 반영.
- (다음) 답변 기능: @OneToMany 연관관계 + 답변 컨트롤러/폼, 또는 질문 등록 폼.
