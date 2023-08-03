package wantedpreonboarding.backend.common.exception;

import lombok.Getter;
import wantedpreonboarding.backend.common.code.ErrorCode;

@Getter
public class CustomIllegalArgumentException extends IllegalArgumentException{

    private final ErrorCode errorCode;

    public CustomIllegalArgumentException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
