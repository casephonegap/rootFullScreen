package at.casephonegap.rootfullscreen;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;

import android.view.WindowManager.LayoutParams;


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.util.Log;



public class rootFullScreen extends CordovaPlugin 
{ @Override
  public boolean execute(String action, JSONArray args, CallbackContext callback) throws JSONException 
  { // grab the correct methods
    if (action.equalsIgnoreCase("enable")) 
    { try 
      { //REQUIRES ROOT
        Build.VERSION_CODES vc = new Build.VERSION_CODES();
        Build.VERSION vr = new Build.VERSION();
        String ProcID = "79"; //HONEYCOMB AND OLDER
        //v.RELEASE  //4.0.3
        if (VERSION.SDK_INT >= VERSION_CODES.ICE_CREAM_SANDWICH) { ProcID = "42"; /*ICS AND NEWER*/ }
        Log.d("Fullscreen Plugin", "try hiding the system bar");
        //REQUIRES ROOT
        Process proc = Runtime.getRuntime().exec(new String[]{"su","-c","service call activity "+ ProcID +" s16 com.android.systemui"}); //WAS 79
        proc.waitFor();
        Log.d("Fullscreen Plugin", "systembar successfully hidden");
        return true;
      }
      catch(Exception ex) { Log.d("Fullscreen Plugin", ex.getMessage()); }
    } 
    else if (action.equalsIgnoreCase("disable")) 
    { try
      { Log.d("Fullscreen Plugin", "try showing the system bar");
        //Process proc = Runtime.getRuntime().exec(new String[]{"am","startservice","-n","com.android.systemui/.SystemUIService"});
        //proc.waitFor();
        String command;
        command = "LD_LIBRARY_PATH=/vendor/lib:/system/lib am startservice -n com.android.systemui/.SystemUIService";
        Process proc = Runtime.getRuntime().exec(new String[] { "su", "-c", command });
        proc.waitFor();
        Log.d("Fullscreen Plugin", "systembar successfully showing");
        return true;
      }
      catch(Exception ex) { Log.d("Fullscreen Plugin", ex.getMessage()); }
    }
    else 
    { callback.error("Unknown Action: " + action);
      //return false;
    }
  return false;
  }
}
