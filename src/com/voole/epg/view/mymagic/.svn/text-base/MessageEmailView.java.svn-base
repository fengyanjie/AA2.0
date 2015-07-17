package com.voole.epg.view.mymagic;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.common.AlwaysMarqueeTextView;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.base.common.ID;
import com.voole.epg.base.common.ShadeButton;
import com.voole.epg.corelib.model.account.MessageInfo;

public class MessageEmailView extends BaseLinearLayout {
	private AlwaysMarqueeTextView theme = null;
	private TextView sender = null;
	private TextView time = null;
	private TextView content = null;
	private ShadeButton previousBtn = null;
	private ShadeButton nextBtn = null;
	private ShadeButton backBtn = null;
	
	private final int TXT_SIZE = DisplayManager.GetInstance().changeTextSize(26);
	private MessageInfo info=null;
	private List<MessageInfo> list=null;
	
	private EmailButtonListener listener = null;
	public void setEmailButtonListener(EmailButtonListener listener){
		this.listener = listener;
	}
	
	public MessageEmailView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public MessageEmailView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public MessageEmailView(Context context) {
		super(context);
		init(context);
	}
	
	/**
	 * up to down : 2:5
	 * @param context
	 */
	private void init(Context context){
		this.setOrientation(VERTICAL);
		LinearLayout.LayoutParams param_layout = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		this.setLayoutParams(param_layout);
		
		LinearLayout layout_title = new LinearLayout(context);
		layout_title.setOrientation(VERTICAL);
		LinearLayout.LayoutParams param_layout_title = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 5);
		param_layout_title.topMargin = DisplayManager.GetInstance().changeHeightSize(20);
		param_layout_title.leftMargin = DisplayManager.GetInstance().changeWidthSize(30);
		param_layout_title.rightMargin = DisplayManager.GetInstance().changeWidthSize(10);
		layout_title.setLayoutParams(param_layout_title);
		
		LinearLayout.LayoutParams param_title_item = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		theme = new AlwaysMarqueeTextView(context);
		theme.setMarquee(true);
		theme.setTextSize(TypedValue.COMPLEX_UNIT_SP,TXT_SIZE);
		theme.setGravity(Gravity.CENTER_VERTICAL);
		theme.setText("主题：");
		theme.setLayoutParams(param_title_item);
		layout_title.addView(theme);
		
		LinearLayout layout_middle = new LinearLayout(context);
		layout_middle.setOrientation(HORIZONTAL);
		layout_middle.setLayoutParams(param_title_item);
		
		sender = new TextView(context);
		sender.setTextSize(TypedValue.COMPLEX_UNIT_SP,TXT_SIZE);
		sender.setGravity(Gravity.CENTER_VERTICAL);
		sender.setText("发信人：");
		LinearLayout.LayoutParams param_sender = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 3);
		sender.setLayoutParams(param_sender);
		layout_middle.addView(sender);
		
		LinearLayout.LayoutParams param_btn = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 5);
		param_btn.leftMargin = DisplayManager.GetInstance().changeWidthSize(10);
		param_btn.rightMargin = DisplayManager.GetInstance().changeWidthSize(10);
		previousBtn = new ShadeButton(context);
		previousBtn.setText("上一封");
		previousBtn.setId(ID.MyMagicActivity.MC_MESSAGEDETAIL_PRE_BTN_ID);
		previousBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (listener != null) {
					listener.previous(list,info);
				}
			}
		});
		previousBtn.setTextSize(TXT_SIZE);
		previousBtn.setLayoutParams(param_btn);
		layout_middle.addView(previousBtn);
		
		nextBtn = new ShadeButton(context);
		nextBtn.setText("下一封");
		nextBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (listener != null) {
					listener.next(list,info);
				}
			}
		});
		nextBtn.setTextSize(TXT_SIZE);
		nextBtn.setLayoutParams(param_btn);
		layout_middle.addView(nextBtn);
		
		backBtn = new ShadeButton(context);
		backBtn.setText("返回");
		backBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (listener != null) {
					listener.back();
				}
			}
		});
		backBtn.setTextSize(TXT_SIZE);
		backBtn.setLayoutParams(param_btn);
		layout_middle.addView(backBtn);
		
		layout_title.addView(layout_middle);
		
		time = new TextView(context);
		time.setTextSize(TypedValue.COMPLEX_UNIT_SP,TXT_SIZE);
		time.setGravity(Gravity.CENTER_VERTICAL);
		time.setText("时间：");
		time.setLayoutParams(param_title_item);
		layout_title.addView(time);
		
		addView(layout_title);
		
		ImageView line = new ImageView(context);
		line.setBackgroundResource(R.drawable.cs_about_line);
		LinearLayout.LayoutParams param_line = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		line.setLayoutParams(param_line);
		addView(line);
		
		content = new TextView(context);
		content.setTextSize(TypedValue.COMPLEX_UNIT_SP,TXT_SIZE);
		LinearLayout.LayoutParams param_content = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 3);
		content.setLayoutParams(param_content);
		addView(content);
		
		/*ScrollView scrollView = new ScrollView(context);
		scrollView.setFocusable(true);
		scrollView.setFocusableInTouchMode(true);
		LinearLayout.LayoutParams param_scroll = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 3);
		param_scroll.leftMargin = DisplayManager.GetInstance().changeWidthSize(30);
		param_scroll.rightMargin = DisplayManager.GetInstance().changeWidthSize(10);
		param_scroll.bottomMargin = DisplayManager.GetInstance().changeHeightSize(20);
		scrollView.setLayoutParams(param_scroll);
		
		LinearLayout layout_content = new LinearLayout(context);
		layout_content.setOrientation(VERTICAL);
		LinearLayout.LayoutParams param_layout_content = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		layout_content.setLayoutParams(param_layout_content);
		
		ImageView line = new ImageView(context);
		line.setBackgroundResource(R.drawable.cs_about_line);
		LinearLayout.LayoutParams param_line = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		line.setLayoutParams(param_line);
		layout_content.addView(line);
		
		content = new TextView(context);
		content.setTextSize(TXT_SIZE);
		LinearLayout.LayoutParams param_content = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		content.setLayoutParams(param_content);
		layout_content.addView(content);
		
		scrollView.addView(layout_content);
		
		addView(scrollView);*/
	}
	
	public void setData(List<MessageInfo> list,MessageInfo info){
		this.list=list;
		this.info=info;
		theme.setText("主题："+this.info.getSubject());
		sender.setText("发言人："+this.info.getFrom());
		time.setText("时间："+this.info.getStartdate().substring(0,10));
		content.setText(info.getContent());
	}
	
	public interface EmailButtonListener {
		public void previous(List<MessageInfo> list,MessageInfo info);
		public void next(List<MessageInfo> list,MessageInfo info);
		public void back();
	}
	
}
