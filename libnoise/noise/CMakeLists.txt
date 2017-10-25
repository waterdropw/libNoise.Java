cmake_minimum_required(VERSION 3.0)

project( noise VERSION 1.0.0 )


aux_source_directory( ${PROJECT_SOURCE_DIR} NOISE_SRC_FILES )
aux_source_directory( ${PROJECT_SOURCE_DIR}/model MODEL_SRC_FILES )
aux_source_directory( ${PROJECT_SOURCE_DIR}/module MODULE_SRC_FILES )


add_library( noise
            ${NOISE_SRC_FILES} 
            ${MODEL_SRC_FILES} 
            ${MODULE_SRC_FILES} )


install(TARGETS 
        noise
        DESTINATION 
        ${CMAKE_INSTALL_PREFIX}/lib)
install(DIRECTORY 
        ${PROJECT_SOURCE_DIR}
        DESTINATION 
        ${CMAKE_INSTALL_PREFIX}/include
        FILES_MATCHING PATTERN "*.h")
install(DIRECTORY 
        ${PROJECT_SOURCE_DIR}/model
        DESTINATION 
        ${CMAKE_INSTALL_PREFIX}/include/noise
        FILES_MATCHING PATTERN "*.h")
install(DIRECTORY 
        ${PROJECT_SOURCE_DIR}/module
        DESTINATION 
        ${CMAKE_INSTALL_PREFIX}/include/noise
        FILES_MATCHING PATTERN "*.h")