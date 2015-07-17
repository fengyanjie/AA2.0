package com.voole.epg.base.common;

import android.content.Context;
import android.os.Bundle;

import com.voole.epg.R;
import com.voole.epg.base.BaseDialog;


public class TVProgressDialog extends BaseDialog{

	public TVProgressDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
	}

	public TVProgressDialog(Context context, int theme) {
		super(context, theme);
	}

	public TVProgressDialog(Context context) {
		super(context, R.style.progressDialog);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cs_progress_dialog);
	}
}
