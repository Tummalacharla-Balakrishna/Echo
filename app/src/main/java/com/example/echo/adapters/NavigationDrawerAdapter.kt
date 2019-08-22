package com.example.echo.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.echo.R
import com.example.echo.activites.MainActivity
import com.example.echo.fragments.*


class NavigationDrawerAdapter(_contentList: ArrayList<String>,_getImages: IntArray,_context: Context):RecyclerView.Adapter<NavigationDrawerAdapter.NavViewHolder>(){
    var contentList:ArrayList<String>?=null
    var getImages: IntArray?=null
    var mcontext:Context?=null
    init {
        this.contentList=_contentList
        this.getImages=_getImages
        this.mcontext=_context
    }

    //The onBindViewHolder() method is used to display the data at the specified position.
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): NavViewHolder {
        var itemview=LayoutInflater.from(parent?.context)
            .inflate(R.layout.row_custom_navigationdrawer,parent,false)
        val returnthis=NavViewHolder(itemview)
        return returnthis
    }

    override fun getItemCount(): Int {

        return (contentList as ArrayList).size
    }

    override fun onBindViewHolder(holder: NavViewHolder, position: Int) {
        holder?.icon_GET?.setBackgroundResource(getImages?.get(position) as Int)
        holder?.text_GET?.setText(contentList?.get(position))
        holder?.contentHolder?.setOnClickListener({
            if(position==0)
            {
                //view to Main Screen
                val mainScreenFragment=MainScreenFragment()
                (mcontext as MainActivity).supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.details_fagment,mainScreenFragment)
                    .commit()
            }else if(position==1)
            {
                //view to Favourire Screen
                val favoriteFragment = FavoriteFragment()
                (mcontext as MainActivity).supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.details_fagment,favoriteFragment)
                    .commit()
            }else if(position==2)
            {
                //view to Settings screen
                val setttingsFragment=SettingsFragment()
                (mcontext as MainActivity).supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.details_fagment,setttingsFragment)
                    .commit()
            }else if(position==3)
            {
                //view to AboutUs Fragment
                val aboutUsFragment=AboutUsFragment()
                (mcontext as MainActivity).supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.details_fagment,aboutUsFragment)
                    .commit()
            }
            MainActivity.Statified.drawerlayout?.closeDrawers()
        })
    }

    //Creating view holder for recycler View
    class NavViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        var icon_GET: ImageView?=null
        var text_GET: TextView?=null
        var contentHolder: RelativeLayout?=null
        init{
            icon_GET=itemView?.findViewById(R.id.icon_navdrawer)
            text_GET=itemView?.findViewById(R.id.text_navdrawer)
            contentHolder=itemView?.findViewById(R.id.navdrawer_item_content_holder)
        }
    }
}
