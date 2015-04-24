package com.indigoway.phonegap.plugin;

import android.util.Log;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import inputservice.NfePrinter.BoletoPrinter;
import inputservice.NfePrinter.BoletoUtils;
import inputservice.NfePrinter.NfePrinterA7;
import inputservice.NfePrinter.ReceiptPrinterA7;
import java.util.ArrayList;
import android.os.Handler;
import java.io.UnsupportedEncodingException;
import android.widget.Toast;

public class A7printer extends CordovaPlugin {

    /* Adicionado  */
    public static NfePrinterA7 nfeprinter;
    public static BoletoPrinter boletoprinter;
    public static ReceiptPrinterA7 receiptprinter;
    private static BoletoUtils boleto;
    private static boolean connected = false;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        //nfeprinter = new NfePrinterA7();
        //boletoprinter = new BoletoPrinter();
        //receiptprinter = new ReceiptPrinterA7();
    }

    @Override
    public boolean execute(String action, JSONArray data, final CallbackContext callbackContext) throws JSONException {
        System.out.println("*****JARRAY data*****" + data.length());

        JSONObject json_data = null;

        for(int i=0; i<data.length(); i++){
            json_data = data.getJSONObject(i);

//            Log.i(  "log_tag", "_id" + json_data.getString("account") +
//                    ", mall_name" + json_data.getInt("_id") +
//                    ", location" + json_data.getString("number") +
//                    ", telephone" + json_data.getString("url") +
//                    ",----" + json_data.getString("balance") +
//                    ",----" + json_data.getString("credit") +
//                    ",----" + json_data.getString("displayName")
//            );
        }


        if (action.equals("boleto")) {
            final JSONObject boletoParams = json_data;
            cordova.getThreadPool().execute(new Runnable() {
                JSONObject p = boletoParams;
                public void run() {
                    genBoletoByClassA7(p);
                    callbackContext.success("Deu certo!"); // Thread-safe.
                }
            });
            return true;
        } else {
            return false;
        }
    }

    public void genBoletoByClassA7(JSONObject boletoData ){

        System.out.println("*****JARRAY data*****" + boletoData.toString());

        boletoprinter = new BoletoPrinter();

        connected = boletoprinter.connect(false);
        if(connected){
            boleto = new BoletoUtils();

            if (boletoData.has("linhaDigitavel"))
                boleto.setLinhaDigitavel(boletoData.optString("linhaDigitavel") );
            if (boletoData.has("nomeBanco"))
                boleto.setNomeBanco(boletoData.optString("nomeBanco"));
            if (boletoData.has("codBanco"))
                boleto.setCodBanco(boletoData.optString("codBanco"));
            if (boletoData.has("localPagamento"))
                boleto.setLocalPagamento(boletoData.optString("localPagamento"));
            if (boletoData.has("localOpcionalPagamento"))
                boleto.setLocalOpcionalPagamento(boletoData.optString("localOpcionalPagamento"));
            if (boletoData.has("vencimento"))
                boleto.setVencimento(boletoData.optString("vencimento"));
            if (boletoData.has("cedente"))
                boleto.setCedente(boletoData.optString("cedente"));
            if (boletoData.has("agenciaCodigoCedente"))
                boleto.setAgenciaCodigoCedente(boletoData.optString("agenciaCodigoCedente"));
            if (boletoData.has("datadocumento"))
                boleto.setDatadocumento(boletoData.optString("datadocumento"));
            if (boletoData.has("numeroDocumento"))
                boleto.setNumeroDocumento(boletoData.optString("numeroDocumento"));
            if (boletoData.has("especieDoc"))
                boleto.setEspecieDoc(boletoData.optString("especieDoc"));
            if (boletoData.has("aceite"))
                boleto.setAceite(boletoData.optString("aceite"));
            if (boletoData.has("dataProcessameto"))
                boleto.setDataProcessameto(boletoData.optString("dataProcessameto"));
            if (boletoData.has("nossoNumero"))
                boleto.setNossoNumero(boletoData.optString("nossoNumero"));
            if (boletoData.has("usoDoBanco"))
                boleto.setUsoDoBanco(boletoData.optString("usoDoBanco"));
            if (boletoData.has("cip"))
                boleto.setCip(boletoData.optString("cip"));
            if (boletoData.has("carteira"))
                boleto.setCarteira(boletoData.optString("carteira"));
            if (boletoData.has("especieMoeda"))
                boleto.setEspecieMoeda(boletoData.optString("especieMoeda"));
            if (boletoData.has("quantidade"))
                boleto.setQuantidade(boletoData.optString("quantidade"));
            if (boletoData.has("valor"))
                boleto.setValor(boletoData.optString("valor"));
            if (boletoData.has("valorDocumento"))
                boleto.setValorDocumento(boletoData.optString("valorDocumento"));
            if (boletoData.has("instrucoesCedente"))
                try {
                    JSONArray values = boletoData.getJSONArray("instrucoesCedente");
                    String[] instrucoesCedenteArray = new String[7];

                    for (int i = 0; i < instrucoesCedenteArray.length ; i++)
                        instrucoesCedenteArray[i] = values.optString(i,"");

                    boleto.setInstrucoesCedente(instrucoesCedenteArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            if (boletoData.has("desconto"))
                boleto.setDesconto(boletoData.optString("desconto"));
            if (boletoData.has("deducoes"))
                boleto.setDeducoes(boletoData.optString("deducoes"));
            if (boletoData.has("multa"))
                boleto.setMulta(boletoData.optString("multa"));
            if (boletoData.has("acrescimos"))
                boleto.setAcrescimos(boletoData.optString("acrescimos"));
            if (boletoData.has("valorCobrado"))
                boleto.setValorCobrado(boletoData.optString("valorCobrado"));
            if (boletoData.has("sacadoNome"))
                boleto.setSacadoNome(boletoData.optString("sacadoNome"));
            if (boletoData.has("sacadoEndereco"))
                boleto.setSacadoEndereco(boletoData.optString("sacadoEndereco"));
            if (boletoData.has("sacadoCep"))
                boleto.setSacadoCep(boletoData.optString("sacadoCep"));
            if (boletoData.has("sacadoCidade"))
                boleto.setSacadoCidade(boletoData.optString("sacadoCidade"));
            if (boletoData.has("sacadoUF"))
                boleto.setSacadoUF(boletoData.optString("sacadoUF"));
            if (boletoData.has("sacadoCnpj"))
                boleto.setSacadoCnpj(boletoData.optString("sacadoCnpj"));

            boletoprinter.getMobilePrinter().Reset();
            boletoprinter.printBoleto(boleto);
        }else{
            //Toast.makeText(this, "Não foi possível realizar a conexão  com a impressora!", Toast.LENGTH_LONG).show();
        }
    }

}