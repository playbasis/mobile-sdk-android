package com.playbasis.android.playbasissdk.secure;

import android.content.Context;

import com.facebook.android.crypto.keychain.SharedPrefsBackedKeyChain;
import com.facebook.crypto.Crypto;
import com.facebook.crypto.Entity;
import com.facebook.crypto.exception.CryptoInitializationException;
import com.facebook.crypto.exception.KeyChainException;
import com.facebook.crypto.util.SystemNativeCryptoLibrary;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by gregoire barret on 2/20/15.
 * For PlayBasisSdk project.
 */
public class ConcrealHelper {
    public static final String TAG = "ConcrealHelper";
    
    public static void encryptFile(Context context, File file, Entity entity, byte[] plainTextBytes) throws IOException, KeyChainException, CryptoInitializationException {
        // Creates a new Crypto object with default implementations of
// a key chain as well as native library.
        Crypto crypto = new Crypto(
                new SharedPrefsBackedKeyChain(context),
                new SystemNativeCryptoLibrary());

// Check for whether the crypto functionality is available
// This might fail if android does not load libaries correctly.
        if (!crypto.isAvailable()) {
            return;
        }

        OutputStream fileStream = new BufferedOutputStream(
                new FileOutputStream(file));

// Creates an output stream which encrypts the data as
// it is written to it and writes it out to the file.
        OutputStream outputStream = crypto.getCipherOutputStream(
                fileStream,
                entity);

// Write plaintext to it.
        outputStream.write(plainTextBytes);
        outputStream.close();
        
    }
    
    public static byte[] decryptFile(Context context, File file, Entity entity) throws IOException, KeyChainException, CryptoInitializationException {
        // Creates a new Crypto object with default implementations of
// a key chain as well as native library.
        Crypto crypto = new Crypto(
                new SharedPrefsBackedKeyChain(context),
                new SystemNativeCryptoLibrary());
        
        // Get the file to which ciphertext has been written.
        FileInputStream fileStream = new FileInputStream(file);



// Creates an input stream which decrypts the data as
// it is read from it.
        InputStream inputStream = crypto.getCipherInputStream(
                fileStream,
                entity);

// Read into a byte array.
        int read;
        byte[] buffer = new byte[1024];
        File out = new File("test");

// You must read the entire stream to completion.
// The verification is done at the end of the stream.
// Thus not reading till the end of the stream will cause
// a security bug.
        while ((read = inputStream.read(buffer)) != -1) {
          //  out.write(buffer, 0, read);
          ///  byte[] bytes = read;
        }

        inputStream.close();
        
    }
    
}
