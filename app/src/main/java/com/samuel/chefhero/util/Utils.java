package com.samuel.chefhero.util;

import android.content.Context;


import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Utils {

    public static String loadJSONFromAssetsWithFileName(Context context, String fileName) throws IOException {

        InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            return new String(buffer, StandardCharsets.UTF_8);

    }
}
