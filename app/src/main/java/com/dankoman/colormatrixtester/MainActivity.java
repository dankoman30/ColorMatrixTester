package com.dankoman.colormatrixtester;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView mSampleNotification;
    private Switch mInvertSwitch;
    private Switch mGrayscaleSwitch;
    private Switch mExpertSwitch;
    private TextView mA;
    private TextView mB;
    private TextView mC;
    private TextView mD;
    private TextView mE;
    private TextView mF;
    private TextView mG;
    private TextView mH;
    private TextView mI;
    private TextView mJ;
    private TextView mK;
    private TextView mL;
    private TextView mM;
    private TextView mN;
    private TextView mO;
    private TextView mP;
    private TextView mQ;
    private TextView mR;
    private TextView mS;
    private TextView mT;
    private Button mApplyButton;
    private Button mResetButton;
    private Button mRedMinus;
    private Button mGreenMinus;
    private Button mBlueMinus;
    private Button mAlphaMinus;
    private Button mRedPlus;
    private Button mGreenPlus;
    private Button mBluePlus;
    private Button mAlphaPlus;

    private boolean shouldInvert;
    private boolean shouldGrayscale;

    private float mIntensity = 1f;
    private ColorMatrix mMatrix = new ColorMatrix();
    private ColorMatrix mGrayscaleMatrix = new ColorMatrix();

    private static double mIncrement = 0.05;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSampleNotification = (ImageView) findViewById(R.id.iv);
        mInvertSwitch = (Switch) findViewById(R.id.invert);
        mGrayscaleSwitch = (Switch) findViewById(R.id.grayscale);
        mExpertSwitch = (Switch) findViewById(R.id.expert);
        mA = (TextView) findViewById(R.id.a);
        mB = (TextView) findViewById(R.id.b);
        mC = (TextView) findViewById(R.id.c);
        mD = (TextView) findViewById(R.id.d);
        mE = (TextView) findViewById(R.id.e);
        mF = (TextView) findViewById(R.id.f);
        mG = (TextView) findViewById(R.id.g);
        mH = (TextView) findViewById(R.id.h);
        mI = (TextView) findViewById(R.id.i);
        mJ = (TextView) findViewById(R.id.j);
        mK = (TextView) findViewById(R.id.k);
        mL = (TextView) findViewById(R.id.l);
        mM = (TextView) findViewById(R.id.m);
        mN = (TextView) findViewById(R.id.n);
        mO = (TextView) findViewById(R.id.o);
        mP = (TextView) findViewById(R.id.p);
        mQ = (TextView) findViewById(R.id.q);
        mR = (TextView) findViewById(R.id.r);
        mS = (TextView) findViewById(R.id.s);
        mT = (TextView) findViewById(R.id.t);
        mApplyButton = (Button) findViewById(R.id.apply);
        mResetButton = (Button) findViewById(R.id.reset);
        mRedMinus = (Button) findViewById(R.id.red_minus);
        mGreenMinus = (Button) findViewById(R.id.green_minus);
        mBlueMinus = (Button) findViewById(R.id.blue_minus);
        mAlphaMinus = (Button) findViewById(R.id.alpha_minus);
        mRedPlus = (Button) findViewById(R.id.red_plus);
        mGreenPlus = (Button) findViewById(R.id.green_plus);
        mBluePlus = (Button) findViewById(R.id.blue_plus);
        mAlphaPlus = (Button) findViewById(R.id.alpha_plus);

        mApplyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                apply();
            }
        });
        mResetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                initialize();
            }
        });



        mRedMinus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                adjustValue((-1) * mIncrement,mA);
                apply();
            }
        });
        mGreenMinus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                adjustValue((-1) * mIncrement,mG);
                apply();
            }
        });
        mBlueMinus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                adjustValue((-1) * mIncrement,mM);
                apply();
            }
        });
        mAlphaMinus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                adjustValue((-1) * mIncrement,mS);
                apply();
            }
        });
        mRedPlus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                adjustValue(mIncrement,mA);
                apply();
            }
        });
        mGreenPlus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                adjustValue(mIncrement,mG);
                apply();
            }
        });
        mBluePlus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                adjustValue(mIncrement,mM);
                apply();
            }
        });
        mAlphaPlus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                adjustValue(mIncrement,mS);
                apply();
            }
        });


        mInvertSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                apply();
            }
        });

        mGrayscaleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                apply();
            }
        });

        mExpertSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    expertMode(true);
                } else {
                    expertMode(false);
                }
            }
        });

        initialize();
    }

    private void expertMode(boolean isEnabled){
        mA.setEnabled(isEnabled);
        mB.setEnabled(isEnabled);
        mC.setEnabled(isEnabled);
        mD.setEnabled(isEnabled);
        mE.setEnabled(isEnabled);
        mF.setEnabled(isEnabled);
        mG.setEnabled(isEnabled);
        mH.setEnabled(isEnabled);
        mI.setEnabled(isEnabled);
        mJ.setEnabled(isEnabled);
        mK.setEnabled(isEnabled);
        mL.setEnabled(isEnabled);
        mM.setEnabled(isEnabled);
        mN.setEnabled(isEnabled);
        mO.setEnabled(isEnabled);
        mP.setEnabled(isEnabled);
        mQ.setEnabled(isEnabled);
        mR.setEnabled(isEnabled);
        mS.setEnabled(isEnabled);
        mT.setEnabled(isEnabled);
        mApplyButton.setEnabled(isEnabled);
    }

    private void initialize() {
        mA.setText("1.0");
        mB.setText("0.0");
        mC.setText("0.0");
        mD.setText("0.0");
        mE.setText("0.0");
        mF.setText("0.0");
        mG.setText("1.0");
        mH.setText("0.0");
        mI.setText("0.0");
        mJ.setText("0.0");
        mK.setText("0.0");
        mL.setText("0.0");
        mM.setText("1.0");
        mN.setText("0.0");
        mO.setText("0.0");
        mP.setText("0.0");
        mQ.setText("0.0");
        mR.setText("0.0");
        mS.setText("1.0");
        mT.setText("0.0");
        mInvertSwitch.setChecked(true);
        mGrayscaleSwitch.setChecked(true);
        mExpertSwitch.setChecked(false);

        apply();
    }

    private void apply() {
        shouldInvert = mInvertSwitch.isChecked();
        shouldGrayscale = mGrayscaleSwitch.isChecked();
        final float a = Float.parseFloat(mA.getText().toString());
        final float b = Float.parseFloat(mB.getText().toString());
        final float c = Float.parseFloat(mC.getText().toString());
        final float d = Float.parseFloat(mD.getText().toString());
        final float e = Float.parseFloat(mE.getText().toString());
        final float f = Float.parseFloat(mF.getText().toString());
        final float g = Float.parseFloat(mG.getText().toString());
        final float h = Float.parseFloat(mH.getText().toString());
        final float i = Float.parseFloat(mI.getText().toString());
        final float j = Float.parseFloat(mJ.getText().toString());
        final float k = Float.parseFloat(mK.getText().toString());
        final float l = Float.parseFloat(mL.getText().toString());
        final float m = Float.parseFloat(mM.getText().toString());
        final float n = Float.parseFloat(mN.getText().toString());
        final float o = Float.parseFloat(mO.getText().toString());
        final float p = Float.parseFloat(mP.getText().toString());
        final float q = Float.parseFloat(mQ.getText().toString());
        final float r = Float.parseFloat(mR.getText().toString());
        final float s = Float.parseFloat(mS.getText().toString());
        final float t = Float.parseFloat(mT.getText().toString());

        final float[] doze_tint = new float[] {
                a,b,c,d,e,
                f,g,h,i,j,
                k,l,m,n,o,
                p,q,r,s,t
        };

        updateImageView(doze_tint);
    }

    private void updateImageView(float[] resolvedTint) {
        float components = 1 - 2 * mIntensity;
        int inversionMultiple = 1;

        // if config boolean to invert is false (do NOT invert),
        // multiply components by -1 (double negative) to undo inversion
        if (!shouldInvert) {
            components = -1 * components;
            inversionMultiple = 0;
        }

        final float[] invert = {
                components, 0f,         0f,         0f, 255f * mIntensity * inversionMultiple,
                0f,         components, 0f,         0f, 255f * mIntensity * inversionMultiple,
                0f,         0f,         components, 0f, 255f * mIntensity * inversionMultiple,
                0f,         0f,         0f,         1f, 0f
        };
        mMatrix.set(invert);

        // we only apply grayscale if configured to do so
        if (shouldGrayscale) {
            mGrayscaleMatrix.setSaturation(1 - mIntensity);
            mMatrix.preConcat(mGrayscaleMatrix);
        }

        // custom tint array gets applied here
        final ColorMatrix tint = new ColorMatrix(resolvedTint);
        mMatrix.postConcat(tint);

        mSampleNotification.clearColorFilter();
        mSampleNotification.setColorFilter(new ColorMatrixColorFilter(mMatrix));
    }

    private void adjustValue(double increment,TextView textView){
        double dOld;
        String oldStr = textView.getText().toString();
        if (oldStr.isEmpty()){
            dOld = 0.0;
        } else {
            dOld = Double.parseDouble(textView.getText().toString());
        }
        double dNew = dOld + increment;
        if (dNew < 0.0) {
            dNew = 0.0;
            Toast.makeText(getApplication(), "please choose a value greater than 0.0",
                    Toast.LENGTH_SHORT).show();
        } else if (dNew > 1.0) {
            dNew = 1.0;
            Toast.makeText(getApplication(), "please choose a value less than 1.0",
                    Toast.LENGTH_SHORT).show();
        }
        String newStr = String.format("%.2f", dNew);
        textView.setText(newStr);

    }
}
