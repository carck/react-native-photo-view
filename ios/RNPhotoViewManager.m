#import "RNPhotoViewManager.h"
#import "RNPhotoView.h"
#import <React/RCTBridgeModule.h>

@implementation RNPhotoViewManager

RCT_EXPORT_MODULE()

- (RNPhotoView *)view {
    return [[RNPhotoView alloc] init]; // Unified initialization for both architectures
}

RCT_REMAP_VIEW_PROPERTY(src, source, NSDictionary)
RCT_EXPORT_VIEW_PROPERTY(loadingIndicatorSrc, NSString)

RCT_REMAP_VIEW_PROPERTY(maximumZoomScale, maxZoomScale, CGFloat)
RCT_REMAP_VIEW_PROPERTY(minimumZoomScale, minZoomScale, CGFloat)

RCT_EXPORT_VIEW_PROPERTY(showsHorizontalScrollIndicator, BOOL)
RCT_EXPORT_VIEW_PROPERTY(showsVerticalScrollIndicator, BOOL)

RCT_EXPORT_VIEW_PROPERTY(onPhotoViewerError, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onPhotoViewerScale, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onPhotoViewerViewTap, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onPhotoViewerTap, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onPhotoViewerLoadStart, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onPhotoViewerLoad, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onPhotoViewerLoadEnd, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onPhotoViewerProgress, RCTDirectEventBlock);

RCT_EXPORT_VIEW_PROPERTY(androidScaleType, NSString)
RCT_EXPORT_VIEW_PROPERTY(fadeDuration, NSInteger)
RCT_EXPORT_VIEW_PROPERTY(shouldNotifyLoadEvents, BOOL)

@end
