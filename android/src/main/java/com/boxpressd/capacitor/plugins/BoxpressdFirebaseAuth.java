package com.boxpressd.capacitor.plugins;

import android.util.Log;
import com.getcapacitor.CapConfig;
import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

@NativePlugin(name = "FirebaseAuthTokenListener")
public class FirebaseAuthTokenListener extends Plugin {
    public static final String CONFIG_KEY_PREFIX = "plugins.FirebaseAuthTokenListener.";
    private static final String PLUGIN_TAG = "FirebaseAuthPlugin";

    private FirebaseAuth firebaseAuth;
    private CapConfig config;

    public void load() {
        super.load();

        this.config = new CapConfig(this.bridge.getActivity().getAssets(), null);
        String languageCode = this.config.getString(CONFIG_KEY_PREFIX + "languageCode", "en");

        // FirebaseApp is not initialized in this process - Error #1
        Log.d(PLUGIN_TAG, "Verifying if the default FirebaseApp was initialized.");
        if (FirebaseApp.getApps(this.getContext()).size() == 0) {
            Log.d(PLUGIN_TAG, "Initializing the default FirebaseApp ");
            FirebaseApp.initializeApp(this.getContext());
        }

        Log.d(PLUGIN_TAG, "Retrieving FirebaseAuth instance");
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.firebaseAuth.setLanguageCode(languageCode);
        this.firebaseAuth.addIdTokenListener((FirebaseAuth.IdTokenListener) auth -> {
            Log.d(PLUGIN_TAG, "Firebase token ID updated");
            FirebaseUser currentUser = auth.getCurrentUser();
            if (currentUser != null) {
                currentUser.getIdToken(false).addOnCompleteListener(task -> {
                    if (task.getResult() != null) {
                        Log.d(PLUGIN_TAG, "Got new Firebase token ID");
                        String tokenId = task.getResult().getToken();
                        // https://capacitorjs.com/docs/plugins/android
                        JSObject ret = new JSObject();
                        ret.put("tokenId", tokenId);
                        notifyListeners("firebaseTokenRefreshed", ret);
                    }
                });
            }
        });
    }

    @PluginMethod()
    public void signOut(PluginCall call) {
        FirebaseUser currentUser = this.firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            this.firebaseAuth.signOut();
        }
        call.resolve();
    }

    @Override
    public void notifyListeners(String eventName, JSObject data) {
        super.notifyListeners(eventName, data);
    }
}
