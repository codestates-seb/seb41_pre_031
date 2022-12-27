package com.codestates.backend.pre_project.likes;

import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.post.answer.entity.Answer;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerLikesService {
    private final AnswerLikesRepository answerLikesRepository;

    public AnswerLikesService(AnswerLikesRepository answerLikesRepository) {
        this.answerLikesRepository = answerLikesRepository;
    }

    public AnswerLikes findByMemberAndAnswer(Member member, Answer answer) {
        Optional<AnswerLikes> answerLikes =
                this.answerLikesRepository.findByMemberAndAnswer(member, answer);
        if (answerLikes.isPresent()) {
            return answerLikes.get();
        } else {
            return new AnswerLikes();
        }
    }

    public void saveAnswerLikes(AnswerLikes answerLikes){
        this.answerLikesRepository.save(answerLikes);
    }
}