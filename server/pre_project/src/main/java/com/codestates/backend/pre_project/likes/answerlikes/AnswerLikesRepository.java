package com.codestates.backend.pre_project.likes.answerlikes;

import com.codestates.backend.pre_project.likes.answerlikes.AnswerLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerLikesRepository extends JpaRepository<AnswerLikes, Long> {
    @Query(value = "SELECT av.answer.answerId, sum(av.answerLikeCount) as answerLikesSum FROM AnswerLikes av GROUP BY av.answer.answerId")
    List<Object[]> findByAnswerLikesCount();
    Optional<AnswerLikes> findByAnswerAndMemberId(long answerId, long memberId);
}