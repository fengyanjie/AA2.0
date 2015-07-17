package com.voole.epg.base.common;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;

public class InputText extends BaseLinearLayout {

	private EditText editText = null;
	private LinearLayout.LayoutParams param_text = null;
	private int PADDING=DisplayManager.GetInstance().changeWidthSize(10);

	public InputText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public EditText getEditText() {
		return editText;
	}

	public InputText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public InputText(Context context) {
		super(context);
		init(context);
	}

	public String getText(){
		return editText.getText().toString();
	}
	
    public void clear(){
    	editText.setText("");
    }
    
    public void setPassword(){
    	if(editText!=null){
    		editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    	}
    }
	
	private void init(Context context) {
//		setFocusable(true);
//		setFocusableInTouchMode(true);
//		setClickable(true);
		param_text = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		editText = new EditText(context);
		param_text.setMargins(8,5,8,5);
		editText.setLayoutParams(param_text);
		editText.setTextColor(Color.WHITE);
		editText.setSingleLine(true);
		editText.setTextSize(TypedValue.COMPLEX_UNIT_SP,DisplayManager.GetInstance().changeTextSize(20));
		editText.setBackgroundResource(R.drawable.cs_input);
		addView(editText);
		
		editText.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				 if(hasFocus){
					 InputText.this.setBackgroundResource(R.drawable.cs_btn_focus);
				 }else{
					 InputText.this.setBackgroundColor(0);
				 }
				
			}
		});
	}

//	@Override
//	protected void onFocusChanged(boolean gainFocus, int direction,
//			Rect previouslyFocusedRect) {
//		if (gainFocus) {
//			setBackgroundResource(R.drawable.input_yes_null);
//			editText.requestFocus();
//		} else {
//			setBackgroundColor(0);
//		}
	//}
}
