cmake_minimum_required(VERSION 3.0...3.10 FATAL_ERROR)
set (CMAKE_C_STANDARD 11)
set (CMAKE_CXX_STANDARD 11)

# Include the C API
project(IPIntelligenceOnPremiseEngine VERSION 4.3.0 LANGUAGES CXX C)

if (MSVC)
    add_compile_options(/wd5105 /wd4100)
else()
    add_compile_options(-fPIC)
#    if (NOT Apple)
#    endif()
endif()

if (MSVC)
    if (CMAKE_GENERATOR_PLATFORM)
        set (GEN_PLATFORM ${CMAKE_GENERATOR_PLATFORM})
    else()
        set (GEN_PLATFORM ${CMAKE_VS_PLATFORM_NAME_DEFAULT})
    endif()

    set(ARCH x64)

else()
    if (DEFINED ARCH)
        # Use the defined ARCH
    elseif (${CMAKE_SYSTEM_PROCESSOR} MATCHES ^aarch64)
        set(ARCH aarch64)
    elseif (${CMAKE_SYSTEM_PROCESSOR} MATCHES ^arm64)
        set(ARCH aarch64)
    elseif (${CMAKE_SYSTEM_PROCESSOR} MATCHES .*64.*)
            set (ARCH x64)
    else()
        set (ARCH x64)
    endif()
endif()
message(STATUS "Arch name: ${ARCH}")

if (MSVC)
    set (OS windows)
elseif (UNIX AND NOT APPLE)
    set (OS linux)
elseif (APPLE)
    set (OS mac)
endif()
message(STATUS "OS name: ${OS}")

include(${CMAKE_CURRENT_LIST_DIR}/ip-intelligence-cxx/CMakeLists.txt NO_POLICY_SCOPE)

set(SRCOUT ${CMAKE_CURRENT_LIST_DIR}/../java/fiftyone/ipintelligence/hash/engine/onpremise)
set(RES ${CMAKE_CURRENT_LIST_DIR}/../../../target/classes)


option(RebuildSwig "RebuildSwig" OFF)

if (RebuildSwig)
    find_package(SWIG 4.0 COMPONENTS java)
    if (NOT SWIG_FOUND)
        message(WARNING "SWIG executable cannot be found so will not be run")
    elseif (NOT SWIG_java_FOUND)
        message(WARNING "SWIG does not include Java support so will not be run")
    else()
        message(STATUS "Rebuilding SWIG interfaces")
        execute_process(COMMAND ${SWIG_EXECUTABLE} -c++ -java -package fiftyone.ipintelligence.engine.onpremise.interop.swig -module IpIntelligenceOnPremiseEngineModule -outdir ${SRCOUT}/interop/swig -o ${CMAKE_CURRENT_LIST_DIR}/Java_Ipi_Engine.cpp ${CMAKE_CURRENT_LIST_DIR}/hash_java.i)
    endif()
endif()
message(STATUS "Done SWIG interfaces")

find_package(Java)

# Only the includes are needed, so we set the libraries to none so they
# are ignored by FindJNI.
set(JAVA_AWT_LIBRARY None)
set(JAVA_JVM_LIBRARY None)
find_package(JNI REQUIRED)

add_library(fiftyone-ip-intelligence-java SHARED
    ${CMAKE_CURRENT_LIST_DIR}/Java_Ipi_Engine.cpp)

set_target_properties(fiftyone-ip-intelligence-java
    PROPERTIES
    RUNTIME_OUTPUT_DIRECTORY "${RES}"
    LIBRARY_OUTPUT_DIRECTORY "${RES}"
    RUNTIME_OUTPUT_DIRECTORY_RELEASE "${RES}"
    PREFIX ""
    OUTPUT_NAME "IPIntelligenceOnPremiseEngine-${OS}-${ARCH}"
)
target_link_libraries(fiftyone-ip-intelligence-java fiftyone-ip-intelligence-cxx)
if (NOT MSVC)
    target_compile_options(fiftyone-ip-intelligence-java INTERFACE "-static-libgcc -static-libstdc++")
endif()

if (MSVC)
	target_compile_options(fiftyone-ip-intelligence-java PRIVATE "/D_CRT_SECURE_NO_WARNINGS" "/W4" "/WX")
else()
	target_compile_options(fiftyone-ip-intelligence-java PRIVATE ${COMPILE_OPTION_DEBUG} "-Werror")
endif()

target_include_directories(fiftyone-ip-intelligence-java PUBLIC ${JAVA_INCLUDE_PATH})
if(WIN32)
    target_include_directories(fiftyone-ip-intelligence-java PUBLIC ${JAVA_INCLUDE_PATH}/win32)
endif()
if(APPLE)
    target_include_directories(fiftyone-ip-intelligence-java PUBLIC ${JAVA_INCLUDE_PATH}/darwin)
endif()
if(UNIX AND NOT APPLE)
    target_include_directories(fiftyone-ip-intelligence-java PUBLIC ${JAVA_INCLUDE_PATH}/linux)
endif()
