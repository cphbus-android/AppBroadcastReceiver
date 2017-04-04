package app

import android.app.Activity
import android.content.*
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class ActivityMain : Activity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val BRB = BroadcastReceiverBasic()

        App.instance.registerReceiver(BR, IntentFilter("CUSTOM_INTENT"))
        App.instance.registerReceiver(BR, IntentFilter(Intent.ACTION_INPUT_METHOD_CHANGED))

        val Intent1 = Intent()
        Intent1.action = "CUSTOM_INTENT"
        Intent1.putExtra(Intent.EXTRA_TEXT, "This is some text to send")
        App.instance.sendBroadcast(Intent1)

        val Intent2 = Intent()
        Intent2.type = "text/plain"
        Intent2.action = Intent.ACTION_SEND
        Intent2.putExtra(Intent.EXTRA_EMAIL, "emailaddress@emailaddress.com")
        Intent2.putExtra(Intent.EXTRA_SUBJECT, "Subject")
        Intent2.putExtra(Intent.EXTRA_TEXT, "Content")
        App.instance.startActivity(Intent2)
    }

    val BR: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            content.append("Received a broadCast: " + intent.action + "\n")

            if (intent.getAction().equals("CUSTOM_INTENT")) {
                content.append("BroadcastReceived: CUSTOM_INTENT\n")
            }
            if (intent.getAction().equals("android.intent.action.INPUT_METHOD_CHANGED")) {
                content.append("BroadcastReceived: INPUT_METHOD_CHANGED\n")
            }

            val extras = intent.extras

            if (extras != null) {
                content.append(extras.toString() + "\n")

                for (key in extras.keySet()) {
                    val value = extras.get(key)
                    content.append("Extra: " + key.toString() + " - " + value.toString() + "\n")
                }
            }
        }
    }
}