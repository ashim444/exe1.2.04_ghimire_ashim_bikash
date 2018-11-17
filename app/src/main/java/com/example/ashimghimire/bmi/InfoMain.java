package com.example.ashimghimire.bmi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class InfoMain extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mnLaout());
    }

    /**
     *Main layout of the Activity InfoMain
     * @return Main Layout
     */
    private View mnLaout() {
        ScrollView scrollView = new ScrollView(this);
        ScrollView.LayoutParams lp = new
                ScrollView.LayoutParams(ScrollView.LayoutParams.MATCH_PARENT,
                ScrollView.LayoutParams.MATCH_PARENT);
        scrollView.setLayoutParams(lp);


        scrollView.addView(addInfoTxt());
        return  scrollView;
    }

    /**
     *Add the child view in the Main Layout
     * @return the child view of the layout
     */

    private View addInfoTxt() {
        LinearLayout ll = new LinearLayout(this);
        LinearLayout.LayoutParams lp = new
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        ll.setLayoutParams(lp);
        ll.setOrientation(LinearLayout.VERTICAL);
        lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        int pad = (int)getResources().getDimension(R.dimen.pad1);
        int size = (int)getResources().getDimension(R.dimen.txtS2);
        ll.setPadding(pad, pad, pad, pad);
        TextView txtView = new TextView(this);
        txtView.setLayoutParams(lp);
        txtView.setText(R.string.BmiInfo);
        txtView.setPadding(pad, pad, pad,pad);
        txtView.setTextSize(size);
        ll.addView(txtView);
        txtView.setText(R.string.BmiInfo2);
        ll.addView(addImage(lp));
        ll.addView(addBtnHome());
        return ll;
    }

    /**
     *Add button to the maon layout
     * @return child view Button
     */
    private View addBtnHome() {
        LinearLayout ll = new LinearLayout(this);
        LinearLayout.LayoutParams lp = new
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        ll.setLayoutParams(lp);
        ll.setOrientation(LinearLayout.VERTICAL);
        Button btn = new Button(this);
        lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.BOTTOM;
        int mrgn = (int)getResources().getDimension(R.dimen.mrgn);
        lp.setMargins(mrgn, mrgn, mrgn, mrgn);
        btn.setLayoutParams(lp);
        btn.setText(R.string.BtnHom);
        btn.setBackgroundColor(getResources().getColor(R.color.btnColor));
        ll.addView(btn);
        btn.setOnClickListener(this);

        return ll;
    }

    /**
     * Add Image to the Activity InfoMain
     * @param lp Layout paramater
     * @return the child view image
     */
    private View addImage(LinearLayout.LayoutParams lp) {
        ImageView img = new ImageView(this);
        img.setLayoutParams(lp);
        img.setImageResource(R.drawable.bmi_info);
        return img;
    }

    /**
     *Invoke when the view is in action and closes the activity
     * @param v is the view with activity
     */
    @Override
    public void onClick(View v) {
        finish();
    }
}
