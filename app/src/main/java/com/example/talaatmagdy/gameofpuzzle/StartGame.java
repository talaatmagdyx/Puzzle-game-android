package com.example.talaatmagdy.gameofpuzzle;



import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.Random;

public class StartGame extends AppCompatActivity implements View.OnClickListener {
    private int numOfElements;
    private OpeartionOfPuzzle[] buttons;
    private int[] ImageLocations;
    private int[] ImageDraw;
    private OpeartionOfPuzzle selected1;
    private OpeartionOfPuzzle selected2;
    private boolean ischoose=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startgame);

        GridLayout gridLayout=(GridLayout)findViewById(R.id.grid_Layout);
        int numColumns= gridLayout.getColumnCount();
        int numRows= gridLayout.getRowCount();

        numOfElements=numColumns*numRows;

        buttons=new OpeartionOfPuzzle[numOfElements];

        ImageDraw=new int[numOfElements/2];
        ImageDraw[0]=R.drawable.circle;
        ImageDraw[1]=R.drawable.diamond;
        ImageDraw[2]=R.drawable.elipse;
        ImageDraw[3]=R.drawable.heart;
        ImageDraw[4]=R.drawable.rectangle;
        ImageDraw[5]=R.drawable.sqaure;
        ImageDraw[6]=R.drawable.star;
        ImageDraw[7]=R.drawable.triangle;

        ImageLocations=new int[numOfElements];

        randomImage();

        for (int r=0;r<numRows;r++){
            for (int c=0;c<numColumns;c++){
                OpeartionOfPuzzle tempButton = new OpeartionOfPuzzle(this,r,c,ImageDraw[ImageLocations[r*numColumns+c]]);
                tempButton.setId(View.generateViewId());
                tempButton.setOnClickListener(this);
                buttons[r*numColumns+c]=tempButton;
                gridLayout.addView(tempButton);

            }
        }

    }



    public void randomImage(){
        Random rand = new Random();

        for (int i=0;i<numOfElements;i++){
            ImageLocations[i]=i%(numOfElements/2);
        }

        for (int i=0;i<numOfElements;i++){
            int temp = ImageLocations[i];
            int swapIndex = rand.nextInt(16);
            ImageLocations[i]=ImageLocations[swapIndex];
            ImageLocations[swapIndex]=temp;
        }

        /*
         Random r; // initialize this

        private int getRandomImage()
        {
                 r = new Random();
                 int select = r.nextInt(imageIDs.length);
                return imageIDs[select];
        }
         */
    }


    @Override
    public void onClick(View v) {
        if (ischoose){
            return;
        }

        OpeartionOfPuzzle button = (OpeartionOfPuzzle) v ;

        if (button.isMatched){
            return;
        }

        if (selected1==null){
            selected1=button;
            selected1.Tap();
            return;
        }
        if (selected1.getId()==button.getId()){
            return;
        }
        if (selected1.getMainPIcleId()==button.getMainPIcleId()){
            button.Tap();

            button.setMatched(true);
            selected1.setMatched(true);

            selected1.setVisibility(View.INVISIBLE);
            button.setVisibility(View.INVISIBLE);
            //selected1=null;
            Toast.makeText(getBaseContext(), "Congratulations\n", Toast.LENGTH_SHORT).show();
            return;

        }else {
            selected2=button;
            selected2.Tap();
            ischoose=true;

           //Thread of Choose
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    selected2.Tap();
                    selected1.Tap();
                    selected1=null;
                    selected2=null;
                    ischoose=false;


                }
            }
            ,500);



        }
    }
}


