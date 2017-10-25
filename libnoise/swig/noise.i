%module Noise


%include "enums.swg"
%javaconst(1);

%{
    #include "noise/latlon.h"
    #include "noise/noisegen.h"
%}

%include "noise/latlon.h"
%include "noise/noisegen.h"

