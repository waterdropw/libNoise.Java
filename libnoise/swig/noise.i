%module Noise


%javaconst(1);

%{
    #include <math.h>
    #include "noise/basictypes.h"
    #include "noise/mathconsts.h"
    #include "noise/exception.h"
    #include "noise/interp.h"
    #include "noise/latlon.h"
    #include "noise/misc.h"
    #include "noise/noisegen.h"
    #include "noise/vectortable.h"
%}

%include "noise/basictypes.h"
%include "noise/exception.h"
%include "noise/interp.h"
%include "noise/latlon.h"
%include "noise/mathconsts.h"
%include "noise/misc.h"
%include "noise/noisegen.h"
%include "noise/vectortable.h"
