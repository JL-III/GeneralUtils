package com.playtheatria.jliii.generalutils.utils;

public record Response<T>(T value, String errorMessage) {

    public boolean isSuccess() {
        return errorMessage == null;
    }
}

