package com.excoffier.guillaume.mydrawerappforrpg;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StreamCorruptedException;

import static android.content.Context.MODE_PRIVATE;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static android.os.Build.VERSION_CODES.O;


public class GameFragment extends Fragment implements View.OnClickListener{
    static final int NO_EXIT = -1;
    static final int NUM_OF_ROOMS = 16;

    public static final String MY_PREFS = "prefs";
    SharedPreferences sharedPrefs;

    Room[] thedungeon;
    Player thePlayer;
    Ennemy gumba;
    Ennemy blueSlime;
    Ennemy greenSlime;
    Ennemy wolfBoss;


    Button northButton;
    Button southButton;
    Button westButton;
    Button eastButton;
    TextView mTextView;
    TextView playerInfotxtView;
    TextView ennemyInfoTxtView;
    Button pickupBtn;
    Button attackBtn;
    ImageView ennemyImageView;



    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thePlayer=new Player(0,"nothing",100,100,0,1,15);
        ReadCount();



    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saved) {
        View v= inflater.inflate(R.layout.fragment_game, container, false);
        initTheDungeon();
        displayRooms();
        readXMLFile();
        setupControls(v);
        initRooms();
        SaveCount();
        return v;


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pickupButton:
                initSearch();
                break;
            case R.id.attackButton:
                initAttack();
                break;
            case R.id.northButton:
                thePlayer.setPlayerPos(thedungeon[thePlayer.getPlayerPos()].getNorth()) ;
                initRooms();
                break;
            case R.id.southButton:
                thePlayer.setPlayerPos(thedungeon[thePlayer.getPlayerPos()].getSouth()) ;
                initRooms();
                break;
            case R.id.eastButton:
                thePlayer.setPlayerPos(thedungeon[thePlayer.getPlayerPos()].getEast()) ;
                initRooms();
                break;
            case R.id.westButton:
                thePlayer.setPlayerPos(thedungeon[thePlayer.getPlayerPos()].getWest());
                initRooms();
                break;






        }

    }
    public void initRooms(){
        ValidDirections();
        if (thePlayer.getPlayerPos()==0  || thePlayer.getPlayerPos()==4 ||thePlayer.getPlayerPos()==10||thePlayer.getPlayerPos()==11||thePlayer.getPlayerPos()==6) {
            mTextView.setText(thedungeon[thePlayer.getPlayerPos()].getDescription());
            playerInfotxtView.setText("Your Hp : " + String.valueOf(thePlayer.getHpActuel()) + "/" + String.valueOf(thePlayer.getHpMax()));
            ennemyInfoTxtView.setText("A Chest ! You should try to search in it...");
            ennemyImageView.setImageResource(R.drawable.chest_close);
            initButtonNormal();
            checkValidDirections(thePlayer.getPlayerPos());

        }
        else if (thePlayer.getPlayerPos()==1 ){
            gumba= new Ennemy(5,10,10,R.drawable.gumba);
            mTextView.setText(thedungeon[thePlayer.getPlayerPos()].getDescription());
            playerInfotxtView.setText("Your Hp : " + String.valueOf(thePlayer.getHpActuel()) + "/" + String.valueOf(thePlayer.getHpMax()));
            ennemyInfoTxtView.setText("Ennemy Hp : "+ String.valueOf(gumba.getHpActuel())+"/"+String.valueOf(gumba.getHpMax()));
            ennemyImageView.setImageResource(R.drawable.gumba);
            initButtonNormal();
        }
        else if (thePlayer.getPlayerPos()==2 ){
            greenSlime= new Ennemy(10,25,25,R.drawable.slime_green);
            mTextView.setText(thedungeon[thePlayer.getPlayerPos()].getDescription());
            playerInfotxtView.setText("Your Hp : " + String.valueOf(thePlayer.getHpActuel()) + "/" + String.valueOf(thePlayer.getHpMax()));
            ennemyInfoTxtView.setText("Ennemy Hp : "+ String.valueOf(greenSlime.getHpActuel())+"/"+String.valueOf(greenSlime.getHpMax()));
            ennemyImageView.setImageResource(R.drawable.slime_green);
            initButtonNormal();
        }
        else if (thePlayer.getPlayerPos()==5 ){
            blueSlime=new Ennemy(15,40,40,R.drawable.slime_blue);
            mTextView.setText(thedungeon[thePlayer.getPlayerPos()].getDescription());
            playerInfotxtView.setText("Your Hp : " + String.valueOf(thePlayer.getHpActuel()) + "/" + String.valueOf(thePlayer.getHpMax()));
            ennemyInfoTxtView.setText("Ennemy Hp : "+ String.valueOf(blueSlime.getHpActuel())+"/"+String.valueOf(blueSlime.getHpMax()));
            ennemyImageView.setImageResource(R.drawable.slime_blue);
            initButtonNormal();
        }
        else if (thePlayer.getPlayerPos()==9){
            wolfBoss= new Ennemy(20,100,100,R.drawable.wolf_boss);
            mTextView.setText(thedungeon[thePlayer.getPlayerPos()].getDescription());
            playerInfotxtView.setText("Your Hp : " + String.valueOf(thePlayer.getHpActuel()) + "/" + String.valueOf(thePlayer.getHpMax()));
            ennemyInfoTxtView.setText("Ennemy Hp : "+ String.valueOf(wolfBoss.getHpActuel())+"/"+String.valueOf(wolfBoss.getHpMax()));
            ennemyImageView.setImageResource(R.drawable.wolf_boss);
            initButtonNormal();
        }
        else if (thePlayer.getPlayerPos()==3||thePlayer.getPlayerPos()==12||thePlayer.getPlayerPos()==7){
            mTextView.setText(thedungeon[thePlayer.getPlayerPos()].getDescription());
            ennemyInfoTxtView.setText("Lucky a Campfire you can rest and heal");
            thePlayer.setHpActuel(thePlayer.getHpMax());
            playerInfotxtView.setText("Your Hp : " + String.valueOf(thePlayer.getHpActuel()) + "/" + String.valueOf(thePlayer.getHpMax()));
            ennemyImageView.setImageResource(R.drawable.campfire);
            initButtonNormal();
            checkValidDirections(thePlayer.getPlayerPos());
        }
        else if (thePlayer.getPlayerPos()==8||thePlayer.getPlayerPos()==13||thePlayer.getPlayerPos()==14){
            mTextView.setText(thedungeon[thePlayer.getPlayerPos()].getDescription());
            ennemyInfoTxtView.setText("Unlucky a well. Since the walls arround you broke the only exit is to go in it");
            playerInfotxtView.setText("Your Hp : " + String.valueOf(thePlayer.getHpActuel()) + "/" + String.valueOf(thePlayer.getHpMax()));
            ennemyImageView.setImageResource(R.drawable.well);
            northButton.setText("Jump in the well");
            southButton.setText("Jump in the well");
            eastButton.setText("Jump in the well");
            westButton.setText("Jump in the well");
            checkValidDirections(thePlayer.getPlayerPos());

        }
        else{
            mTextView.setText(thedungeon[thePlayer.getPlayerPos()].getDescription());
            ennemyInfoTxtView.setText("You Can rest now!! Your adventure is now finished and you managed to find your sibling !");
            playerInfotxtView.setText("Your Hp : " + String.valueOf(thePlayer.getHpActuel()) + "/" + String.valueOf(thePlayer.getHpMax()));
            mTextView.setText("If you want to restart the game you should try to see the Achivement folder !");
            ennemyImageView.setImageResource(R.drawable.idle_otherside);
            initButtonNormal();
            checkValidDirections(thePlayer.getPlayerPos());
        }




    }
    public void initSearch(){
        if (thePlayer.getPlayerPos()==0){
            ennemyImageView.setImageResource(R.drawable.cursed_sword);
            ennemyInfoTxtView.setText("Nice a sword ! You will now be able to defend youself");
            pickupBtn.setEnabled(false);
        }
        else if (thePlayer.getPlayerPos()==4){
            ennemyImageView.setImageResource(R.drawable.golden_torsal);
            ennemyInfoTxtView.setText("Nice You got more armor! Your hp will increase for the rest of the adventure");
            thePlayer.setHpMax(thePlayer.getHpMax()+50);
            thePlayer.setHpActuel(thePlayer.getHpActuel()+50);
            playerInfotxtView.setText("Your Hp : " + String.valueOf(thePlayer.getHpActuel()) + "/" + String.valueOf(thePlayer.getHpMax()));
            pickupBtn.setEnabled(false);
        }
        else{
            ennemyImageView.setImageResource(R.drawable.trap);
            ennemyInfoTxtView.setText("No a trap! You lost 50 hp due to bleeding");
            thePlayer.setHpActuel(thePlayer.getHpActuel()-50);
            playerInfotxtView.setText("Your Hp : " + String.valueOf(thePlayer.getHpActuel() + "/" + String.valueOf(thePlayer.getHpMax())));
            mTextView.setText("Don't always Trust chest ...");
            pickupBtn.setEnabled(false);

        }

    }
    public void initAttack(){
        if (thePlayer.getPlayerPos()==1)
        {
            if(gumba.getHpActuel()!=0){
                gumba.setHpActuel(gumba.getHpActuel()-thePlayer.getDamage());
                ennemyInfoTxtView.setText("Ennemy Hp : "+ String.valueOf(gumba.getHpActuel())+"/"+String.valueOf(gumba.getHpMax()));
                thePlayer.setHpActuel(thePlayer.getHpActuel()-gumba.ennemyPos);
                playerInfotxtView.setText("Your Hp : " + String.valueOf(thePlayer.getHpActuel()) + "/" + String.valueOf(thePlayer.getHpMax()));
                mTextView.setText(mTextView.getText()+"\n"+"You lost "+gumba.ennemyPos+" HP while attacking");
                if(gumba.getHpActuel()<=0){
                    ennemyImageView.setImageResource(R.drawable.tombe);
                    ennemyInfoTxtView.setText("Good job, you can pass now");
                    attackBtn.setEnabled(false);
                    checkValidDirections(thePlayer.getPlayerPos());
                }

            }

        }
        else if (thePlayer.getPlayerPos()==2){
            if(greenSlime.getHpActuel()!=0){
                greenSlime.setHpActuel(greenSlime.getHpActuel()-thePlayer.getDamage());
                ennemyInfoTxtView.setText("Ennemy Hp : "+ String.valueOf(greenSlime.getHpActuel())+"/"+String.valueOf(greenSlime.getHpMax()));
                thePlayer.setHpActuel(thePlayer.getHpActuel()-greenSlime.ennemyPos);
                playerInfotxtView.setText("Your Hp : " + String.valueOf(thePlayer.getHpActuel()) + "/" + String.valueOf(thePlayer.getHpMax()));
                mTextView.setText(mTextView.getText()+"\n"+"You lost "+greenSlime.ennemyPos+" HP while attacking");
                if(greenSlime.getHpActuel()<=0){
                    ennemyImageView.setImageResource(R.drawable.tombe);
                    ennemyInfoTxtView.setText("Good job, you can pass now");
                    attackBtn.setEnabled(false);
                    checkValidDirections(thePlayer.getPlayerPos());
                }

            }

        }
        else if (thePlayer.getPlayerPos()==5){
            if(blueSlime.getHpActuel()!=0){
                blueSlime.setHpActuel(blueSlime.getHpActuel()-thePlayer.getDamage());
                ennemyInfoTxtView.setText("Ennemy Hp : "+ String.valueOf(blueSlime.getHpActuel())+"/"+String.valueOf(blueSlime.getHpMax()));
                thePlayer.setHpActuel(thePlayer.getHpActuel()-blueSlime.ennemyPos);
                playerInfotxtView.setText("Your Hp : " + String.valueOf(thePlayer.getHpActuel()) + "/" + String.valueOf(thePlayer.getHpMax()));
                mTextView.setText(mTextView.getText()+"\n"+"You lost "+blueSlime.ennemyPos+" HP while attacking");
                if(blueSlime.getHpActuel()<=0){
                    ennemyImageView.setImageResource(R.drawable.tombe);
                    ennemyInfoTxtView.setText("Good job, you can pass now");
                    attackBtn.setEnabled(false);
                    checkValidDirections(thePlayer.getPlayerPos());
                }

            }
        }
        else if (thePlayer.getPlayerPos()==9){
            if(wolfBoss.getHpActuel()!=0){
                wolfBoss.setHpActuel(wolfBoss.getHpActuel()-thePlayer.getDamage());
                ennemyInfoTxtView.setText("Ennemy Hp : "+ String.valueOf(wolfBoss.getHpActuel())+"/"+String.valueOf(wolfBoss.getHpMax()));
                thePlayer.setHpActuel(thePlayer.getHpActuel()-wolfBoss.ennemyPos);
                playerInfotxtView.setText("Your Hp : " + String.valueOf(thePlayer.getHpActuel()) + "/" + String.valueOf(thePlayer.getHpMax()));
                mTextView.setText(mTextView.getText()+"\n"+"You lost "+wolfBoss.ennemyPos+" HP while attacking");
                if(wolfBoss.getHpActuel()<=0){
                    ennemyImageView.setImageResource(R.drawable.tombe);
                    ennemyInfoTxtView.setText("Good job, you can pass now");
                    attackBtn.setEnabled(false);
                    checkValidDirections(thePlayer.getPlayerPos());
                }

            }
        }

    }
    public void initButtonNormal(){
        northButton.setText("North");
        southButton.setText("South");
        eastButton.setText("East");
        westButton.setText("West");
    }
    public void disableAllDirectionsButton(){
        northButton.setEnabled(false);
        eastButton.setEnabled(false);
        westButton.setEnabled(false);
        southButton.setEnabled(false);
    }

    public void initTheDungeon()
    {
        thedungeon = new Room[NUM_OF_ROOMS];
        for (int pos = 0; pos < NUM_OF_ROOMS; pos++)
        {
            thedungeon[pos] = new Room();
        }
    } // public static void initTheDungeon()

    public void displayRooms()
    {
        Log.w("display ROOM", "**************** start of display rooms " +
                "********************************");
        for (int pos = 0; pos < NUM_OF_ROOMS; pos++)
        {
            Log.w("display ROOM", "North = " + thedungeon[pos].getNorth());
            Log.w("display ROOM", "East = " + thedungeon[pos].getEast());
            Log.w("display ROOM", "West = " + thedungeon[pos].getWest());
            Log.w("display ROOM", "South = " + thedungeon[pos].getSouth());
            Log.w("display ROOM", "Decription = " + thedungeon[pos].getDescription());
        }
        Log.w("display ROOM", "**************** end of display rooms " +
                "**********************************");
    } // public void displayRooms(){

    public void readXMLFile() {
        int pos = 0; // May be use this variable, to keep track of what position of the array of Room Objects.
        try {
            XmlResourceParser xpp = getResources().getXml(R.xml.dungeon);
            xpp.next();
            int eventType = xpp.getEventType();
            int room_count = 0;
            String elemtext = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    String elemName = xpp.getName();
                    if (elemName.equals("dungeon")) {
                        String titleAttr = xpp.getAttributeValue(null,"title");
                        String authorAttr = xpp.getAttributeValue(null,"author");
                    } // if (elemName.equals("dungeon"))
                    if (elemName.equals("room")) {
                        room_count = room_count + 1;
                    }
                    if (elemName.equals("north")) {
                        elemtext = "north";
                    }
                    if (elemName.equals("east")) {
                        elemtext = "east";
                    }
                    if (elemName.equals("south")) {
                        elemtext = "south";
                    }
                    if (elemName.equals("west")) {
                        elemtext = "west";
                    }
                    if (elemName.equals("description")) {
                        elemtext = "description";
                    }
                } // if (eventType == XmlPullParser.START_TAG)
                // You will need to add code in this section to read each element of the XML file
                // And then store the value in the current Room Object.
                // NOTE: This method initTheDungeon() creates and array of Room Objects, ready to be populated!
                // As you can see at the moment the data/text is displayed in the LogCat Window
                // Hint: xpp.getText()
                else if (eventType == XmlPullParser.TEXT) {
                    if (elemtext.equals("north")) {
                        Log.w("ROOM", "north = " + xpp.getText());
                        thedungeon[room_count-1].setNorth( Integer.valueOf(xpp.getText()));
                    }
                    else if (elemtext.equals("east")) {
                        Log.w("ROOM", "east = " + xpp.getText());
                        thedungeon[room_count-1].setEast(Integer.valueOf(xpp.getText()));
                    }
                    else if (elemtext.equals("south")) {
                        Log.w("ROOM", "south = " + xpp.getText());
                        thedungeon[room_count-1].setSouth(Integer.valueOf(xpp.getText()));
                    }
                    else if (elemtext.equals("west")) {
                        Log.w("ROOM", "west = " + xpp.getText());
                        thedungeon[room_count-1].setWest(Integer.valueOf(xpp.getText()));
                    }
                    else if (elemtext.equals("description")) {
                        Log.w("ROOM", "description = " + xpp.getText());
                        thedungeon[room_count-1].setDescription( xpp.getText() );
                    }
                } // else if (eventType == XmlPullParser.TEXT)
                eventType = xpp.next();
            } // while (eventType != XmlPullParser.END_DOCUMENT)
        } // try
        catch (XmlPullParserException e) {
        }
        catch (IOException e) {
        }
    } // public void readXMLFile()

    public void setupControls(View v){
        mTextView=(TextView)v.findViewById(R.id.logOfFighttxtView);
        playerInfotxtView=(TextView)v.findViewById(R.id.playerInfotxtView);
        ennemyInfoTxtView=(TextView)v.findViewById(R.id.ennemyInfotxtView);
        ennemyImageView=(ImageView)v.findViewById(R.id.ennemyImageVIew);
        pickupBtn=(Button)v.findViewById(R.id.pickupButton);
        attackBtn=(Button)v.findViewById(R.id.attackButton);
        northButton=(Button)v.findViewById(R.id.northButton);
        southButton=(Button)v.findViewById(R.id.southButton);
        eastButton=(Button)v.findViewById(R.id.eastButton);
        westButton=(Button)v.findViewById(R.id.westButton);
        pickupBtn.setOnClickListener(this);
        attackBtn.setOnClickListener(this);
        northButton.setOnClickListener(this);
        southButton.setOnClickListener(this);
        eastButton.setOnClickListener(this);
        westButton.setOnClickListener(this);
    }

    public void ValidDirections(){
        if (thePlayer.getPlayerPos()==0|| thePlayer.getPlayerPos()==4||thePlayer.getPlayerPos()==10|| thePlayer.getPlayerPos()==11|| thePlayer.getPlayerPos()==6){
            pickupBtn.setEnabled(true);
        }
        else{
            pickupBtn.setEnabled(false);
        }
        if (pickupBtn.isEnabled()){
            attackBtn.setEnabled(false);
        }
        else if (thePlayer.getPlayerPos()==3||thePlayer.getPlayerPos()==12||thePlayer.getPlayerPos()==7||thePlayer.getPlayerPos()==8||thePlayer.getPlayerPos()==13||thePlayer.getPlayerPos()==14|| thePlayer.getPlayerPos()==15){
            attackBtn.setEnabled(false);
        }
        else{
            attackBtn.setEnabled(true);
        }
        if (attackBtn.isEnabled()){
            disableAllDirectionsButton();

        }

        SaveCount();



    }
    public void checkValidDirections(int currentPos){
        if (thedungeon[currentPos].getNorth() !=NO_EXIT){
            northButton.setEnabled(true);
        }
        else{
            northButton.setEnabled(false);
        }
        if (thedungeon[currentPos].getEast() !=NO_EXIT){
            eastButton.setEnabled(true);
        }
        else{
            eastButton.setEnabled(false);
        }
        if (thedungeon[currentPos].getWest() !=NO_EXIT){
            westButton.setEnabled(true);
        }
        else{
            westButton.setEnabled(false);
        }
        if (thedungeon[currentPos].getSouth() !=NO_EXIT){
            southButton.setEnabled(true);
        }
        else{
            southButton.setEnabled(false);
        }

    }
    public void SaveCount()
    {
        sharedPrefs = getActivity().getSharedPreferences(MY_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPrefs.edit();
        edit.putInt("playerpos", thePlayer.getPlayerPos());
        edit.putInt("playerdamage",thePlayer.getDamage());
        edit.putInt("playerhealth",thePlayer.getHpActuel());
        edit.putInt("playerhealthmax",thePlayer.getHpMax());

        edit.commit();
    }
    public void ReadCount()
    {
        sharedPrefs = getActivity().getSharedPreferences(MY_PREFS, MODE_PRIVATE);
        thePlayer.setPlayerPos(sharedPrefs.getInt("playerpos", 0));
        thePlayer.setHpActuel(sharedPrefs.getInt("playerhealth",100));
        thePlayer.setHpMax(sharedPrefs.getInt("playerhealthmax",100));
        thePlayer.setDamage(sharedPrefs.getInt("playerdamage",15));
        Bundle bundle =getArguments();
        if(bundle!=null){
            thePlayer.setPlayerPos(bundle.getInt("resetpos"));
            thePlayer.setHpActuel(bundle.getInt("resethealth"));
            thePlayer.setHpMax(bundle.getInt("resethealthmax"));
            thePlayer.setDamage(bundle.getInt("resetdamage"));
        }

    }



}


