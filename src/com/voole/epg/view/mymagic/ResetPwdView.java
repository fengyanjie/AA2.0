package com.voole.epg.view.mymagic;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.voole.epg.R;
import com.voole.epg.base.BaseRelativeLayout;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.base.common.ID;
import com.voole.epg.base.common.MyEditText;
import com.voole.epg.base.common.ShadeButton;

public class ResetPwdView extends BaseRelativeLayout {

	private LayoutInflater inflater;
	private View view;
	private EditText pass_et;
	private EditText compass_et;
	private ShadeButton ok_btn;

	private ResetPwdViewListener resetPwdViewListener=null;

	public void setResetPwdViewListener(
			ResetPwdViewListener resetPwdViewListener) {
		this.resetPwdViewListener = resetPwdViewListener;
	}

	
	
	public interface ResetPwdViewListener {
		public void onFinsih(String password,String compassword);
	}

	public ResetPwdView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public ResetPwdView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public ResetPwdView(Context context) {
		super(context);
		init(context);
	}

	public void clearPwd(){
		pass_et.setText("");
		compass_et.setText("");
	}
	
	private void init(Context context) {

		inflater = LayoutInflater.from(context);
		RelativeLayout layout = new RelativeLayout(context);

		RelativeLayout.LayoutParams param_layout = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		param_layout.leftMargin = DisplayManager.GetInstance().changeWidthSize(
				70);
		param_layout.rightMargin = DisplayManager.GetInstance()
				.changeWidthSize(50);
		param_layout.topMargin = DisplayManager.GetInstance().changeHeightSize(
				60);
		

		layout.setLayoutParams(param_layout);

		view = inflater.inflate(R.layout.cs_mymagic_um_resetpwd, null);
		view.setId(1111);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		view.setLayoutParams(params);
		layout.addView(view);

		pass_et=(MyEditText)view.findViewById(R.id.pass_et);
		pass_et.setId(ID.MyMagicActivity.UM_USERINFO_PWD_RESET_INPUT_ID);
		pass_et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		
		compass_et=(MyEditText)view.findViewById(R.id.compass_et);
		compass_et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		
		
		
		ok_btn = new ShadeButton(context);
		ok_btn.setText("完成");
		ok_btn.setTextSize(DisplayManager.GetInstance().changeTextSize(22));
		RelativeLayout.LayoutParams param_ok_btn = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_ok_btn.addRule(RelativeLayout.CENTER_HORIZONTAL);
		param_ok_btn.addRule(RelativeLayout.BELOW, 1111);
		param_ok_btn.topMargin = 20;
		ok_btn.setLayoutParams(param_ok_btn);
		ok_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(resetPwdViewListener!=null){
					String password=(pass_et.getText().toString()) ;
					String compassword=(compass_et.getText().toString()) ;
					resetPwdViewListener.onFinsih(password,compassword);
				}
			}
		});
		
		layout.addView(ok_btn);
		addView(layout);
	}

}
