package com.alinz.parkerdan.shareextension;

import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import android.graphics.Bitmap;
import android.util.Log;

import java.io.InputStream;
import java.util.ArrayList;


public class ShareModule extends ReactContextBaseJavaModule {


  public ShareModule(ReactApplicationContext reactContext) {
      super(reactContext);
  }

  @Override
  public String getName() {
      return "ReactNativeShareExtension";
  }

  @ReactMethod
  public void close() {
    try {
      getCurrentActivity().finish();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @ReactMethod
  public void data(Promise promise) {
      promise.resolve(processIntent());
  }

  public WritableMap processIntent() {
      WritableMap map = Arguments.createMap();

      String value = "";
      String type = "";
      String action = "";

      Activity currentActivity = getCurrentActivity();

      if (currentActivity != null) {

        Intent intent = currentActivity.getIntent();
        action = intent.getAction();
        type = intent.getType();

        if (type == null) {
          type = "";
        }

        if (Intent.ACTION_SEND.equals(action) && "text/plain".equals(type)) {
          value = intent.getStringExtra(Intent.EXTRA_TEXT);

        } else if (Intent.ACTION_SEND.equals(action) && ("image/*".equals(type) || "image/gif".equals(type) || "image/jpeg".equals(type) || "image/png".equals(type) || "image/jpg".equals(type) ) ) {
          Uri uri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
          value = "file://" + RealPathUtil.getRealPathFromURI(currentActivity, uri);
       } else if (Intent.ACTION_SEND_MULTIPLE.equals(action) && type != null) {

            if (type.startsWith("image/")) {
                ArrayList<Uri> imageUris = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
                ArrayList<String> images = new ArrayList<String>();

                if (imageUris != null) {
                    for(int i = 0; i < (imageUris.size() > 5 ? 5 : imageUris.size()) ; i++){
                     //   Log.d("IMAGE - " , "file://" + RealPathUtil.getRealPathFromURI(currentActivity, imageUris.get(i)));
                        images.add("file://" + RealPathUtil.getRealPathFromURI(currentActivity, imageUris.get(i)));
                    }
                   // Log.d("ARRALIST - " , images.toString());
                    value = images.toString();
                }
            }

        } else {
         value = "";
       }

      } else {
        value = "";
        type = "";
      }

      map.putString("type", type);
      map.putString("value",value);

      return map;
  }
}
