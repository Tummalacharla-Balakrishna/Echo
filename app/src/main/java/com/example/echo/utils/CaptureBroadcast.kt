package com.example.echo.utils

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import com.example.echo.R
import com.example.echo.activites.MainActivity
import com.example.echo.fragments.SongPlayingFragment

class CaptureBroadcast: BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?){
        if(intent?.action==Intent.ACTION_NEW_OUTGOING_CALL)
        {
            try{
                if(SongPlayingFragment.Statified.mediaPlayer?.isPlaying as Boolean)
                {
                    MainActivity.Statified.notificationManager?.cancel(1978)
                }
            }catch(e:Exception){
                e.printStackTrace()
            }
            try{
                if(SongPlayingFragment.Statified.mediaPlayer?.isPlaying as Boolean)
                {
                    SongPlayingFragment.Statified.mediaPlayer?.pause()
                    SongPlayingFragment.Statified.playPauseImageButton?.setBackgroundResource(R.drawable.play_icon)
                }}
            catch(e:Exception){
                e.printStackTrace()
            }
        }else{
            val tm :TelephonyManager=context?.getSystemService(Service.TELEPHONY_SERVICE) as TelephonyManager
            when(tm.callState){
                TelephonyManager.CALL_STATE_RINGING->{
                    try{
                        if(SongPlayingFragment.Statified.mediaPlayer?.isPlaying as Boolean)
                        {
                            MainActivity.Statified.notificationManager?.cancel(1978)
                        }
                    }catch(e:Exception){
                        e.printStackTrace()
                    }

                    try{
                        if(SongPlayingFragment.Statified.mediaPlayer?.isPlaying as Boolean)
                        {
                            SongPlayingFragment.Statified.mediaPlayer?.pause()
                            SongPlayingFragment.Statified.playPauseImageButton?.setBackgroundResource(R.drawable.play_icon)
                        }}
                    catch(e:Exception){
                        e.printStackTrace()
                    }

                }
                else->{

                }
            }
        }
        /*if(intent?.action==Intent.ACTION_NEW_OUTGOING_CALL)
        {
            try{
                if(SongPlayingFragment.Statified.mediaPlayer?.isPlaying as Boolean)
                {
                    MainActivity.Statified.notificationManager?.cancel(1978)
                }
        }catch(e:Exception){
            e.printStackTrace()
        }
            try{
                if(SongPlayingFragment.Statified.mediaPlayer?.isPlaying as Boolean)
                {
                    SongPlayingFragment.Statified.mediaPlayer?.pause()
                    SongPlayingFragment.Statified.playPauseImageButton?.setBackgroundResource(R.drawable.play_icon)
                }}
            catch(e:Exception){
                e.printStackTrace()
            }
        }else{
            val tm :TelephonyManager=context?.getSystemService(Service.TELEPHONY_SERVICE) as TelephonyManager
            when(tm.callState){
                TelephonyManager.CALL_STATE_RINGING->{
                    try{
                        if(SongPlayingFragment.Statified.mediaPlayer?.isPlaying as Boolean)
                        {
                            MainActivity.Statified.notificationManager?.cancel(1978)
                        }
                    }catch(e:Exception){
                        e.printStackTrace()
                    }

                    try{
                        if(SongPlayingFragment.Statified.mediaPlayer?.isPlaying as Boolean)
                        {
                            SongPlayingFragment.Statified.mediaPlayer?.pause()
                            SongPlayingFragment.Statified.playPauseImageButton?.setBackgroundResource(R.drawable.play_icon)
                        }}
                    catch(e:Exception){
                        e.printStackTrace()
                    }

                }
                else->{

                }
            }
        }*/
    }

}