package upload.http.params;

import java.util.HashMap;
import java.util.Map;

import Modal.pictureModal;

public interface parameters {
	public void putTitle(String title);//电影标题
	public void putBigclass(String bclassid);//电影栏目
	public void putClass(String classid);//电影类型
	public void putFilepass(long filepass);//当前时间戳/电影id
	public void putCampany(String companyname);//电影公司
	public void putTitlepic(String url);//图片url
	public void putNewstime(String currentTime);//上传时间
	public void putMovietime(String movietime);//电影出版年份
	public void putLanguage(String language);//语言
	public void putPlayer(String name);//演员 --(Tom Cruise, Jon Voight)
	public void putDirector(String name);//导演
	public void putArea(String name);//地区
	public void putLz(Boolean b);//1完结 0未完结
	public void putState(String state);//状态
	public void putVideo(Map<String,String> data);//视频与播放器(HD或BD)url
	public void putMovieSay(String describ);//简介
	public void putNewsPath(String date);//电影存放的地址的上一级目录,例如: 2018-01-02
	public void putNewstempid(String id);//页面模板 --默认使用电影模板-- id === 6
	public void putGroupid(String id);//权限设置 -> 0游客 1普通会员 2vip会员
	public void putEcmsFrom(String url);//页面前一个地址
	public void putKeyWord(String keyword);//关键字
	public void putEnews(String action);//Post到后台的动作，'AddNews'为添加信息,'EditNews'为修改信息,etc
	public void putId(int id);
	public void putFilename(String filename);//电影的存放目录:{电影名}/index
	public void putPicture(pictureModal p);//图片地址
}
