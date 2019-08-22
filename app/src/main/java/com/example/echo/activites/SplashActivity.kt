package com.example.echo.activites

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.widget.Toast
import com.example.echo.R




class SplashActivity : AppCompatActivity() {
    var permissionsString=arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.MODIFY_AUDIO_SETTINGS,Manifest.permission.READ_PHONE_STATE,Manifest.permission.PROCESS_OUTGOING_CALLS,Manifest.permission.RECORD_AUDIO)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //initial activity set as Splash activity
        setContentView(R.layout.activity_splash)

        //Checking All the Permissions are Granted or Not
        if(!hasPermission( this@SplashActivity, *permissionsString)) {

            ActivityCompat.requestPermissions(this@SplashActivity,permissionsString,131)
        }
        else
        {
            //stopping splash activity and shifting view to MainActivity
            Handler().postDelayed({
                val startact= Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(startact)
                this.finish()
            },1000)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            131->{if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED && grantResults[2]==PackageManager.PERMISSION_GRANTED && grantResults[3]==PackageManager.PERMISSION_GRANTED && grantResults[4]==PackageManager.PERMISSION_GRANTED)
            {
                Handler().postDelayed({
                    val startact= Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(startact)
                    this.finish()
                },1000)
            }
            else
            {
                //when permission has not set display this message
                Toast.makeText(this@SplashActivity,"Please grant all permissions",Toast.LENGTH_SHORT).show()
                this.finish()
            }
                return
            }
            else->{
                Toast.makeText(this@SplashActivity,"Something went wrong!",Toast.LENGTH_SHORT).show()
                this.finish()
                return
            }}
    }
    //Checking all the permission and return bool value
    fun hasPermission(context: Context, vararg permissions: String): Boolean{
        var hasAllPermissions=true;
        for(permission in permissions)
        {
            val res=context.checkCallingOrSelfPermission(permission)
            if(res!=PackageManager.PERMISSION_GRANTED)
            {
                hasAllPermissions=false
            }
        }
        return hasAllPermissions
    }
}
