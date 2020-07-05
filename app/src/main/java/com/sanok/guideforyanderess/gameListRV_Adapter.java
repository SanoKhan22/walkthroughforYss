package com.sanok.guideforyanderess;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;

public class gameListRV_Adapter extends RecyclerView.Adapter<gameListRV_Adapter.gmListViewHolder> {

    public static final int GAMELIST_RECYCLERVIEW_ITEMTYPE = 111;
    public static final int GAMEWALLPAPER_VIEWPAGER2_ITEMTYPE = 222 ;

    Context mContext ;
    ArrayList<gameListItemPojo> gmItemDataList ;
    int mViewItemType ;

    public gameListRV_Adapter(Context mContext, ArrayList<gameListItemPojo> gmItemDataList, int mViewItemType) {
        this.mContext = mContext;
        this.gmItemDataList = gmItemDataList;
        this.mViewItemType = mViewItemType;
    }

    @NonNull
    @Override
    public gmListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;
        if(mViewItemType  == GAMELIST_RECYCLERVIEW_ITEMTYPE){
            view = LayoutInflater.from(mContext).inflate(R.layout.gamelistitem_layout,parent,false);
        }
        else if( mViewItemType == GAMEWALLPAPER_VIEWPAGER2_ITEMTYPE) {
            Log.e("TAG_ALL", "OK IN INFALTING VIEW VIEWPAGE" );

            view = LayoutInflater.from(mContext).inflate(R.layout.wallppr_vp2_layout,parent,false);
        }
        else {
            view = LayoutInflater.from(mContext).inflate(R.layout.gamelistitem_layout,parent,false);
        }

        return new gmListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull gmListViewHolder holder, int position) {
        gameListItemPojo item = gmItemDataList.get(position);
        if(mViewItemType  == GAMELIST_RECYCLERVIEW_ITEMTYPE){
            holder.setData(item.getGmListImgSrc() , item.getGameName() ,item.getGameRDate());

        }
        else if( mViewItemType == GAMEWALLPAPER_VIEWPAGER2_ITEMTYPE) {
            Log.e("TAG_ALL", "OK IN BINDING VIEW VIEWPAGE" );

            holder.setWallpprData(item.getGmListImgSrc());
        }
        else {
            holder.setData(item.getGmListImgSrc() , item.getGameName() ,item.getGameRDate());

        }
    }

    @Override
    public int getItemCount() {
        if(gmItemDataList.size() == 0 || gmItemDataList == null){
            return 0;
        }
        else
            return gmItemDataList.size();
    }



    public class gmListViewHolder extends RecyclerView.ViewHolder{

        ImageView gmListImgView ;
        TextView gmListName_TV , gmListRDate_TV ;
        Button setWallpprBtn ;
        public gmListViewHolder(@NonNull View itemView) {
            super(itemView);





           if(mViewItemType == GAMELIST_RECYCLERVIEW_ITEMTYPE){
               gmListImgView = itemView.findViewById(R.id.gameImageView_L);
               gmListName_TV = itemView.findViewById(R.id.gameName_L);
               gmListRDate_TV = itemView.findViewById(R.id.gameRdate_L);
           }
           else if(mViewItemType == GAMEWALLPAPER_VIEWPAGER2_ITEMTYPE){
               Log.e("TAG_ALL", "OK IN VIEWHOLDER VIEW VIEWPAGE" );
               gmListImgView = itemView.findViewById(R.id.gmWlpprImageView_L);

               setWallpprBtn = itemView.findViewById(R.id.setWallpaperBtn_L);


           }

        }
        public void setData(final int gmImgSrc , String gmName , String gmRDate ){

            gmListImgView.setImageResource(gmImgSrc);
            gmListName_TV.setText(gmName);
            gmListRDate_TV.setText(gmRDate);


        }

        public void setWallpprData(final int gmImgSrc){

            gmListImgView.setImageResource(gmImgSrc);
            int SET_WALLPAPER_PERMISSION = PackageManager.PERMISSION_GRANTED;
            int SET_WALLPAPER_HINT_PERMISSION = PackageManager.PERMISSION_GRANTED;

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                SET_WALLPAPER_PERMISSION = mContext.checkSelfPermission(Manifest.permission.SET_WALLPAPER);
                SET_WALLPAPER_HINT_PERMISSION = mContext.checkSelfPermission(Manifest.permission.SET_WALLPAPER_HINTS);

            }


            final int finalSET_WALLPAPER_PERMISSION = SET_WALLPAPER_PERMISSION;
            final int finalSET_WALLPAPER_HINT_PERMISSION = SET_WALLPAPER_HINT_PERMISSION;
            setWallpprBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(finalSET_WALLPAPER_PERMISSION == PackageManager.PERMISSION_GRANTED &&
                            finalSET_WALLPAPER_HINT_PERMISSION == PackageManager.PERMISSION_GRANTED
                    ){
                        WallpaperManager wpMngr =  WallpaperManager.getInstance(mContext.getApplicationContext());
                        WindowManager w = (WindowManager) mContext.getSystemService(mContext.WINDOW_SERVICE);
                        Bitmap tempbitMap = BitmapFactory.decodeResource(mContext.getResources(),gmImgSrc);

                        DisplayMetrics dpMetric = new DisplayMetrics();
                        w.getDefaultDisplay().getMetrics(dpMetric);



                        int height = dpMetric.heightPixels;
                        int width = dpMetric.widthPixels;

                        Bitmap bitmap = Bitmap.createScaledBitmap(tempbitMap,width,height, true);
                        Log.e("TAG_ALL" , "CLICK SET ON WALL SET " + width + " : "  + height );

                        wpMngr.suggestDesiredDimensions(width,height);

                        try {


                            wpMngr.setBitmap(bitmap);
                            wpMngr.setWallpaperOffsetSteps(1f,1f);

                            Toast.makeText(mContext.getApplicationContext(),"Set Wallpaper" , Toast.LENGTH_LONG).show();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                   else {
                        Toast.makeText(mContext.getApplicationContext(),"Please Allow Perrmissions" , Toast.LENGTH_LONG).show();

                    }
                }
            });
        }



    }
}
