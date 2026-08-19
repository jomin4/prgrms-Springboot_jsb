package com.mysite.sbb;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
class QuestionServiceTest {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionRepository questionRepository;

    @Test
    @DisplayName("getList / getQuestion / 없는 id는 예외")
    void serviceLayer() {
        // given: 질문 1개 저장
        Question q = new Question();
        q.setSubject("서비스 계층 테스트");
        q.setContent("컨트롤러가 아니라 서비스가 로직을 담당한다");
        q.setCreateDate(LocalDateTime.now());
        questionRepository.save(q);

        // 1) 목록 조회
        List<Question> list = questionService.getList();
        assertEquals(1, list.size());

        // 2) 단건 조회 (존재)
        int id = list.get(0).getId();
        Question found = questionService.getQuestion(id);
        assertEquals("서비스 계층 테스트", found.getSubject());

        // 3) 없는 id 조회 -> DataNotFoundException
        DataNotFoundException ex = assertThrows(
                DataNotFoundException.class,
                () -> questionService.getQuestion(9999)
        );
        assertEquals("question not found", ex.getMessage());
    }
}
