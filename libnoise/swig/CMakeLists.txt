cmake_minimum_required(VERSION 3.0)

set( GEN_LAN java )
set( EXTRA_LIBS noise )

find_package(SWIG REQUIRED)
include(${SWIG_USE_FILE})

if( ${GEN_LAN} EQUAL python )
    find_package(PythonLibs)
    include_directories(${PYTHON_INCLUDE_PATH})
    set(EXTRA_LIBS ${EXTRA_LIBS} ${PATHON_LIBRARIES})
    set( TARGET_SWIG_OUTDIR ${CMAKE_INSTALL_PREFIX}/src/noise )
    
    message("python_include_path: ${PYTHON_INCLUDE_PATH}")
else()
    message("No language is specified, use default Java")
    set(GEN_LAN java)
    find_package(Java)
    include(UseJava)
    set(EXTRA_LIBS ${EXTRA_LIBS} ${JAVA_LIBRARIES})
    set( TARGET_SWIG_OUTDIR ${CMAKE_INSTALL_PREFIX}/src/net/xbwee/noise )

    find_package(JNI)
    include_directories(${JNI_INCLUDE_DIRS})
    set(EXTRA_LIBS ${EXTRA_LIBS} ${JNI_LIBRARIES})

endif()

set( CMAKE_SWIG_FLAGS "" )

include_directories( ${CMAKE_INSTALL_PREFIX}/include )
link_directories( ${CMAKE_INSTALL_PREFIX}/lib )

# noise base
set_property( SOURCE noise.i PROPERTY CPLUSPLUS ON )
set_property( SOURCE noise.i PROPERTY CMAKE_SWIG_OUTDIR ${TARGET_SWIG_OUTDIR} )
swig_add_library(noise-wrapper 
                TYPE STATIC 
                LANGUAGE ${GEN_LAN} 
                SOURCES noise.i)
swig_link_libraries( noise-wrapper ${EXTRA_LIBS} )

# noise model
set_property( SOURCE model.i PROPERTY CPLUSPLUS ON )
set_property( SOURCE model.i PROPERTY CMAKE_SWIG_OUTDIR ${TARGET_SWIG_OUTDIR}/model )
swig_add_library(model-wrapper 
                TYPE STATIC 
                LANGUAGE ${GEN_LAN} 
                SOURCES model.i)
swig_link_libraries( model-wrapper ${EXTRA_LIBS} )

# noise module
set_property( SOURCE module.i PROPERTY CPLUSPLUS ON )
set_property( SOURCE module.i PROPERTY CMAKE_SWIG_OUTDIR ${TARGET_SWIG_OUTDIR}/module )
swig_add_library(module-wrapper 
                TYPE STATIC 
                LANGUAGE ${GEN_LAN} 
                SOURCES module.i)
swig_link_libraries( module-wrapper ${EXTRA_LIBS} )


install(TARGETS 
        noise-wrapper
        DESTINATION 
        ${CMAKE_INSTALL_PREFIX}/lib)
install(TARGETS 
        model-wrapper
        DESTINATION 
        ${CMAKE_INSTALL_PREFIX}/lib)
install(TARGETS 
        module-wrapper
        DESTINATION 
        ${CMAKE_INSTALL_PREFIX}/lib)

# install(DIRECTORY 
#         ${CMAKE_CURRENT_BINARY_DIR}
#         DESTINATION 
#         ${TARGET_SWIG_OUTPUT_DIR}
#         FILES_MATCHING PATTERN "*.java")

