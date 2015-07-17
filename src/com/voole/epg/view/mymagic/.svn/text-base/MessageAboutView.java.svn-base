package com.voole.epg.view.mymagic;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.voole.epg.Config;
import com.voole.epg.R;
import com.voole.epg.Config.OemType;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.common.DisplayManager;

public class MessageAboutView extends BaseLinearLayout {
	private LayoutInflater inflater = null;
	private View view = null;
	private TextView version_title = null;
	private TextView version_content = null;
	private TextView help_title = null;
	private TextView help_content = null;
	private final int TEXT_SIZE = DisplayManager.GetInstance().changeTextSize(26);
	public MessageAboutView(Context context) {
		super(context);
		init(context);
	}

	public MessageAboutView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public MessageAboutView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	private void init(Context context){
		inflater = LayoutInflater.from(context);
		view = inflater.inflate(R.layout.cs_message_about, null);
		LinearLayout.LayoutParams param_view = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		param_view.leftMargin = DisplayManager.GetInstance().changeWidthSize(30);
		param_view.rightMargin = DisplayManager.GetInstance().changeWidthSize(30);
		param_view.topMargin = DisplayManager.GetInstance().changeWidthSize(20);
		view.setLayoutParams(param_view);
		version_title = (TextView)view.findViewById(R.id.version_title);
		version_content = (TextView)view.findViewById(R.id.version_content);
		help_title = (TextView)view.findViewById(R.id.help_title);
		help_title.setFocusable(true);
		help_title.setFocusableInTouchMode(true);
		help_content = (TextView)view.findViewById(R.id.help_content);
		
		version_title.setTextSize(TypedValue.COMPLEX_UNIT_DIP,TEXT_SIZE);
		version_content.setTextSize(TypedValue.COMPLEX_UNIT_DIP,TEXT_SIZE);
		help_title.setTextSize(TypedValue.COMPLEX_UNIT_DIP,TEXT_SIZE);
		help_content.setTextSize(TypedValue.COMPLEX_UNIT_DIP,TEXT_SIZE);
		
		version_title.setText("版本信息");
		version_content.setText("当前版本："+Config.GetInstance().getVersionDisplayText());
		help_title.setText("使用帮助");
		addView(view);
		
		setContentData(getHelpContent(Config.OemType.getOemType(Config.GetInstance().getOemType())));
	}
	
	public void setContentData(String string){
		help_content.setText(string);
	}
	
