%module NoiseUtils

%javaconst(1);
%nspace noise;

%{
    #include <stdlib.h>
    #include <string.h>
    #include <string>

    #include "noise/noise.h"
%}

%include "noiseutils/noiseutils.h"