cmake_minimum_required(VERSION 3.0)

set( GEN_LAN java )
set( EXTRA_LIBS noise noiseutils )

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
set( SWIG_OUTFILE_DIR ${CMAKE_CURRENT_BINARY_DIR} )

include_directories( ${LIBNOISE_ROOT_DIR}/include )
# link_directories( ${CMAKE_INSTALL_PREFIX}/lib )

# noise base
set( CMAKE_SWIG_OUTDIR ${TARGET_SWIG_OUTDIR} )
set_property( SOURCE noise.i PROPERTY CPLUSPLUS ON )
set_property( SOURCE noise.i PROPERTY 
                SWIG_FLAGS -package "net.xbwee.noise" )
swig_add_library(NoiseBase 
                TYPE SHARED 
                LANGUAGE ${GEN_LAN} 
                SOURCES noise.i)
swig_link_libraries( NoiseBase ${EXTRA_LIBS} )

# noise model
set( CMAKE_SWIG_OUTDIR ${TARGET_SWIG_OUTDIR}/model )
set_property( SOURCE model.i PROPERTY CPLUSPLUS ON )
set_property( SOURCE model.i PROPERTY 
                SWIG_FLAGS -package "net.xbwee.noise.model" )
swig_add_library(NoiseModel 
                TYPE SHARED 
                LANGUAGE ${GEN_LAN} 
                SOURCES model.i)
swig_link_libraries( NoiseModel ${EXTRA_LIBS} )

# noise module
set( CMAKE_SWIG_OUTDIR ${TARGET_SWIG_OUTDIR}/module )
set_property( SOURCE module.i PROPERTY CPLUSPLUS ON )
set_property( SOURCE module.i PROPERTY 
                SWIG_FLAGS -package "net.xbwee.noise.module" )
swig_add_library(NoiseModule 
                TYPE SHARED 
                LANGUAGE ${GEN_LAN} 
                SOURCES module.i)
swig_link_libraries( NoiseModule ${EXTRA_LIBS} )


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


