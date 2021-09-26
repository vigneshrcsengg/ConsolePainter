/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlr.tech.app;

/**
 *
 * @author Vignesh
 */
public enum ExitCode {

    EXIT(0, "Exiting Application"),
    SUCCESS(1, "Success"),
    ERR_INVALID_ARGS(501, "Invalid Command!"),
    ERR_INVALID_PARAMS(502, "Invalid Parmeters!"),
    ERR_CREATE_CANVAS(503, "Error creating canvas"),
    ERR_DRAW_INLINE(504, "Error draw inline"),
    ERR_DRAW_RECTANGLE(505, "Error draw rectangle"),
    ERR_BUCKETFILL(506, "Error Bukcet Fill"),
    ERR_EMPTY_CANVAS(507, "Empty Canvas"),
    ERR_INPUT_PARAM_LINE_RECT(508, "Input Param of Line/RectAngle is not accepted");

    private final int code;
    private final String message;

    private ExitCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static ExitCode getExitCodeResponse(int code) {
        for (ExitCode response : ExitCode.values()) {
            if (response.code == code) {
                return response;
            }
        }
        throw new IllegalArgumentException("Unsupported ExitCode code: " + code);
    }
}
