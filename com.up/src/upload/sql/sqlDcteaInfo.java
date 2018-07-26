package upload.sql;

public enum sqlDcteaInfo {
	URL("localhost"),DATABASE("sq_"),USERNAME("root"),PASSWORD("root"),PREFIX("phome_mianbao_");
	
	private String value;
	sqlDcteaInfo(String value){
		this.value = value;
	}
	public String getValue() {
		return this.value;
	}
	@Override
	public String toString() {
		return String.valueOf(this.value);
	}
}
