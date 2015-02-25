function rootFullScreen() { this.fsOnFlag = false; };

/* hide android software buttons */
rootFullScreen.prototype.enable = function () 
{ console.log('rootFullScreen.enable aufgerufen');
  if (this.fsOnFlag == false) 
  { this.fsOnFlag = true;
    console.log('enable cordova aufruf absetzen');
    cordova.exec(null, null, 'rootFullScreen', 'enable', []);
  }
};

/* show android software buttons */
rootFullScreen.prototype.disable = function () 
{ console.log('rootFullScreen.disable aufgerufen');
  if (this.fsOnFlag == true) 
  { this.fsOnFlag = false;
    console.log('disable cordova aufruf absetzen');
    cordova.exec(null, null, 'rootFullScreen', 'disable', []);
  }
};

module.exports = new rootFullScreen();
