package com.example.talaatmagdy.gameofpuzzle;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatButton;
import android.widget.GridLayout;

public class OpeartionOfPuzzle  extends AppCompatButton {
     int row;
     int column;
     int MainPIcleId;
    // when click of image
     boolean isTapped=false;
    //when match of image
     boolean isMatched=false;
    //main
     Drawable main_pic;
     Drawable spare_pic;

    public OpeartionOfPuzzle(Context context , int r , int c , int main_picId){
        super(context);

        row=r;
        column=c;
        MainPIcleId=main_picId;

        main_pic=context.getDrawable(main_picId);
        spare_pic=context.getDrawable(R.drawable.cover);

        setBackground(spare_pic);


        GridLayout.LayoutParams draw = new GridLayout.LayoutParams(GridLayout.spec(r),GridLayout.spec(c));


        draw.width=(int) getResources().getDisplayMetrics().density*100;
        draw.height=(int) getResources().getDisplayMetrics().density*140;

        setLayoutParams(draw);


    }
    // if id of picture is match
    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }
   //get MainPIcleId
    public int getMainPIcleId() {
        return MainPIcleId;
    }
    public void Tap(){
        if (isMatched){
            return;
        }
        if (isTapped){
            setBackground(spare_pic);
            isTapped=false;
        }else {
            setBackground(main_pic);
            isTapped=true;
        }

    }
}

