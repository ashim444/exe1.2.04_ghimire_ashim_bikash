package com.example.ashimghimire.bmi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity
        implements AdapterView.OnItemSelectedListener,
        View.OnClickListener, TextWatcher {


    int margin;
    float weight = 1.0f;
    double userWeight, userHeight, userAge, intlAge, intlFat;
    EditText edtage, edtHeight, edtWeight;
    TextView bmiTxt, idlWgtTxt, fatTxt, message;
    Button resetBtn, infoBtn;
    int pad;
    StrnFrmt strnFrmt;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        margin = (int) getResources().getDimension(R.dimen.mrgn);
        pad = (int) getResources().getDimension(R.dimen.pad1);
        setContentView(MainLyout());
        edtWeight.addTextChangedListener(this);
        edtHeight.addTextChangedListener(this);
        edtage.addTextChangedListener(this);
        strnFrmt = new StrnFrmt();
    }

    /**
     *
     * @return the main layout of the program
     */
    private LinearLayout MainLyout() {
        LinearLayout ll = new LinearLayout(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        ll.setLayoutParams(lp);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setPadding(pad, pad, pad, pad);
        int i = (int) getResources().getColor(R.color.backGround);
        ll.setBackgroundColor(i);
        ll.addView(lyutAdd1());
        ll.addView(lyutAdd2());
        ll.addView(lyutAdd3());
        ll.addView(lyutNes1());
        ll.addView(setUpButton(R.string.btnReset, R.string.actnInfo));
        return ll;
    }

    /**
     *
     * @param idRset is the id of the button that reset the application
     * @param idIndo is the id of the button that send the intent to start the info activity in the application
     * @return buttons to main layout
     */

    private View setUpButton(int idRset, int idIndo) {
        LinearLayout ll = new LinearLayout(this);
        LinearLayout.LayoutParams lp = new
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        ll.setLayoutParams(lp);
        resetBtn = new Button(this);
        resetBtn.setId(AppData.actnRset);
        infoBtn = new Button(this);
        infoBtn.setId(AppData.info);
        lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, weight);
        lp.setMargins(margin, margin, margin, margin);
        lp.gravity = Gravity.BOTTOM;
        ll.addView(addBtn(idIndo, infoBtn, lp));
        ll.addView(addBtn(idRset, resetBtn, lp));
        resetBtn.setOnClickListener(this);
        infoBtn.setOnClickListener(this);
        return ll;
    }

    /**
     * Function sets the layout of button
     * @param id : Id of button
     * @param btn : buttn
     * @param lp: Layout paramater for the button
     * @return: button
     */
    public Button addBtn(int id, Button btn, LinearLayout.LayoutParams lp){
        btn.setLayoutParams(lp);
        btn.setTextSize((int) getResources().getDimension(R.dimen.btnTxtsiz));
        btn.setBackgroundColor( getResources().getColor(R.color.btnColor));
        btn.setText(id);
        return btn;
    }

    /**
     * Function identifies the event click and recognise the event
     * @param v is view that is being clicked
     */
    @Override
    public void onClick(View v) {
        /*Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage( getBaseContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        startActivity(i);*/
        switch (v.getId()){
            case AppData.info: Intent infoIntent = new Intent(MainActivity.this, InfoMain.class);
                startActivity(infoIntent); break;
            case  AppData.actnRset:  startActivity(new Intent(this, MainActivity.class)); break;
        }
    }

    /**
     * Function lnerLyot is function that contain a Linear Layout
     * @return View Linear Layout
     */

    public LinearLayout lnerLyut() {
        LinearLayout ll = new LinearLayout(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setLayoutParams(lp);
        return ll;
    }

    /**
     * It is function that set first childlayout of the main layout
     * @return First child layout
     */
    public LinearLayout lyutAdd1() {
        LinearLayout ll = lnerLyut();
        ll.addView(textAdd(R.string.txtAge));
        edtage = editAdd(R.string.year);
        edtage.setId(AppData.ageID);
        ll.addView(edtage);
        ll.addView(spnAdd());
        return ll;
    }
    /**
     * It is function that set second childlayout of the main layout
     * @return second child layout
     */

    public LinearLayout lyutAdd2() {
        LinearLayout ll = lnerLyut();
        ll.addView(textAdd(R.string.textHeight));
        edtHeight = editAdd(R.string.CM);
        edtHeight.setId(AppData.heightId);
        ll.addView(edtHeight);
        ll.addView(textAdd(R.string.CM));
        return ll;
    }
    /**
     * It is function that set third childlayout of the main layout
     * @return third child layout
     */

    public LinearLayout lyutAdd3() {
        LinearLayout ll = lnerLyut();
        ll.addView(textAdd(R.string.textWeight));
        edtWeight = editAdd(R.string.KG);
        edtWeight.setId(AppData.weightId);
        ll.addView(edtWeight);
        ll.addView(textAdd(R.string.KG));
        return ll;
    }
    /**
     * It is function that set  childlayout of the main layout
     * @return  child layout
     */

    public LinearLayout lyutNes1() {
        LinearLayout ll = new LinearLayout(this);
        ll.setPadding(pad, pad, pad, pad);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(margin, margin, margin, margin);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(setLyuttxt());
        ll.addView(setLyuttxt2());
        int size = (int) getResources().getDimension(R.dimen.txtS2);
        message = textAdd(R.string.message);
        message.setId(AppData.message);
        message.setTextSize(size);
        ll.addView(message);
        return ll;
    }
    /**
     * It is function that set  childlayout of the main layout
     * @return  child layout
     */
    private LinearLayout setLyuttxt() {
        LinearLayout ll = lnerLyut();
        int size = (int) getResources().getDimension(R.dimen.txtS2);
        TextView bm = textAdd(R.string.bmiTxt);
        bm.setTextSize(size);
        ll.addView(bm);
        bm = textAdd(R.string.idlWeight);
        bm.setTextSize(size);
        ll.addView(bm);
        bm = textAdd(R.string.fat);
        bm.setTextSize(size);
        ll.addView(bm);
        return ll;
    }
    /**
     * It is function that set  childlayout of the main layout
     * @return  child layout
     */
    private LinearLayout setLyuttxt2() {
        LinearLayout ll = lnerLyut();
        int size = (int) getResources().getDimension(R.dimen.txtS2);
        bmiTxt = textAdd(R.string.guess);
        bmiTxt.setTextSize(size);
        bmiTxt.setId(AppData.guessBmi);

        ll.addView(bmiTxt);
        idlWgtTxt = textAdd(R.string.guess);
        idlWgtTxt.setTextSize(size);
        idlWgtTxt.setId(AppData.guessbdyWt);
        ll.addView(idlWgtTxt);
        fatTxt = textAdd(R.string.guess);
        fatTxt.setTextSize(size);
        fatTxt.setId(AppData.guessFat);
        ll.addView(fatTxt);
        return ll;
    }

    /**
     * Funtion that set TextView attributr and return it
     * @param id of the text view that is to be returned
     * @return Text Vier
     */

    public TextView textAdd(int id) {
        TextView txt = new TextView(this);
        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, weight);
        ll.setMargins(margin, margin, margin, margin);
        txt.setText(id);
        txt.setTextSize(getResources().getDimension(R.dimen.txtS));
        int i = (int) getResources().getColor(R.color.txtColour);
        txt.setTextColor(i);
        txt.setLayoutParams(ll);
        txt.setAllCaps(true);
        //txt.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.ITALIC));
        txt.setGravity(Gravity.CENTER_HORIZONTAL);
        return txt;
    }

    /**
     * Function that sets EditTextView attribute and return it
     * @param id of the edit text that is to be returned
     * @return the Edit text VIew with id
     */


    public EditText editAdd(int id) {
        EditText edtTxt = new EditText(this);
        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, weight);
        ll.setMargins(margin, margin, margin, margin);
        edtTxt.setHint(id);
        edtTxt.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        //edtTxt.setTextSize(R.dimen.txtS);
        edtTxt.setLayoutParams(ll);
        return edtTxt;
    }

    /**
     * Function create the Spinner View and return it
     * @return Spinner
     */
    public Spinner spnAdd() {
        Spinner spin = new Spinner(this);
        spin.setId(AppData.spin);
        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, weight);
        ll.setMargins(margin, margin, margin, margin);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sex, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);
        return spin;
    }

    /**
     *Function invoked when the view has been clicked
      * @param parent Adapeter View
     * @param view View
     * @param position of the view
     * @param id id of view selected
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                intlAge = AppData.maleWt;
                intlFat = AppData.intlMaleFat;
                break;
            case 1:
                intlAge = AppData.femalewt;
                intlFat = AppData.intlFemaleFat;
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }





    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {  }

    /**
     * Function Invoked when view has been clicked
     * @param s is the editable value that is being typed in the view
     */
    @Override
    public void afterTextChanged(Editable s) {

        if (s == edtage.getEditableText()) {//When User is editing this view
            userAge = strnFrmt.ParseDouble(edtage.getText().toString());//assign value form the view which is not null
            bmiTxt.setText(calculateBMI());//calculate BMI
            fatTxt.setText(calculateFat());//calculate Fat
        }
        if (s == edtHeight.getEditableText()) {//When User is editing this view
            userHeight = strnFrmt.ParseDouble(edtHeight.getText().toString());//assign value form the view which is not null
            bmiTxt.setText(calculateBMI());//calculate BMI
            idlWgtTxt.setText(calculateIdelWeight());// calculateIdelWt
            fatTxt.setText(calculateFat());//calculate Fat
        }

        if (s == edtWeight.getEditableText()) {//When User is editing this view
            userWeight = strnFrmt.ParseDouble(edtWeight.getText().toString());//assign value form the view which is not null
            bmiTxt.setText(calculateBMI());//calculate BMI
            fatTxt.setText(calculateFat());//calculate Fat
        }

    }

    /**
     * Function calculate the Fat of the user
     * @return User Body fat in string
     */
    private String calculateFat() {
        String userFat = "";
        double userBmi = strnFrmt.ParseDouble(calculateBMI());//check the non null value
        if (userAge > 0.0 && userBmi > 8.0) {//is user has input his age
            try {
                double userFatNum = (1.2 * userBmi) + (0.23 * userAge) - intlFat;//formula Google
                userFat = strnFrmt.numberFormat(userFatNum) + "%";
                return userFat;
            } catch (NumberFormatException e) {
            }

        }
        return userFat;

    }

    /**
     * Function calculate the idelWeight of the user
     * @return the ideal weight of the user
     */

    private String calculateIdelWeight() {
        String idlWt = "";
        if (userHeight > 150.0) {//checks if User height is greater the 150. Ideal wt can be measured if height is greate the 150
            try {
                double heightInInch = userHeight / AppData.cmToInch;//converting height in cm to  inch
                idlWt = strnFrmt.numberFormat(intlAge + (AppData.valueMultiplyKg * (heightInInch - AppData.minusinch)));//calculation the ideal weight
                return idlWt;
            } catch (NumberFormatException e) {
            }
        }
        return idlWt;
    }

    /**
     * Function calculate the BMI of the User
     * @return the USer BMI
     */

    String calculateBMI() {
        String userBmi = "";
        if (userHeight != 0.0 && userWeight != 0.0) {// check if user has both value
            try {
                double heightInMeter = userHeight * 0.01;//converting heignt in cm to meter
                double userBmiDouble = userWeight / (heightInMeter * heightInMeter);
                //calculateFat(Double.parseDouble(numberFormat(userBmiDouble)));
                messageDisplay(Double.parseDouble(strnFrmt.numberFormat(userBmiDouble)));//Display User Message on according BMI
                return strnFrmt.numberFormat(userBmiDouble);//returning BMI in string format
            } catch (NumberFormatException e) {
            }
        }
        return userBmi;
    }

    /**
     * Function display message to user according to their BMI
     * @param userBmiDouble BMI of user in doubl data type
     */
    private void messageDisplay(double userBmiDouble) {
        String mes = "";


        if (userBmiDouble > 0.0 & userBmiDouble < 18.50) {
            mes += "Time to grab a bite\n" + "UnderWeight";
            stColor((int) getResources().getColor(R.color.dngrClor));

        }
        if (userBmiDouble > 18.5 && userBmiDouble < 25.0) {
            mes += "Great \n" + "Normal Shape";
            stColor((int) getResources().getColor(R.color.txtColour));

        }
        if (userBmiDouble > 25.0 && userBmiDouble < 30.0) {
            mes += "Time to run\n" + "overWeight";
            stColor((int) getResources().getColor(R.color.obseClor));

        }
        if (userBmiDouble > 30.0) {
            mes += "Time to run\n" + "Obese";
            stColor((int) getResources().getColor(R.color.dngrClor));
        }

        message.setText(mes);// setting message to user


    }

    /**
     * Setting Color In according to BMI with danger coluor
     * @param clor The colour Level the danger in user BMI
     */
    void stColor(int clor){

        bmiTxt.setTextColor(clor);
        message.setTextColor(clor);
        fatTxt.setTextColor(clor);
    }

    /**
     * Menu Create and attribute of the menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int order = 0;
        //menu.add(0, AppData.info, order++, R.string.actnInfo).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add(0, AppData.actnExit, order++, R.string.actnExit).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        return true;
    }

    /**
     * Function That is invoked when view has been clicked
     * @param item View that is being selected
     * @return
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case AppData.actnExit:
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

}
