package com.voole.epg.cooperation.aidl;
import com.voole.epg.cooperation.aidl.VooleUser;
interface VooleAIDL {
    VooleUser getVooleUser();
    void startAuth();
    String getPortalUrl();
    void startProxy();
    String getProviderID();
    String getMessageCount();
    String getPlayUrl(String mid, String sid);
    String formatAuthPlayUrl(String mid,String sid);
    String getAddResumeUrl(String mid, String sid, int playtime, String title, String imgUrl);
    String getResumeInfo(String mid, String sid);
}