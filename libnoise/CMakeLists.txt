cmake_minimum_required(VERSION 3.0)

project( libnoise VERSION 0.1.0 )


set(CMAKE_BUILD_TYPE Debug)

# set(CMAKE_INSTALL_PREFIX ${CMAKE_CURRENT_SOURCE_DIR})
set(CMAKE_CXX_FLAGS_DEBUG "$ENV{CXXFLAGS} -O0 -Wall -g -ggdb")
set(CMAKE_CXX_FLAGS_RELEASE "$ENV{CXXFLAGS} -O3 -Wall")
set(CMAKE_C_STANDARD 99)
set(CMAKE_INCLUDE_CURRENT_DIR ON)
set(LIBNOISE_ROOT_DIR ${CMAKE_CURRENT_SOURCE_DIR})

add_subdirectory(noise)
add_subdirectory(noiseutils)
add_subdirectory(swig)

