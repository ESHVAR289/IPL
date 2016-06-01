package com.ipl.CallbackInterface;

/**
 * Created by bridgeit007 on 27/5/16.
 */
public interface CallbackDownloadImg {
    void onSuccess(byte[] result);
    void onError(String result);
}
