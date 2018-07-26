package Modal;

public class pictureModal {
	private String smallpic = "";
	private String bigpic = "";
	private String indexpic = "";
	private String maxpic = "";
	private int id = 0;
	
	public pictureModal(String s,int id) {
		setSmallpic(s);
		setBigpic(s);
		setIndexpic(s);
		setMaxpic(s);
		setId(id);
	}
	
	public String getSmallpic() {
		return smallpic;
	}
	public void setSmallpic(String smallpic) {
		this.smallpic = smallpic;
	}
	public String getBigpic() {
		return bigpic;
	}
	public void setBigpic(String bigpic) {
		this.bigpic = bigpic;
	}
	public String getIndexpic() {
		return indexpic;
	}
	public void setIndexpic(String indexpic) {
		this.indexpic = indexpic;
	}
	public String getMaxpic() {
		return maxpic;
	}
	public void setMaxpic(String maxpic) {
		this.maxpic = maxpic;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		String text = "id : "+getId()+"\n"+"smallpath : "+getSmallpic()+"\n"+"bigpath : "+getBigpic()+"\n";
		
		return text;
	}
}
