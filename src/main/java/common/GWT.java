package common;
import static org.assertj.core.api.Assertions.assertThat;

public class GWT <T> {
    private T given;
    private T actual;
    private IGiven<T> givenFun;
    private IWhen<T> whenFun;

    public GWT<T> given(String description, IGiven<T> fun){
        givenFun = fun;
        return this;
    }

    public GWT<T> when(String description, IWhen<T> fun){
        whenFun = fun;
        return this;
    }

    public void then(String description){
        given = givenFun.run();
        actual = whenFun.run(given);
        assertThat(given).isEqualTo(actual);
    }
    public void then(String description, Runnable assertion){
        given = givenFun.run();
        actual = whenFun.run(given);
        assertion.run();
    }


}
