package me.henry.canutecore.delegates;

/**
 * Created by zj on 2017/8/14.
 * me.henry.canutecore.delegates
 */

public abstract class CanuteDelegate extends PermissionCheckerDelegate{
    public <T extends CanuteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