	private String getHelpContent(OemType type){
		
		switch (type) {
		case TP_Link:
			StringBuffer sb = new StringBuffer();
			sb.append("一、优朋影视简介\n");
			sb.append("优朋普乐是国内唯一一家与美国好莱坞索尼、华纳兄弟、派拉蒙、环球、20世纪福克斯五大电影公司结成数字发行战略合作伙伴的企业。节目内容覆盖电影、电视剧、少儿、动漫、综艺、纪录片、生活、3D等八大频道。\n");
			sb.append("二、充值卡购买渠道\n");
			sb.append("拨打播放器厂家服务电话或优朋普乐24 小时服务热线：400-655-7899或登录优朋影视官方服务网址：dk.voole.com直接购买。\n");
			sb.append("三、使用说明\n");
			sb.append("【翻页】\n");
			sb.append("1、用遥控器点击页码输入框，调出数字键盘，输入页数翻页。\n");
			sb.append("2、用鼠标点击页码输入框，调出数字键盘，输入页数翻页。\n");
//			sb.append("3、用鼠标点击翻页箭头翻页。\n");
			sb.append("【搜索】\n");
			sb.append("1、点击“今日推荐”右上角“搜索”按钮进入搜索页面。\n");
			sb.append("2、从频道列表中选择搜索按钮进入搜索页面。\n");
			sb.append("3、输入影片名称首字母进行搜索，比如“碟中谍”要输入“DZD”。\n");
			sb.append("【收藏】\n");
			sb.append("1、影片详情页点击“收藏”按钮，收藏您喜爱的影片。\n");
			sb.append("2、频道列表中选择“我的收藏”，可以对您收藏的影片进行观赏、查询和删除。\n");
			sb.append("【观片历史】\n");
			sb.append("1、点击“今日推荐”右上角“观片历史”按钮进入“观片历史”页面。\n");
			sb.append("2、从频道列表中选择观片历史进入观片历史页面。\n");
			sb.append("3、观片历史中您可以查询一个月内的观看记录，可以重新观看或从上一次的观看结束时间进行续播。\n");
			sb.append("【充值】\n");
			sb.append("1、点击“今日推荐”右上角“我要充值”按钮，进入账户管理-我要充值页面。\n");
			sb.append("2、频道列表中选择“账户管理”，进入 “我要充值”页面。\n");
			sb.append("3、未订购包年、包月服务且账户余额为0元时，点击付费影片，弹出扣费提示信息，点击确定，进入“我要充值”页面。\n");
			sb.append("4、输入正确的充值卡密码，点击确定。\n");
			sb.append("【订购】\n");
			sb.append("1、频道列表中选择“账户管理”，进入 “我要订购”页面。\n");
			sb.append("2、未订购包年、包月服务，点击付费影片，弹出扣费提示信息，点击“更多优惠”，进入“我要订购”页面。\n");
			sb.append("3、选择您需要订购的包年或包月服务，点击确定按钮。\n");
			sb.append("【订购记录及有效期查询】\n");
			sb.append("1、进入影片详情页，可显示您已订购的服务及有效期限。\n");
			sb.append("2、从频道列表，选择“账户管理”——>“我要订购”，可查看您已订购的服务及有效期限。\n");
			sb.append("四、播放操作\n");
			sb.append("【播放/暂停】\n");
			sb.append("1、按遥控器OK（确定）键。\n");
			sb.append("2、鼠标点击播放/暂停按钮。\n");
			sb.append("【快进】\n");
			sb.append("1、长按遥控器右键。\n");
			sb.append("【快退】\n");
			sb.append("1、音量+键，改为音量上键。\n");
			sb.append("【增大音量】\n");
			sb.append("1、按遥控器音量+键。\n");
			sb.append("2、鼠标向右拖拽音量条。\n");
			sb.append("【减小音量】\n");
			sb.append("1、音量-键，改为音量下键。\n");
			sb.append("2、鼠标向左拖拽音量条。\n");
			sb.append("【静音】\n");
			sb.append("1、鼠标单击音量按钮。\n");
			sb.append("【选集】\n");
			sb.append("1、播放电视剧时，按遥控器菜单键，屏幕右侧出现集数列表，选择需要观看的集数，按确认键选中集数播放。\n");
			sb.append("【退出播放】\n");
			sb.append("1、播放过程中按遥控器返回键。\n");
			sb.append("2、播放过程中点击鼠标右键。\n");
			return sb.toString();
		default:
			break;
		}
		StringBuffer sb = new StringBuffer();
		sb.append("一、优朋影视简介\n");	sb.append("优朋普乐是国内唯一一家与美国好莱坞索尼、华纳兄弟、派拉蒙、环球、20世纪福克斯五大电影公司结成数字发行战略合作伙伴的企业。节目内容覆盖电影、电视剧、少儿、动漫、综艺、纪录片、生活、3D等八大频道。\n");
		sb.append("二、充值卡购买渠道\n");
		sb.append("拨打播放器厂家服务电话或优朋普乐24 小时服务热线：400-655-7899或登录优朋影视官方服务网址：dk.voole.com直接购买。\n");
		sb.append("三、使用说明\n");
		sb.append("【翻页】\n");
		sb.append("1、使用用遥控器输入数字键输入页数翻页。\n");
		sb.append("2、用鼠标点击页码输入框，调出数字键盘，输入页数翻页。\n");
		sb.append("3、用鼠标点击翻页箭头翻页。\n");
		sb.append("【搜索】\n");
		sb.append("1、点击“优朋首页”右上角“搜索”按钮进入搜索页面。\n");
		sb.append("2、任意界面右上角选择搜索按钮进入搜索页面。\n");
		sb.append("3、输入影片名称首字母进行搜索，比如“碟中谍”要输入“DZD”。\n");
		sb.append("【收藏】\n");
		sb.append("1、影片详情页点击“收藏”按钮，收藏您喜爱的影片。\n");
		sb.append("2、任意界面右上角选择“收藏”，可以对您收藏的影片进行观赏、查询和删除。\n");
		sb.append("【观片历史】\n");
		sb.append("1、点击任意界面右上角“历史”按钮进入“观片历史”页面。\n");
		sb.append("2、观片历史中您可以查询一个月内的观看记录，可以重新观看或从上一次的观看结束时间进行续播。\n");
		sb.append("【充值】\n");
		sb.append("1、点击任意界面右上角“充值”按钮，进入“我的优朋-消费中心-我要充值”页面。\n");
		sb.append("2、未订购包年、包月服务且账户余额为0元时，点击付费影片，弹出扣费提示信息，点击确定，进入“我要充值”页面。\n");
		sb.append("3、输入正确的充值卡密码，点击确定。\n");
		sb.append("【订购】\n");
		sb.append("1、任意界面中选择右上角“我的优朋”，进入“消费中心-我要订购”页面。\n");
		sb.append("2、未订购包年、包月服务，点击付费影片，弹出扣费提示信息，点击“更多优惠”，进入“我要订购”页面。\n");
		sb.append("3、选择您需要订购的包年或包月服务，点击确定按钮。\n");
		sb.append("【订购记录及有效期查询】\n");
		sb.append("1、进入影片详情页，可显示您已订购的服务及有效期限。\n");
		sb.append("2、从任意界面中选择右上角，选择“我的优朋”，进入消费中心-订购记录页面。可查看您已订购的服务及有效期限。\n");
		sb.append("四、播放操作\n");
		sb.append("【播放/暂停】\n");
		sb.append("1、按遥控器OK（确定）键。\n");
		sb.append("2、鼠标点击播放/暂停按钮。\n");
		sb.append("【快进】\n");
		sb.append("1、长按遥控器右键。\n");
		sb.append("【快退】\n");
		sb.append("1、长按遥控器左键。\n");
		sb.append("【增大音量】\n");
		sb.append("1、按遥控器音量+键。\n");
		sb.append("【减小音量】\n");
		sb.append("1、按遥控器音量—键。\n");
		sb.append("【静音】\n");
		sb.append("1、按遥控器静音键。\n");
		sb.append("【调出播放列表】\n");
		sb.append("1、播放过程中按遥控器上下键。\n");
		sb.append("【选集】\n");
		sb.append("1、播放电视剧时，遥控器直接输入集数，点击确定键。\n");
		sb.append("【退出播放】\n");
		sb.append("1、播放过程中按遥控器返回键。\n");
		sb.append("2、播放过程中点击鼠标右键。\n");
		return sb.toString();
	}
}
