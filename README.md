# react-native-photo-view

Provides custom Image view for React Native that allows to perform
pinch-to-zoom on images. Works on both iOS and Android.

This component uses [PhotoDraweeView](https://github.com/ongakuer/PhotoDraweeView) for Android and [MWPhotobrowser](https://github.com/mwaterfall/MWPhotoBrowser) on iOS.

## Usage

```javascript
import PhotoView from 'react-native-photo-view';
```

### PhotoView - For Displaying Images

Use `PhotoView` when you want to display images with zoom/pan support:

```javascript
<PhotoView
  source={{uri: 'https://facebook.github.io/react/img/logo_og.png'}}
  minimumZoomScale={0.5}
  maximumZoomScale={3}
  androidScaleType="center"
  onLoad={() => console.log("Image loaded!")}
  style={{width: 300, height: 300}} />
```

### ScalingView - For Displaying Child Views with Zoom/Pan

Use `ScalingView` when you want to display arbitrary child views (like video players) with zoom/pan/fling gesture support. This is a container specifically designed for children, with optimized gesture handling.

```javascript
import { ScalingView } from 'react-native-photo-view';
import { Video } from 'react-native-video';

<ScalingView
  minimumZoomScale={0.5}
  maximumZoomScale={4.0}
  onScaleChange={(scale) => console.log('Scale:', scale)}
  style={{width: 300, height: 300}}>
  <Video
    source={{uri: 'https://example.com/video.mp4'}}
    style={{width: '100%', height: '100%'}}
    controls
  />
</ScalingView>
```

ScalingView features:
- **Double-tap zoom**: Tap twice to zoom in/out
- **Pinch-to-zoom**: Standard pinch gesture for smooth zooming
- **Pan/drag**: Click and drag to move around when zoomed
- **Fling/momentum**: Swipe to continue scrolling with momentum
- **No image rendering**: Pure container for child gestures (no image display)
- **Configurable zoom ranges**: Set min/max scale levels

### Mixed Content - Image with Overlay

To display an image with overlay text or other views, use PhotoView with children:

```javascript
<PhotoView
  source={{uri: 'https://facebook.github.io/react/img/logo_og.png'}}
  minimumZoomScale={0.5}
  maximumZoomScale={3}
  style={{width: 300, height: 300}}>
  <Text style={{fontSize: 16, marginBottom: 10, color: 'white'}}>
    This text appears over the image
  </Text>
</PhotoView>
```

## Architecture

### PhotoView
- **Purpose**: Display images with pinch-to-zoom
- **Base**: Extends PhotoDraweeView (Android) or uses MWPhotobrowser (iOS)
- **Gestures**: Built-in image zoom/pan handling
- **Children**: Optional overlay (overlays appear on top of image, not affected by zoom gestures)
- **Android Implementation**: Image-only, no gesture handling for children

### ScalingView
- **Purpose**: Display arbitrary child views with zoom/pan gestures
- **Base**: FrameLayout (Android) or UIScrollView wrapper (iOS)
- **Gestures**: Pinch, double-tap, pan, fling - all applied to children
- **Children**: Required - the view you want to zoom/pan
- **Android Implementation**: Uses Attacher pattern with ScaleDragDetector
- **iOS Implementation**: Uses UIScrollView with zoom/pan protocol

**Key Difference**: 
- `PhotoView` = Image display with optional overlay
- `ScalingView` = Child view container with gesture support

## Properties

### PhotoView Properties

| Property | Type | Description |
|-----------------|----------|--------------------------------------------------------------|
| source | Object | same as source for other React images |
| loadingIndicatorSource | Object | source for loading indicator |
| fadeDuration | int | duration of image fade (in ms) |
| minimumZoomScale | float | The minimum allowed zoom scale. The default value is 1.0 |
| maximumZoomScale | float | The maximum allowed zoom scale. The default value is 3.0 |
| showsHorizontalScrollIndicator | bool | **iOS only**: When true, shows a horizontal scroll indicator. The default value is true. |
| showsVerticalScrollIndicator | bool | **iOS only**: When true, shows a vertical scroll indicator. The default value is true. |
| scale | float | Set zoom scale programmatically |
| androidZoomTransitionDuration | int | **Android only**: Double-tap zoom transition duration |
| androidScaleType | String | **Android only**: One of the default *Android* scale types: "center", "centerCrop", "centerInside", "fitCenter", "fitStart", "fitEnd", "fitXY" |
| onLoadStart | func | Callback function |
| onLoad | func | Callback function |
| onLoadEnd | func | Callback function |
| onProgress | func | **iOS only**: Callback function, invoked on download progress with {nativeEvent: {loaded, total}}. |
| onTap | func | Callback function (called on image tap) |
| onViewTap | func | Callback function (called on tap outside of image) |
| onScale | func | Callback function |

### ScalingView Properties

| Property | Type | Description |
|-----------------|----------|--------------------------------------------------------------|
| minimumZoomScale | float | The minimum allowed zoom scale. The default value is 0.5 |
| maximumZoomScale | float | The maximum allowed zoom scale. The default value is 5.0 |
| scale | float | Set zoom scale programmatically |
| zoomTransitionDuration | int | Duration of zoom animations in milliseconds (default: 300) |
| onScaleChange | func | Callback function, invoked when zoom scale changes |
| onDoubleTap | func | Callback function, invoked when double-tapped |

## Compared to [react-native-image-zoom](https://github.com/Anthonyzou/react-native-image-zoom)

react-native-image-zoom functionality is similar, but there are several major differencies:

* PhotoView is based on PhotoDraweeView which is the "PhotoView For Fresco". It works better, it supports several
important callbacks out-of-box and it is, actually, recommended by Chris Banes, because his
[PhotoView](https://github.com/chrisbanes/PhotoView) (base for react-native-image-zoom) doesn't completely
support Facebook Fresco;
* PhotoView has more options like fadeDuration and minimumZoomScale/maximumZoomScale and more important callbacks;
* PhotoView is written in the same manner as default React Image, and it supports most of the
features Image has (the goal is to be fully compaitable with Image and support absolutely everything);
* It is possible to use PhotoView as a container - children can be displayed on top of the image and will be affected by zoom and pan gestures!
* ScalingView is a dedicated component for child views with zoom/pan support, providing optimal gesture handling

## Automatic installation

Just two simple steps:

```console
npm install --save react-native-photo-view
```

```console
react-native link react-native-photo-view
```

## Manual installation


### Android
1. Add these lines to `android/settings.gradle`
```
include ':react-native-photo-view'
project(':react-native-photo-view').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-photo-view/android')
```

2. Add one more dependency to `android/app/build.gradle`
```
dependencies {
    compile project(':react-native-photo-view')
}
```

3. Add it to your `MainActivity.java` for RN < 0.29 and to your `MainApplication.java` for RN >=0.29

To register `PhotoViewPackage`, you need to change the `MainActivity` or `MainApplication` depending on React Native version of your app:
```java
import com.reactnative.photoview.PhotoViewPackage;

// ...

public class MainActivity extends ReactActivity {
    // ...

    @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
          new MainReactPackage(),
          new PhotoViewPackage() // add this manager
      );
    }

    // ...
}
```

### IOS
1. Add this line to your podfile
```
  pod 'react-native-photo-view', path: './node_modules/react-native-photo-view'
```

2. Run `pod install`
