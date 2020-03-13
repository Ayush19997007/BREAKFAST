package com.example.android.breakfast;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int numberOfTea=0;
    String txt="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void submitOrder(View view)
    {

        String priceMessage="";
        CheckBox cb=(CheckBox) findViewById(R.id.checkbox1);
        boolean value = cb.isChecked();
        CheckBox cb1=findViewById(R.id.checkbox2);
                boolean value1=cb1.isChecked();
                TextView tv1=findViewById(R.id.yourName);
        txt= tv1.getText().toString();
        int price=calculateMoney(value,value1,numberOfTea);
      //  Log.v("MainActivity","icecream with topping is ordered"+value);

            priceMessage = createOrderSummary(numberOfTea, value, value1, price, txt);


//        displayMessage(priceMessage);

        //FOR GETTING LOACTION
//        Intent intent=new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("geo:20.2603328,85.8393432"));
//        if(intent.resolveActivity(getPackageManager())!=null)
//        {
//            startActivity(intent);
//        }
        //FOR ALARMS
//        int seconds=45;
//        String st="HI";
//        startTimer(st, seconds);

        //FOR EMAIL
    //    String addresses="hello ayush";
        String subject="FIRST EMAIL";
        composeEmail(priceMessage,subject) ;

    }


// alarm code
//    public void startTimer(String message, int seconds) {
//        Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER)
//                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
//                .putExtra(AlarmClock.EXTRA_LENGTH, seconds)
//                .putExtra(AlarmClock.EXTRA_SKIP_UI, true);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
//    }
public void composeEmail(String addresses, String subject) {
    Intent intent = new Intent(Intent.ACTION_SENDTO);
    intent.setData(Uri.parse("mailto:")); // only email apps should handle this
//    intent.putExtra(Intent.EXTRA_EMAIL,addresses);
    intent.putExtra(Intent.EXTRA_SUBJECT, subject);
    intent.putExtra(Intent.EXTRA_TEXT,addresses);
    if (intent.resolveActivity(getPackageManager()) != null) {
        startActivity(intent);
    }
}

    public void increment(View view) {
        numberOfTea=numberOfTea+1;
        display(numberOfTea);
    }
    public void decrement(View view) {
        if (numberOfTea>0) {
            numberOfTea = numberOfTea - 1;
            display(numberOfTea);
        }
    }
    public void reset(View view) {
        numberOfTea=0;
        display(numberOfTea);
    }
    private int calculateMoney(boolean b1,boolean b2,int numberOfTea)
    {
        int totalp=0;

        if(b2)
        {
            totalp = 30 + numberOfTea * 10; //cost of sprinkles topping is 30
        }
        else if(b1) {
            totalp = 20 + numberOfTea * 10; //cost of whipped cream topping is 20
        }
        else if(b1 && b2)
        {
            totalp=50+numberOfTea*10;
        }

        else {
            totalp = numberOfTea * 10;
        }
        return (totalp);
        }
        private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    private String createOrderSummary(int numberOfTea, boolean b, boolean c,int sum,String txt)
    {
        String priceMessage="Your name is "+txt;
        priceMessage +="\n1. Number Of Tea ordered ="+numberOfTea;
        priceMessage +="\n2. thanks for ordering";
        priceMessage +="\n3. have you ordered whipped cream topping:"+b;
        priceMessage +="\n4. have you ordered sprinkle topping:"+c;
        priceMessage +="\n5.icecream price is = $2";
       priceMessage +="\n total price is = "+sum;
        return priceMessage;
    }
}