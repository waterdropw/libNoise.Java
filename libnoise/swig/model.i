%module NoiseModel


%include "enums.swg"
%javaconst(1);

%{
    #include "noise/model/cylinder.h"
    #include "noise/model/line.h"
    #include "noise/model/plane.h"
    #include "noise/model/sphere.h"
%}

%include "noise/model/cylinder.h"
%include "noise/model/line.h"
%include "noise/model/plane.h"
%include "noise/model/sphere.h"