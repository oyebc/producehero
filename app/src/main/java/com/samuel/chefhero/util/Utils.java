package com.samuel.chefhero.util;

import android.content.Context;


import com.google.gson.Gson;
import com.samuel.chefhero.data.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Utils {

    public static final String DATA_FILE_NAME = "dummy_user.json";

    private static String loadJSONFromAssetsWithFileName(Context context, String fileName) throws IOException {

        InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            return new String(buffer, StandardCharsets.UTF_8);

    }

    public static User getUserFromAssets(Context context) throws IOException{
        String userText = Utils.loadJSONFromAssetsWithFileName(context, Utils.DATA_FILE_NAME);
        Gson gson = new Gson();
        return gson.fromJson(userText, User.class);
    }
}
