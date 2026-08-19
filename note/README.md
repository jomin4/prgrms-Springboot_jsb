# 학습 진도표 — SBB (Spring Boot 질문/답변 게시판)

주 자료: https://www.slog.gg/p/14163 · 참조: https://github.com/jhs512/p-14163-1

| 단원 | 제목 | 핵심 개념 | 상태 |
|------|------|-----------|------|
| 01 | 프로젝트 뼈대 | Spring Boot 부팅, Gradle, @SpringBootApplication | ✅ 완료 |
| 02 | 첫 컨트롤러 | @Controller, @GetMapping, @ResponseBody, 요청/응답 왕복 | ✅ 완료 |
| 03 | JPA + 엔티티 | Question 엔티티, JPA/H2, DB 테이블 자동 생성, 프로필 | ✅ 완료 |
| 04 | 연관관계 | Answer 엔티티, @ManyToOne(1:N), 외래키 | ✅ 완료 |
| 05 | 레포지토리 + 테스트 | JpaRepository, 저장/조회, JUnit, 바인딩 값 | ✅ 완료 |
| 06 | 서비스 계층 | @Service, DI(생성자 주입), DataNotFoundException | ✅ 완료 |
| 07 | 화면(Thymeleaf) | Controller + 템플릿, Model, th:each, 목록/상세 | ✅ 완료 |
| 08 | 답변 기능 | @OneToMany 양방향, @PostMapping, PRG 패턴 | ✅ 완료 |
| 09 | JPA 쿼리 메서드 | findBySubject 등 이름 기반 쿼리 | ⬜ 예정 |
| 10 | 마무리 | 루트 리다이렉트, 폴리시 | ⬜ 예정 |

> 각 단원 상세는 `note/NN-제목.md` 참고.

---

## ▶️ 다음에 이어서 (재개 가이드)

- **진행률**: 8/약 10단원 완료 (약 80%). 핵심 뼈대(엔티티→레포→서비스→컨트롤러→화면→답변)는 모두 통과.
- **다음 단원 = 09 JPA 쿼리 메서드**: `QuestionRepository`에 `findBySubject`, `findBySubjectAndContent`, `findBySubjectLike` 추가 → 메서드 이름이 어떻게 `WHERE`절로 번역되는지 실제 생성 쿼리·바인딩 값으로 확인 (강사 커밋 016~019 참고).
- **그다음 = 10 마무리**: `MainController`의 `/` → `redirect:/question/list`, 스타일/템플릿 폴리시 → 강사 레포 완주.

### 로컬에서 직접 실행해 보려면
```bash
./gradlew bootRun
```
브라우저에서 http://localhost:8080/question/list 접속 (dev 프로필이 질문 2건 자동 시드).
빌드/테스트: `./gradlew build`

### 운영 메모
- dev는 H2 파일 DB(`db_dev.*`, gitignore됨). 재실행이 꼬이면 실행 중인 java 종료 후 `db_dev*.db` 삭제.

