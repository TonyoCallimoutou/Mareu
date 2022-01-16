package com.example.mareu.methode;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Spinner;

public class MySpinnerFilter extends androidx.appcompat.widget.AppCompatSpinner {

    OnItemSelectedListener listener;

    public MySpinnerFilter(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public void setSelection(int position)
    {
        super.setSelection(position);

        if (position == getSelectedItemPosition())
        {
            listener.onItemSelected(null, null, position, 0);
        }
    }


    public void setOnItemSelectedListener(OnItemSelectedListener listener)
    {
        this.listener = listener;
    }
}
