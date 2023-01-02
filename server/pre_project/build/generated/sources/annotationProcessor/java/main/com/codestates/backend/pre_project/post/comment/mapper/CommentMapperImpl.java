package com.codestates.backend.pre_project.post.comment.mapper;

import com.codestates.backend.pre_project.post.comment.dto.CommentDto;
import com.codestates.backend.pre_project.post.comment.entity.Comment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-02T10:18:54+0900",
    comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 18.0.2 (Amazon.com Inc.)"
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
