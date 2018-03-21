package com.example.islam.gergawi_test; /**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.islam.gergawi_test.R;

import java.text.NumberFormat;
import java.util.jar.Attributes;

import static android.R.attr.name;
import static android.R.attr.x;
import static android.R.id.message;
import static android.R.string.yes;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {


    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean gergawi = true;
        if(gergawi)
            Log.v("WeatherActivity", "It's raining, better bring an umbrella.");
        else
            Log.v("WeatherActivity", "It's unlikely to rain.");

        Log.v("WeatherActivity", "Thank you for using the WhetherWeather App.");
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }




    /**
     * This method displays the given price on the screen.
     */
    /**
     * private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
     */

    public void inCrement(View view) {
        if (quantity >= 10) {
            Toast toast = Toast.makeText(this, "you cannot have more than 10 cup", Toast.LENGTH_LONG);
            toast.show();
        }
        else
            quantity = quantity + 1;

        display(quantity);

    }

    public void deCrement(View view) {
        if (quantity == 1) {
            Toast toast = Toast.makeText(this, "you cannot have less than one cup", Toast.LENGTH_LONG);
            toast.show();
        }
        else
            quantity = quantity - 1;
        display(quantity);

    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        EditText editText = (EditText) findViewById(R.id.addname);
        String Name = editText.getText().toString();

        // check box checked method for whipped cream
        CheckBox checkBox = (CheckBox)findViewById(R.id.checked);
        boolean hasTopping =  checkBox.isChecked();

        // check box checked method for chocolate
        CheckBox checkBox2 = (CheckBox)findViewById(R.id.chocolate);
        boolean chocolate =  checkBox2.isChecked();

        int Price = calculatePrice(hasTopping,chocolate);
        String PriceMassage = orderSummary(Name,Price,hasTopping,chocolate);
//        displayMessage(PriceMassage);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("*/*");
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT,"coffee oreder by : " + Name );
        intent.putExtra(intent.EXTRA_TEXT,PriceMassage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
//        displayMessage(orderSummary(calculatePrice(quantity)));

//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("geo:47.6 , -122.3"));
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
    }

    /**
     * This method displays the given text on the screen.
     */
//    private void displayMessage(String message) {
//        TextView ordersummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//       ordersummaryTextView.setText(message);
//    }

    /**
     * Calculates the price of the order.
     *
     * @param haschocolate is the number of cups of coffee ordered
     * @param haswhippedcream is the number of cups of coffee ordered
     */
    private int calculatePrice(boolean haswhippedcream, boolean haschocolate) {
        // price of one cup of coffee
        int baseprice =  5;

        // price $1 for topping whippedcream
        if (haswhippedcream)
            baseprice +=1;

        // price $2 for topping chocolate
        if(haschocolate)
            baseprice +=2;
        int price = baseprice * quantity;
        return price ;
    }



    /**
     * order summary method
     * @parm is the return of calculatePrice
     * @parm name for customer name
     * @parm hastopping for topping cream
     * @parm chocolate for chocolate toppimg
     * @parm price for order total price
     */
    private String orderSummary( String name , int price , boolean hasTopping , boolean chocolate){

//        int numberOfCoffe = calculatePrice(quantity);
        String priceMessage = name + getString(R.string.Name) + "\n " + getString(R.string.Add_whipped_cream)+ hasTopping;
        priceMessage += "\n " + getString(R.string.Add_chocolate) + chocolate;
        priceMessage += "\n " + getString(R.string.quantity) + quantity ;
        priceMessage += "\n "+ getString(R.string.Total) + price + "\n" + getString(R.string.Thank_you);
        return priceMessage;

    }


}