# Summary
[thdev.tech](http://thdev.tech) Blog의 샘플 코드입니다

Android BottomNavigation 샘플입니다.

- [블로그 링크: Android BottomNavigationView 사용하기](http://thdev.tech/androiddev/2016/12/16/Android-BottomNavigationView-Intro.html)

## Use

- [Open API 서비스 - 영화관 입장관리 통합전산망](http://www.kobis.or.kr/kobisopenapi/homepg/main/main.do)의 일별 박스 오피스와 주간 박스오피스 2가지 API를 사용하였습니다.

해당 사이트에 접근하셔서 API 키를 발급받아

`local.properties`에 다음과 같이 코드를 추가해주시면 되겠습니다.

```
kobis_api_key="발급받은 API Key"
```



## Preview

- BottomSheet Sample

![bottomNavigation]


## Build info

- Android studio 2.2.3

- buildToolsVersion = 25.0.1
- compile sdk version = 25
- Target sdk version = 25
- Min sdk version = 16
- gradle-wrapper.properties = 2.14.1
- com.android.tools.build:gradle : 2.2.3


## Library version info

- Dependencies
    - [Android support library version](https://developer.android.com/topic/libraries/support-library/revisions.html) : 25.0.1

    - [ButterKnife](http://jakewharton.github.io/butterknife/) : 8.4.0

    - [kotlin](https://kotlinlang.org/docs/tutorials/kotlin-android.html) : 1.0.5-2
    - [th-base](https://github.com/taehwandev/AndroidBase) : 1.0.4

## API

- [BottomNavigationView](https://developer.android.com/reference/android/support/design/widget/BottomNavigationView.html)
- [Exploring the Android Design Support Library: Bottom Navigation View](https://medium.com/@hitherejoe/exploring-the-android-design-support-library-bottom-navigation-drawer-548de699e8e0#.edvptzkb0)
- [Material Bottom navigation](https://material.google.com/components/bottom-navigation.html)


## License

```
Copyright 2016 Tae-hwan

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

[bottomNavigation]: images/bottomNavigation.gif