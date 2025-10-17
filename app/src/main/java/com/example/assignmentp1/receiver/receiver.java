package com.example.assignmentp1.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.example.assignmentp1.MainActivity;

public class receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();
        if(intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)){
            if(bundle != null){
                Object[] pdusObj = (Object[]) bundle.get("pdus");
                String format = bundle.getString("format").toString();
                String message = "";
                for (Object o : pdusObj) {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) o, format);
                    String sender = currentMessage.getDisplayOriginatingAddress();
                    message = currentMessage.getDisplayMessageBody();
                    String printMessage = "Sender: " + sender + " Message: " + message;
                }
                Intent launchIntent = new Intent(context, MainActivity.class);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                String prefix = "Ticker:<<";
                String suffix = ">>";

                if(message.startsWith(prefix) && message.endsWith(suffix)){
                    String ticker = message.substring(prefix.length(), message.length() - suffix.length());
                    if(ticker.matches("[a-zA-Z]+")){
                        launchIntent.putExtra("TICKER", ticker.toUpperCase());
                    } else {
                        launchIntent.putExtra("INVALID_TICKER", true);
                    }
                } else {
                    launchIntent.putExtra("INVALID_FORMAT", true);
                }
                context.startActivity(launchIntent);
            }
        }
    }
}