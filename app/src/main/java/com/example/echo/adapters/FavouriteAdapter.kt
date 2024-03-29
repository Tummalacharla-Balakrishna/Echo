package com.example.echo.adapters

import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.echo.R
import com.example.echo.Songs
import com.example.echo.fragments.SongPlayingFragment

//For saving favourite songs
class FavoriteAdapter(_songDetails: ArrayList<Songs>, _context: Context) : RecyclerView.Adapter<FavoriteAdapter.MyViewHolder>() {


    var songDetails: ArrayList<Songs>? = null
    var mContext: Context? = null

    init {
        this.songDetails = _songDetails
        this.mContext = _context
    }
    
    //To add Song to favourite screen
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val songObject = songDetails?.get(position)
        holder.trackTitle?.text = songObject?.songTitle
        holder.trackArtist?.text = songObject?.artist

        holder.contentHolder?.setOnClickListener({
            var songPlayingFragment= SongPlayingFragment()
            var args= Bundle()
            args.putString("songArtist",songObject?.artist)
            args.putString("path",songObject?.songData)
            args.putString("songTitle",songObject?.songTitle)
            args.putInt("songId",songObject?.songId?.toInt() as Int)
            args.putInt("songPosition",position)
            //To process data between activites
            args.putParcelableArrayList("songData",songDetails)
            songPlayingFragment.arguments=args
            SongPlayingFragment.Statified.mediaPlayer?.pause()
            (mContext as FragmentActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.details_fagment, songPlayingFragment)
                .addToBackStack("SongPlayingFragmentFavorite")
                .commit()
        })
    }

    //Creates new ViewHolder if not exits
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent?.context)
            .inflate(R.layout.row_custom_mainscreen_adapter, parent, false)
        return MyViewHolder(itemView)
    }
    override fun getItemCount(): Int {

        if (songDetails == null) {
            return 0
        }
        else {
            return (songDetails as ArrayList<Songs>).size
        }
    }
    //RecyclerView used to get dynamic data
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var trackTitle: TextView? = null
        var trackArtist: TextView? = null
        var contentHolder: RelativeLayout? = null

        init {
            trackTitle = view.findViewById(R.id.trackTitle) as TextView
            trackArtist = view.findViewById(R.id.trackArtist) as TextView
            contentHolder = view.findViewById(R.id.contentRow) as RelativeLayout
        }
    }
}
