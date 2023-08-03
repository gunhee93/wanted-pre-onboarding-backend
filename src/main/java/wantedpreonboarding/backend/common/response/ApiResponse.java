package wantedpreonboarding.backend.common.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wantedpreonboarding.backend.common.code.SuccessCode;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse {

    private String code;
    private String message;

    public ApiResponse(SuccessCode successCode) {
        this.code = successCode.getCode();
        this.message = successCode.getMessage();
    }
}
