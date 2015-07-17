package com.voole.epg.model.keycode;

import com.voole.epg.Config;


public class KeyCodeManager {
	private static KeyCodeManager instance = null;
	
	private KeyCodeManager(){
		
	}
		
	public static KeyCodeManager GetInstance(){
        if (instance == null){
            instance = new KeyCodeManager();
        }
        return instance;
    }
	
	private IKeyCode iKeyCode = null;
	
	public IKeyCode getKeyCodeValue(){
		if(iKeyCode == null){
			init();
		}
		return iKeyCode;
	}
	
	private void init(){
		switch (Config.KeyCodeType.getType(Config.GetInstance().getKeyCodeType())) {
		case Standard:
			iKeyCode = new StandardKeyCode();
			break;
		}
	}
}
