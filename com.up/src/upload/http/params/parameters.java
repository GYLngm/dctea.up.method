package upload.http.params;

import java.util.HashMap;
import java.util.Map;

import Modal.pictureModal;

public interface parameters {
	public void putTitle(String title);//��Ӱ����
	public void putBigclass(String bclassid);//��Ӱ��Ŀ
	public void putClass(String classid);//��Ӱ����
	public void putFilepass(long filepass);//��ǰʱ���/��Ӱid
	public void putCampany(String companyname);//��Ӱ��˾
	public void putTitlepic(String url);//ͼƬurl
	public void putNewstime(String currentTime);//�ϴ�ʱ��
	public void putMovietime(String movietime);//��Ӱ�������
	public void putLanguage(String language);//����
	public void putPlayer(String name);//��Ա --(Tom Cruise, Jon Voight)
	public void putDirector(String name);//����
	public void putArea(String name);//����
	public void putLz(Boolean b);//1��� 0δ���
	public void putState(String state);//״̬
	public void putVideo(Map<String,String> data);//��Ƶ�벥����(HD��BD)url
	public void putMovieSay(String describ);//���
	public void putNewsPath(String date);//��Ӱ��ŵĵ�ַ����һ��Ŀ¼,����: 2018-01-02
	public void putNewstempid(String id);//ҳ��ģ�� --Ĭ��ʹ�õ�Ӱģ��-- id === 6
	public void putGroupid(String id);//Ȩ������ -> 0�ο� 1��ͨ��Ա 2vip��Ա
	public void putEcmsFrom(String url);//ҳ��ǰһ����ַ
	public void putKeyWord(String keyword);//�ؼ���
	public void putEnews(String action);//Post����̨�Ķ�����'AddNews'Ϊ�����Ϣ,'EditNews'Ϊ�޸���Ϣ,etc
	public void putId(int id);
	public void putFilename(String filename);//��Ӱ�Ĵ��Ŀ¼:{��Ӱ��}/index
	public void putPicture(pictureModal p);//ͼƬ��ַ
}
