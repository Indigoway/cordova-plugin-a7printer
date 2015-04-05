# [Work In Progress]
## Cordova a7printer Plugin 

Bluetooth communication with the a7printer

## Using
Clone the plugin

    $ git clone https://github.com/indigoway/cordova-plugin-a7printer.git

Create a new Cordova Project

    $ cordova create a7printer com.example.a7printerapp A7printer
    
Install the plugin

    $ cd a7printer
    $ cordova plugin install ../cordova-plugin-a7printer
    

Edit `www/js/index.js` and add the following code inside `onDeviceReady`

```js
    var success = function(message) {
        alert(message);
    }

    var failure = function() {
        alert("Error calling A7printer Plugin");
    }

    a7printer.boleto("World", success, failure);
```

Install iOS or Android platform

    cordova platform add ios
    cordova platform add android
    
Run the code

    cordova run 

## More Info

For more information on setting up Cordova see [the documentation](http://cordova.apache.org/docs/en/4.0.0/guide_cli_index.md.html#The%20Command-Line%20Interface)

For more info on plugins see the [Plugin Development Guide](http://cordova.apache.org/docs/en/4.0.0/guide_hybrid_plugins_index.md.html#Plugin%20Development%20Guide)
