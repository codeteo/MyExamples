package teo.ram.css.myexamplesrandom;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by css on 9/14/14.
 */
public class ObjectAnimatorActivity extends Activity{
    private static final String[] items= { "lorem", "ipsum", "dolor",
            "sit", "amet", "consectetuer", "adipiscing", "elit", "morbi",
            "vel", "ligula", "vitae", "arcu", "aliquet", "mollis", "etiam",
            "vel", "erat", "placerat", "ante", "porttitor", "sodales",
            "pellentesque", "augue", "purus" };

    private TextView word=null;
    int position=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objectanimatoractivity);

        word=(TextView)findViewById(R.id.word);

        ValueAnimator positionAnim = ObjectAnimator.ofInt(this, "wordPosition", 0, 24);
        positionAnim.setDuration(12500);
//        positionAnim.setRepeatCount(ValueAnimator.INFINITE);
//        positionAnim.setRepeatMode(ValueAnimator.RESTART);
        positionAnim.start();
    }

    public void setWordPosition(int position) {
        this.position=position;
        word.setText(items[position]);
    }

    public int getWordPosition() {
        return(position);
    }


}
