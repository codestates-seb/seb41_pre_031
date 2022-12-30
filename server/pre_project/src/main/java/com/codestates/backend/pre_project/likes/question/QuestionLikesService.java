package com.codestates.backend.pre_project.likes.question;

import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.post.answer.entity.Answer;
import com.codestates.backend.pre_project.post.question.Question;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionLikesService {
    private final QuestionLikesRepository questionLikesRepository;

    public QuestionLikesService(QuestionLikesRepository questionLikesRepository) {
        this.questionLikesRepository = questionLikesRepository;
    }

    public QuestionLikes findByMemberAndQuestion(Member member, Question question) {
        Optional<QuestionLikes> questionLikes =
                this.questionLikesRepository.findByMemberAndQuestion(member, question);
        if (questionLikes.isPresent()) {
            return questionLikes.get();
        } else {
            return new QuestionLikes();
        }
    }

    public void saveQuestionLikes(QuestionLikes questionLikes){
        this.questionLikesRepository.save(questionLikes);
    }
}