cordova-ringtone-plugin
=======================

cordova plugin to handle ringtoneManager

cordova plugin add https://github.com/wangda8791/ringtone.git

function ringtoneOn() {
  ringtone.on(function success(){}, function fail(){});
}

function ringtoneOff() {
  ringtone.off(function success(){}, function fail(){});
}