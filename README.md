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
    
If for only add to the project 

    $ cordova plugin add ../cordova-plugin-a7printer --save
    
    
Edit `www/js/index.js` and add the following code inside `onDeviceReady`

```js
    var success = function(message) {
        alert(message);
    }

    var failure = function() {
        alert("Error calling A7printer Plugin");
    }

   var obj = {
            linhaDigitavel:"23792.38401 61130.370598 68001.271805 1 56220000320000",
            nomeBanco:"Bradesco",
            codBanco:"756-0",
            localPagamento:"Banco Bradesco S.A.",
            localOpcionalPagamento:"Pagavel preferencialmente na rede bradesco ou banco postal.",
            vencimento:"27/02/2013",
            cedente:"ARUS Informatica Ltda",
            agenciaCodigoCedente:"12346 / 0003235",
            datadocumento:"06/02/2013",
            numeroDocumento:"284 / 02",
            especieDoc:"OU",
            aceite:"Nao",
            dataProcessameto:"06/02/2013",
            nossoNumero:"1-3",
            usoDoBanco:"08650",
            cip:"000",
            carteira:"1",
            especieMoeda:"R$",
            quantidade:"",
            valor:"5,00",
            valorDocumento:"5,00",
            instrucoesCedente:[
                "instrucoesCedente",
                "instrucoesCedente2",
                "instrucoesCedente1"
            ],
            desconto:"",
            deducoes:"",
            multa:"",
            acrescimos:"0",
            valorCobrado:"5",
            sacadoNome:"AllanWeb T.I",
            sacadoEndereco:"teste Endereço",
            sacadoCep:"87200-000",
            sacadoCidade:"Cianorte",
            sacadoUF:"PR",
            sacadoCnpj:"10.555.666/0001-50"
        }



        a7printer.boleto(obj, success, failure);
```

Install iOS or Android platform

    cordova platform add ios
    cordova platform add android
    
Run the code

    cordova run 

## More Info

For more information on setting up Cordova see [the documentation](http://cordova.apache.org/docs/en/4.0.0/guide_cli_index.md.html#The%20Command-Line%20Interface)

For more info on plugins see the [Plugin Development Guide](http://cordova.apache.org/docs/en/4.0.0/guide_hybrid_plugins_index.md.html#Plugin%20Development%20Guide)
