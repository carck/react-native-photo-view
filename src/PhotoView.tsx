import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { requireNativeComponent } from 'react-native';
import ViewPropTypes from 'react-native/Libraries/Components/View/ViewPropTypes';

const resolveAssetSource = require('react-native/Libraries/Image/resolveAssetSource');

const isFabricEnabled = (global as any)?.nativeFabricUIManager != null
const RNPhotoView = isFabricEnabled
    ? require('./PhotoViewNativeComponent').default
    : requireNativeComponent('RNPhotoView');

export default class PhotoView extends Component {
    static propTypes = {
        source: PropTypes.oneOfType([
            PropTypes.shape({
                uri: PropTypes.string
            }),
            // Opaque type returned by require('./image.jpg')
            PropTypes.number
        ]),
        loadingIndicatorSource: PropTypes.oneOfType([
            PropTypes.shape({
                uri: PropTypes.string
            }),
            // Opaque type returned by require('./image.jpg')
            PropTypes.number
        ]),
        fadeDuration: PropTypes.number,
        minimumZoomScale: PropTypes.number,
        maximumZoomScale: PropTypes.number,
        scale: PropTypes.number,
        androidZoomTransitionDuration: PropTypes.number,
        androidScaleType: PropTypes.oneOf(["center", "centerCrop", "centerInside", "fitCenter", "fitStart", "fitEnd", "fitXY", "matrix"]),
        onLoadStart: PropTypes.func,
        onError: PropTypes.func,
        onLoad: PropTypes.func,
        onLoadEnd: PropTypes.func,
        onTap: PropTypes.func,
        onViewTap: PropTypes.func,
        onScale: PropTypes.func,
        onProgress: PropTypes.func,
        showsHorizontalScrollIndicator: PropTypes.bool,
        showsVerticalScrollIndicator: PropTypes.bool,
        ...ViewPropTypes
    };

    render() {
        const source = resolveAssetSource(this.props.source);
        var loadingIndicatorSource = resolveAssetSource(this.props.loadingIndicatorSource);

        if (source && source.uri === '') {
            console.warn('source.uri should not be an empty string');
        }

        if (source && source.uri) {
            var { onLoadStart, onLoad, onLoadEnd, onError } = this.props;

            var nativeProps = {
                ...this.props,
                shouldNotifyLoadEvents: !!(onLoadStart || onLoad || onLoadEnd || onError),
                src: source,
                loadingIndicatorSrc: loadingIndicatorSource ? loadingIndicatorSource.uri : null,
            };

            return <RNPhotoView {...nativeProps} />
        }
        return null
    }
}

