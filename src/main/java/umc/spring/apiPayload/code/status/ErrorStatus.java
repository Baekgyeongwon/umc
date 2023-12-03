package umc.spring.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),
    // 멤버 관려 에러
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 없습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수 입니다."),

    // 게시글 관련 에러
    ARTICLE_NOT_FOUND(HttpStatus.BAD_REQUEST, "ARTICLE4001", "게시글이 없습니다."),

    // 미션 관련 에러
    MISSION_NOT_FOUND(HttpStatus.BAD_REQUEST, "ARTICLE4001", "해당하는 미션이 없습니다."),
    // 내 미션 관련 에러
    MY_MISSION_NOT_FOUND(HttpStatus.BAD_REQUEST, "ARTICLE4001", "해당하는 내 미션이 없습니다."),
    // 지역 관련 에러
    REGION_NOT_FOUND(HttpStatus.BAD_REQUEST, "REGION4001", "해당하는 지역이 없습니다."),

    // 페이징 관련 에러
    PAGE_NUMBER_UNDER_ZERO(HttpStatus.BAD_REQUEST, "PAGE4001", "페이지의 최소값은 0 이상이여야 합니다."),

    // For test
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트"),


    // 식당 관련 에러
    RESTAURANT_NOT_FOUND(HttpStatus.BAD_REQUEST, "RESTAURANT4001", "해당하는 식당이 없습니다."),
    // 음식 카테고리 관련 에러
    FOOD_CATEGORY_NOT_FOUND(HttpStatus.BAD_REQUEST, "FOODCATEGORY4001", "해당하는 카테고리가 없습니다.");
    // ~~~ 관련 응답 ....


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
