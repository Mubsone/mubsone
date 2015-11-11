package com.example.mubsone.mubsone;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mikel on 11/11/15.
 */
public class JWTManager {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    public JWTManager(Context context)
    {
        this.pref = context.getSharedPreferences("Authentication", 0);
        this.editor = this.pref.edit();
    }

    public void setToken(String token)
    {
        editor.putString("token", token);
        editor.commit();
    }

    public String getToken()
    {
        return this.pref.getString("token", null);
    }

    public void delToken()
    {
        editor.putString("token", null);
    }

    public boolean _hasToken()
    {
        return this.pref.getString("token", null) != null;
    }
}
