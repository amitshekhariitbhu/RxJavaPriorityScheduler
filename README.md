<p align="center">
<img alt="PRDownloader" src=https://raw.githubusercontent.com/amitshekhariitbhu/RxJavaPriorityScheduler/master/assets/rxps.png />
</p>

# RxPS - RxJavaPriorityScheduler - A RxJava Priority Scheduler library for Android and Java applications

### Overview of RxPS - RxJavaPriorityScheduler library
* RxPS is used to set the priority for the given task.
* Simple way to set the priority for the given task.

## [Outcome School Blog](https://outcomeschool.com/blog) - High-quality content to learn Android concepts.

## Using RxPS - RxJavaPriorityScheduler Library in your Android application

Add this in your `settings.gradle`:
```groovy
maven { url 'https://jitpack.io' }
```

If you are using `settings.gradle.kts`, add the following:
```kotlin
maven { setUrl("https://jitpack.io") }
```

Add this in your `build.gradle`
```groovy
implementation 'com.github.amitshekhariitbhu:RxJavaPriorityScheduler:1.0.0'
```

If you are using `build.gradle.kts`, add the following:
```kotlin
implementation("com.github.amitshekhariitbhu:RxJavaPriorityScheduler:1.0.0")
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

You can connect with me on:

- [Twitter](https://twitter.com/amitiitbhu)
- [LinkedIn](https://www.linkedin.com/in/amit-shekhar-iitbhu)
- [GitHub](https://github.com/amitshekhariitbhu)
- [Facebook](https://www.facebook.com/amit.shekhar.iitbhu)

[**Read all of our blogs here.**](https://outcomeschool.com/blog)
  
### License
```
    Copyright (C) 2022 Amit Shekhar

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
