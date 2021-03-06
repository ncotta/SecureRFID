package com.example.myapplication

import android.content.Intent
import android.nfc.NfcAdapter
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var nfcAdapter: NfcAdapter? = null
    private var textViewInfo: TextView? = null
    private var textViewTagInfo:TextView? = null
    private var textViewBlock:TextView? = null
    private var btn: Button? = null
    private var i = 0
    private var j = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        /*
        This function automatically executes on application opening
         */
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_main)

        textViewInfo = findViewById(R.id.info)
        textViewTagInfo = findViewById(R.id.taginfo)
        textViewBlock = findViewById(R.id. block)

        btn = findViewById(R.id.btnRFID)  // Main RFID Scanner Button
        btn!!.setOnClickListener(this)    // Listen for button to be pressed

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)  // obtain device's NFCAdapter
        if (nfcAdapter == null) {   // Device does not have NFCAdapter
            while (i < 4) {
                Toast.makeText(
                    this,
                    "NFC IS NOT SUPPORTED ON THIS DEVICE!",
                    Toast.LENGTH_LONG
                ).show()
                i++
            }
//            finish()
        } else if (!nfcAdapter!!.isEnabled) {  // Device has NFCAdapter but is turned off
            while (j < 4) {
                Toast.makeText(
                    this,
                    "NFC IS SUPPORTED BUT NOT ENABLED!",
                    Toast.LENGTH_LONG
                ).show()
                j++
            }
//            finish()
        } else {  // Good to go
            Toast.makeText(this,
                "NFC IS SUPPORTED, APP IS RUNNING!",
                Toast.LENGTH_LONG).show()
        }
    }

    override fun onClick(v: View?) {
        /*
        This function will wait for the button to be pressed
        TODO: Call function to mask RFID from card? Or we set to a switch...
         */
        btn?.text = "RFID Blocking (ON)"
        val intent = Intent(this, NFCActivity::class.java)
        // val intent = Intent(this, NFCActivityIntent::class.java)
        startActivity(intent)
    }
}