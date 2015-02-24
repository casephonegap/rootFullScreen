function rootFullScreen () {
	this.fsOnFlag = false;
};

/*
* hide android software buttons
*/
rootFullScreen.prototype.enable = function () 
{ if (this.fsOnFlag == false) 
  { this.fsOnFlag = true;
    cordova.exec(null, null, 'rootFullScreen', 'enable', []);
  }
};

/*
* show android software buttons
*/
rootFullScreen.prototype.disable = function () 
{ if (this.fsOnFlag == true) 
  { this.fsOnFlag = false;
    cordova.exec(null, null, 'rootFullScreen', 'disable', []);
  }
};

module.exports = new rootFullScreen();
