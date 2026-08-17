package com.reactnative.photoview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import me.relex.photodraweeview.Attacher;
import me.relex.photodraweeview.IAttacher;
import me.relex.photodraweeview.OnPhotoTapListener;
import me.relex.photodraweeview.OnScaleChangeListener;
import me.relex.photodraweeview.OnViewTapListener;

public class ScalingView extends FrameLayout implements IAttacher {

    private Attacher mAttacher;

    public ScalingView(Context context) {
        super(context);
        init();
        setMinimumScale(0.5f);
        setMaximumScale(5.0f);
    }

    protected void init() {
        if (mAttacher == null || mAttacher.getView() == null) {
            mAttacher = new Attacher(this);
            setOnTouchListener(null);
        }
    }

    public Attacher getAttacher() {
        return mAttacher;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        mAttacher.onTouch(this, event);
        MotionEvent childEvent = MotionEvent.obtain(event);
        Matrix inverseMatrix = new Matrix();
        if (mAttacher.getDrawMatrix().invert(inverseMatrix)) {
            childEvent.transform(inverseMatrix);
        }
        super.dispatchTouchEvent(childEvent);
        childEvent.recycle();
        return true;
    }

    @Override protected void dispatchDraw(@NonNull Canvas canvas) {
        int saveCount = canvas.save();
        canvas.concat(mAttacher.getDrawMatrix());
        super.dispatchDraw(canvas);
        canvas.restoreToCount(saveCount);
    }

    @Override protected void onAttachedToWindow() {
        init();
        super.onAttachedToWindow();
    }

    @Override protected void onDetachedFromWindow() {
        mAttacher.onDetachedFromWindow();
        super.onDetachedFromWindow();
    }

    @Override public float getMinimumScale() {
        return mAttacher.getMinimumScale();
    }

    @Override public float getMediumScale() {
        return mAttacher.getMediumScale();
    }

    @Override public float getMaximumScale() {
        return mAttacher.getMaximumScale();
    }

    @Override public void setMinimumScale(float minimumScale) {
        mAttacher.setMinimumScale(minimumScale);
    }

    @Override public void setMediumScale(float mediumScale) {
        mAttacher.setMediumScale(mediumScale);
    }

    @Override public void setMaximumScale(float maximumScale) {
        mAttacher.setMaximumScale(maximumScale);
    }

    @Override public float getScale() {
        return mAttacher.getScale();
    }

    @Override public void setScale(float scale) {
        mAttacher.setScale(scale);
    }

    @Override public void setScale(float scale, boolean animate) {
        mAttacher.setScale(scale, animate);
    }

    @Override public void setScale(float scale, float focalX, float focalY, boolean animate) {
        mAttacher.setScale(scale, focalX, focalY, animate);
    }

    @Override public void setOrientation(@Attacher.OrientationMode int orientation) {
        mAttacher.setOrientation(orientation);
    }

    @Override public void setZoomTransitionDuration(long duration) {
        mAttacher.setZoomTransitionDuration(duration);
    }

    @Override public void setAllowParentInterceptOnEdge(boolean allow) {
        mAttacher.setAllowParentInterceptOnEdge(allow);
    }

    @Override public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener listener) {
        mAttacher.setOnDoubleTapListener(listener);
    }

    @Override public void setOnScaleChangeListener(OnScaleChangeListener listener) {
        mAttacher.setOnScaleChangeListener(listener);
    }

    @Override public void setOnLongClickListener(OnLongClickListener listener) {
        mAttacher.setOnLongClickListener(listener);
    }

    @Override public void setOnPhotoTapListener(OnPhotoTapListener listener) {
        mAttacher.setOnPhotoTapListener(listener);
    }

    @Override public void setOnViewTapListener(OnViewTapListener listener) {
        mAttacher.setOnViewTapListener(listener);
    }

    @Override public OnPhotoTapListener getOnPhotoTapListener() {
        return mAttacher.getOnPhotoTapListener();
    }

    @Override public OnViewTapListener getOnViewTapListener() {
        return mAttacher.getOnViewTapListener();
    }
 
    @Override protected void onSizeChanged(
            int w,
            int h,
            int oldw,
            int oldh) {

        super.onSizeChanged(w, h, oldw, oldh);

        mAttacher.update(w, h);
    }
}
