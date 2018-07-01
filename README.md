<p align="center">
<img alt="PRDownloader" src=https://raw.githubusercontent.com/MindorksOpenSource/RxJavaPriorityScheduler/master/assets/rxps.png />
</p>

# RxPS - RxJavaPriorityScheduler - A RxJava Priority Scheduler library for Android and Java applications
[![Mindorks](https://img.shields.io/badge/mindorks-opensource-blue.svg)](https://mindorks.com/open-source-projects)
[![Mindorks Community](https://img.shields.io/badge/join-community-blue.svg)](https://mindorks.com/join-community)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

### Overview of RxPS - RxJavaPriorityScheduler library
* RxPS is used to set the priority for the given task.
* Simple way to set the priority for the given task.

## Using RxPS - RxJavaPriorityScheduler Library in your Android application

Add this in your build.gradle
```groovy
implementation 'com.mindorks.scheduler:rxps:0.1.0'
```

### Setting low level priority for a task - use `RxPS.low()`
```java
getObservable()
.subscribeOn(RxPS.low())
.subscribe(getObserver());

// or

getObservable()
.subscribeOn(RxPS.get(Priority.LOW))
.subscribe(getObserver());
```

### Setting medium level priority for a task - use `RxPS.medium()`
```java
getObservable()
.subscribeOn(RxPS.medium())
.subscribe(getObserver());

// or

getObservable()
.subscribeOn(RxPS.get(Priority.MEDIUM))
.subscribe(getObserver());
```

### Setting high level priority for a task - use `RxPS.high()`
```java
getObservable()
.subscribeOn(RxPS.high())
.subscribe(getObserver());

// or

getObservable()
.subscribeOn(RxPS.get(Priority.HIGH))
.subscribe(getObserver());
```

### Setting immediate level priority for a task - use `RxPS.immediate()`
```java
getObservable()
.subscribeOn(RxPS.immediate())
.subscribe(getObserver());

// or

getObservable()
.subscribeOn(RxPS.get(Priority.IMMEDIATE))
.subscribe(getObserver());
```

### TODO
* Documentation
* Test Cases
* Customizations

## If this library helps you in anyway, show your love :heart: by putting a :star: on this project :v:

[Check out Mindorks awesome open source projects here](https://mindorks.com/open-source-projects)
  
### License
```
    Copyright (C) 2018 MINDORKS NEXTGEN PRIVATE LIMITED

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

### Contributing to RxPS - RxJavaPriorityScheduler
All pull requests are welcome, make sure to follow the [contribution guidelines](CONTRIBUTING.md)
when you submit pull request.