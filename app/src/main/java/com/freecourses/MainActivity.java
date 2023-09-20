package com.freecourses;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.denzcoskun.imageslider.ImageSlider;
import com.freecourses.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;


public class MainActivity extends AppCompatActivity {
 //cUsD3md2TAq8TMyVM1xQAd:APA91bHGt_73IlFRHUNBzlXmtT5f44n6177V1uI4b_6zrZlCgWg-rhozQXmN036ATxO2JMJjLTrgy45Q8ZT1YEXRSK5KaNrMgUkHZqPZA7SY_Z8IRqG1bzEp7SAs5wdGhvZHvypNSCtF
    private AppBarConfiguration mAppBarConfiguration;
    ActivityMainBinding binding;
    String[] urls=new String[50];
    BroadcastReceiver broadcastReceiver;
    ImageSlider imageSlide;
    public String type;
    Intent login;
    TextView t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextView textiew=findViewById(R.id.maintext);
        Animation myanimation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.animation1);
        textiew.startAnimation(myanimation);

        broadcastReceiver=new ConnectReceiver();
        registered();
//----------------All Course Link Here---------------------------------------------------------------
        urls[0]="https://www.learnvern.com/c-programming-tutorial-in-hindi?ref=637e68";
        urls[1]="https://www.learnvern.com/cpp-tutorial?ref=637e68";
        urls[2]="https://www.learnvern.com/core-java-programming-tutorial?ref=637e68";
        urls[3]="https://www.learnvern.com/advanced-java-tutorial?ref=637e68";

        urls[4]="https://www.learnvern.com/core-python-programming-tutorial-in-hindi?ref=637e68";
        urls[5]="https://www.learnvern.com/core-php-tutorial?ref=637e68";
        urls[6]="https:/www.learnvern.com/mysql-tutorial?ref=637e68";
        urls[7]="https:/www.learnvern.com/sql-server-tutorial?ref=637e68";
        urls[8]="https:/www.learnvern.com/oracle-sql-tutorial?ref=637e68";
//  Layout3 ---------------------------------------------------------------------------------------------------------
        urls[9]="https://www.udemy.com/share/106bqS3@0GDyOjPV-5Ht690nP6TrnOtjKHcPcakop7w1rs09BuZ7-6tMYY7vdXDPyNtJ70T_Ew==/";
        urls[10]="https://www.udemy.com/share/104Vvu3@b5cfJXmuI_g3BF9r9cXNazven97DCmrDx7TTI9vB5UEfx01Mfs80RePx01NyJdY5OA==/";
        urls[11]="https://www.udemy.com/share/106bqS3@Qj3WPRzhOJng30qQpcUUrSnQ_j5TG2mTIMOI0S1DxN1yVxMIrd23-ybiMx5_hgWWYQ==/";
        urls[12]="https://www.udemy.com/share/101vi03@0AozEDMO-DSsBv1VljgLF76o0BOe8u4LNb3OwEcboxiC8GHp4ckDArWnk3NW0WFxkw==/ ";
//Layout4-------------------------------------------------------------------------------------------------------
        urls[13]="https:/www.learnvern.com/android-tutorial?ref=637e68";
        urls[14]="https:/www.learnvern.com/ethical-hacking-course?ref=637e68";
        urls[15]="https:/www.learnvern.com/computer-hardware?ref=637e68";
        urls[16]="https:/www.learnvern.com/computer-networking-course?ref=637e68";
//-------------------------Layout 5--------------------------------------------------------------------
        urls[17]="https:/www.learnvern.com/html5-tutorial?ref=637e68";
        urls[18]="https:/www.learnvern.com/javascript-tutorial-in-hindi?ref=637e68";
        urls[19]="https:/www.learnvern.com/css3-tutorial-in-hindi?ref=637e68";
        urls[20]="https:/www.mygreatlearning.com/academy/learn-for-free/courses/web-development-basics-in-hindi?utm_source=share_with_friends";

        urls[21]="https://simpli-web.app.link/e/nE7PMHCCBub";
        urls[22]="https://simpli-web.app.link/e/UIxR31GCBub";
        urls[23]="https://simpli-web.app.link/e/OTnfUZPEBub";
        urls[24]="https://simpli-web.app.link/e/naXrvlUEBub";
        urls[25]="https://simpli-web.app.link/e/8CTRtAYEBub";
        urls[26]="https://simpli-web.app.link/e/SoClczEGBub";
        urls[27]="https://simpli-web.app.link/e/lnk4QrbFBub";
