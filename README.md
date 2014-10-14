cordova-plugin-firephoneheadtracking
====================================

A Cordova plugin to handle Amazon's FirePhone Head Tracking using their exact same WebApp JavaScript API. You can use this plugin if you want to generate a standard Amazon Android App and still uses the Head Tracking native API from JavaScript. As The API is the same as the one Amazon exposes in their WebApp environment, your App/Code will be 100% compatible if you want to develop an Amazon WebApp too.

##How to use it

###Setup your Cordova project and install the plugin

This plugin has been published in the Cordova Plugin Registry (Plugman) so adding it to any Cordova project version 3.0 and above can be done using the Cordova Command Line Interface (CLI).

1. Install the Cordova CLI: https://cordova.apache.org/docs/en/3.0.0/guide_cli_index.md.html
2. Create a Cordova project and add the desired platforms (remember that this plugins works for iOS and Android for now only): https://cordova.apache.org/docs/en/3.0.0/guide_cli_index.md.html
3. Install the plugin with the following command line:

	`cordova plugin add com.judax.cordova.plugin.firephoneheadtracking`

4.- The API that is exposed is exactly the same as the JavaScrip API Amazon has released for their Amazon Web Apps. You can follow a complete tutorial at the following link: https://developer.amazon.com/public/community/post/TxX1C30LEEOJP2/Adding-Dynamic-Perspective-to-Your-HTML5-Games-Tutorial

NOTE: I haven't been able to find very good online API documentation about the Amazon HTML5 Head Tracking API, although if you download the full FirePhone SDK, it provides a great guide on how to use it in PDF format that I have included inside the doc folder in this repository.

###Modifications in your application

Not many modifications are needed in order to be able use the the plugin. Indeed only the inclusion of the "FirePhoneHeadTracking.js" file is required (and even this requirement might be removed in the future by using Cordova plugin modules). 

	...
	<head>
		<script src="FirePhoneHeadTracking.js"></script>
	...

IMPORTANT: The whole API (the registering head tracking event listeners) won't be available until the Cordova `deviceready` event is fired, so make sure that your application does not try to call or use it before that event.

##Future improvements

* Remove the requirement to add the FirePhoneHeadTracking.js file by using Cordova modules.

##Additional references

* A sweet tutorial on how to include the head tracking HTML5 API inside your games by Jesse Freeman: https://developer.amazon.com/public/community/post/TxX1C30LEEOJP2/Adding-Dynamic-Perspective-to-Your-HTML5-Games-Tutorial

* More information about the FirePhone and the Head Tracking or Dynamic Perspective feature: https://developer.amazon.com/public/solutions/devices/fire-phone#DP

