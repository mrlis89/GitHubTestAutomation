package common;
import static org.assertj.core.api.Assertions.assertThat;

public class GWT <T> {
    private T given;
    private T actual;

    public GWT<T> given(String description, IGiven<T> fun){
        given = fun.run();
        return this;
    }

    public GWT<T> when(String description, IWhen<T> fun){
        actual = fun.run(given);
        return this;
    }
    public GWT<T> when(String description, IWhenVoid<T> fun){
        fun.run(given);
        return this;
    }

    public void then(String description){
        assertThat(given).isEqualTo(actual);
    }
    public void then(String description, Runnable assertion){
        assertion.run();
    }


}
