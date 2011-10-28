package pvpservergui.filter;

public interface Filter<T> {
	public boolean accept(T t);
}
