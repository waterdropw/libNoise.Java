%module NoiseModel


%{
    #include <assert.h>
    #include <math.h>
    #include <stdlib.h>

    #include "noise/latlon.h"
    #include "noise/mathconsts.h"
    #include "noise/module/modulebase.h"

    #include "noise/model/cylinder.h"
    #include "noise/model/line.h"
    #include "noise/model/plane.h"
    #include "noise/model/sphere.h"
%}

%include "noise/model/cylinder.h"
%include "noise/model/line.h"
%include "noise/model/plane.h"
%include "noise/model/sphere.h"