package com.samuel.chefhero.data;

import android.content.Context;

import com.google.gson.Gson;
import com.samuel.chefhero.data.model.User;
import com.samuel.chefhero.util.Utils;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<User> login(Context context, String username, String password) {

        try {

            User fakeUser = Utils.getUserFromAssets(context);
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }

}
