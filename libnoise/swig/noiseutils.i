%module NoiseUtils


%include "enums.swg"
%javaconst(1);

%{
    #include "noise/basictypes.h"
    #include "noiseutils/noiseutils.h"
%}

%include "noiseutils/noiseutils.h"