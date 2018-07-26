package upload.sql;

public enum sqlNativeInfo {
	URL("localhost"),DATABASE("movieinfo"),USERNAME("root"),PASSWORD("root");
	
	private String value;
	sqlNativeInfo(String value){
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
