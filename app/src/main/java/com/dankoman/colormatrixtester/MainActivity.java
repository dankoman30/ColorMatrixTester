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

public class MainActivity extends AppCompatActivity {

    private ImageView mSampleNotification;
    private Switch mInvertSwitch;
    private Switch mGrayscaleSwitch;
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

    private boolean shouldInvert;
    private boolean shouldGrayscale;

    private float mIntensity = 1f;
    private ColorMatrix mMatrix = new ColorMatrix();
    private ColorMatrix mGrayscaleMatrix = new ColorMatrix();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSampleNotification = (ImageView) findViewById(R.id.iv);
        mInvertSwitch = (Switch) findViewById(R.id.invert);
        mGrayscaleSwitch = (Switch) findViewById(R.id.grayscale);
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

        initialize();

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
    }

    private void initialize() {
        mInvertSwitch.setChecked(true);
        mGrayscaleSwitch.setChecked(true);
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
        mMatrix.preConcat(tint);

        mSampleNotification.clearColorFilter();
        mSampleNotification.setColorFilter(new ColorMatrixColorFilter(mMatrix));
    }
}