#ifdef RCT_NEW_ARCH_ENABLED

#import <React/RCTConversions.h>
#import <React/RCTFabricComponentsPlugins.h>
#import <react/renderer/components/RNPhotoViewSpec/ComponentDescriptors.h>
#import <react/renderer/components/RNPhotoViewSpec/Props.h>
#import <react/renderer/components/RNPhotoViewSpec/EventEmitters.h>
#import "RNPhotoView.h"
#import "RNPhotoViewComponentView.h"

using namespace facebook::react;

@implementation RNPhotoViewComponentView

- (instancetype)initWithFrame:(CGRect)frame {
    if (self = [super initWithFrame:frame]) {
        // Initialize RNPhotoView as the content view
        self.contentView = [[RNPhotoView alloc] initWithFrame:self.bounds];
    }
    return self;
}

- (void)layoutSubviews {
    [super layoutSubviews];
    // Ensure the contentView fills the entire component view
    self.contentView.frame = self.bounds;
}

static const Props::Shared &sharedDefaultProps()
{
    static auto defaultProps = std::make_shared<RNPhotoViewProps>();
    return defaultProps;
}

- (void)updateProps:(Props::Shared const &)props
           oldProps:(Props::Shared const &)oldProps {
    [super updateProps:props oldProps:oldProps];

    // Update RNPhotoView properties based on the new props
    auto newProps = std::static_pointer_cast<RNPhotoViewProps const>(props);
    auto oldViewProps = oldProps
        ? std::static_pointer_cast<RNPhotoViewProps const>(oldProps)
        : std::static_pointer_cast<RNPhotoViewProps const>(sharedDefaultProps());;

    RNPhotoView *photoView = (RNPhotoView *)self.contentView;

    // Update source if it has changed
    if (newProps->source.uri != oldViewProps->source.uri) {
        NSMutableDictionary *source = [NSMutableDictionary dictionary];

        // Add uri to source if present
        NSString *uri = RCTNSStringFromStringNilIfEmpty(newProps->source.uri);
        if (uri) {
            source[@"uri"] = uri;
        }

        // Add headers to source if present
        if (!newProps->source.headers.empty()) {
            NSMutableDictionary *headers = [NSMutableDictionary dictionary];
            for (const auto &header : newProps->source.headers) {
                [headers setValue:RCTNSStringFromString(header.value) forKey:RCTNSStringFromString(header.key)];
            }
            source[@"headers"] = headers;
        }

        [photoView setSource:source];
    }

    // Update loadingIndicatorSrc if it has changed
    if (newProps->loadingIndicatorSrc != oldViewProps->loadingIndicatorSrc) {
        NSString *loadingIndicatorSrc = RCTNSStringFromStringNilIfEmpty(newProps->loadingIndicatorSrc);
        [photoView setLoadingIndicatorSrc:loadingIndicatorSrc];
    }

    // Update minimumZoomScale if it has changed
    if (newProps->minimumZoomScale != oldViewProps->minimumZoomScale) {
        [photoView setMinimumZoomScale:newProps->minimumZoomScale];
    }

    // Update maximumZoomScale if it has changed
    if (newProps->maximumZoomScale != oldViewProps->maximumZoomScale) {
        [photoView setMaximumZoomScale:newProps->maximumZoomScale];
    }

    // Update scale if it has changed
    if (newProps->scale != oldViewProps->scale) {
        [photoView setScale:newProps->scale];
    }

    // Update showsHorizontalScrollIndicator if it has changed
    if (newProps->showsHorizontalScrollIndicator != oldViewProps->showsHorizontalScrollIndicator) {
        [photoView setShowsHorizontalScrollIndicator:newProps->showsHorizontalScrollIndicator];
    }

    // Update showsVerticalScrollIndicator if it has changed
    if (newProps->showsVerticalScrollIndicator != oldViewProps->showsVerticalScrollIndicator) {
        [photoView setShowsVerticalScrollIndicator:newProps->showsVerticalScrollIndicator];
    }
}

// Add prepareForRecycle to reset the content view
- (void)prepareForRecycle {
    [super prepareForRecycle];
    // Create a new RNPhotoView instance and assign it to contentView
    self.contentView = [[RNPhotoView alloc] initWithFrame:self.bounds];
}

// Add the componentDescriptorProvider class function
+ (ComponentDescriptorProvider)componentDescriptorProvider {
    return concreteComponentDescriptorProvider<RNPhotoViewComponentDescriptor>();
}

@end

Class<RCTComponentViewProtocol> RNPhotoViewCls(void)
{
    return RNPhotoViewComponentView.class;
}

#endif