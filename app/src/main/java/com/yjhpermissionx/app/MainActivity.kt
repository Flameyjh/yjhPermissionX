package com.yjhpermissionx.app

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.yjhpermissionx.yjhlibrary.PermissionX
import kotlinx.android.synthetic.main.activity_main.*

//申请拨打电话权限并拨打电话

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        makeCallBtn.setOnClickListener{
            PermissionX.request(this,
            Manifest.permission.CALL_PHONE){ allGranted, deniedList ->
                if (allGranted){
                    Toast.makeText(this, "你接受了所有权限", Toast.LENGTH_SHORT).show()
                    call()
                }else{
                    Toast.makeText(this, "你拒绝了 $deniedList", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun call() {
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        } catch (e: SecurityException){
            e.printStackTrace()
        }
    }
}
