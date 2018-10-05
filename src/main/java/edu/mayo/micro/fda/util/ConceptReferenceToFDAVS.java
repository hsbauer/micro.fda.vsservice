package edu.mayo.micro.fda.util;

import java.util.List;
import java.util.stream.Collectors;

import org.LexGrid.LexBIG.DataModel.Core.ResolvedConceptReference;
import org.LexGrid.commonTypes.Source;


public class ConceptReferenceToFDAVS {
	public static final String FDA = "FDA";
	public static final String NCI = "NCI";
	private static final String PT = "PT";
	private static final Object SY = "SY";
	
	public static String getFDASourceDefinition(ResolvedConceptReference fullEnt) {
		if (fullEnt.getEntity().getDefinitionAsReference().stream()
				.anyMatch(x -> hasPropertyForSource(x.getSourceAsReference(), FDA))) {
			return fullEnt.getEntity().getDefinitionAsReference().stream()
					.filter(x -> hasPropertyForSource(x.getSourceAsReference(), FDA))
					.findFirst().get().getValue()
					.getContent();
		}
		return null;
	}
	
	public static String getFDASourcePT(ResolvedConceptReference fullEnt) {
		if (fullEnt.getEntity().getPresentationAsReference().stream()
				.anyMatch(x -> hasPropertyForSource(x.getSourceAsReference(), FDA) 
						&& hasPropertyForPTAndSource(x.getRepresentationalForm(),
								x.getSourceAsReference(), FDA))) {
		return fullEnt.getEntity().getPresentationAsReference().stream().
		filter(
				x -> hasPropertyForSource(x.getSourceAsReference(), FDA) && 
				hasPropertyForPTAndSource(x.getRepresentationalForm(),x.getSourceAsReference(), FDA) ).
		findFirst().get().getValue().getContent();
		}
		return null;
	}

	public static List<String> getFDASourceSynonyms(ResolvedConceptReference fullEnt) {
		return fullEnt.getEntity().getPresentationAsReference().stream().
		filter(
				x -> hasPropertyForSource(x.getSourceAsReference(), FDA) && 
				hasPropertyForSYAndSource(
						x.getRepresentationalForm(),x.getSourceAsReference(), FDA))
		.map(y -> y.getValue().getContent()).collect(Collectors.toList());
	}
	public static String getFDASubsetName(ResolvedConceptReference fullEnt) {
		return null;
	}
	public static String getNCISubsetCode(String uri) {
		return uri.substring(uri.lastIndexOf("/"));
	}
	public static String getNCItConceptCode(ResolvedConceptReference fullEnt) {
		return fullEnt.getCode();
	}

	public static String getNCItDefinition(ResolvedConceptReference fullEnt) {
		if (fullEnt.getEntity().getDefinitionAsReference().stream()
				.anyMatch(x -> hasPropertyForSource(x.getSourceAsReference(), NCI))) {
			return fullEnt.getEntity().getDefinitionAsReference().stream()
					.filter(x -> hasPropertyForSource(x.getSourceAsReference(), NCI))
					.findFirst().get().getValue()
					.getContent();
		}
		return null;
	}
	
	public static String getSource(ResolvedConceptReference fullEnt) {
		return FDA;
	}
	
	private static boolean hasPropertyForSource(List<Source> sources, String s) {
		return sources.stream().anyMatch(source -> source.getContent().equals(s));
	}
	
	private static boolean hasPropertyForPTAndSource(String representationalForm, List<Source> sources,
			String s) {
		return sources.stream().anyMatch(source -> source.getContent().equals(s)) && 
				representationalForm.equals(PT);
	}
	
	private static boolean hasPropertyForSYAndSource(String representationalForm, List<Source> sources,
			String s) {
		return sources.stream().anyMatch(source -> source.getContent().equals(s)) && 
				representationalForm.equals(SY);
	}
	

}
