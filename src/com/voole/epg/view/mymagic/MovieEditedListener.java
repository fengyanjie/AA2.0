package com.voole.epg.view.mymagic;

import java.util.List;

import com.voole.epg.corelib.model.movies.Film;

public interface MovieEditedListener {
	public void onEdited();

	public void onDelete(List<? extends Film> list);

	public void onClear(List<? extends Film> list);

	public void onCancel();
}
