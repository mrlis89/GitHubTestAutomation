package common;

@FunctionalInterface
public interface IWhenVoid<T> {
    public void run(T given);
}
