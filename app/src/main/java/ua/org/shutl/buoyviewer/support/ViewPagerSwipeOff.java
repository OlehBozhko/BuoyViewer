package ua.org.shutl.buoyviewer.support;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ViewPagerSwipeOff extends ViewPager {
    public ViewPagerSwipeOff(Context context) {
        super(context);
    }

    public ViewPagerSwipeOff(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }
}
