import type { HostComponent, ViewProps } from 'react-native';
import type { DirectEventHandler, Float, Int32 } from 'react-native/Libraries/Types/CodegenTypes';
import codegenNativeComponent from 'react-native/Libraries/Utilities/codegenNativeComponent';

type Source = Readonly<{
  uri: string;
  headers?: Array<Readonly<{ key: string; value: string }>>;
}>;

type ProgressEvent = Readonly<{
  loaded: Int32;
  total: Int32;
}>;

type TapEvent = Readonly<{
  x: Int32;
  y: Int32;
}>;

type ScaleEvent = Readonly<{
  scale: Float;
}>;

type LoadEvent = Readonly<{

}>;

export interface NativeProps extends ViewProps {
  source?: Source;
  loadingIndicatorSrc?: string;
  fadeDuration?: WithDefault<Int32, -1>; //android only
  minimumZoomScale?: WithDefault<Float, 1.0>;
  maximumZoomScale?: WithDefault<Float, 3.0>;
  scale?: WithDefault<Float, 1.0>;
  androidScaleType?: WithDefault<
    'center' | 'centerCrop' | 'centerInside' | 'fitCenter' | 'fitStart' | 'fitEnd' | 'fitXY',
    'center'>; // android only
  androidZoomTransitionDuration?: WithDefault<Int32, 200>; //android only
  onLoadStart?: DirectEventHandler<LoadEvent> | null;
  onLoad?: DirectEventHandler<LoadEvent> | null;
  onError?: DirectEventHandler<LoadEvent> | null;
  onLoadEnd?: DirectEventHandler<LoadEvent> | null;
  onTap?: DirectEventHandler<TapEvent> | null;
  onViewTap?: DirectEventHandler<TapEvent> | null;
  onScale?: DirectEventHandler<ScaleEvent> | null;
  showsHorizontalScrollIndicator?: boolean; //ios only
  showsVerticalScrollIndicator?: boolean; //ios only
  shouldNotifyLoadEvents?: boolean; // android only
  onProgress?: DirectEventHandler<ProgressEvent> | null; //ios only
};

export default codegenNativeComponent<NativeProps>('RNPhotoView') as HostComponent<NativeProps>;
