package com.moment.util;

import java.util.ArrayList;
import java.util.List;

public class AutoClassify {

	public static String[] Literature = {"文学","诗歌","散文","小说","剧本","寓言童话","语言文字","赋","词","戏曲","名著","哲学"};
	
	public static String[] design = {"设计","创意","构图","色彩","色彩","视觉元素","工业","环艺","装潢","展示","服装","平面设计","审美","节奏","韵律","对称","平衡"};
	
	public static String[] Television = {"影视","电影","视频","电视","好莱坞","欧美大片","特效","动漫","动作片","喜剧","恐怖片","幽默","大牌","明星"};
	
	public static String[] travel = {"旅游","穷游","青旅","天气","徒步","骑行","自驾游","登山","冒险","风景","名胜","古迹","景色","小桥流水","江河","高山","人文"};
	
	public static String[] recipe = {"食谱","菜系","正食","甜点","饮品","糕点","午茶","沙拉","水果","拼盘","冰淇淋","烹煮","清蒸","爆炒","冷冻","加热","牛奶","鸡蛋","面粉"};
	
	public static String[] internet = {"互联网","移动","android","ios","winphone","mac","PC","搜索","云计算","大数据","O2O","电子商务",
		                      "数据挖掘","服务器","虚拟机","云","CDN","负载均衡","网络","安全","手机","支付","购物","社交","团购","打车","APP","算法","体验","用户"};
	
	public static String[] internetOfThing = {"物联网","可穿戴设备","智能家居","智能","手环","手表","定位","识别"};
	
	public static String[] communication = {"通信","4G","运营商","5G","光网络","光纤","通信","流量","OTT","网络设备","交换机","路由器","协议","终端"};
	
	public static String[] electronic = {"电子","芯片","嵌入式","器件","模块","焊接","电阻","电子","电磁","信号与系统","树莓派","傅里叶","电路板","PCB"};
	
	public static String[] car = {"汽车","标志","豪车","导航","轴承","车载系统","保养","车险","电动汽车","车联网","自动驾驶","ICar","引擎"};
	
	/**
	 * 判断标签和内容是否包含某一分类的属性关键词
	 * @param tagList
	 * @param content
	 * @param classifies
	 * @return
	 */
	public static int judgeIsContain(String tagList,String content,String[] classifies)
	{
		int count = 0;
		for(String item : classifies)
		{
			if(tagList.contains(item))
				return 1;
			if(content.contains(item))
				count++;
		}
		if(count > 2)
			return 1;
		return 0;
	}
	
	/**
	 * 根据灵感内容和标签进行自动分类
	 * @param tagList
	 * @param content
	 * @return
	 */
	public static int autoClassify(String tagList,String content)
	{
		List<String[]> classifies = new ArrayList<String[]>();
		classifies.add(internet);
		classifies.add(internetOfThing);
		classifies.add(communication);
		classifies.add(electronic);
		classifies.add(car);
		classifies.add(Literature);
		classifies.add(design);
		classifies.add(Television);
		classifies.add(travel);
		classifies.add(recipe);
		
		for(int i = 0; i < 10; i++)
		{
			if(1 == judgeIsContain(tagList,content,classifies.get(i)))
			{
				return (i + 6) > 10 ? (i - 4) : (i + 6);
			}
		}
		return -1;
	}
	
}
