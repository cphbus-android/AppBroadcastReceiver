package app

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class BroadcastReceiverBasic : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("DebugReceiver", "Received a broadCast: " + intent.action)

        if (intent.action.equals("CUSTOM_INTENT")) {
            Log.d("DebugReceiver", "BroadcastReceived: CUSTOM_INTENT\n")
        }
        if (intent.action.equals("android.intent.action.INPUT_METHOD_CHANGED")) {
            Log.d("DebugReceiver", "BroadcastReceived: INPUT_METHOD_CHANGED\n")
        }

        val extras = intent.extras

        if (extras != null) {
            Log.d("DebugReceiver", extras.toString())

            for (key in extras.keySet()) {
                val value = extras.get(key)
                Log.d("DebugReceiver", "Extra: " + key.toString() + " - " + value.toString())
            }
        }
    }
}