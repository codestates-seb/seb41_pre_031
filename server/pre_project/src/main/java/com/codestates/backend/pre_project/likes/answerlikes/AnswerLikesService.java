package com.codestates.backend.pre_project.likes.answerlikes;

import com.codestates.backend.pre_project.exception.BusinessLogicException;
import com.codestates.backend.pre_project.exception.ExceptionCode;
import com.codestates.backend.pre_project.member.service.MemberService;
import com.codestates.backend.pre_project.post.answer.entity.Answer;
import com.codestates.backend.pre_project.post.answer.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerLikesService {
        private final AnswerLikesRepository answerLikesRepository;
        private final AnswerLikesMapper mapper;

    public void addLike(AnswerLikesPatchDto answerLikesPatchDto) {
        Optional<AnswerLikes> optionalAnswerLikes = answerLikesRepository.findByAnswerAndMemberId(answerLikesPatchDto.getAnswerId(),answerLikesPatchDto.getMemberId());

        if(optionalAnswerLikes.isPresent()){
            AnswerLikes findAnswerLikes = optionalAnswerLikes.orElseThrow();

            if(answerLikesPatchDto.getAnswerLikesCount() != findAnswerLikes.getAnswerLikesCount() ){
                answerLikesRepository.deleteById(findAnswerLikes.getAnswerlikesId());
            }

        }
        // 투표한 적이 없을 때
        else{
            AnswerLikes answerLikes= mapper.answerLikesPatchDtoToAnswerLikes(answerLikesPatchDto);
            answerLikesRepository.save(answerLikes);
        }
    }
}