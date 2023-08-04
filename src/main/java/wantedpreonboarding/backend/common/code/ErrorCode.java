package wantedpreonboarding.backend.common.code;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // Common
    INTERNAL_SERVER_ERROR(500, "C001", "서버 내부 오류입니다."),
    INVALID_INPUT_VALUE(400, "C002", "잘못된 입력입니다."),
    UNAUTHORIZED(401, "C003", "인증되지 않은 사용자입니다."),
    INVALID_TOKEN(400, "C004", "유효하지 않은 토큰입니다."),

    NO_INFORMATION(400, "C005", "권한 정보가 없는 토큰입니다."),
    NOT_FOUND_INFORMATION(404, "C006", "Security Context 에 인증 정보가 없습니다."),

    // User
    DUPLICATE_EMAIL(400, "U001", "중복된 이메일입니다."),
    INVALID_EMAIL(400, "U002", "유효하지 않은 이메일입니다."),
    NOT_REGISTERED_USER(404, "U003", "등록되지 않은 사용자입니다."),
    WRONG_PASSWORD(400, "U004", "비밀번호가 틀렸습니다."),
    NOT_FOUND_USER(404, "U005", "유저를 찾을 수 없습니다."),
    NO_MATCHES_INFO(400, "U006", "토큰의 유저 정보가 일치하지 않습니다."),
    NO_MATCHES_PASSWORD(400, "U007", "비밀번호가 일치하지 않습니다."),
    NOT_FOUND_POST(404, "P001", "게시물을 찾을 수 없습니다.");


    //에러의 코드 상태 반환
    private final int status;

    //에러 코드의 코드 간의 구분 값 ex) user 첫번째 > U001
    private final String divisionCode;

    // 에러 코드의 메시지
    private final String message;

    ErrorCode(int status, String divisionCode, String message) {
        this.status = status;
        this.divisionCode = divisionCode;
        this.message = message;
    }
}