//------------------------Web Developement-------------------------------------
        urls[28]="https:/www.learnvern.com/html5-tutorial?ref=637e68";
        urls[29]="https:/www.learnvern.com/javascript-tutorial-in-hindi?ref=637e68";
        urls[30]="https:/www.learnvern.com/css3-tutorial?ref=637e68";
        urls[31]="https:/www.learnvern.com/css3-tutorial-in-hindi?ref=637e68";
        urls[32]="https:/www.learnvern.com/html5-tutorial-in-hindi?ref=637e68";
        urls[33]="https:/www.learnvern.com/jquery-tutorial?ref=637e68";
        urls[34]="https:/www.learnvern.com/javascript-tutorial-for-web-designers?ref=637e68";
        urls[35]="https:/www.learnvern.com/reactjs-tutorial?ref=637e68";
        urls[36]="https:/www.learnvern.com/laravel-tutorial?ref=637e68";
        urls[37]="https:/www.learnvern.com/javascript-tutorial-for-web-designer-in-hindi?ref=637e68";
        urls[38]="https:/www.learnvern.com/jquery-tutorial-in-hindi?ref=637e68";
//        Diploma Courses
        urls[39]="https://alison.com/course/diploma-in-software-testing-revised#l-main-pub";

//       SharedPreference Data
        final SharedPreferences sharedPreferences=getSharedPreferences("Data", Context.MODE_PRIVATE);
        type = sharedPreferences.getString("Name1","");
        login = new Intent(MainActivity.this, login.class);
        Toast.makeText(this, type, Toast.LENGTH_SHORT).show();





        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(view -> {
        });

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if(!task.isSuccessful()){
                            return;
                        }
                        String token=task.getResult();
                        Log.d("Token",token);
                    }
                });



        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,R.id.profileFragment,R.id.feedbackFragment,R.id.certificate,R.id.logoutFragment2)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        Menu menu=navigationView.getMenu();
        MenuItem menuItem = menu.findItem(R.id.logoutFragment2);

        if (type.isEmpty()){
            menuItem.setEnabled(false);
        }else{
            menuItem.setEnabled(true);
        }

    }
    protected void  registered(){
        if(Build.VERSION.SDK_INT >=  Build.VERSION_CODES.N){
            registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       MenuInflater inflater= getMenuInflater();
       inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.darkmode:
                Toast.makeText(this, "DarkMode is On", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    //    public void checkConnection(){
//        ConnectivityManager manager=(ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activNetwork=manager.getActiveNetworkInfo();
//        if(null!=activNetwork){
//            if(activNetwork.getType()==ConnectivityManager.TYPE_WIFI){
//                Toast.makeText(this, "WifiEnabled", Toast.LENGTH_SHORT).show();
//            }
//            else if(activNetwork.getType()==ConnectivityManager.TYPE_MOBILE)
//            {
//                Toast.makeText(this, "Internet active", Toast.LENGTH_SHORT).show();
//            }else {
//                Toast.makeText(this, "No internet Connection", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }


//Layout3 ----------------------------------------------------------------------------------------------------
        public void card1 (View view){
        if (type.isEmpty()){
            checkLog();
        }else {
            try {
                Intent i = new Intent(MainActivity.this, webScreen.class);
                i.putExtra("links", urls[0]);
                startActivity(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

        public void card2 (View view){
            if (type.isEmpty()){
                checkLog();
            }else {
                try {
                    Intent i = new Intent(MainActivity.this, webScreen.class);
                    i.putExtra("links", urls[1]);
                    startActivity(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }


    public void card4 (View view){
        try {
            Intent i = new Intent(MainActivity.this, webScreen.class);
            i.putExtra("links",urls[3]);
            startActivity(i);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void card3 (View view){
        if (type.isEmpty()){
            checkLog();
        }else {
            try {
                Intent i = new Intent(MainActivity.this, webScreen.class);
                i.putExtra("links", urls[2]);
                startActivity(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //    Layout2 ----------------------------------------------------------------------------------------------------
    public void card5l1(View view){
        if(type.isEmpty()){
            checkLog();
        }else {
            Intent i = new Intent(MainActivity.this, webScreen.class);
            i.putExtra("links", urls[4]);
            startActivity(i);
        }
    }

    public void php(View view){

        if(type.isEmpty()){
            checkLog();
        }else {
            Intent i = new Intent(MainActivity.this, webScreen.class);
            i.putExtra("links", urls[5]);
            startActivity(i);
        }
    }
    public void card2l2(View view){
        if(type.isEmpty()){
            checkLog();
        }else {
            Intent i = new Intent(MainActivity.this, webScreen.class);
            i.putExtra("links", urls[7]);
            startActivity(i);
        }
    }
    public void card3l2(View view){
        if(type.isEmpty()){
            checkLog();
        }else {
            Intent i = new Intent(MainActivity.this, webScreen.class);
            i.putExtra("links", urls[6]);
            startActivity(i);
        }
    }
    public void card4l2(View view){
        if(type.isEmpty()){
            checkLog();
        }else {
            Intent i = new Intent(MainActivity.this, webScreen.class);
            i.putExtra("links", urls[8]);
            startActivity(i);
        }

    }
//  Layout3 ---------------------------------------------------------------------------------------------------------
public void card1l3(View view){
    if(type.isEmpty()){
        checkLog();
    }else {
        Intent i = new Intent(MainActivity.this, webScreen.class);
        i.putExtra("links", urls[9]);
        startActivity(i);
    }
}

public void card2l3(View view){
    if(type.isEmpty()){
        checkLog();
    }else {
        Intent i = new Intent(MainActivity.this, webScreen.class);
        i.putExtra("links", urls[10]);
        startActivity(i);
    }
    }

public void card3l3(View view){
    if(type.isEmpty()){
        checkLog();
    }else {
        Intent i = new Intent(MainActivity.this, webScreen.class);
        i.putExtra("links", urls[11]);
        startActivity(i);
    }
    }
public void card4l3(View view){
    if(type.isEmpty()){
        checkLog();
    }else {
        Intent i = new Intent(MainActivity.this, webScreen.class);
        i.putExtra("links", urls[13]);
        startActivity(i);
    }

}
//Layout4-------------------------------------------------------------------------------------------------------
public void card1l4(View view){
    if(type.isEmpty()){
        checkLog();
    }else {
        Intent i = new Intent(MainActivity.this, webScreen.class);
        i.putExtra("links", urls[14]);
        startActivity(i);
    }
}

    public void card2l4(View view){
        if(type.isEmpty()){
            checkLog();
        }else {
            Intent i = new Intent(MainActivity.this, webScreen.class);
            i.putExtra("links", urls[14]);
            startActivity(i);
        }
    }


    public void card3l4(View view){
        if(type.isEmpty()){
            checkLog();
        }else {
            Intent i = new Intent(MainActivity.this, webScreen.class);
            i.putExtra("links", urls[15]);
            startActivity(i);
        }
    }
    public void card4l4(View view){
        if(type.isEmpty()){
            checkLog();
        }else {
            Intent i = new Intent(MainActivity.this, webScreen.class);
            i.putExtra("links", urls[15]);
            startActivity(i);
        }

    }

    //-------------------------WEB DEVELOPMENT--------------------------------------------------------------------
    public void webcard3(View view){
        if(type.isEmpty()){
            checkLog();
        }else {
            Intent i = new Intent(MainActivity.this, webScreen.class);
            i.putExtra("links", urls[28]);
            startActivity(i);
        }
    }
    public void webcard5(View view){
        if(type.isEmpty()){
            checkLog();
        }else {
            Intent i = new Intent(MainActivity.this, webScreen.class);
            i.putExtra("links", urls[29]);
            startActivity(i);
        }
    }
    public void webcard6(View view){
        if(type.isEmpty()){
            checkLog();
        }else {
            Intent i = new Intent(MainActivity.this, webScreen.class);
            i.putExtra("links", urls[30]);
            startActivity(i);
        }
    }
    public void webcard4(View view){
        if(type.isEmpty()){
            checkLog();
        }else {
            Intent i = new Intent(MainActivity.this, webScreen.class);
            i.putExtra("links", urls[31]);
            startActivity(i);
        }
    }
    public void webcard2(View view){
        if(type.isEmpty()){
            checkLog();
        }else {
            Intent i = new Intent(MainActivity.this, webScreen.class);
            i.putExtra("links", urls[32]);
            startActivity(i);
        }
    }
    public void webcard1(View view){
        if(type.isEmpty()){
            checkLog();
        }else {
            Intent i = new Intent(MainActivity.this, webScreen.class);
            i.putExtra("links", urls[30]);
            startActivity(i);
        }
    }
    public void webcard7(View view) {
        if(type.isEmpty()){
            checkLog();
        }else {
            Intent i = new Intent(MainActivity.this, webScreen.class);
            i.putExtra("links", urls[34]);
            startActivity(i);
        }
    }
    public void webcard8(View view) {
        if(type.isEmpty()){
            checkLog();
        }else {
            Intent i = new Intent(MainActivity.this, webScreen.class);
            i.putExtra("links", urls[35]);
            startActivity(i);
        }
    }
    public void webcard9(View view) {
        if(type.isEmpty()){
            checkLog();
        }else {
            Intent i = new Intent(MainActivity.this, webScreen.class);
            i.putExtra("links", urls[36]);
            startActivity(i);
        }
    }
    public void webcard10(View view) {
        if(type.isEmpty()){
            checkLog();
        }else {
            Intent i = new Intent(MainActivity.this, webScreen.class);
            i.putExtra("links", urls[37]);
            startActivity(i);
        }
    }
    public void webcard11(View view) {
        if(type.isEmpty()){
            checkLog();
        }else {
            Intent i = new Intent(MainActivity.this, webScreen.class);
            i.putExtra("links", urls[38]);
            startActivity(i);
        }
    }


        //------------------------------------FithLayout----------------------------------------------------------------
    public void card1l5(View view) {
        if(type.isEmpty()){
            checkLog();
        }else {
            Intent i = new Intent(MainActivity.this, webScreen.class);
            i.putExtra("links", urls[17]);
            startActivity(i);
        }
    }
    public void card2l5(View view) {
        if(type.isEmpty()){
            checkLog();
        }else {
            Intent i = new Intent(MainActivity.this, webScreen.class);
            i.putExtra("links", urls[18]);
            startActivity(i);
        }
    }
    public void card3l5(View view) {
        if(type.isEmpty()){
            checkLog();
        }else {
            Intent i = new Intent(MainActivity.this, webScreen.class);
            i.putExtra("links", urls[19]);
            startActivity(i);
        }
    }
    public void card4l5(View view) {
        if(type.isEmpty()){
            checkLog();
        }else {
            Intent i = new Intent(MainActivity.this, webScreen.class);
            i.putExtra("links", urls[20]);
            startActivity(i);
        }
    }



    public void yes(View view) {
        final SharedPreferences sharedPreferences=getSharedPreferences("Data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        Toast.makeText(MainActivity.this, "Logout Sucessfully", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this, MainActivity.class);
        startActivity(i);

    }

    public void no(View view) {
        Toast.makeText(MainActivity.this, "LogOut Canclled", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this, MainActivity.class);
        startActivity(i);

    }
////-------------------------------Certification View------------------------------------------------------------
    public void certi1(View view) {
        Toast.makeText(this, "Hell", Toast.LENGTH_SHORT).show();
    }
    public void certi2(View view) {
        Toast.makeText(this, "Hell", Toast.LENGTH_SHORT).show();

    }
    public void certi3(View view) {
        if(type.isEmpty()){
            checkLog();
        }else {
            Intent i = new Intent(MainActivity.this, webScreen.class);
            i.putExtra("links", urls[21]);
            startActivity(i);
        }
    }
    public void certi4(View view) {
        if(type.isEmpty()){
            checkLog();
        }else {
            Intent i = new Intent(MainActivity.this, webScreen.class);
            i.putExtra("links", urls[22]);
            startActivity(i);
        }
    }
    public void certi5(View view) {
        if(type.isEmpty()){
            checkLog();
        }else {
            Intent i = new Intent(MainActivity.this, webScreen.class);
            i.putExtra("links", urls[23]);
            startActivity(i);
        }
    }
    public void certi6(View view) {
        if(type.isEmpty()){
            checkLog();
        }else {
            Intent i = new Intent(MainActivity.this, webScreen.class);
            i.putExtra("links", urls[24]);
            startActivity(i);
        }
    }
    public void certi7(View view) {
        if(type.isEmpty()){
            checkLog();
        }else {
            Intent i = new Intent(MainActivity.this, webScreen.class);
            i.putExtra("links", urls[25]);
            startActivity(i);
        }
    }
    public void certi8(View view) {
        if(type.isEmpty()){
            checkLog();
        }else {
            Intent i = new Intent(MainActivity.this, webScreen.class);
            i.putExtra("links", urls[26]);
            startActivity(i);
        }
    }
    public void certi9(View view) {
        if(type.isEmpty()){
            checkLog();
        }else {
            Intent i = new Intent(MainActivity.this, webScreen.class);
            i.putExtra("links", urls[27]);
            startActivity(i);
        }
    }


    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this).setMessage("Do you want to Exit ?").setCancelable(false)
                .setTitle(R.string.app_name)
                .setIcon(R.drawable.logo)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                        .setNegativeButton("No" ,null).show();
    }
    void checkLog() {
        new AlertDialog.Builder(this).setMessage("Please Log In ").setCancelable(false)
                .setTitle(R.string.app_name)
                .setIcon(R.drawable.logo)
                .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(login);
                    }
                })
                .setNegativeButton("Cancel", null).show();
    }


}
