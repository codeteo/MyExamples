package teo.ram.css.myexamplesrandom;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by css on 9/17/14.
 */
public class PropertyCustomEvaluator extends Activity {
    public ObjectAnimator anim0 , anim1, anim2;
    TextView textViewId000, textViewId111, textViewId222;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propert_custom_evaluator);

        textViewId000 = (TextView) findViewById(R.id.textViewId000);
        textViewId111 = (TextView) findViewById(R.id.textViewId111);
        textViewId222 = (TextView) findViewById(R.id.textViewId222);

        int w = textViewId222.getWidth();
        Log.i("width", "width== " + w);


        CustomEvaluator c = new CustomEvaluator();
        CustomEvaluator1 c1 = new CustomEvaluator1();
        CustomEvaluator2 c2 = new CustomEvaluator2();


        anim0 = ObjectAnimator.ofObject(textViewId000, "translationX", c, -120f, 720f);
        anim0.setDuration(4000);
        anim0.start();

        anim1 = ObjectAnimator.ofObject(textViewId111, "translationX", c1, -240f, 720f);
        anim1.setDuration(4000);
        anim1.start();

        anim2 = ObjectAnimator.ofObject(textViewId222, "translationX", c2, -360f, 720f);
        anim2.setDuration(4000);
        anim2.start();



        int w1 = textViewId222.getWidth();
        Log.i("width", "width== " + w1);


    }

    public class CustomEvaluator implements TypeEvaluator<Float> {

        @Override
        public Float evaluate(float fraction, Float startValue, Float endValue) {
//            Log.i("values", "startValue== " + startValue + "  endValue==  " + endValue);
            if (fraction < 0.8f) {
                float startFloat = startValue;
//                Log.i("return", "return000==  " + (startFloat + fraction * (endValue - startFloat)));
                return startFloat + fraction * (endValue - startFloat);
            } else {
                return startValue +  ( -0.5f + fraction )  * (endValue - startValue)/2;
            }
        }
    }
    public class CustomEvaluator1 implements TypeEvaluator<Float> {

        @Override
        public Float evaluate(float fraction, Float startValue, Float endValue) {
//            Log.i("values", "startValue== " + startValue + "  endValue==  " + endValue);
            if (fraction < 0.8f) {
                float startFloat = startValue;
//                Log.i("return", "return111==  " +   (startFloat + fraction * (endValue - startFloat)));
                return startFloat + fraction * (endValue - startFloat);
            } else {
                return endValue - 300f;
            }
        }
    }
    public class CustomEvaluator2 implements TypeEvaluator<Float> {

        @Override
        public Float evaluate(float fraction, Float startValue, Float endValue) {
//            Log.i("values", "startValue== " + startValue + "  endValue==  " + endValue);
            if (fraction < 0.8f) {
                float startFloat = startValue;
//                Log.i("return", "return222==  " +   (startFloat + fraction * (endValue - startFloat)));
                return startFloat + fraction * (endValue - startFloat);
            } else {
                return endValue - 300f;
            }
        }
    }

     //   return new ValueAnimation<Float>( Float.valueOf( fromValue ), Float.valueOf( toValue ), receiver, new FloatEvaluator() );

}
