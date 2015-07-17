package com.voole.epg.base.common;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;

public class NumberDialog extends Dialog {

	private Context mContext = null;
	private NumberInputView numberInputView = null;
	private int inputNumber = -1;
	private int totalPageSize = 0;

	private int inputLimit = 0;

	private NumberListener numberListener = null;

	public void setNumber(int number) {
		this.inputNumber = number;
		numberInputView.setInput(inputNumber);
	}

	public void clearNumber() {
		numberInputView.clearInput();
	}

	public void setTotalPageSize(int totalPageSize) {
		this.totalPageSize = totalPageSize;
	}

	public void setNumberListener(NumberListener numberListener) {
		this.numberListener = numberListener;
	}

	public interface NumberListener {
		public void onGotoPage(int pageNo);
		public void onInput(String str);
	}

	public NumberDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		this.mContext = context;
	}

	public NumberDialog(Context context, int theme) {
		super(context, theme);
		this.mContext = context;
	}

	public NumberDialog(Context context) {
		super(context);
		this.mContext = context;
	}

	public void setInputLimit(int inputLimit) {
		this.inputLimit = inputLimit;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		numberInputView = new NumberInputView(mContext);
		setContentView(numberInputView);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_1:
			numberInputView.setInput(1);
			return true;
		case KeyEvent.KEYCODE_2:
			numberInputView.setInput(2);
			return true;
		case KeyEvent.KEYCODE_3:
			numberInputView.setInput(3);
			return true;
		case KeyEvent.KEYCODE_4:
			numberInputView.setInput(4);
			return true;
		case KeyEvent.KEYCODE_5:
			numberInputView.setInput(5);
			return true;
		case KeyEvent.KEYCODE_6:
			numberInputView.setInput(6);
			return true;
		case KeyEvent.KEYCODE_7:
			numberInputView.setInput(7);
			return true;
		case KeyEvent.KEYCODE_8:
			numberInputView.setInput(8);
			return true;
		case KeyEvent.KEYCODE_9:
			numberInputView.setInput(9);
			return true;
		case KeyEvent.KEYCODE_0:
			numberInputView.setInput(0);
			return true;
		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	public class NumberInputView extends BaseLinearLayout {

		private NumberButton[] buttons = null;
		private TextView input_tv = null;
		private ShadeButton ok_btn = null;
		private ShadeButton clear_btn = null;
		private int ITEM_SZIE = 10;

		public void setInput(int input) {
			if (input_tv.getText().toString().length() < inputLimit) {
				input_tv.append(input + "");
			}
		}

		public String getInput() {
			return input_tv.getText().toString();
		}

		public void clearInput() {
			input_tv.setText("");
		}

		public NumberInputView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			initView(context);
		}

		public NumberInputView(Context context, AttributeSet attrs) {
			super(context, attrs);
			initView(context);
		}

		public NumberInputView(Context context) {
			super(context);
			initView(context);
		}

		private void initView(final Context context) {
			setOrientation(VERTICAL);
			setGravity(Gravity.CENTER);
			setBackgroundResource(R.drawable.cs_navigation_goto_bg);
			buttons = new NumberButton[ITEM_SZIE];

			LinearLayout input_text = new LinearLayout(context);
			LinearLayout.LayoutParams input_text_param = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			input_text_param.topMargin = 20;
			input_text_param.leftMargin = 40;
			input_text_param.rightMargin = 40;
			LinearLayout.LayoutParams input_tv_param = new LinearLayout.LayoutParams(
					380, LayoutParams.WRAP_CONTENT);
			input_text.setGravity(Gravity.CENTER_HORIZONTAL);
			input_tv = new TextView(context);
			input_tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, DisplayManager
					.GetInstance().changeTextSize(25));
			input_tv.setGravity(Gravity.CENTER_HORIZONTAL);
			input_text.setLayoutParams(input_text_param);
			input_tv.setSingleLine(true);
			TextPaint tp = input_tv.getPaint();
			tp.setFakeBoldText(true);
			input_tv.setLayoutParams(input_tv_param);
			input_tv.setBackgroundResource(R.drawable.cs_navigation_goto_input_bg);
			input_tv.setTextColor(Color.BLACK);
			if (inputNumber > 0) {
				input_tv.setText(inputNumber + "");
			}
			// input_tv.setBackgroundColor(Color.parseColor("#929292"));
			input_text.addView(input_tv);
			addView(input_text);

			LinearLayout number_up = new LinearLayout(context);
			LinearLayout.LayoutParams number_up_param = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			number_up_param.topMargin = 20;
			number_up.setLayoutParams(number_up_param);
			LinearLayout.LayoutParams btn_param = new LinearLayout.LayoutParams(
					DisplayManager.GetInstance().changeWidthSize(80),
					DisplayManager.GetInstance().changeHeightSize(70));
			btn_param.setMargins(5, 5, 5, 5);

			number_up.setGravity(Gravity.CENTER_HORIZONTAL);
			number_up.setOrientation(HORIZONTAL);
			for (int i = 0; i < ITEM_SZIE / 2; i++) {
				buttons[i] = new NumberButton(context);
				buttons[i].setLayoutParams(btn_param);
				buttons[i].setText(i + 1 + "");
				number_up.addView(buttons[i]);
			}
			addView(number_up);
			LinearLayout number_down = new LinearLayout(context);
			number_down.setGravity(Gravity.CENTER_HORIZONTAL);
			number_down.setOrientation(HORIZONTAL);
			for (int i = ITEM_SZIE / 2; i < ITEM_SZIE; i++) {
				buttons[i] = new NumberButton(context);
				buttons[i].setLayoutParams(btn_param);
				if (i == 9) {
					buttons[i].setText(0 + "");
				} else {
					buttons[i].setText(i + 1 + "");
				}
				number_down.addView(buttons[i]);
			}
			addView(number_down);

			for (int i = 0; i < ITEM_SZIE; i++) {
				buttons[i].setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						String inputStr = input_tv.getText().toString();
						if (inputStr.length() < inputLimit) {
							input_tv.append(((ShadeButton) v).getText());
						}
					}
				});
			}

			LinearLayout oper_btn = new LinearLayout(context);
			oper_btn.setOrientation(HORIZONTAL);
			oper_btn.setGravity(Gravity.CENTER_HORIZONTAL);
			LinearLayout.LayoutParams oper_btn_param = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			oper_btn_param.bottomMargin = 20;
			oper_btn.setLayoutParams(oper_btn_param);
			ok_btn = new ShadeButton(context);
			ok_btn.setText(context.getString(R.string.common_ok_button));
			ok_btn.setTextSize(DisplayManager.GetInstance().changeTextSize(25));
			ok_btn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					String number = input_tv.getText().toString();
					if ("".equals(number)) {
						// numberInputDialog.dismiss();
						dismiss();
					} else {
						dismiss();
						if (numberListener != null) {
							numberListener.onInput(number);
							if (inputLimit > 5) {
								return;
							}
						}
						// numberInputDialog.dismiss();
						int pageNumber = Integer.parseInt(number);
						if (pageNumber >= 1 && pageNumber <= totalPageSize) {
							if (numberListener != null) {
								numberListener.onGotoPage(Integer
										.parseInt(number));
							}
						} else {
							new TVAlertDialog.Builder(context)
									.setCancelable(false)
									.setTitle(
											R.string.movie_navigator_input_fail)
									.setPositiveButton(
											R.string.common_ok_button,
											new DialogInterface.OnClickListener() {
												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {

												}
											}).create().show();
						}
					}
				}
			});
			oper_btn.addView(ok_btn);

			clear_btn = new ShadeButton(context);
			clear_btn.setText(context.getString(R.string.common_clear_button));
			clear_btn.setTextSize(DisplayManager.GetInstance().changeTextSize(
					25));
			clear_btn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					input_tv.setText("");
				}
			});
			oper_btn.addView(clear_btn);
			addView(oper_btn);
		}
	}

}
