package edu.mayo.micro.fda.interfaces;

import java.util.List;

public interface ValueSets<T> {
	
	List<T> getAllValues(String terminology);

}
