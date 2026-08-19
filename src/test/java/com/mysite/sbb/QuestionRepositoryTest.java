package com.mysite.sbb;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest
class QuestionRepositoryTest {
    @Autowired
    private QuestionRepository questionRepository;

    @Test
    @DisplayName("질문 2개를 저장한 뒤 전체/단건 조회한다")
    void saveAndFind() {
        // 1) 저장 (INSERT)
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("스프링부트 모델 질문입니다.");
        q2.setContent("id는 자동으로 생성되나요?");
        q2.setCreateDate(LocalDateTime.now());
        questionRepository.save(q2);

        // 2) 전체 조회 (SELECT)
        List<Question> all = questionRepository.findAll();
        assertEquals(2, all.size());
        assertEquals("sbb가 무엇인가요?", all.get(0).getSubject());

        // 3) 단건 조회 (SELECT ... WHERE id = ?)
        int firstId = all.get(0).getId();
        Question found = questionRepository.findById(firstId).orElseThrow();
        assertEquals("sbb가 무엇인가요?", found.getSubject());
    }
}
