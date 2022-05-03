package common;

@FunctionalInterface
public interface IWhenReturn<T> {
    public T run(T given);
}
