%module NoiseModel


%import "noise.i"
%import "module.i"

%pragma(java) jniclassimports=%{
import net.xbwee.libnoise.module.Module;
%}
%typemap(javaimports) SWIGTYPE %{
import net.xbwee.libnoise.module.Module;
%}


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