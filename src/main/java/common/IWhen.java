package common;

@FunctionalInterface
public interface IWhen<T> {
    public T run(T given);
}
