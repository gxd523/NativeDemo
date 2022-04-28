# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.
# 设置生成Native库所需的CMake的最低版本
cmake_minimum_required(VERSION 3.18.1)

# Declares and names the project.

project("natived")

set(SO_FILE_NAME test-lib)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
        # so文件名称libxxx.so，要和下面的target_link_libraries的第一个参数保持一致
        ${SO_FILE_NAME}

        # Sets the library as a shared library.
        # SHARED打出的包为so文件, STATIC打出的包为a文件
        SHARED

        # Provides a relative path to your source file(s).
        # 指定C++文件的相对路径
        native-lib.cpp)

# 可以定义多个库，即打出多个so
#add_library(
#        native-lib1
#        SHARED
#        a.cpp
#)

# 指定头文件路径
include_directories(src/main/cpp/include/)

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

# 关联通过find_library()将log的路径转化为的变量log-lib，使之能够调用log库
target_link_libraries(
        # Specifies the target library.
        ${SO_FILE_NAME}

        # Links the target library to the log library included in the NDK.
        ${log-lib})

# 设置so的输出路径
#set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/../jniLibs/${CMAKE_ANDROID_ARCH_ABI})
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI})