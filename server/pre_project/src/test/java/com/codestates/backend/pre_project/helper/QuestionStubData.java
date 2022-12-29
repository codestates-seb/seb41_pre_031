package com.codestates.backend.pre_project.helper;

import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.post.question.Question;
import com.codestates.backend.pre_project.post.question.QuestionTag;
import com.codestates.backend.pre_project.post.question.Tag;
import com.codestates.backend.pre_project.post.question.dto.QuestionDto;
import com.codestates.backend.pre_project.post.question.dto.QuestionTagDto;
import com.codestates.backend.pre_project.post.question.dto.QuestionTagResponseDto;
import com.codestates.backend.pre_project.post.question.mapper.QuestionMapper;
import net.bytebuddy.asm.Advice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class QuestionStubData {
    static Member member = MemberStubData.MockMember.getSingleResultMember(1L);

    private static Map<HttpMethod, Object> stubRequestBody;

    static {
        //TODO
        //태그변경 테스트
        List<QuestionTagDto> questionTagDtos = new LinkedList<>();
        questionTagDtos.add(new QuestionTagDto(1L, 1L, "JAVA"));
        stubRequestBody = new HashMap<>();
        stubRequestBody.put(HttpMethod.POST, new QuestionDto.Post(
                "버스 안에서", "열심히 하는 중", MemberStubData.MockMember.getSingleResponseBody().getMemberId(), questionTagDtos
        ));
        stubRequestBody.put(HttpMethod.PATCH,
                new QuestionDto.Patch(1L, 1L, "버스 안에서", "열심히 하는 중", new LinkedList<QuestionTagDto>()));
    }

    public static class MockQuestion {

        public static Object getRequestBody(HttpMethod method){
            return stubRequestBody.get(method);
        }

        public static QuestionDto.Response getSingleResponseBody(){
            return new QuestionDto.Response(1L, 1L, "버스 안에서", "열심히 하는 중", new ArrayList<>(), LocalDateTime.of(2022, 12, 27, 2, 54), LocalDateTime.of(2022, 12, 27, 2, 54), 0L
            );
        }
        public static QuestionDto.Response getSingleRequestBody(Long questionId, Long memberId, String questionTitle, String questionBody, List<QuestionTagResponseDto> questionTags, LocalDateTime questionRegDate, LocalDateTime questionLastDate, long questionLikes) {
            List<QuestionTagResponseDto> questionTagResponseDtos = new LinkedList<>();
            questionTagResponseDtos.add(new QuestionTagResponseDto(1L, 1L, "JAVA"));
            Long optionalQuestionId = Optional.ofNullable(questionId).orElse(1L);
            Long optionalMemberId = Optional.ofNullable(memberId).orElse(1L);
            String optionalQuestiontitle = Optional.ofNullable(questionTitle).orElse("버스 안에서");
            String optionalQuestionBody = Optional.ofNullable(questionBody).orElse("열심히 하는 중");
            LocalDateTime optionalRegDate = Optional.ofNullable(questionRegDate).orElse(LocalDateTime.of(2022, 12, 27, 2, 54));
            LocalDateTime optionalLastDate = Optional.ofNullable(questionLastDate).orElse(LocalDateTime.of(2022, 12, 27, 2, 54));
            Long optionalQuestionLikes = Optional.ofNullable(questionLikes).orElse(0L);
            List<QuestionTagResponseDto> optionalQuestionTagDto = Optional.ofNullable(questionTags).orElse(questionTagResponseDtos);

            return new QuestionDto.Response(optionalQuestionId, optionalMemberId, optionalQuestiontitle, optionalQuestionBody, optionalQuestionTagDto, optionalRegDate, optionalLastDate, optionalQuestionLikes);
        }

        public static Question getSingleResultQuestion(long questionId) {
            List<QuestionTag> questionTags = new LinkedList<>();

            //Question 내에 있는 QuestionTag에 Question이 지정돼 있지 않은 경우 this로 지정
            //Tag내 QuestionTag도 this로 지정
            questionTags.add(new QuestionTag(1L, null, new Tag(1L, "JAVA", null)));
            return new Question(1L, "버스 안에서", "열심히 하는 중", 0L, 0L, 0L, LocalDateTime.of(2022, 12, 27, 2, 54), LocalDateTime.of(2022, 12, 27, 2, 54), member, null, null, null, null);
        }

        public static Question getSingleResultQuestion(long questionId, Map<String, String> updatedInfo) {
            List<QuestionTag> questionTags = new LinkedList<>();
            questionTags.add(new QuestionTag(1L, new Question(), new Tag(1L, "JAVA", new ArrayList<>())));


            String questionTitle = Optional.ofNullable(updatedInfo.get("questionName")).orElse("버스 안에서");
            String questionBody = Optional.ofNullable(updatedInfo.get("questionBody")).orElse("열심히 하는 중");

            Question question = new Question(1L, questionTitle, questionBody, 0L, 0L, 0L, LocalDateTime.of(2022, 12, 27, 2, 54), LocalDateTime.now(), member, null, null, questionTags, null);
            return question;
        }

        public static Page<Question> getMultiResultQuestion() {
            List<QuestionTag> questionTags = new LinkedList<>();
            questionTags.add(new QuestionTag(1L, new Question(), new Tag(1L, "JAVA", new ArrayList<>())));
            Question q1 = new Question(1L, "버스 안에서", "열심히 하는 중", 0L, 0L, 0L, LocalDateTime.of(2022, 12, 27, 2, 54), LocalDateTime.of(2022, 12, 27, 2, 54), member, null, null, questionTags, null);
            Question q2 = new Question(2L, "졸린데", "졸린데 자면 안될까요", 0L, 0L, 0L, LocalDateTime.of(2022, 12, 27, 3, 57), LocalDateTime.of(2022, 12, 27, 3, 57), member, null, null, questionTags, null);

            return new PageImpl<>(List.of(q1, q2),
                    PageRequest.of(0, 10, Sort.by("questionRegDate")), 2);
        }

        public static List<QuestionDto.Response> getMultiResponseBody() {
            List<QuestionTagResponseDto> questionTagResponseDtos = new LinkedList<>();
            questionTagResponseDtos.add(new QuestionTagResponseDto(1L, 1L, "JAVA"));
            return List.of(
                    new QuestionDto.Response(1L, 1L, "버스 안에서", "열심히 하는 중", questionTagResponseDtos, LocalDateTime.of(2022, 12, 27, 2, 54), LocalDateTime.of(2022, 12, 27, 2, 54), 0L),
                    new QuestionDto.Response(2L, 1L, "졸린데", "졸린데 자면 안될까요", questionTagResponseDtos, LocalDateTime.of(2022, 12, 27, 3, 57), LocalDateTime.of(2022, 12, 27, 3, 57), 0L)
            );
        }
    }
}

