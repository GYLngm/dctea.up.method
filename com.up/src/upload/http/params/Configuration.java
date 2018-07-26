package upload.http.params;

public enum Configuration {
	URL("localhost"),PORT(""),adminAccount("admin"),adminPassword("123456"),LOG_DIR("D:/dcteaLog");
	
	private String value;
	
	Configuration(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
	
}
