import React, { Component } from 'react';
import {
  FlatList,
  StyleSheet,
  View,
  Dimensions,
  Image,
} from 'react-native';
import PhotoView,{ScalingView} from 'react-native-photo-view';

const { width, height } = Dimensions.get('window');

export default class App extends Component {
  constructor(props) {
    super(props);

    this.state = {
      images: [
        'https://reactnative.dev/img/homepage/dissection.png',
        'https://reactnative.dev/img/homepage/dissection.png',
        'https://reactnative.dev/img/homepage/dissection.png',
      ],
    };
  }

  renderItem = ({ item, index }) => {
    return (
      <View style={styles.item}>
        <ScalingView
          source={{}}
          onLoad={() => console.log(`Image ${index + 1} loaded`)}
          onTap={() => console.log(`Image ${index + 1} tapped`)}
          minimumZoomScale={1}
          maximumZoomScale={3}
          androidScaleType="center"
        >
          <Image style={styles.photo} source={{ uri: item }}></Image>
        </ScalingView>
      </View>
    );
  };

  render() {
    const { images } = this.state;

    return (
      <View style={styles.container}>
        <FlatList
          data={images}
          renderItem={this.renderItem}
          keyExtractor={(item, index) => `${index}-${item}`}
          horizontal
          pagingEnabled
          showsHorizontalScrollIndicator={false}
          decelerationRate="fast"
        />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#000',
  },

  item: {
    width: width,
    height: height,
    justifyContent: 'center',
    alignItems: 'center',
  },

  photo: {
    width: width,
    height: height,
    backgroundColor: '#000',
  },
});