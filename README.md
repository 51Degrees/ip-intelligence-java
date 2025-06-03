# 51Degrees IP Intelligence Engines

![51Degrees](https://51degrees.com/img/logo.png?utm_source=github&utm_medium=repository&utm_content=readme_main&utm_campaign=java-open-source "Data rewards the curious") **Java IP Intelligence**

[Developer Documentation](https://51degrees.com/ip-intelligence-java/index.html?utm_source=github&utm_medium=repository&utm_content=documentation&utm_campaign=java-open-source "developer documentation")

## Introduction

This repository contains Java implementation of the IP Intelligence [specification](https://github.com/51Degrees/specifications/blob/main/ip-intelligence-specification/README.md).

## Dependencies

For runtime dependencies, see our [dependencies](http://51degrees.com/documentation/_info__dependencies.html) page.
The [tested versions](https://51degrees.com/documentation/_info__tested_versions.html) page shows 
the JDK versions that we currently test against. The software may run fine against other versions, 
but additional caution should be applied.

### Data

The Java API can either use our cloud service to get its data or it can use a local (on-premise) copy of the data.

#### Cloud (coming soon)

The cloud service for IP Intelligence is currently in development.

You will require [resource keys](https://51degrees.com/documentation/_info__resource_keys.html)
to use the Cloud API, as described on our website. Get resource keys from
our [configurator](https://configure.51degrees.com/), see our [documentation](https://51degrees.com/documentation/_concepts__configurator.html) on 
how to use this.

#### On-Premise

In order to perform IP intelligence on-premise, you will need to use a
data file.

[ip-intelligence-data](https://github.com/51Degrees/ip-intelligence-data/) repository instructs how to obtain a 'Lite' data file, otherwise [contact us](https://51degrees.com/contact-us) to obtain an 'Enterprise' data file.

## Installation

Our latest release is available as compiled JARs on Maven - or you can compile from source as described below.

### Maven

The 51Degrees Java IP Intelligence package is available on maven. Make sure to select
the [latest version](https://mvnrepository.com/artifact/com.51degrees/ip-intelligence).

```xml
<!-- Make sure to select the latest version from https://mvnrepository.com/artifact/com.51degrees/pipeline.ip-intelligence -->
<dependency>
    <groupId>com.51degrees</groupId>
    <artifactId>ip-intelligence</artifactId>
    <version>4.4.19</version>
</dependency>
```

This package includes the Cloud and on-premise APIs.

### Build and Install from source

IP Intelligence on-premise uses a native binary. (i.e. compiled from C code to target a specific 
platform/architecture) This section explains how to build this binary.

#### Pre-requisites

- Install C build tools:
  - Windows:
    - You will need either Visual Studio 2019 or the [C++ Build Tools](https://visualstudio.microsoft.com/visual-cpp-build-tools/) installed.
      - Minimum platform toolset version is `v142`
      - Minimum Windows SDK version is `10.0.18362.0`
    - Set the CMake command path in the PATH environment variable: 
      - `set PATH="[Visual Studio Installation Path]\[Visual Studio Version]\BuildTools\Common7\IDE\CommonExtensions\Microsoft\CMake\CMake\bin\";%PATH%`

  - Linux:
    - Debian / Ubuntu: `sudo apt-get install g++ make libatomic1 cmake`
    - RHEL / CentOS / Fedora: `sudo yum install cmake gcc-c++ libatomic`

- Maven version 3.8.4 or higher is recommended, and what is used for our own build.
- If you have not already done so, pull the git submodules that contain the native code:
  - `git submodule update --init --recursive`

#### Build steps

Batch script and Bash script are provided to support building native binaries on Windows and Linux/macOS.
These scripts are implicitly called by the Maven build step.

```
mvn clean install
```

On Windows, the Platform Toolset version and Windows 10 SDK version can be overwritten when 
running `mvn` by adding following options:
- `-DplatformToolsetVersion=[ Platform Toolset Version ]`
- `-DwindowsSDKVersion=[ Windows 10 SDK Version ]`

This is not recommended unless absolutely necessary and should be used with caution.

## Tests

You will need [resource keys](https://51degrees.com/documentation/_info__resource_keys.html)
(see above) to complete the tests and run examples which include exercising the cloud API.

To verify the code:

```
mvn clean test -DTestResourceKey=[Resource Key]
```
For tests and examples that require a license key add the following option:
- `-DLicenseKey=[License Key]`

## Projects

- **ip-intelligence** - Main package providing all IP Intelligence capabilities including both cloud and on-premise engines.
- **ip-intelligence.engine.on-premise** - On-premise detection engine using local data files for IP intelligence analysis.
- **ip-intelligence.cloud** - Cloud-based detection service for IP intelligence (coming soon).
- **ip-intelligence.shared** - Shared classes and utilities used across all IP intelligence engines.
