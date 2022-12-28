package com.codestates.backend.pre_project.helper;

import com.codestates.backend.pre_project.member.dto.MemberDto;
import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.post.answer.dto.AnswerDto;
import com.codestates.backend.pre_project.post.answer.entity.Answer;
import com.codestates.backend.pre_project.post.comment.entity.Comment;
import com.codestates.backend.pre_project.post.question.Question;
import net.bytebuddy.asm.Advice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;

import java.time.LocalDateTime;
import java.util.*;

public class AnswerStubData {

    private static Map<HttpMethod, Object> stubRequestBody;
    static {
        stubRequestBody = new HashMap<>();
        stubRequestBody.put(HttpMethod.POST, new AnswerDto.Post(1L,
                1L,"답변 내용"));
        stubRequestBody.put(HttpMethod.PATCH, new AnswerDto.Patch(1L,"답변 내용"));
    }


    public static class MockAnswer {
        public static Object getRequestBody(HttpMethod method) {
            return stubRequestBody.get(method);
        }

        public static AnswerDto.Response getSingleResponseBody() {
            return new AnswerDto.Response(1L, 1L, "답변 내용", new ArrayList<>(),0);
        }


        public static AnswerDto.Response getSingleResponseBody(long answerId, long memberId,
                                                               String answerBody, List<Comment> commentBody,
                                                               int answerLikes) {
            long optionalAnswerId = Optional.ofNullable(answerId).orElse(1L);
            long optionalMemberId = Optional.ofNullable(memberId).orElse(1L);
            String optionalAnswerBody = Optional.ofNullable(answerBody).orElse("답변 내용");
            List<Comment> optionalCommentBody = Optional.ofNullable(commentBody).orElse(new ArrayList<>());
            int optionalAnswerLikes = Optional.ofNullable(answerLikes).orElse(0);
            return new AnswerDto.Response(optionalAnswerId, optionalMemberId, optionalAnswerBody, optionalCommentBody, optionalAnswerLikes);
        }

        public static Page<Answer> getMultiResultAnswer() {
            Answer answer1 = new Answer(1L, "답변 내용", 1, false,
                    LocalDateTime.of(2022, 12,27,20,42, 22),LocalDateTime.of(2022, 12,27,20,42, 22),
                    new Member(),new Question(),new ArrayList<>(),new ArrayList<>());
            Answer answer2 = new Answer(2L, "답변 내용2", 1, false,
                    LocalDateTime.of(2022, 12,27,20,42, 22),LocalDateTime.of(2022, 12,27,20,42, 22),
                    new Member(),new Question(),new ArrayList<>(),new ArrayList<>());

            return new PageImpl<>(List.of(answer1,answer2),
                    PageRequest.of(0, 10, Sort.by("answerId").descending()),2);
        }

        public static List<AnswerDto.Response> getMultiResponseBody(){

            return List.of(
                    new AnswerDto.Response(1L, 1L, "답변 내용", new ArrayList<>(), 1),
                    new AnswerDto.Response(2L, 1L, "답변 내용2", new ArrayList<>(), 1)
            );
        }

        public static Answer getSingleResultAnswer(){
            Answer answer = new Answer(1L, "답변 내용", 1, false,
                    LocalDateTime.of(2022, 12,27,20,42, 22),LocalDateTime.of(2022, 12,27,20,42, 22),
                    new Member(),new Question(),new ArrayList<>(),new ArrayList<>());

            return answer;
        }

        public static Answer getSingleResultAnswer(long answerId, Map<String, String> updatedInfo){
            String answerBody  = Optional.ofNullable(updatedInfo.get("answerBody")).orElse("답변 내용");
            LocalDateTime answerLastTime = LocalDateTime.parse(Optional.ofNullable(updatedInfo.get("answerLastDate")).orElse(String.valueOf(LocalDateTime.of(2022, 12,27,20,42, 22))));
            Answer answer = new Answer();
            answer.setAnswerId(answerId);
            answer.setAnswerBody(answerBody);
            answer.setAnswerLikes(1);
            answer.setAnswerSelected(false);
            answer.setComments(new ArrayList<>());
            answer.setMember(new Member());
            answer.setQuestion(new Question());
            answer.setLikes(new ArrayList<>());

            return answer;
        }
}}
