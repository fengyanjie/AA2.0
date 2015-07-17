package com.voole.epg.base.common;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.voole.epg.R;
import com.voole.epg.base.BaseButton;
import com.voole.epg.base.BaseDialog;

public class TVAlertDialog extends BaseDialog {

	private static int  TEXT_SIZE=DisplayManager.GetInstance().changeTextSize(28);
	
	public TVAlertDialog(Context context) {
		super(context);
	}
	
	public TVAlertDialog(Context context, int theme) {
		super(context, theme);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Window window = getWindow();
		WindowManager.LayoutParams layoutParams = window.getAttributes();
		layoutParams.width = (int) (DisplayManager.GetInstance().getScreenWidth() / 2.1);
		layoutParams.height = (int) (DisplayManager.GetInstance().getScreenHeight() / 2.4);
		window.setAttributes(layoutParams);
	}

	public static class Builder{
		private Context context;
		private String title;
		private boolean flag = true;
		private String positiveButtonText;
		private String negativeButtonText;
		
		private DialogInterface.OnClickListener
		                        positiveButtonClickListener,
		                        negativeButtonClickListener;
		
		public Builder(Context context){
			this.context = context;
		}
		
		public Builder setTitle(String title){
			this.title = title;
			return this;
		}
		
		public Builder setTitle(int resId){
			this.title = (String)context.getText(resId);
			return this;
		}
		
		public Builder setCancelable(boolean flag){
			this.flag = flag;
			return this;
		}
		
		public Builder setPositiveButton(String positiveButtonText,
				DialogInterface.OnClickListener listener){
			this.positiveButtonText = positiveButtonText;
			this.positiveButtonClickListener = listener;
			return this;
		}
		
		public Builder setPositiveButton(int positiveButtonText,
				DialogInterface.OnClickListener listener){
			this.positiveButtonText = (String)context.getText(positiveButtonText);
			this.positiveButtonClickListener = listener;
			return this;
		}
		
		public Builder setNegativeButton(String negativeButtonText,
				DialogInterface.OnClickListener listener){
			this.negativeButtonText = negativeButtonText;
			this.negativeButtonClickListener = listener;
			return this;
		}
		
		public Builder setNegativeButton(int negativeButtonText,
				DialogInterface.OnClickListener listener){
			this.negativeButtonText = (String)context.getText(negativeButtonText);
			this.negativeButtonClickListener = listener;
			return this;
		}
		
		public TVAlertDialog create() {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final TVAlertDialog epgDialog = new TVAlertDialog(context,
					R.style.alertDialog);
			View layout = inflater.inflate(R.layout.cs_alert_dialog, null);
			TextView title = (TextView)layout.findViewById(R.id.title);
			title.setText(this.title);
			title.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE);
			if (positiveButtonText != null) {
				((ShadeButton) layout.findViewById(R.id.positiveButton))
						.setText(this.positiveButtonText);
				if (positiveButtonClickListener != null) {
					((ShadeButton) layout.findViewById(R.id.positiveButton))
							.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View v) {
									epgDialog.dismiss();
									positiveButtonClickListener.onClick(
											epgDialog,
											DialogInterface.BUTTON_POSITIVE);
								}
							});
				}
			} else {
				layout.findViewById(R.id.bt1).setVisibility(
						View.GONE);
			}
			if (negativeButtonText != null) {
				((ShadeButton) layout.findViewById(R.id.negativeButton))
						.setText(this.negativeButtonText);
				if (negativeButtonClickListener != null) {
					((ShadeButton) layout.findViewById(R.id.negativeButton))
							.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View v) {
									epgDialog.dismiss();
									negativeButtonClickListener.onClick(
											epgDialog,
											DialogInterface.BUTTON_NEGATIVE);
								}
							});
				}
			} else {
				layout.findViewById(R.id.bt2).setVisibility(
						View.GONE);
			}
			epgDialog.setContentView(layout);
			epgDialog.setCancelable(flag);
			return epgDialog;
		}
	}

}
