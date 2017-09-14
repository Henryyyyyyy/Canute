package me.henry.canutecore.delegates.web.event;


import me.henry.canutecore.util.log.CanuteLogger;

/**
 * Created by 傅令杰
 */

public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        CanuteLogger.e("UndefineEvent", params);
        return null;
    }
}
