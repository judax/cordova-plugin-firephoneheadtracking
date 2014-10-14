(function() {
    function removeCallback(container, callback) {
        for (var i = 0; i < container.length; i++) {
            if (container[i] === callback) {
                container.splice(i, 1);
                i--;
            }
        }
    }
    function setupPlugin() {
        if (!cordova.firePhoneHeadTracking) {
            cordova.firePhoneHeadTracking = {
                listeners : [],
                oldWindowAddEventListener : null,
                oldWindowRemoveEventListener : null
            };

            cordova.exec(function success() {
                var i = 0; 
                for (i  = 0; i < cordova.firePhoneHeadTracking.listeners.length; i++) {
                    cordova.firePhoneHeadTracking.listeners[i].apply(this, Array.prototype.slice.call(arguments));
                }
            }, function error(errorMessage) {
                console.error(errorMessage);
            }, "FirePhoneHeadTracking", "setAmazonHeadTrackingCallbackContext", []);

            cordova.firePhoneHeadTracking.oldWindowAddEventListener = cordova.firePhoneHeadTracking.oldWindowAddEventListener || window.addEventListener;
            window.addEventListener = function(eventName, callback) {
                    if (eventName === 'amazonheadtracking') {
                        cordova.firePhoneHeadTracking.listeners.push(callback);
                    }
                    else {
                        return cordova.firePhoneHeadTracking.oldWindowAddEventListener.apply(this, Array.prototype.slice.call(arguments));
                    }
            };

            cordova.firePhoneHeadTracking.oldWindowRemoveEventListener = cordova.firePhoneHeadTracking.oldWindowRemoveEventListener || window.removeEventListener;
            window.removeEventListener = function(eventName, callback) {
                    if (eventName === 'amazonheadtracking') {
                        removeCallback(cordova.firePhoneHeadTracking.listeners, callback);
                    }
                    else {
                        return cordova.firePhoneHeadTracking.oldWindowRemoveEventListener.apply(this, Array.prototype.slice.call(arguments));
                    }
            };
        }
    }
    // Wait for Cordova to be ready in order to start the plugin
    document.addEventListener("deviceready", setupGamepadPlugin);
})();


