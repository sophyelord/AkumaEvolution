package search;

public interface OracleObserver<OBJ> {

	public default void setSearchOracle(SearchOracle<OBJ> oracle) {}
}
