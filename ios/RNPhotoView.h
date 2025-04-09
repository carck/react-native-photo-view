#import <UIKit/UIKit.h>
#import <React/RCTComponent.h>
#import "MWTapDetectingImageView.h"
#import "MWTapDetectingView.h"

@interface RNPhotoView : UIScrollView <MWTapDetectingImageViewDelegate, MWTapDetectingViewDelegate>

@property (nonatomic, strong) NSDictionary *source;
@property (nonatomic, strong) NSString *loadingIndicatorSrc;
@property (nonatomic, assign) CGFloat minZoomScale;
@property (nonatomic, assign) CGFloat maxZoomScale;
@property (nonatomic, assign) NSInteger scale;
@property (nonatomic, strong) NSString *androidScaleType;
@property (nonatomic, assign) NSInteger fadeDuration;
@property (nonatomic, assign) BOOL shouldNotifyLoadEvents;

@property (nonatomic, copy) RCTDirectEventBlock onPhotoViewerError;
@property (nonatomic, copy) RCTDirectEventBlock onPhotoViewerScale;
@property (nonatomic, copy) RCTBubblingEventBlock onPhotoViewerViewTap;
@property (nonatomic, copy) RCTBubblingEventBlock onPhotoViewerTap;
@property (nonatomic, copy) RCTDirectEventBlock onPhotoViewerLoadStart;
@property (nonatomic, copy) RCTDirectEventBlock onPhotoViewerLoad;
@property (nonatomic, copy) RCTDirectEventBlock onPhotoViewerLoadEnd;
@property (nonatomic, copy) RCTDirectEventBlock onPhotoViewerProgress;

@end
