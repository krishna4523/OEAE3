package com.example.shreekrishna.movierating;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Shreekrishna on 7/12/2016.
 */
public class CustomAdaptor extends RecyclerView.Adapter {

    //when i create object of this class i need to pass context and the data array ie
    //in our case it is Response class object.searchBeans
    Context ctx;
    ArrayList<Response.SearchBean> mList;

    CustomAdaptor(Context ctx,ArrayList<Response.SearchBean> list){
        this.ctx=ctx;
        mList=list;
    }
    //what happned here is whenever i create this class object i pass context and data(ArrayList)




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(ctx).inflate(R.layout.row,null);//what was 2nd parameter why null?
        //what happened here?


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.mtvTitle.setText(mList.get(position).getTitle());
        myViewHolder.mtvYear.setText(mList.get(position).getYear());
        Picasso.with(ctx).load(mList.get(position).getPoster()).into(myViewHolder.mPoster);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mtvTitle,mtvYear;
        ImageView mPoster;

        public MyViewHolder(View itemView) {
            super(itemView);
            mtvTitle= (TextView) itemView.findViewById(R.id.tvMovieTitle);
            mtvYear= (TextView) itemView.findViewById(R.id.tvMovieYear);
            mPoster= (ImageView) itemView.findViewById(R.id.ivPoster);
        }
    }


}
