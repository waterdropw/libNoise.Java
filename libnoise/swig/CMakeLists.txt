cmake_minimum_required(VERSION 3.0)


set( EXTRA_LIBS noiseutils noise )
set( TARGET_PACKAGE_PATH "net/xbwee/libnoise")

find_package(SWIG REQUIRED)
include(${SWIG_USE_FILE})

find_package(Java)
include(UseJava)


find_package(JNI)
include_directories(${JNI_INCLUDE_DIRS})


set( CMAKE_SWIG_FLAGS "" )
set( SWIG_OUTFILE_DIR ${CMAKE_CURRENT_BINARY_DIR} )
set( TARGET_SWIG_OUTDIR ${TARGET_PACKAGE_PATH} )

include_directories( ${LIBNOISE_ROOT_DIR}/include )
link_directories( ${CMAKE_INSTALL_PREFIX}/lib )

# noise base
set( CMAKE_SWIG_OUTDIR ${TARGET_SWIG_OUTDIR}/noise )
set_property( SOURCE noise.i PROPERTY CPLUSPLUS ON )
set_property( SOURCE noise.i PROPERTY 
                SWIG_FLAGS -package "net.xbwee.libnoise.noise" )
swig_add_library(NoiseBase 
                TYPE SHARED 
                LANGUAGE java 
                SOURCES noise.i)
swig_link_libraries( NoiseBase ${EXTRA_LIBS} )

# noise model
set( CMAKE_SWIG_OUTDIR ${TARGET_SWIG_OUTDIR}/model )
set_property( SOURCE model.i PROPERTY CPLUSPLUS ON )
set_property( SOURCE model.i PROPERTY 
                SWIG_FLAGS -package "net.xbwee.libnoise.model" )
swig_add_library(NoiseModel 
                TYPE SHARED 
                LANGUAGE java 
                SOURCES model.i)
swig_link_libraries( NoiseModel ${EXTRA_LIBS} )

# noise module
set( CMAKE_SWIG_OUTDIR ${TARGET_SWIG_OUTDIR}/module )
set_property( SOURCE module.i PROPERTY CPLUSPLUS ON )
set_property( SOURCE module.i PROPERTY 
                SWIG_FLAGS -package "net.xbwee.libnoise.module" )
swig_add_library(NoiseModule 
                TYPE SHARED 
                LANGUAGE java 
                SOURCES module.i)
swig_link_libraries( NoiseModule ${EXTRA_LIBS} )


# noise utils
set( CMAKE_SWIG_OUTDIR ${TARGET_SWIG_OUTDIR}/utils )
set_property( SOURCE noiseutils.i PROPERTY CPLUSPLUS ON )
set_property( SOURCE noiseutils.i PROPERTY 
                SWIG_FLAGS -package "net.xbwee.libnoise.utils" )
swig_add_library(NoiseUtils 
                TYPE SHARED 
                LANGUAGE java 
                SOURCES noiseutils.i)
swig_link_libraries( NoiseUtils ${EXTRA_LIBS} )



set(CMAKE_JNI_TARGET TRUE)
file(GLOB_RECURSE  LIBNOISE_JAR_SRC_FILES 
        "${CMAKE_CURRENT_BINARY_DIR}/${TARGET_PACKAGE_PATH}/*.java")
file(GLOB LIBNOISE_LIB_FILES 
        "${CMAKE_CURRENT_BINARY_DIR}/*.dll"
        "${CMAKE_CURRENT_BINARY_DIR}/*.dll.a"
        "${CMAKE_CURRENT_BINARY_DIR}/*.so")

# add_jar(${PROJECT_NAME} 
#         ${LIBNOISE_JAR_SRC_FILES}
#         VERSION ${PROJECT_VERSION})

# add_custom_command(TARGET ${PROJECT_NAME} 
#                 POST_BUILD
#                 COMMAND "jar -cvf ${PROJECT_NAME}-sources-${PROJECT_VERSION}.jar ${LIBNOISE_JAR_SRC_FILES}" )

install(TARGETS 
        NoiseBase
        DESTINATION 
        ${CMAKE_INSTALL_PREFIX}/lib)
install(TARGETS 
        NoiseModel
        DESTINATION 
        ${CMAKE_INSTALL_PREFIX}/lib)
install(TARGETS 
        NoiseModule
        DESTINATION 
        ${CMAKE_INSTALL_PREFIX}/lib)
install(TARGETS 
        NoiseUtils
        DESTINATION 
        ${CMAKE_INSTALL_PREFIX}/lib)

# install(DIRECTORY ${CMAKE_CURRENT_BINARY_DIR}/${TARGET_PACKAGE_PATH}/ 
#         DESTINATION ${CMAKE_INSTALL_PREFIX}/src/${TARGET_PACKAGE_PATH})

# install_jar( libnoise ${CMAKE_INSTALL_PREFIX}/lib )
