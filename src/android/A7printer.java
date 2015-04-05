package com.indigoway.phonegap.plugin;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

import inputservice.NfePrinter.BoletoPrinter;
import inputservice.NfePrinter.BoletoUtils;
import inputservice.NfePrinter.NfePrinterA7;
import inputservice.NfePrinter.ReceiptPrinterA7;
import android.os.Handler;
import java.io.UnsupportedEncodingException;
import android.widget.Toast;

public class A7printer extends CordovaPlugin {

    /* Adicionado  */
    public static NfePrinterA7 nfeprinter;
    public static BoletoPrinter boletoprinter;
    public static ReceiptPrinterA7 receiptprinter;
    private static BoletoUtils boleto;
    private static int btntype = 0;
    private static boolean connected = false;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        nfeprinter = new NfePrinterA7();
        boletoprinter = new BoletoPrinter();
        receiptprinter = new ReceiptPrinterA7();
    }

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        if (action.equals("boleto")) {

            cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    genBoletoByClassA7();
                    String name = data.getString(0);
                    String message = "Hello, " + name;
                    callbackContext.success(message); // Thread-safe.
                }
            });

            return true;

        } else {
            return false;
        }
    }

    public void genBoletoByClassA7(){
        connected = boletoprinter.connect(false);
        if(connected){
            boleto = new BoletoUtils();
            boleto.setLinhaDigitavel("23792.38401 61130.370598 68001.271805 1 56220000320000");
            boleto.setNomeBanco("Bradesco");
            boleto.setCodBanco("237-2");
            boleto.setLocalPagamento("Banco Bradesco S.A.");
            boleto.setLocalOpcionalPagamento("Pagavel preferencialmente na rede bradesco ou banco postal.");
            boleto.setVencimento("27/02/2013");
            boleto.setCedente("Input Service Informatica Ltda");
            boleto.setAgenciaCodigoCedente("02384-1 / 0012718-3");
            boleto.setDatadocumento("06/02/2013");
            boleto.setNumeroDocumento("00000TESTE");
            boleto.setEspecieDoc("OU");
            boleto.setAceite("Nao");
            boleto.setDataProcessameto("06/02/2013");
            boleto.setNossoNumero("06/11/303705968-5");
            boleto.setUsoDoBanco("08650");
            boleto.setCip("000");
            boleto.setCarteira("06");
            boleto.setEspecieMoeda("R$");
            boleto.setQuantidade("");
            boleto.setValor("");
            boleto.setValorDocumento("3.200,00");
            boleto.setInstrucoesCedente(new String[]{"Instrucoes de responsabilidade do cedente    *** Valores expressos em R$ ***","","","","","",""});
            boleto.setDesconto(" -200,00");
            boleto.setDeducoes("  -10,00");
            boleto.setMulta("   50,00");
            boleto.setAcrescimos("   10,00");
            boleto.setValorCobrado("3.050,00");
            boleto.setSacadoNome("INPUT SERVICE INFORMATCA LTDA");
            boleto.setSacadoEndereco("RUA DEPUTADO MIGUEL PETRELLI, 355");
            boleto.setSacadoCep("06705-442");
            boleto.setSacadoCidade("COTIA");
            boleto.setSacadoUF("SP");
            boleto.setSacadoCnpj("061.557.856/0001-57");

            boletoprinter.getMobilePrinter().Reset();
            boletoprinter.printBoleto(boleto);

            btntype = 1;
        }else{
            //Toast.makeText(this, "Não foi possível realizar a conexão  com a impressora!", Toast.LENGTH_LONG).show();
        }
    }


    // manages Threads messages of Receipt
    static private Handler threadHandlerReceipt = new Handler() {
        public void handleMessage(android.os.Message msg) {
            // handling messages and acting for the Thread goes here
            if(msg.what==1){
               //
            }else{
                while (receiptprinter.getMobilePrinter().QueryPrinterStatus()!= 0) {
                }
                receiptprinter.disconnect();
            }
        }
    };
}
