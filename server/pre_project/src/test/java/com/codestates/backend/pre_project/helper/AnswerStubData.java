package com.codestates.backend.pre_project.helper;

import com.codestates.backend.pre_project.member.dto.MemberDto;
import com.codestates.backend.pre_project.post.answer.dto.AnswerDto;
import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;

public class AnswerStubData {

    private static Map<HttpMethod, Object> stubRequestBody;
    static {
        stubRequestBody = new HashMap<>();
        stubRequestBody.put(HttpMethod.POST, new AnswerDto.Post(1L,
                1L,"답변 내용입니다"));
    }

    private static Map<HttpMethod, Object> stubAnswerDto;
    static {
        stubRequestBody.put(HttpMethod.POST, new AnswerDto.Post(1, 1,"답변입니다"));
    }

    public static class MockAnswer {
        public static Object getRequestBody(HttpMethod method) {
            return stubAnswerDto.get(method);
        }

        public static AnswerDto.Response getSingleResponseBody() {
            return new AnswerDto.Response(1, "답변입니다", "답변자 이름", "댓글 내용");
        }
    }

//        public static AnswerDto.Response getSingleResponseBody(long answerId, String answerBody) {
//            long optionalAnswerId = Optional.ofNullable(answerId).orElse(1L);
//            String optionalAnswerBody = Optional.ofNullable(answerBody).orElse("답변입니다");
//            return new MemberDto.Response(optionalAnswerId,
//                    optionalAnswerBody);
//        }
}
