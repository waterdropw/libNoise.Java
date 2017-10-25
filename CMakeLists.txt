cmake_minimum_required(VERSION 3.0)


set(CMAKE_BUILD_TYPE Debug)

set(CMAKE_INSTALL_PREFIX ${CMAKE_CURRENT_SOURCE_DIR})

option( COMPILE_TUTORIALS
        "If the folder tutorials/ should be compiled" OFF)
option( COMPILE_EXAMPLES
        "If the folder examples/ should be compiled" OFF)

add_subdirectory(libnoise)

if( ${COMPILE_TUTORIALS} )
    add_subdirectory(tutorials)
endif()
if( ${COMPILE_EXAMPLES} )
    add_subdirectory(examples)
endif()

