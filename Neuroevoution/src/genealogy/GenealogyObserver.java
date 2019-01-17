package genealogy;

public interface GenealogyObserver {

	public default void setGenealogyDatabase(GenealogyDatabase gd) {}
	
}
