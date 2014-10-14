package com.judax.cordova.plugin.firephoneheadtracking;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONException;
import org.json.JSONObject;

import com.amazon.headtracking.HeadTrackingEvent;
import com.amazon.headtracking.HeadTrackingListener;
import com.amazon.headtracking.HeadTrackingManager;

public class CordovaPluginFirePhoneHeadTracking extends CordovaPlugin implements HeadTrackingListener
{
	private HeadTrackingManager headTrackingManager = null;
	private CallbackContext amazonHeadTrackingCallbackContext = null;
	private JSONObject eventArgument = null;
	
	@Override
	public void initialize(final CordovaInterface cordova, CordovaWebView webView)
	{
		super.initialize(cordova, webView);
		
		if (headTrackingManager == null)
		{
			headTrackingManager = HeadTrackingManager.createInstance(cordova.getActivity());
			headTrackingManager.registerListener(this);
		}
	}
	
	@Override
	public void onDestroy()
	{
		if (headTrackingManager != null) 
		{
			headTrackingManager.unregisterListener(this);
			headTrackingManager = null;
		}
	}

	@Override
	public boolean execute(String action, String rawArgs,
			CallbackContext callbackContext) throws JSONException
	{
		boolean valid = false;
		if (action.equals("setAmazonHeadTrackingCallbackContext"))
		{
			amazonHeadTrackingCallbackContext = callbackContext;
		}
		return valid;
	}

	
	@Override
	public void onHeadTrackingEvent(HeadTrackingEvent headTrackingEvent)
	{
		try
		{
			eventArgument.put("x_mm", Double.valueOf(headTrackingEvent.x_mm));
			eventArgument.put("y_mm", Double.valueOf(headTrackingEvent.y_mm));
			eventArgument.put("z_mm", Double.valueOf(headTrackingEvent.z_mm));
			eventArgument.put("headInclinationAngle_deg", Double.valueOf(headTrackingEvent.headInclinationAngle_deg));
			eventArgument.put("timestamp", Double.valueOf(headTrackingEvent.timestamp_nsecs / 1000000.0));
			eventArgument.put("timestamp_nsecs", Long.valueOf(headTrackingEvent.timestamp_nsecs));
			eventArgument.put("isFaceDetected", Boolean.valueOf(headTrackingEvent.isFaceDetected));
			eventArgument.put("isTracking", Boolean.valueOf(headTrackingEvent.isTracking));
			amazonHeadTrackingCallbackContext.success(eventArgument);
		}
		catch(JSONException e) 
		{
			amazonHeadTrackingCallbackContext.error(e.toString());
		}
	}


}
