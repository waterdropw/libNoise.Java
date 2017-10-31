%module Noise


%include "enums.swg"
%javaconst(1);

%include "typemaps.i"
%apply double &OUTPUT {double& x, double& y, double& z};

%include "noise/basictypes.h"
%include "noise/latlon.h"
%include "noise/noisegen.h"


%{
    #include "noise/basictypes.h"
    #include "noise/latlon.h"
    #include "noise/noisegen.h"
%}