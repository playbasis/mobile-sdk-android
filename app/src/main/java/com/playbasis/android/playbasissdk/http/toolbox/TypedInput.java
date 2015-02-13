package com.playbasis.android.playbasissdk.http.toolbox;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by gregoire on 1/9/15.
 */
public interface TypedInput {
    /** Returns the mime type. */
    String mimeType();

    /** Length in bytes. Returns {@code -1} if length is unknown. */
    long length();

    /**
     * Read bytes as stream. Unless otherwise specified, this method may only be called once. It is
     * the responsibility of the caller to close the stream.
     */
    InputStream in() throws IOException;
}
