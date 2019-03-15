package com.amrita.vidyut.BottomNavigation;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.amrita.vidyut.ApiManager;
import com.amrita.vidyut.Data;
import com.amrita.vidyut.R;
import com.amrita.vidyut.SignInActivity;
import com.amrita.vidyut.User;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.IOException;
import java.net.URI;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

public class QrCode extends Fragment {
    private String auth;
    private ImageView imageView;
    private TextView textView;
    private int cacheSize = (int)(0.3 * 1024 * 1024);
    private View view;
    private String str;
    private Dialog progress;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        auth = SignInActivity.Auth;
        view=inflater.inflate(R.layout.fragment_qrcode,null);
        TextView textView=new TextView(getActivity().getApplicationContext());
        Typeface typeface = ResourcesCompat.getFont(getActivity(), R.font.frontage_bold);
        textView.setTypeface(typeface);
        textView.setText("QR");
        textView.setTextColor(getResources().getColor(R.color.colorPrimary));
        ActionBar ab = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if(ab!=null) {
            ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            ab.setCustomView(textView);
            ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#ffffff"));
            ab.setBackgroundDrawable(colorDrawable);
            ab.setDisplayShowTitleEnabled(false);
            final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
            upArrow.setColorFilter(getResources().getColor(R.color.lightBlack), PorterDuff.Mode.SRC_ATOP);
            ab.setHomeAsUpIndicator(upArrow);
        }
        imageView=view.findViewById(R.id.qrcode);
        textView=view.findViewById(R.id.vidqr);
        progessdialog(getActivity());
        new QRclass().execute(auth);

        return view;
    }

    private void progessdialog(Context mcontext) {
        progress = new Dialog(mcontext);
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        progress.setContentView(R.layout.dialog_loading);
        ProgressBar bar = progress.findViewById(R.id.new_post_progress);
        bar.setVisibility(View.VISIBLE);
        progress.show();
    }
     public class QRclass extends AsyncTask<String,Void,User> {

         public final static int QRcodeWidth = 500 ;
         @Override
         protected User doInBackground(String... strings) {
             Cache cache = new Cache(getActivity().getCacheDir(), cacheSize);
             OkHttpClient okHttpClient = new OkHttpClient.Builder()
                     .cache(cache).addInterceptor(new Interceptor() {

                         @Override
                         public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {

                             Request request = chain.request();

                             if (!isNetworkAvailable()) {

                                 int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale \

                                 request = request

                                         .newBuilder()

                                         .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)

                                         .build();

                             }

                             return chain.proceed(request);

                         }

                     })

                     .build();
             ApiManager apiManager = new ApiManager(okHttpClient);
            Call<User> call=apiManager.getUser(auth);
             User user=null;
             try {
               user=call.execute().body();
             } catch (IOException e) {
                 e.printStackTrace();
             }

            return user;
         }

         @Override
         protected void onPostExecute(User user) {
             try{
                 String vid = user.getDatas().getVid();
                 if(vid.length()==3){
                     textView.setText("V190"+vid);
                     str="V190"+vid;
                 }
                 else if(vid.length()==2){
                     textView.setText("V1900"+vid);
                     str="V1900"+vid;
                 }
                 else if(vid.length()==1){
                     textView.setText("V19000"+vid);
                     str="V19000"+vid;
                 }
                 else{
                     textView.setText("V19"+vid);
                     str="V19"+vid;
                 }
                 String farer=user.getDatas().getFarer();
                 if(progress!=null){
                     progress.dismiss();
                 }

                 Toast.makeText(getContext(),user.getDatas().getVid(),Toast.LENGTH_SHORT).show();
                 Bitmap bitmap;
                 if(farer.isEmpty()){

                 }
                 else{
                     try {
                         bitmap=encodeQR(farer);
                         imageView.setImageBitmap(bitmap);
                     } catch (WriterException e) {
                         e.printStackTrace();
                     }

                 }
             }catch (NullPointerException ne){
                 try {
                    Bitmap bitmap=encodeQR(str);
                     imageView.setImageBitmap(bitmap);
                 } catch (WriterException e) {
                     e.printStackTrace();
                 }
             }

             super.onPostExecute(user);
         }

         private Bitmap encodeQR(String str) throws WriterException {
             BitMatrix bitMatrix;
             try {
                 bitMatrix = new MultiFormatWriter().encode(
                         str,
                         BarcodeFormat.DATA_MATRIX.QR_CODE,
                         QRcodeWidth, QRcodeWidth, null
                 );

             } catch (IllegalArgumentException Illegalargumentexception) {

                 return null;
             }
             int w = bitMatrix.getWidth();
             int h = bitMatrix.getHeight();
             int[] pixels = new int[w * h];
             for (int y = 0; y < h; y++) {
                 int offset = y * w;
                 for (int x = 0; x < w; x++) {
                     pixels[offset + x] = bitMatrix.get(x, y) ? BLACK : WHITE;
                 }
             }
             Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
             bitmap.setPixels(pixels, 0, QRcodeWidth, 0, 0, w, h);
             return bitmap;

         }
     }



    private boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager

                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }

}