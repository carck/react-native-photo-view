package com.reactnative.photoview;

import android.content.Context;
import android.graphics.Matrix;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import android.widget.ImageView;

import me.relex.photodraweeview.Attacher;
import me.relex.photodraweeview.IAttacher;
import me.relex.photodraweeview.OnPhotoTapListener;
import me.relex.photodraweeview.OnScaleChangeListener;
import me.relex.photodraweeview.OnViewTapListener;

public class ScalingView extends FrameLayout implements IAttacher {

    private Attacher mAttacher;
    private TextureView mTextureView;
    private ImageView mImageView;

    public ScalingView(Context context) {
        super(context);
        init();
        setMinimumScale(0.5f);
        setMaximumScale(5.0f);
    }

    protected void init() {
        if (mAttacher == null || mAttacher.getView() == null) {
            mAttacher = new Attacher(this);
            mAttacher.setOnMatrixChangeListener(matrix -> {
                applyVideoMatrix();
            });
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

    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        applyVideoMatrix();
    }

    @Override
    public void onViewRemoved(View child) {
        super.onViewRemoved(child);
        applyVideoMatrix();
    }

    @Override
    protected void onLayout(
            boolean changed,
            int left,
            int top,
            int right,
            int bottom) {

        super.onLayout(changed, left, top, right, bottom);

        applyVideoMatrix();
    }

    private TextureView findTextureView(View view) {
        if (view instanceof TextureView) {
            return (TextureView) view;
        }

        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;

            for (int i = 0; i < group.getChildCount(); i++) {
                TextureView result = findTextureView(group.getChildAt(i));
                if (result != null) {
                    return result;
                }
            }
        }

        return null;
    }

    private ImageView findImageView(View view) {
        if (view instanceof ImageView) {
            return (ImageView) view;
        }

        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;

            for (int i = 0; i < group.getChildCount(); i++) {
                ImageView result = findImageView(group.getChildAt(i));
                if (result != null) {
                    return result;
                }
            }
        }

        return null;
    }

    private void applyVideoMatrix() {
        if (mTextureView == null || !mTextureView.isAttachedToWindow()) {
            mTextureView = findTextureView(this);
        }

        if (mImageView == null || !mImageView.isAttachedToWindow()) {
            mImageView = findImageView(this);
        }

        if (mTextureView != null) {
            mTextureView.setTransform(mAttacher.getDrawMatrix());
        }

        if (mImageView != null) {
            mImageView.setScaleType(ImageView.ScaleType.MATRIX);
            mImageView.setImageMatrix(mAttacher.getDrawMatrix());
        }
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

    @Override public void update(int w, int h){

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
