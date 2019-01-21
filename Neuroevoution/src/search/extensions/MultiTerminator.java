package search.extensions;

import termination.SearchTerminator;

public interface MultiTerminator<OBJ> {

	public void setSearchTerminator(SearchTerminator<OBJ> terminator);
}
