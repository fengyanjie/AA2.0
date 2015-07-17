package com.voole.epg.model.play;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.voole.epg.R;
import com.voole.epg.base.BaseDialog;
import com.voole.epg.base.common.DisplayManager;

public class PlayDialog extends BaseDialog {

	private static int  TEXT_SIZE=DisplayManager.GetInstance().changeTextSize(28);
	private static int screenWidth = 0;
	private static int screenHeight = 0;
	
	private void init() {
		screenHeight = DisplayManager.GetInstance().getScreenHeight();
		screenWidth = DisplayManager.GetInstance().getScreenWidth();
	}
	
	public PlayDialog(Context context) {
		super(context);
		init();
	}
	
	public PlayDialog(Context context, int theme) {
		super(context, theme);
		init();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
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
		
		public PlayDialog create(double width,double height) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final PlayDialog epgDialog = new PlayDialog(context,
					R.style.alertDialog);
			View layout = inflater.inflate(R.layout.cs_play_dialog, null);
			TextView title = (TextView)layout.findViewById(R.id.title);
			title.setPadding(5, 0, 5, 0);
			title.setText(this.title);
			title.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE);
			if (positiveButtonText != null) {
				((PlayButton) layout.findViewById(R.id.positiveButton))
						.setText(this.positiveButtonText);
				if (positiveButtonClickListener != null) {
					((PlayButton) layout.findViewById(R.id.positiveButton))
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
				layout.findViewById(R.id.positiveButton).setVisibility(
						View.GONE);
			}
			if (negativeButtonText != null) {
				((PlayButton) layout.findViewById(R.id.negativeButton))
						.setText(this.negativeButtonText);
				if (negativeButtonClickListener != null) {
					((PlayButton) layout.findViewById(R.id.negativeButton))
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
				layout.findViewById(R.id.negativeButton).setVisibility(
						View.GONE);
			}
			epgDialog.setContentView(layout,new LayoutParams((int)(screenWidth/2*width),(int)(screenHeight/2*height)));
			epgDialog.setCancelable(flag);
			return epgDialog;
		}
	}

}
