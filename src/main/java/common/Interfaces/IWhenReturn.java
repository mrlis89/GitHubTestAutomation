package common.Interfaces;

@FunctionalInterface
public interface IWhenReturn<T> {
    public T run(T given);
}
