package umc.spring.apiPayload.exception.handler;

import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.exception.GeneralException;

public class MyMissionHandler extends GeneralException {
    public MyMissionHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
