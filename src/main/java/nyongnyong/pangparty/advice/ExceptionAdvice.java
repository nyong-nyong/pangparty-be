package nyongnyong.pangparty.advice;

import io.jsonwebtoken.ExpiredJwtException;
import nyongnyong.pangparty.dto.ErrorRes;
import nyongnyong.pangparty.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(CommentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorRes handleCommentNotFoundException(CommentNotFoundException e) {
        return new ErrorRes("존재하지 않는 댓글입니다.", HttpStatus.NOT_FOUND.value(), "COMMENT_NOT_FOUND");
    }

    @ExceptionHandler(EmailAuthFailExcpetion.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorRes handleEmailAuthFailExcpetion(EmailAuthFailExcpetion e) {
        return new ErrorRes("이메일 인증에 실패하였습니다.", HttpStatus.UNAUTHORIZED.value(), "EMAIL_AUTH_FAIL");
    }

    @ExceptionHandler(EventNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorRes handleEventNotFoundException(EventNotFoundException e) {
        return new ErrorRes("존재하지 않는 이벤트입니다.", HttpStatus.NOT_FOUND.value(), "EVENT_NOT_FOUND");
    }

    @ExceptionHandler(MemberAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorRes handleMemberAlreadyExistsException(MemberAlreadyExistsException e) {
        return new ErrorRes("이미 존재하는 사용자입니다.", HttpStatus.CONFLICT.value(), "MEMBER_ALREADY_EXISTS");
    }

    @ExceptionHandler(MemberNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorRes handleMemberNotFoundException(MemberNotFoundException e) {
        return new ErrorRes("존재하지 않는 사용자입니다.", HttpStatus.NOT_FOUND.value(), "MEMBER_NOT_FOUND");
    }

    @ExceptionHandler(PostNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorRes handlePostNotFoundException(PostNotFoundException e) {
        return new ErrorRes("존재하지 않는 게시물입니다.", HttpStatus.NOT_FOUND.value(), "POST_NOT_FOUND");
    }

    @ExceptionHandler(FeedNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorRes handleFeedNotFoundException(FeedNotFoundException e) {
        return new ErrorRes("존재하지 않는 게시물입니다.", HttpStatus.NOT_FOUND.value(), "FEED_NOT_FOUND");
    }

    @ExceptionHandler(RollingPaperNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorRes handleRollingPaperNotFoundException(RollingPaperNotFoundException e) {
        return new ErrorRes("존재하지 않는 롤링페이퍼입니다.", HttpStatus.NOT_FOUND.value(), "ROLLING_PAPER_NOT_FOUND");
    }

    @ExceptionHandler(StickerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorRes handleStickerNotFoundException(StickerNotFoundException e) {
        return new ErrorRes("존재하지 않는 스티커입니다.", HttpStatus.NOT_FOUND.value(), "STICKER_NOT_FOUND");
    }

    @ExceptionHandler(TokenInvalidException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorRes handleTokenInvalidException(TokenInvalidException e) {
        return new ErrorRes("유효하지 않은 토큰입니다.", HttpStatus.UNAUTHORIZED.value(), "TOKEN_INVALID");
    }

    @ExceptionHandler(TokenRefreshFailException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorRes handleTokenRefreshFailException(TokenRefreshFailException e) {
        return new ErrorRes("토큰 갱신에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR.value(), "TOKEN_REFRESH_FAIL");
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorRes handleExpiredJwtException(ExpiredJwtException e) {
        return new ErrorRes("토큰이 만료되었습니다", HttpStatus.BAD_REQUEST.value(), "TOKEN_EXPIRED");
    }
}
