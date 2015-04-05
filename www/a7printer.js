/*global cordova, module*/

module.exports = {
    boleto: function (name, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "A7printer", "boleto", [name]);
    }
};
