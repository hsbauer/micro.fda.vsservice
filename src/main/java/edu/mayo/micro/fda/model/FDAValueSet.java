package edu.mayo.micro.fda.model;

import java.util.List;

public class FDAValueSet {
	
	String source;
	String NCISubsetCode;
	String FDASubsetName;
	String NCItConceptCode;
	String FDASourcePT;
	List<String> FDASourceSynonyms;
	String FDASourceDefinition;
	String NCItDefinition;
	
	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public String getNCISubsetCode() {
		return NCISubsetCode;
	}
	
	public void setNCISubsetCode(String nCISubsetCode) {
		NCISubsetCode = nCISubsetCode;
	}
	public String getFDASubsetName() {
		return FDASubsetName;
	}
	
	public void setFDASubsetName(String fDASubsetName) {
		FDASubsetName = fDASubsetName;
	}
	
	public String getNCItConceptCode() {
		return NCItConceptCode;
	}
	
	public void setNCItConceptCode(String nCItConceptCode) {
		NCItConceptCode = nCItConceptCode;
	}
	
	public String getFDASourcePT() {
		return FDASourcePT;
	}
	
	public void setFDASourcePT(String fDASourcePT) {
		FDASourcePT = fDASourcePT;
	}
	
	public List<String> getFDASourceSynonyms() {
		return FDASourceSynonyms;
	}
	public void setFDASourceSynonyms(List<String> fDASourceSynonyms) {
		FDASourceSynonyms = fDASourceSynonyms;
	}
	
	public String getFDASourceDefinition() {
		return FDASourceDefinition;
	}
	
	public void setFDASourceDefinition(String fDASourceDefinition) {
		FDASourceDefinition = fDASourceDefinition;
	}
	
	public String getNCItDefinition() {
		return NCItDefinition;
	}
	
	public void setNCItDefinition(String nCItDefinition) {
		NCItDefinition = nCItDefinition;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((FDASourceDefinition == null) ? 0 : FDASourceDefinition.hashCode());
		result = prime * result + ((FDASourcePT == null) ? 0 : FDASourcePT.hashCode());
		result = prime * result + ((FDASourceSynonyms == null) ? 0 : FDASourceSynonyms.hashCode());
		result = prime * result + ((FDASubsetName == null) ? 0 : FDASubsetName.hashCode());
		result = prime * result + ((NCISubsetCode == null) ? 0 : NCISubsetCode.hashCode());
		result = prime * result + ((NCItConceptCode == null) ? 0 : NCItConceptCode.hashCode());
		result = prime * result + ((NCItDefinition == null) ? 0 : NCItDefinition.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FDAValueSet other = (FDAValueSet) obj;
		if (FDASourceDefinition == null) {
			if (other.FDASourceDefinition != null)
				return false;
		} else if (!FDASourceDefinition.equals(other.FDASourceDefinition))
			return false;
		if (FDASourcePT == null) {
			if (other.FDASourcePT != null)
				return false;
		} else if (!FDASourcePT.equals(other.FDASourcePT))
			return false;
		if (FDASourceSynonyms == null) {
			if (other.FDASourceSynonyms != null)
				return false;
		} else if (!FDASourceSynonyms.equals(other.FDASourceSynonyms))
			return false;
		if (FDASubsetName == null) {
			if (other.FDASubsetName != null)
				return false;
		} else if (!FDASubsetName.equals(other.FDASubsetName))
			return false;
		if (NCISubsetCode == null) {
			if (other.NCISubsetCode != null)
				return false;
		} else if (!NCISubsetCode.equals(other.NCISubsetCode))
			return false;
		if (NCItConceptCode == null) {
			if (other.NCItConceptCode != null)
				return false;
		} else if (!NCItConceptCode.equals(other.NCItConceptCode))
			return false;
		if (NCItDefinition == null) {
			if (other.NCItDefinition != null)
				return false;
		} else if (!NCItDefinition.equals(other.NCItDefinition))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		return true;
	}
	
	
	

}
