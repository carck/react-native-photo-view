import React from 'react';
import { requireNativeComponent } from 'react-native';

/**
 * ScalingView - A container view with pinch-to-zoom, pan, and fling support
 * 
 * Use this component when you want to display arbitrary child views with zoom/pan gestures.
 * For displaying images with zoom support, use PhotoView instead.
 * 
 * Props:
 * - minimumZoomScale (number): Minimum zoom level (default: 0.5)
 * - maximumZoomScale (number): Maximum zoom level (default: 5.0)
 * - scale (number): Current zoom scale (default: 1.0)
 * - zoomTransitionDuration (number): Duration of zoom animations in ms (default: 300)
 * - onScaleChange (function): Called when zoom scale changes
 * - onDoubleTap (function): Called when double-tapped
 * 
 * Example:
 * ```jsx
 * <ScalingView
 *   minimumZoomScale={0.5}
 *   maximumZoomScale={4.0}
 *   onScaleChange={(scale) => console.log('Scale:', scale)}
 * >
 *   <Video
 *     source={{ uri: 'https://example.com/video.mp4' }}
 *     style={{ width: '100%', height: '100%' }}
 *   />
 * </ScalingView>
 * ```
 */
const ScalingView = React.forwardRef((props, ref) => {
  return <NativeScalingView ref={ref} {...props} />;
});

ScalingView.displayName = 'ScalingView';

const NativeScalingView = requireNativeComponent('ScalingView');

export default ScalingView;
