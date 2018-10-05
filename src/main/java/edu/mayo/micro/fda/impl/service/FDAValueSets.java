package edu.mayo.micro.fda.impl.service;

import java.util.List;
import java.util.stream.Collectors;

import org.LexGrid.LexBIG.DataModel.Core.ResolvedConceptReference;
import org.LexGrid.LexBIG.Exceptions.LBException;
import org.LexGrid.LexBIG.Exceptions.LBInvocationException;
import org.LexGrid.LexBIG.Exceptions.LBResourceUnavailableException;
import org.LexGrid.LexBIG.LexBIGService.CodedNodeSet;
import org.LexGrid.LexBIG.LexBIGService.LexBIGService;
import org.LexGrid.LexBIG.Utility.Constructors;
import org.LexGrid.LexBIG.Utility.Iterators.ResolvedConceptReferencesIterator;
import org.LexGrid.codingSchemes.CodingScheme;
import org.LexGrid.commonTypes.Properties;
import org.LexGrid.concepts.Entity;
import org.LexGrid.util.assertedvaluesets.AssertedValueSetParameters;
import org.lexgrid.valuesets.sourceasserted.SourceAssertedValueSetService;
import org.lexgrid.valuesets.sourceasserted.impl.SourceAssertedValueSetServiceImpl;

import edu.mayo.micro.fda.interfaces.ValueSets;
import edu.mayo.micro.fda.model.FDAValueSet;
import edu.mayo.micro.fda.util.ConceptReferenceToFDAVS;

public class FDAValueSets implements ValueSets<FDAValueSet> {
	LexBIGService lbs;

	public List<FDAValueSet> getAllValues(String terminology) {
		SourceAssertedValueSetService sasservice = SourceAssertedValueSetServiceImpl.getDefaultValueSetServiceForVersion(new AssertedValueSetParameters.Builder().build());
		CodingScheme valueSet = null;
		try {
			valueSet = sasservice.getSourceAssertedValueSetforTopNodeEntityCode(terminology).get(0);
		} catch (LBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String name = valueSet.getCodingSchemeName();
		String uri = valueSet.getCodingSchemeURI();
		Properties props = valueSet.getProperties();
		return valueSet.getEntities().getEntityAsReference().stream().
				map(x -> transformToFDAValueSet(x, 
						name, 
						uri,  
						props)
		).collect(Collectors.toList());
	}

	private FDAValueSet transformToFDAValueSet(Entity entity, String subsetName, String subsetURI, Properties properties) {
		ResolvedConceptReference fullEnt = null;
		try {
			fullEnt = getFullLexEVSEntity(entity.getEntityCode());
		} catch (LBResourceUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LBInvocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FDAValueSet vs = new FDAValueSet();
		vs.setFDASourceDefinition(ConceptReferenceToFDAVS.getFDASourceDefinition(fullEnt));
		vs.setFDASourcePT(ConceptReferenceToFDAVS.getFDASourcePT(fullEnt));
		vs.setFDASourceSynonyms(ConceptReferenceToFDAVS.getFDASourceSynonyms(fullEnt));
		vs.setFDASubsetName(subsetName);
		vs.setNCISubsetCode(ConceptReferenceToFDAVS.getNCISubsetCode(subsetURI));
		vs.setNCItConceptCode(ConceptReferenceToFDAVS.getNCItConceptCode(fullEnt));
		vs.setNCItDefinition(ConceptReferenceToFDAVS.getNCItDefinition(fullEnt));
		vs.setSource(ConceptReferenceToFDAVS.getSource(fullEnt));
		return null;
	}

	private ResolvedConceptReference getFullLexEVSEntity(String entityCode) throws LBResourceUnavailableException, LBInvocationException {
		CodedNodeSet set = null;
		ResolvedConceptReferencesIterator list = null;
		try {
			set = lbs.getCodingSchemeConcepts("Thesaurus", null);
			set.restrictToCodes(Constructors.createConceptReferenceList(entityCode));
			list = set.resolve(null, null, null);
		} catch (LBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list.hasNext()? list.next(): null;
	}
	
	public static void main(String ...strings) {
		new FDAValueSets().test();
	}

	private void test() {
		getAllValues("C160039");
		
	}

}
