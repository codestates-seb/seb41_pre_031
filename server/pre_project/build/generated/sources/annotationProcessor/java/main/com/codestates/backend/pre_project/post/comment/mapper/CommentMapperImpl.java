package com.codestates.backend.pre_project.post.comment.mapper;

import com.codestates.backend.pre_project.post.comment.dto.CommentDto;
import com.codestates.backend.pre_project.post.comment.entity.Comment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-30T22:15:28+0900",
    comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.17 (Amazon.com Inc.)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment commentDtoQuestionPatchToComment(CommentDto.QuestionPatch questionPatch) {
        if ( questionPatch == null ) {
            return null;
        }

        Comment.CommentBuilder comment = Comment.builder();

        comment.commentId( questionPatch.getCommentId() );
        comment.commentBody( questionPatch.getCommentBody() );

        return comment.build();
    }

    @Override
    public Comment commentDtoAnswerPatchToComment(CommentDto.AnswerPatch answerPatch) {
        if ( answerPatch == null ) {
            return null;
        }

        Comment.CommentBuilder comment = Comment.builder();

        comment.commentId( answerPatch.getCommentId() );
        comment.commentBody( answerPatch.getCommentBody() );

        return comment.build();
    }
}
