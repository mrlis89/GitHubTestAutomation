package common;

import common.Interfaces.*;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class GWT<T> {
    private final WebDriver webDriver;
    private T given;
    private T actual;
    private IGiven<T> givenFunc;
    private IWhenReturn<T> whenFunc;
    private IWhenReturnNoParam<T> whenFuncNoParam;
    private IWhenVoid<T> whenVoidFunc;

    public GWT(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

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

    public void then(String description) {
        runGivenWhenFunc();
        assertThat(given).isEqualTo(actual);
    }

    public void then(String description, Runnable assertion) {
        runGivenWhenFunc();
        assertion.run();
    }
    public void then(String description, IThenVoid<T> assertion) {
        runGivenWhenFunc();
        assertion.run(given, actual);
    }

    private void runGivenWhenFunc()  {
        given = givenFunc.run();
        if (whenFunc != null) {
            actual = whenFunc.run(given);
        } else if (whenFuncNoParam != null) {
            actual = whenFuncNoParam.run();
        } else whenVoidFunc.run(given);
    }


}
