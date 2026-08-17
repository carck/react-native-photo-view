package com.reactnative.photoview;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

public class ScalingViewManager extends ViewGroupManager<ScalingView> {
    private static final String REACT_CLASS = "ScalingView";

    ScalingViewManager(ReactApplicationContext context) {
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected ScalingView createViewInstance(ThemedReactContext reactContext) {
        return new ScalingView(reactContext);
    }

    @ReactProp(name = "minimumZoomScale")
    public void setMinimumZoomScale(ScalingView view, float scale) {
        view.setMinimumScale(scale);
    }

    @ReactProp(name = "maximumZoomScale")
    public void setMaximumZoomScale(ScalingView view, float scale) {
        view.setMaximumScale(scale);
    }

    @ReactProp(name = "scale")
    public void setScale(ScalingView view, float scale) {
        view.setScale(scale, true);
    }

    @ReactProp(name = "androidZoomTransitionDuration")
    public void setScale(ScalingView view, int durationMs) {
        view.setZoomTransitionDuration(durationMs);
    }
}
