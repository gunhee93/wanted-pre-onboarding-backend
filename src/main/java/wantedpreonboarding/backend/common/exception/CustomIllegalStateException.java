package wantedpreonboarding.backend.common.exception;

import lombok.Getter;
import wantedpreonboarding.backend.common.code.ErrorCode;

@Getter
public class CustomIllegalStateException extends IllegalStateException {

    private final ErrorCode errorCode;

    public CustomIllegalStateException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
