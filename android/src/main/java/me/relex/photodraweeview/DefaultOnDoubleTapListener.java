package me.relex.photodraweeview;

import android.graphics.RectF;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class DefaultOnDoubleTapListener implements GestureDetector.OnDoubleTapListener {

    protected Attacher mAttacher;

    public DefaultOnDoubleTapListener(Attacher attacher) {
        setPhotoDraweeViewAttacher(attacher);
    }

    @Override public boolean onSingleTapConfirmed(MotionEvent e) {

        if (mAttacher == null) {
            return false;
        }
        View view = mAttacher.getView();
        if (view == null) {
            return false;
        }

        if (mAttacher.getOnPhotoTapListener() != null) {
            final RectF displayRect = mAttacher.getDisplayRect();

            if (null != displayRect) {
                final float x = e.getX(), y = e.getY();
                if (displayRect.contains(x, y)) {
                    float xResult = (x - displayRect.left) / displayRect.width();
                    float yResult = (y - displayRect.top) / displayRect.height();
                    mAttacher.getOnPhotoTapListener().onPhotoTap(view, xResult, yResult);
                    return true;
                }
            }
        }

        if (mAttacher.getOnViewTapListener() != null) {
            mAttacher.getOnViewTapListener().onViewTap(view, e.getX(), e.getY());
            return true;
        }

        return false;
    }

    @Override public boolean onDoubleTap(MotionEvent event) {
        if (mAttacher == null) {
            return false;
        }

        try {
            float scale = mAttacher.getScale();
            float x = event.getX();
            float y = event.getY();

            // min, mid, max
            if (scale < mAttacher.getMediumScale()) {
                mAttacher.setScale(mAttacher.getMediumScale(), x, y, true);
            } else if (scale >= mAttacher.getMediumScale() && scale < mAttacher.getMaximumScale()) {
                mAttacher.setScale(mAttacher.getMaximumScale(), x, y, true);
            } else {
                mAttacher.setScale(mAttacher.getMinimumScale(), x, y, true);
            }
        } catch (Exception e) {
            // Can sometimes happen when getX() and getY() is called
        }
        return true;
    }

    @Override public boolean onDoubleTapEvent(MotionEvent event) {
        return false;
    }

    public void setPhotoDraweeViewAttacher(Attacher attacher) {
        mAttacher = attacher;
    }
}
