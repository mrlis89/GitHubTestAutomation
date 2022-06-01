package common;

import common.Interfaces.*;

public class GWT<T> {
    private T given;
    private T actual;
    private IGiven<T> givenFunc;
    private IWhenReturn<T> whenFunc;
    private IWhenReturnNoParam<T> whenFuncNoParam;
    private IWhenVoid<T> whenVoidFunc;

    public GWT<T> given(String description, IGiven<T> func) {
        givenFunc = func;
        return this;
    }
    public GWT<T> when(String description, IWhenReturn<T> func) {
        whenFunc = func;
        return this;
    }
    public GWT<T> when(String description, IWhenReturnNoParam<T> func) {
        whenFuncNoParam = func;
        return this;
    }
    public GWT<T> when(String description, IWhenVoid<T> func) {
        whenVoidFunc = func;
        return this;
    }
    public void then(Runnable assertion) {
        runGWTTest();
        assertion.run();
    }
    public void then(IThenVoid<T> assertion) {
        runGWTTest();
        assertion.run(given, actual);
    }

    private void runGWTTest()  {
        given = givenFunc.run();
        if (whenFunc != null) {
            actual = whenFunc.run(given);
        } else if (whenFuncNoParam != null) {
            actual = whenFuncNoParam.run();
        } else whenVoidFunc.run(given);
    }
}
