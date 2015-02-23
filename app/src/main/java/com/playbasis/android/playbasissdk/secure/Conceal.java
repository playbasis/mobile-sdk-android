package com.playbasis.android.playbasissdk.secure;

import android.content.Context;
import android.util.Log;

import com.facebook.crypto.Entity;
import com.facebook.crypto.exception.CryptoInitializationException;
import com.facebook.crypto.exception.KeyChainException;

import java.io.File;
import java.io.IOException;

/** 
 * Created by gregoire barret on 2/23/15.
 * For PlayBasisSdk project.
 */
public class Conceal {
    public static final String TAG = "Contreal";

    private Context mContext;
    private File mFile;
    private Entity mEntity;
    
    public Conceal(Context context) {
        this.mContext= context;
        mFile = new File(mContext.getFilesDir(), "requestCache");
        mEntity = new Entity("requestCache");
    }
    
    
    public void write(String content){
        if(content==null){
            Log.e("CONTREAL", "Write::String null");
            return;
        }
        try {
            ConcealHelper.encryptFile(mContext, mFile, mEntity, content.getBytes());
        } catch (IOException e) {
            Log.e("CONTREAL", "Write::IOException: " + e.toString());
        } catch (KeyChainException e) {
            Log.e("CONTREAL", "Write::KeyChainException: " + e.toString());
        } catch (CryptoInitializationException e) {
            Log.e("CONTREAL", "Write::CryptoInitializationException: " + e.toString());
        }

    }
    
    public String read(){
        String content = "";
        try {
            content = ConcealHelper.decryptFile(mContext, mFile, mEntity);
        } catch (IOException e) {
            Log.e("CONTREAL", "Read::IOException: " + e.toString());
        } catch (KeyChainException e) {
            Log.e("CONTREAL", "Read::KeyChainException: " + e.toString());
        } catch (CryptoInitializationException e) {
            Log.e("CONTREAL", "Read::CryptoInitializationException: " + e.toString());
        }
        return content;
    }
    
}
