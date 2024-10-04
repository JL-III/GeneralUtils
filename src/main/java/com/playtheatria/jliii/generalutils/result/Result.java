package com.playtheatria.jliii.generalutils.result;

public sealed interface Result<T, E extends Throwable> permits Ok, Err {
    T value() throws E;
}
