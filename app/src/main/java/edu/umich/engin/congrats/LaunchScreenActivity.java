package edu.umich.engin.congrats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.tagmanager.Container;
import com.google.android.gms.tagmanager.Container.FunctionCallMacroCallback;
import com.google.android.gms.tagmanager.Container.FunctionCallTagCallback;
import com.google.android.gms.tagmanager.ContainerHolder;
import com.google.android.gms.tagmanager.TagManager;
import com.google.firebase.messaging.FirebaseMessagingService;


import java.util.Map;
import java.util.concurrent.TimeUnit;

public class LaunchScreenActivity extends AppCompatActivity {

    // Container id for Google Tag Manager
    private final String CONTAINER_ID = "GTM_KPJPD5";
    // Timeout in milliseconds for container to open
    private static final long TIMEOUT_FOR_CONTAINER_OPEN_MILLISECONDS = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.umich.engin.congrats.R.layout.activity_launch_screen);
        Intent intent = new Intent(LaunchScreenActivity.this, Onboarding.class);
        startActivity(intent);
        // Google Tag Manager stuff
        //TagManager tagManager = TagManager.getInstance(this);
        // Make request to load container
        /*PendingResult<ContainerHolder> pending =
                tagManager.loadContainerPreferNonDefault(CONTAINER_ID, R.raw.gtm_kpjpd5);*/
        //Firebase.setAndroidContext(this);
        // The onResult method will be called as soon as one of the following happens:
        //     1. a saved container is loaded
        //     2. if there is no saved container, a network container is loaded
        //     3. the 2-second timeout occurs
       /*pending.setResultCallback(new ResultCallback<ContainerHolder>() {
            @Override
            public void onResult(ContainerHolder containerHolder) {
                ContainerHolderSingleton.setContainerHolder(containerHolder);
                Container container = containerHolder.getContainer();
                if (!containerHolder.getStatus().isSuccess()) {
                    Log.e("congrats", "failure loading container");
                    return;
                }
                ContainerLoadedCallback.registerCallbacksForContainer(container);
                containerHolder.setContainerAvailableListener(new ContainerLoadedCallback());
            }
        }, TIMEOUT_FOR_CONTAINER_OPEN_MILLISECONDS, TimeUnit.MILLISECONDS);
        Intent intent = new Intent(LaunchScreenActivity.this, Onboarding.class);
        startActivity(intent);
        finish();
    }*/

    //private static class ContainerLoadedCallback implements ContainerHolder.ContainerAvailableListener {
       // @Override
        //public void onContainerAvailable(ContainerHolder containerHolder, String containerVersion) {
            // We load each container when it becomes available.
           /* Container container = containerHolder.getContainer();
            registerCallbacksForContainer(container);
        }*/

        //public static void registerCallbacksForContainer(Container container) {
            // Register two custom function call macros to the container.
            //container.registerFunctionCallMacroCallback("increment", new CustomMacroCallback());
            //container.registerFunctionCallMacroCallback("mod", new CustomMacroCallback());
            // Register a custom function call tag to the container.
            //container.registerFunctionCallTagCallback("custom_tag", new CustomTagCallback());
        //}

       /* private static class CustomMacroCallback implements FunctionCallMacroCallback {
            private int numCalls;

            @Override
            public Object getValue(String name, Map<String, Object> parameters) {
                if ("increment".equals(name)) {
                    return ++numCalls;
                } else if ("mod".equals(name)) {
                    return (Long) parameters.get("key1") % Integer.valueOf((String) parameters.get("key2"));
                } else {
                    throw new IllegalArgumentException("Custom macro name: " + name + " is not supported.");
                }
            }
        }*/

       // private static class CustomTagCallback implements FunctionCallTagCallback {
            //@Override
            //public void execute(String tagName, Map<String, Object> parameters) {
                // The code for firing this custom tag.
             /*   Log.i("CuteAnimals", "Custom function call tag :" + tagName + " is fired.");
            }
        }*/
    }
}
