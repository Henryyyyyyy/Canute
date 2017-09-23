package me.henry.canutecore.ui.camera;

import android.net.Uri;

import me.henry.canutecore.delegates.PermissionCheckerDelegate;
import me.henry.canutecore.util.file.FileUtil;


public class CanuteCamera {

    public static Uri createCropFile() {
        return Uri.parse
                (FileUtil.createFile("crop_image",FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }
}
