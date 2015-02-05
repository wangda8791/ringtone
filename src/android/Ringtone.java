package org.scn.cordova.ringtone;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;

/**
 * This class echoes a string called from JavaScript.
 */
public class Ringtone extends CordovaPlugin {

	private AudioManager audioManager = null;
	private MediaPlayer thePlayer = null;
	
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("on")) {
            this.on(callbackContext);
            return true;
        } else if (action.equals("off")) {
            this.off(callbackContext);
            return true;
        }
        return false;
    }

    private void on(CallbackContext callbackContext) {

    	audioManager = (AudioManager) cordova.getActivity().getSystemService(Context.AUDIO_SERVICE);
    	thePlayer = MediaPlayer.create(cordova.getActivity().getApplicationContext(), RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));
    	
    	if (thePlayer == null) return;

    	try {
    		thePlayer.prepare();
    	} catch (Exception e) {
    	    e.printStackTrace();
    	}

    	try {
    		thePlayer.setVolume(Float.parseFloat(Double.toString(audioManager.getStreamVolume(AudioManager.STREAM_RING) / 7.0)),
    				Float.parseFloat(Double.toString(audioManager.getStreamVolume(AudioManager.STREAM_RING) / 7.0)));
    	} catch (Exception e) {
    	    e.printStackTrace();
    	}

    	thePlayer.start();
    }

    private void off(CallbackContext callbackContext) {
    	
    	if (audioManager == null || thePlayer == null) return;
    	thePlayer.stop();
    }
}