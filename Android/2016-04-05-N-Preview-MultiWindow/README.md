# Android N Preview Multi-Window Example
[![License](https://img.shields.io/hexpm/l/plug.svg)]()

## Requirements
- Android Studio 2.1 Beta
- Target Sdk Version : N
- Min Sdk Version : N
- Gradle : gradle-2.12-all.zip
- build.gradle : classpath 'com.android.tools.build:gradle:2.1.0-beta1'
- buildToolsVersion "24.0.0 rc3"


## Preview 2 change
API Name change(Multi-Window API)
- inMultiWindow -> isInMultiWindowMode
- onMultiWindowChanged -> onMultiWindowModeChanged

AndroidManifest.xml change
- Remove API Name : android:minimalSize
- Add API Name : android:minimalWidth
                 android:minimalHeight

## Android Studio 2.1 Beta

![build_bug_as_2_1_beta_01.png](https://raw.githubusercontent.com/taehwandev/BlogExample/master/Android/2016-04-05-N-Preview-MultiWindow/images/build_bug_as_2_1_beta_01.png)

Cache clean.
![cache_clean.png](https://raw.githubusercontent.com/taehwandev/BlogExample/master/Android/2016-04-05-N-Preview-MultiWindow/images/cache_clean.png)


## API

- [Multi-Window Support](http://developer.android.com/preview/features/multi-window.html)
- [ButterKnife](http://jakewharton.github.io/butterknife/)


## API License

Copyright 2013 Jake Wharton

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.


## License

Copyright 2016 Taehwan

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
