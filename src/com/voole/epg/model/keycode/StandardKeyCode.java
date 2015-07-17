package com.voole.epg.model.keycode;

public class StandardKeyCode implements IKeyCode{

	@Override
	public int getPreviousPageCode() {
//		return KeyEvent.KEYCODE_PAGE_UP;
		return 92;
	}

	@Override
	public int getNextPageCode() {
//		return KeyEvent.KEYCODE_PAGE_DOWN;
		return 93;
	}
	
}
